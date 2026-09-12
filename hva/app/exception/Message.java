package hva.app.exception;

public interface Message {

  static String problemOpeningFile(Exception cause) {
    return "Problem opening file: " + cause.getMessage();
  }

  static String unknownAnimalKey(String key) {
    return "The animal '" + key + "' does not exist.";
  }

  static String duplicateAnimalKey(String key) {
    return "The animal '" + key + "' already exists.";
  }

  static String unknownSpeciesKey(String key) {
    return "The species '" + key + "' does not exist.";
  }

  static String unknownEmployeeKey(String key) {
    return "The employee '" + key + "' does not exist.";
  }

  static String unknownVeterinarianKey(String key) {
    return "The veterinarian '" + key + "' does not exist.";
  }

  static String duplicateEmployeeKey(String key) {
    return "The employee '" + key + "' already exists.";
  }

  static String unknownHabitatKey(String key) {
    return "The habitat '" + key + "' does not exist.";
  }

  static String duplicateHabitatKey(String key) {
    return "The habitat '" + key + "' already exists.";
  }

  static String unknownTreeKey(String key) {
    return "The tree '" + key + "' does not exist.";
  }

  static String duplicateTreeKey(String key) {
    return "The tree '" + key + "' already exists.";
  }

  static String unknownVaccineKey(String key) {
    return "The vaccine '" + key + "' does not exist.";
  }

  static String duplicateVaccineKey(String key) {
    return "The vaccine '" + key + "' already exists.";
  }

  static String notAuthorized(String vetKey, String speciesKey) {
    return "The veterinarian '" + vetKey + "' cannot administer vaccines to the species '" + speciesKey + "'";
  }

  static String noResponsibility(String employeeKey, String responsibilityKey) {
    return "Responsibility (habitat or species) '" + responsibilityKey +
      "' not assigned to employee '" + employeeKey + "'.";
  }

}
