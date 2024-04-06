import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.lang.Byte;

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
      if (this.hash[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < this.hash.length; i++) {
      str.append(String.format("h", Byte.toUnsignedInt(this.hash[i])));
    }
    return new String(str);
  }

  public boolean equals(Object other) {
    if (other instanceof Hash) {
      return Arrays.equals(((Hash) other).getData(), this.hash);
    }
    return false;
  }

public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(msg.getBytes());
    byte[] hash = md.digest();
    return hash;
    
} // calculateHash(String)


}