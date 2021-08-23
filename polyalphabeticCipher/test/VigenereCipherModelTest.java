import static org.junit.Assert.*;

import org.junit.Test;

public class VigenereCipherModelTest {

  @Test
  public void testKeyExtenderShorterThanMessage() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("abc", "hello"), "ABCAB");
  }

  @Test
  public void testKeyExtenderSameSize() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("abcde", "hello"), "ABCDE");
  }

  @Test
  public void testKeyExtenderShortMSG() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("abcde", "hell"), "ABCD");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("", "hell"), "ABCD");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid2() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("abc", ""), "ABCD");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender(null, "hell"), "ABCD");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull2() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.keyExtender("abc", null), "ABCD");
  }

  @Test
  public void encodeTestBasic() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abc", "hello"), "IGOMQ");
  }

  @Test
  public void encodeTestBasic2() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abc", "hello"), "IGOMQ");
  }

  @Test
  public void encodeTestKeyLonger() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abcde", "abcd"), "BDFH");
  }

  @Test
  public void encodeTestMessageLowerCaseKeyUpper() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("ABCDE", "abcd"), "BDFH");
  }

  @Test
  public void encodeBothUpper() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("ABCDE", "ABCD"), "BDFH");
  }

  @Test
  public void encodeBothLower() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abcde", "abcd"), "BDFH");
  }

  @Test
  public void encodeTestMessageUpperCaseKeyLower() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abcde", "ABCD"), "BDFH");
  }

  @Test(expected = IllegalArgumentException.class)
  public void encodeTestMessageNull() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode("abcde", null), "BDFH");
  }

  @Test(expected = IllegalArgumentException.class)
  public void encodeTestKeyNull() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode(null, "abcd"), "BDFH");
  }

  @Test(expected = IllegalArgumentException.class)
  public void encodeTestMessageAndKeyNull() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.encode(null, null), "BDFH");
  }

  @Test
  public void decode() {
    VigenereCipherModel test = new VigenereCipherModel();
    assertEquals(test.decode("ABCD", "bdfh"), "ABCD");
  }
}