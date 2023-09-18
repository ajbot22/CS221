//Done in collaboration between Aj Botticelli & Zach Brown
import java.util.*;
import javax.swing.JOptionPane;

public class infixToPostfix {

    public static boolean highestPrec(String infix, int currentIndex, HashMap<Character, Integer> precedenceHash, char value){
        /*
         * Purpose: Check if the given character value is the highest precedence either until the end of line or next close 
           parenthesis with precedence determined by a hashmap of key-value pairs of characters and their precedence weight
         * Variables:
         * infix- A string expression for the infix value
         * currentIndex- The current index to start reading the infix expression from (not including that value)
         * precedenceHash- A hashmap which defines the weighted precedenses of operators contained within it
         * value- the character value for which precedence comparison is weighted against
         * Returns: the function should return true of the value is of equal or higher precedence to every operator after 
           the defined index in the infix expression up to either the end of line or first close parenthesis
         */
        while(infix.charAt(currentIndex)!=')' || currentIndex==infix.length()){
            currentIndex++;
            if(precedenceHash.get(value)>=precedenceHash.get(infix.charAt(currentIndex)))
                continue;
            else return false;
        }
        return true;
    }
    public static void main(String[] args){
        Stack operatorStack = new Stack();
        Stack valueStack = new Stack();
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
        int temp1;
        int temp2;
        boolean operatorToPlace = false;

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
                //If there is an operator that should be placed before adding new numbers, check for that now
                if(operatorToPlace){
                    do{
                        //pop top of stack out and place it into the postfix string 
                        postfixString.append(operatorStack.pop());
                        postfixString.append(' ');
                    }while(operatorStack.top() != '(' && highestPrec(infix,i,precedenceHash, operatorStack.top())); //check if condition is true for the next top of head aslong as that is not a parenthesis
                    operatorToPlace = false;
                }
            }
            //If the item is not an integer or space, check if it is an operator (in hashmap) and if so, either stack it or place the operator
            else{
                for(int j = 0; j < keyArray.length; j++){
                    if(item == (char)keyArray[j]){
                        //Check for each operator
                        //Is it an open parenthesis
                            //If so, use true for the boolean flag for the method
                        //Is it a close parenthesis
                            //If so, decrement the open counter by 1
                        //Is the operator equal or higher or equal precedence to all remaining operators in the text or there are no more operators in the text
                            //if inside a parenthesis, you check for all remaining operators before the next close parenthesis
                        //If the method returns true, do not stack the operator but instead place the next number and then it in the text, elsewise stack it
                            //Also repeat this for each next item at the top of the stack until it is either a parenthesis or no longer the highest precedence in its context
                        if(item == '('){
                            operatorToPlace = false;
                            operatorStack.push(item);
                        }
                        else if(item == ')'){//If you are reaching a closed parenthesis, then all the operators before it in the stack must have been popped already so the top of the stack should be the open parenthesis
                            operatorStack.pop();
                        } 
                        else if(highestPrec(infix,i,precedenceHash, item)){
                            operatorToPlace = true;
                            operatorStack.push(item);
                        }
                        else{
                            operatorStack.push(item);
                        }
                        break;
                    }
                }
            }
        }
        //Process to Evaluate: Go character by character through the postfix string
        for(int l=0;l<postfixString.length();l++){
        char value = infix.charAt(l);
            //Push operands into the stack
            if(Character.isDigit(value))
                valueStack.push(value);
            else{
                //Execute operators on the top two items of the stack in the order 2nd item opr 1st item
                //Top to check item, store it in a variable, pop. Do this twice then push the result
                temp1 = valueStack.pop();
                temp2 = valueStack.pop();
                switch(value){
                    case'*':valueStack.push((char)(temp2*temp1));
                            break;
                    case'/':valueStack.push((char)(temp2/temp1));
                            break;
                    case'%':valueStack.push((char)(temp2%temp1));
                            break;
                    case'+':valueStack.push((char)(temp2+temp1));
                            break;
                    case'-':valueStack.push((char)(temp2-temp1));
                            break;
                }
            }
            if(l==postfixString.length())
                while(!valueStack.empty())
                    System.out.println(valueStack.pop());
        }

    }
}

                //Check for 3 things on putting a number in the string
                    //Is there an operator in the stack that can be executed (not a parenthesis)
                    //Is the top operator in the stack of equal or higher precedence to all remaining operators in the text or there are no more operators in the text
                    //Is there a number behind it to operate on
                //If there is an open parenthesis present in the stack, treat these rules all from the bounds of those parenthesis
                    //Number pushed since placing open, operator in the stack besides the open, top operator in the stack greater precedence than all before not including the close
                //If these are all true, pop the operator out of the stack and put it on the line, elsewise continue reading
              
