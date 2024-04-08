import java.io.PrintWriter;
import org.w3c.dom.Node;

public class BlockChain {
  Block first;
  Block last;
  int size;
  double alexis;
  double blake = 0;

  BlockChain(int initial) {
    new Block(1, initial, null);
    this.alexis = initial;
    size++;
  }

  Block mine(int amount) {
    if (amount > 0) {
      this.alexis += amount;
      this.blake -= amount;
    } else {
      this.alexis -= amount;
      this.blake += amount;
    }
    Block block = new Block(this.size++, amount, this.last.getHash());
    return block;

  }

  int getSize() {
    return this.size;
  }

  void append(Block blk) throws IllegalArgumentException {
    this.size++;
    //link last to this one
    //check validity
    this.last = blk;
    //STUB
  }

  boolean removeLast() {
    if (this.size == 0) {
      return false;
    }
    //get previous somehow
    //set previous to last
    this.size--;
    return true;
    //STUB
  }

  Hash getHash() {
    return this.last.getHash();
  }

  boolean isValidBlockChain() {
    //if every block is valid
    //and the amounts are valid
    //return true
    //else
    //maybe remove the last one
    return false;
    //STUB
  }

  void printBalances(PrintWriter pen) {
    pen.println("Alexis: " + alexis + ", Blake: " + blake);
  }

  public String toString() {
    //for all the blocks
    //block toString()
    //appended together
    return ""; //STUB

  }


  public static class Node {
    



  }
}
