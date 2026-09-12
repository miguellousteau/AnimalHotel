package hva.app.main;

public interface Prompt {

  static String openFile() {
    return "File to open: ";
  }

  static String saveAs() {
    return "Save file as: ";
  }

  static String newSaveAs() {
    return "Unnamed file. " + saveAs();
  }

  static String saveBeforeExit() {
    return "Save before closing? ";
  }

}