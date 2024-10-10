package telran.view;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	void writeLine(Object obj);

	 <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper);
	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	Integer readInt(String prompt, String errorPrompt) ;

	 Long readLong(String prompt, String errorPrompt) ;

	Double readDouble(String prompt, String errorPrompt);
	Double readNumberRange(String prompt, String errorPrompt, double min, double max) ;
	String readStringPredicate(String prompt, String errorPrompt,
			Predicate<String> predicate) ;
	String readStringOptions(String prompt, String errorPrompt,
			HashSet<String> options);
	LocalDate readIsoDate(String prompt, String errorPrompt);
	LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from,
			LocalDate to) ;
	

}