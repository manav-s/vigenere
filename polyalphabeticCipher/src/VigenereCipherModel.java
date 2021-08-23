/**
 * Represents the model of a Poly-alphabetic cipher.
 */
public class VigenereCipherModel implements CipherModelInterface {

  /**
   * Creates a String that will be repeated over the course of the String message.
   *
   * @param key
   * @param message
   * @return
   */
  public String keyExtender(String key, String message) {
    if (key == null || message == null || key.length() == 0 || message.length() == 0) {
      throw new IllegalArgumentException("Key or message is invalid.");
    }
    String result = "";

    String keyUpper = key.toUpperCase();

    for (int i = 0; i < message.length(); i++) {
      int index = i % key.length();
      result += keyUpper.charAt(index);
    }
    return result;
  }

  @Override
  public String encode(String key, String message) throws IllegalArgumentException {

    if (key == null || message == null || key.length() == 0 || message.length() == 0) {
      throw new IllegalArgumentException("Key or message is invalid.");
    }

    String keyUpper = keyExtender(key, message);
    String messageUpper = message.toUpperCase();

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      // int key char
      char keyLetter = keyUpper.charAt(i);
      int asciiValueOfKeyChar = (int) keyLetter;
      int keyPosition = asciiValueOfKeyChar - 64;

      // int message char

      char messageLetter = messageUpper.charAt(i);
      int asciiValueOfMessageChar = (int) messageLetter;
      int messagePosition = asciiValueOfMessageChar - 64;

      // int message + key char
      int newCharacter = (keyPosition + messagePosition) % 26;
      char letter = (char) (newCharacter + 64);

      // add onto string
      result.append(letter);
    }
    return result.toString();
  }

  @Override
  public String decode(String key, String alteredMessage) {

    if (key == null || alteredMessage == null || key.length() == 0
        || alteredMessage.length() == 0) {
      throw new IllegalArgumentException("Key or message is invalid.");
    }

    String keyUpper = keyExtender(key, alteredMessage);
    String messageUpper = alteredMessage.toUpperCase();

    String result = "";

    for (int i = 0; i < messageUpper.length(); i++) {
      int alteredMessageIndex = (messageUpper.charAt(i) - keyUpper.charAt(i) + 25) % 26;
      alteredMessageIndex += 'A';
      result += (char) (alteredMessageIndex);
    }
    return result;
  }
}
