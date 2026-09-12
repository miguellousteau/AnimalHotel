package pt.tecnico.uilib.forms;

/**
 * Interaction messages.
 */
interface Message {

  /**
   * @param form
   * @param key
   * @return message text.
   */
  static String keyAlreadyExists(String form, String key) {
    return String.format("The field '%s' is duplicated in the form '%s'.", key, form);
  }

  /**
   * @param form
   * @return message text.
   */
  static String formNotFilled(String form) {
    return String.format("The form '%s' is not filled. Invoke the parse() method first.", form);
  }

  /**
   * @param form
   * @param key
   * @return message text.
   */
  static String keyNotFound(String form, String key) {
    return String.format("The field '%s' does not exist in the form '%s'.", key, form);
  }

  /**
   * @param form
   * @param key
   * @param actualType
   * @param requestedType
   * @return message text.
   */
  static String typeMismatch(String form, String key, String actualType, String requestedType) {
    return String.format("The field '%s' of the form '%s' has type '%s' instead of '%s'.", key, form, actualType, requestedType);
  }

}