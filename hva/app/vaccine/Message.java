package hva.app.vaccine;

public interface Message {

  static String wrongVaccine(String vaccineKey, String animalKey) {
    return "The vaccine '" + vaccineKey + "' is not appropriate for the animal '" + animalKey + "'.";
  }

}