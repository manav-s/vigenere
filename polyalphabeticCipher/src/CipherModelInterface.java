/**
 * The model of any String encryption method that uses another String to disguise data.
 */
public interface CipherModelInterface {

  /**
   * Encodes the given message with a provided String key. For maximum security, please provide a
   * key with the same length as the message.
   *
   * @param key the String to encode the message
   * @throws IllegalArgumentException if the message or key is invalid
   */
  String encode(String key, String message);

  /**
   * Decodes the given message with a provided String key.
   *
   * @param key the String to decode the message
   * @throws IllegalArgumentException if the message or key is invalid
   */
  String decode(String key, String message);
}
