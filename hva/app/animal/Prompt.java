package hva.app.animal;

public interface Prompt {

  static String animalKey() {

    return "Animal unique identifier: ";

  }

  static String animalName() {

    return "Animal name: ";

  }

  static String speciesKey() {

    return "Species unique identifier: ";

  }

  static String speciesKeys() {

    return "List of species identifiers: ";

  }

  static String speciesName() {

    return "Species name: ";

  }

}