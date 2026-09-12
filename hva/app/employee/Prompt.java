package hva.app.employee;

public interface Prompt {

  static String employeeKey() {

    return "Employee unique identifier: ";

  }

  static String employeeName() {

    return "Employee name: ";

  }

  static String employeeType() {

    return "Employee type? (VET or TRT) ";

  }

  static String responsibilityKey() {

    return "Responsibility unique identifier: ";

  }

}
