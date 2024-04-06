import java.io.PrintWriter;
import org.w3c.dom.Node;

public class BlockChain {
  Block first;
  Block last;
  int size;
  double alexis;
  double blake = 0;

  BlockChain(int initial) {
    Block(1, initial, null);
    this.alexis = initial;
    size++;
  }

  Block mine(int amount)

  int getSize() {
    return this.size;
  }

  void append(Block blk)

  boolean removeLast()

  Hash getHash()

  boolean isValidBlockChain()

  void printBalances(PrintWriter pen) {
    pen.println("Alexis: " + alexis + ", Blake: " + blake);
  }

  String toString()


  public static class Node {


  }
}
