package telran.view;

import java.time.LocalDate;
import java.util.HashSet;

record Employee(long id, String name, String department, int salary, LocalDate birthDate) {
}

public class Main {
    static InputOutput io = new StandardInputOutput();
    /*********************** */
    // For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = { "QA", "Audit", "Development", "Management" };
    // name should be at least 3 English letters; first - capital, others - lower
    // case
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    /*********************************** */
    public static void main(String[] args) {
        readEmployeeBySeparateFields();
        readEmployeeAsObject();
    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject("Enter employee data in the format:" +
                " <id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));

                });
        io.writeLine("You are entered the following Employee data");
        io.writeLine(empl);
    }

    static void readEmployeeBySeparateFields() {
        long id = io.readLong("Enter employee ID (from " + MIN_ID + " to " + MAX_ID + "):", "Invalid ID");

        String name = io.readStringPredicate("Enter employee name (at least 3 letters, first capital):", 
                "Invalid name format", str -> str.matches("[A-Z][a-z]{2,}"));

        String department = io.readStringOptions("Enter employee department (one of: " + String.join(", ", DEPARTMENTS) + "):", 
                "Invalid department", new HashSet<>(java.util.Arrays.asList(DEPARTMENTS)));

        int salary = io.readNumberRange("Enter employee salary (from " + MIN_SALARY + " to " + MAX_SALARY + "):", 
                "Invalid salary", MIN_SALARY, MAX_SALARY).intValue();
    
        LocalDate birthDate = io.readIsoDateRange("Enter employee birth date (yyyy-MM-dd):", 
                "Invalid date format", LocalDate.now().minusYears(MAX_AGE), LocalDate.now().minusYears(MIN_AGE));
    
        Employee empl = new Employee(id, name, department, salary, birthDate);
    
        io.writeLine("You have entered the following Employee data:");
        io.writeLine(empl);
    }
}