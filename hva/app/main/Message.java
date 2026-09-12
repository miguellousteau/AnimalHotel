package hva.app.main;

public interface Message {

  static String fileNotFound() {
    return "The file does not exist.";
  }

  static String fileNotFound(String filename) {
    return "The file '" + filename + "' does not exist.";
  }

}