//Done in collaboration between Aj Botticelli & Zach Brown
import java.util.*;
import javax.swing.JOptionPane;

public class infixToPostfix {
    public static void main(String[] args){
        Stack operatorStack = new Stack();
        HashMap<Character, Integer> precedenceHash = new HashMap<Character, Integer>(){{
            put('(',5);
            put(')',5);
            put('*',3);
            put('/',3);
            put('%',3);
            put('+',1);
            put('-',1);
        }};
        StringBuilder postfixString = new StringBuilder();
        int finalValue;
        
        //Input: 1 String, an expression of numbers and operators        
        //Process to Print: Read in thie input and go character by character
        String infix = JOptionPane.showInputDialog("Enter Infix");
        for(int i = 0; i < infix.length(); i++){
            //If the item is not an integer or space, put it in the operator stack
            char item = infix.charAt(i);
            Object[] keyArray = precedenceHash.keySet().toArray();
            for(int j = 0; j < keyArray.length; j++){
                if(item == (char)keyArray[j]){
                    operatorStack.push(item);
                    break;
                }
            }
            //If the item is an operand, put it onto the string and put a space
                //if the following item is also an operand, concatonate it to the prior until the next item is not
                //Make a hashmap(basically a dict) which has name of operator and its precedence weight as values 
                //Check for 3 things on putting a number in the string
                    //Is there a number behind it
                    //Is there an operator in the stack
                    //If there is, is the next operator lower or equal precedence then the one in the stack
                        //If not, push the higher precedence operator to the stack
                    //If these are all true, pop the operator out of the stack and put it on the line
        }
        //Process to Evaluate: Go character by character through the postfix string
            //Push operands into the stack
            //Execute operators on the top two items of the stack in the order 2nd item opr 1st item
            //Top to check item, store it in a variable, pop. Do this twice then push the result
    }
}
