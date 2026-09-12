package hva.app.vaccine;

public interface Prompt {

  static String vaccineKey() {
    return "Vaccine identifier: ";
  }

  static String vaccineName() {
    return "Vaccine name: ";
  }

  static String veterinarianKey() {
    return "Identifier of the veterinarian administering the vaccine: ";
  }

  static String listOfSpeciesKeys() {
    return "Identifiers of the species that can receive the vaccine: ";
  }

}