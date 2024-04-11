import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
public class Block {
  int num;
  int amount;
  Hash prevHash = null;
  Hash hash;
  long nonce;


  Block(int num, int amount, Hash prevHash) {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    try {
    this.nonce = mine();
    this.hash = computeHash(this.num, this.amount, this.prevHash, this.amount);
    } catch (Exception e) {}
  }

  Block(int num, int amount, Hash prevHash, long nonce){
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    try {
    this.hash = computeHash(this.num, this.amount, this.prevHash, this.nonce);
    } catch (Exception e) {}
  }

  long mine() throws Exception {
    Random rand = new Random();
    Hash hash;
    long nonce;
    do {
      nonce = rand.nextLong();
      hash = computeHash(num, amount, prevHash, nonce);
    } while (!hash.isValid());
    return nonce;
  }

  Hash computeHash(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    byte[] intbytes = ByteBuffer.allocate(Integer.BYTES).putInt(num).array();
    md.update(intbytes);
    intbytes = ByteBuffer.allocate(Integer.BYTES).putInt(amount).array();
    md.update(intbytes);
    if (prevHash != null) {
      md.update(prevHash.getData());
    }
    byte[] longbytes = ByteBuffer.allocate(Long.BYTES).putLong(nonce).array();
    md.update(longbytes);
    Hash hash = new Hash(md.digest());
    return hash;

  }

  int getNum() {
    return this.num;
  }

  int getAmount() {
    return this.amount;
  }

  long getNonce() {
    return this.nonce;
  }

  Hash getPrevHash() {
    return this.prevHash;
  }

  Hash getHash() {
    return this.hash;
  }

  public String toString() {
    return ("Block: " + this.num + "(Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")");
  }
}