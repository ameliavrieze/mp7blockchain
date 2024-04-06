import java.security.MessageDigest;
import java.util.Random;
public class Block {
  int num;
  int amount;
  Hash prevHash = null;
  Hash hash;
  int nonce;


  Block(int num, int amount, Hash prevHash) {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    try {
    this.nonce = mine();
    } catch (Exception e) {}
  }

  Block(int num, int amount, Hash prevHash, long nonce){}

  long mine() throws Exception {long count = 0;
    Random rand = new Random();
    Hash hash;
    long nonce;
    do {
      nonce = rand.nextLong();
      hash = computeHash(num, amount, prevHash, nonce);
    } while (! hash.isValid());
    return nonce;
  }

  Hash computeHash(int num, int amount, Hash prevHash, long nonce) {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(msg.getBytes());
    byte[] hash = md.digest();
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

  String toString() {
    return ("Block: " + this.num + "(Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")");
  }
}