import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A main method for user interaction with the BlockChain.
 * @author Linda Jing
 * @author Amelia Vrieze
 */

public class BlockChainDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("You need to have an initial amount for Block 1");
            return;
          }
        Scanner myScanner= new Scanner(System.in);
        PrintWriter Pen = new PrintWriter(System.out, true);
        BlockChain myBlockChain = new BlockChain(Integer.parseInt(args[0]));
        System.out.println(myBlockChain.toString());
        String input;
        do{
            Pen.println("Command? ");
            input = myScanner.nextLine();
            switch(input){
                case "mine":{
                    try {
                        System.out.println("Amount transferred? ");
                        String transAmountInput = myScanner.nextLine();
                        int transAmountInt = Integer.parseInt(transAmountInput);
                        Block tempBlock = myBlockChain.mine(transAmountInt);
                        System.out.printf("amount = %d, nonce = %d%n", transAmountInt, tempBlock.getNonce());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid amount transferred.");
                    }
                    break;
                }
                case "append":{
                    int transAmountInt;
                    try {
                        System.out.print("Amount transferred? ");
                        String transAmountInput = myScanner.nextLine();
                        transAmountInt = Integer.parseInt(transAmountInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid amount transferred.");
                        continue;
                    }
                    try {
                        System.out.print("Nonce? ");
                        String nonceInput = myScanner.nextLine();
                        Long nonceInt = Long.parseLong(nonceInput);
                        Block tempBlock = new Block(myBlockChain.last.current.getNum() + 1, transAmountInt, myBlockChain.last.current.getHash(), nonceInt);
                        myBlockChain.append(tempBlock);
                        System.out.println("Block successfully appended.");
                    } catch (NumberFormatException e) {
                        System.err.println("Please enter a valid nonce.");
                    } catch (Exception e) {
                        System.err.println("Transaction failed");
                    }  
                    break;                  
                }
                case "remove":{
                    myBlockChain.removeLast();
                    break;
                }
                case "check":{
                    if (myBlockChain.isValidBlockChain()) {
                        Pen.println("Chain is valid!");
                      } else {
                        System.out.println("Chain is invalid.");
                      }
                      break;
                }
                case "report":{
                    myBlockChain.printBalances(Pen);
                    break;
                }
                case "help":{
                    Pen.println("Valid commands:\n" + //
                            "    mine: discovers the nonce for a given transaction\n" + //
                            "    append: appends a new block onto the end of the chain\n" + //
                            "    remove: removes the last block from the end of the chain\n" + //
                            "    check: checks that the block chain is valid\n" + //
                            "    report: reports the balances of Alexis and Blake\n" + //
                            "    help: prints this list of commands\n" + //
                            "    quit: quits the program");
                            continue;
                }case "quit":{
                    Pen.close();
                    myScanner.close();
                    System. exit(0);
                }           
                default: {
                    Pen.println("Invalid command");
                    continue;
                }
            }
            Pen.println(myBlockChain.toString());
        }while(true);
    }   
}