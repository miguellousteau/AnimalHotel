package pt.tecnico.uilib.text;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interaction messages.
 */
interface Message {

  /**
   * Message for presenting errors in commands.
   * 
   * @param error error message.
   * @return message text.
   */
  static String invalidOperation(String error) {
    return "Invalid operation: " + error;
  }

  /**
   * Message for communicating an invalid option.
   * 
   * @return message text.
   */
  static String invalidOption() {
    return "Invalid option!";
  }

  /**
   * Message for presenting EOF errors in commands.
   * 
   * @param e an <code>EOFException</code>.
   * @return message text.
   */
  static String errorEOF(EOFException e) {
    return "End of input data (EOF): " + e;
  }

  /**
   * Message for presenting IO errors in commands.
   * 
   * @param ioe an <code>IOException</code>.
   * @return message text.
   */
  static String errorIO(IOException ioe) {
    return "I/O problem: " + ioe;
  }

  /**
   * Message for presenting number reading errors in commands.
   * 
   * @param e a <code>NumberFormatException</code>.
   * @return message text.
   */
  static String errorInvalidNumber(NumberFormatException e) {
    return "Invalid number!";
  }

  /**
   * Message for presenting EOF errors in commands.
   * 
   * @param e a <code>RuntimeEOFException</code>.
   * @return message text.
   */
  static String errorREOF(RuntimeEOFException e) {
    return "End of input data (R-EOF): " + e;
  }

  /**
   * Error message for file-not-found errors (input).
   * 
   * @param e an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static String inputError(FileNotFoundException e) {
    return "Error setting input: " + e;
  }

  /**
   * Error message for IO errors (closing input).
   * 
   * @param e an IO exception.
   * @return error message.
   */
  static String errorClosingInput(IOException e) {
    return "Error closing input: " + e;
  }

  /**
   * Error message for EOF errors.
   * 
   * @return error message.
   */
  static String endOfInput() {
    return "End of input stream";
  }

  /**
   * Error message for file-not-found errors (output).
   * 
   * @param e an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static String outputError(FileNotFoundException e) {
    return "Error setting output: " + e;
  }

  /**
   * Error message for file-not-found errors (log).
   * 
   * @param e an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static String logError(FileNotFoundException e) {
    return "Error specifying log file: " + e;
  }

}