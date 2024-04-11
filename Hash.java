import java.util.Arrays;
import java.lang.Byte;

/**
 * A wrapper class for a cryptographic hashcode.
 * @author Amelia Vrieze
 * @author Linda Jing
 */

public class Hash {
  byte[] hash;

  Hash(byte[] data) {
    this.hash = data;
  }

  byte[] getData() {
    return this.hash;
  }

  boolean isValid() {
    for (int i = 0; i < 3; i++) {
      if (Byte.toUnsignedInt(this.hash[i]) != 0) {
        return false;
      }
    }
    return true;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < this.hash.length; i++) {
      str.append(String.format("%h", Byte.toUnsignedInt(this.hash[i])));
    }
    return new String(str);
  }

  public boolean equals(Object other) {
    if (other instanceof Hash) {
      return Arrays.equals(((Hash) other).getData(), this.hash);
    }
    return false;
  }


}