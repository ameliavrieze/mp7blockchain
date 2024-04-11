import java.io.PrintWriter;

public class BlockChain {
  Node first;
  Node last;
  int size;
  int alexis;
  int blake = 0;

  BlockChain(int initial) {
    this.first = new Node(new Block(1, initial, null));
    System.out.println("check Block Chain created");
    this.last = this.first;
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
    Block block = new Block(++this.size, Math.abs(amount), this.last.current.getHash());
    return block;

  }

  int getSize() {
    return this.size;
  }

  void append(Block blk) throws IllegalArgumentException {
    this.size++;
    Node next = new Node(blk);
    this.last.setNext(next);
    this.last = next;
    if (!isValidBlockChain()) {
      removeLast();
      throw new IllegalArgumentException();
    }

  }

  boolean removeLast() {
    if (this.size < 2) {
      return false;
    }
    Node temp = this.first;
    for (int i = 1; i < this.size - 1; i++) {
      temp = temp.next;
    }
    this.last = temp;
    temp.setNext(null);
    this.size--;
    return true;
  }

  Hash getHash() {
    return this.last.current.getHash();
  }

  boolean isValidBlockChain() {
    Node temp = this.first;
    while (temp.next != null) {
      try {
        Block current = temp.current;
        if (current.computeHash(current.num, current.amount, current.prevHash, current.nonce) 
            != temp.next.current.prevHash) {
              return false;
        }
      } catch (Exception e) {
        return false;
      }
      temp = temp.next;
    }
    return true;
  }

  void printBalances(PrintWriter pen) {
    pen.println("Alexis: " + alexis + ", Blake: " + blake);
  }

  public String toString() {
    Node temp = this.first;
    String str = "";
    while (temp != null) {
      str += temp.current.toString() + "\n";
      temp = temp.next;
    }
    return str;
  }


  public class Node {
    Block current;
    Node next;
    
    public Node(Block current) {
      this.current = current;
      this.next = null;
    }

    void setNext(Node next){
      this.next = next;
    }


  }
}
