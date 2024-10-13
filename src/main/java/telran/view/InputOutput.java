package telran.view;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj){
		writeString(obj.toString() + "\n");
	}

	 default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper){
		boolean running = false;
		T res = null;
		do {
			running = false;
			try {
				String strRes = readString(prompt);
				res = mapper.apply(strRes);
			} catch (Exception e) {
				writeLine(errorPrompt + ": " + e.getMessage());
				running = true;
			}

		}while(running);
		return res;
	}

	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, str -> {
			int res = Integer.parseInt(str);
			return res;
		});
	}

	default Long readLong(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, str -> {
			long res = Long.parseLong(str);
			return res;
		});
	}

	default Double readDouble(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, str -> {
			double res = Double.parseDouble(str);
			return res;
		});
	}

	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
		return readObject(prompt, errorPrompt, str -> {
			double res = Double.parseDouble(str);
			if (res < min || res > max) {
				throw new IllegalArgumentException("Value must be between " + min + " and " + max);
			}
			return res;
		});
	}

	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, str -> {
			if (!predicate.test(str)) {
				throw new IllegalArgumentException("Input doesn't match the required predicate");
			}
			return str;
		});
	}

	default String readStringOptions(String prompt, String errorPrompt, HashSet<String> options) {
		return readObject(prompt, errorPrompt, str -> {
			if (!options.contains(str)) {
				throw new IllegalArgumentException("Input is not one of the valid options: " + options);
			}
			return str;
		});
	}

	default LocalDate readIsoDate(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, str -> {
			return LocalDate.parse(str);
		});
	}

	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from, LocalDate to) {
		return readObject(prompt, errorPrompt, str -> {
			LocalDate date = LocalDate.parse(str);
			if (date.isBefore(from) || date.isAfter(to)) {
				throw new IllegalArgumentException("Date must be between " + from + " and " + to);
			}
			return date;
		});
	}
}