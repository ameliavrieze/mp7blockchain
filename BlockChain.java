import java.io.PrintWriter;

/**
 * A BlockChain structure with utilities for appending, traversing, and validating BlockChain transactions.
 * @author Amelia Vrieze
 * @author Linda Jing
 */

public class BlockChain {
  Node first;
  Node last;
  int size;
  int alexis;
  int blake = 0;

  BlockChain(int initial) {
    this.first = new Node(new Block(1, initial, null));
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
    Block block = new Block(++this.size, amount, this.last.current.getHash());
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
      System.err.println("Not valid block chain.");
      throw new IllegalArgumentException();
    }
  }

  boolean removeLast() {
    if (this.size < 2) {
      return false;
    }
    Node temp = this.first;
    while(temp.next.next != null) {
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
/* 
  boolean isValidBlockChain() {
    Node temp = this.first;
    while (temp.next!= null) {
      Block block = temp.current;
      int totalAmount=0;
      try {
        if (!block.getHash().equals(temp.next.current.prevHash)) {
              return false;
        }
      } catch (Exception e) {
        System.err.println("Exception in isValidBlockChain");
        return false;
      }
      temp = temp.next;
      totalAmount=totalAmount+block.getAmount();
      System.out.println("amount:"+block.getAmount());
      System.out.println("total amount:"+totalAmount);
      if (totalAmount <0) {
        return false;
      } // if
    }
    return true;
  }
  */
boolean isValidBlockChain() {
    Node temp = this.first;
    int totalAmount = 0;  
    if (temp == null) {
        System.err.println("Blockchain is empty.");
        return false;
    }
    Hash lastHash = null;  
    while (temp != null) {  
        Block block = temp.current;
        //System.out.println("amount:" + block.getAmount());
        totalAmount += block.getAmount();
        //System.out.println("total amount:" + totalAmount);
        if (lastHash != null && !block.prevHash.equals(lastHash)) {
            System.err.println("Hash chain is broken at block with amount " + block.getAmount());
            return false;
        }
        lastHash = block.getHash();
        temp = temp.next;
    }
    if (totalAmount < 0) {
        System.err.println("Invalid blockchain: total amount is negative.");
        return false;
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
