import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;



/**
 * An individual node in a BlockChain, that stores certain cryptographic data.
 * @author Amelia Vrieze
 * @author Linda Jing
 */
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
    } catch (Exception e) {
      System.err.println("Block initialize failed.");
    }
  }

  Block(int num, int amount, Hash prevHash, long nonce){
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    try {
    this.hash = computeHash(this.num, this.amount, this.prevHash, this.nonce);
    } catch (Exception e) {
      System.err.println("Block initialize failed.");
    }
  }

  long mine() throws Exception {
    Random rand = new Random();
    Hash newhash;
    long testNonce = 0;
    do {
      testNonce = rand.nextLong();
      newhash = computeHash(this.num, this.amount, this.prevHash, testNonce);
    } while (!newhash.isValid());
    return testNonce;
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
    Hash newhash = new Hash(md.digest());
    return newhash;

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