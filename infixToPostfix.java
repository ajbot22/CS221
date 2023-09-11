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
        Boolean pushedNum = false;

        //Input: 1 String, an expression of numbers and operators        
        //Process to Print: Read in thie input and go character by character
        String infix = JOptionPane.showInputDialog("Enter Infix");
        for(int i = 0; i < infix.length(); i++){
            char item = infix.charAt(i);
            Object[] keyArray = precedenceHash.keySet().toArray();
            //If the item is an operand, put it onto the string and put a space
            if(Character.isDigit(item)){
                postfixString.append(item);
                //if the following item is also an operand, concatonate it to the prior until the next item is not
                while(++i < infix.length() && Character.isDigit(infix.charAt(i))){
                    postfixString.append(item);
                }
                //i will be any char but a number, dont want to skip checking it to be an operator
                i--;
                postfixString.append(' ');
                //Check for 3 things on putting a number in the string
                    //Is there a number behind it
                    //Is there an operator in the stack
                    //If there is, is the next operator lower or equal precedence then the one in the stack
                        //If not, push the higher precedence operator to the stack
                    //If these are all true, pop the operator out of the stack and put it on the line
                
                //Set this flag to be true after all checks for the pushed number have been done
                //The flag exists to check if a number was just pushed when pushing a new number
                pushedNum = true;
            }
            //If the item is not an integer or space, put it in the operator stack
            else{
                for(int j = 0; j < keyArray.length; j++){
                    if(item == (char)keyArray[j]){
                        operatorStack.push(item);
                        pushedNum = false;
                        break;
                    }
                }
            }
        }
        //Process to Evaluate: Go character by character through the postfix string
            //Push operands into the stack
            //Execute operators on the top two items of the stack in the order 2nd item opr 1st item
            //Top to check item, store it in a variable, pop. Do this twice then push the result
    }
}
