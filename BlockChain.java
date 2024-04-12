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
    Block block = new Block(++this.size, amount, this.last.current.getHash());
    return block;
  }

  int getSize() {
    return this.size;
  }

  void append(Block blk) throws IllegalArgumentException {
    this.size++;
    this.alexis += blk.getAmount();
    this.blake -= blk.getAmount();
    Node next = new Node(blk);
    this.last.setNext(next);
    this.last = next;
    if (!isValidBlockChain() || !blk.getHash().isValid()) {
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
    this.alexis -= this.last.next.current.getAmount();
    this.blake += this.last.next.current.getAmount();
    temp.setNext(null);
    this.size--;
    return true;
  }

  Hash getHash() {
    return this.last.current.getHash();
  }

boolean isValidBlockChain() {
    Node temp = this.first; 
    if (temp == null) {
        System.err.println("Blockchain is empty.");
        return false;
    }
    Hash lastHash = null;  
    while (temp != null) {  
        Block block = temp.current;
        if (lastHash != null && !block.prevHash.equals(lastHash)) {
            System.err.println("Hash chain is broken at block with amount " + block.getAmount());
            return false;
        }
        lastHash = block.getHash();
        temp = temp.next;
    }
    if (this.alexis < 0 || this.blake < 0) {
        System.err.println("Invalid blockchain: negative account balance.");
        return false;
    }
    return true;
}

  void printBalances(PrintWriter pen) {
    pen.println("Alexis: " + this.alexis + ", Blake: " + this.blake);
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
