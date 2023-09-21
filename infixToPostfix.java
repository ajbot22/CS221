//Done in collaboration between Aj Botticelli & Zach Brown
import java.util.*;
import javax.swing.JOptionPane;

public class infixToPostfix {

    public static StringBuilder highestPrec(Stack operatorStack, HashMap<Character, Integer> precedenceHash, char value, StringBuilder postfixString){
        /*
         * Method should take the operator stack, the precedense hashmap, the value being checked, and the postfix string
         * It will comb through the operator stack, popping and appending anything of higher or equal precedence until the end or a open parenthesis is hit
         * Then it will return the postfix string and back in the main method the value will be added to the stack
         */
        for(int i = 0; i < operatorStack.sp && operatorStack.top() != '('; i++){
            if(precedenceHash.containsKey(value) && precedenceHash.get(value)<=operatorStack.top()){
                postfixString.append(operatorStack.top());
                postfixString.append(' ');
                operatorStack.pop();
                i--;
            }
        }
        return postfixString;
    }
    public static void main(String[] args){
        Stack operatorStack = new Stack();
        Stack valueStack = new Stack();
        int temp1;
        int temp2;
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
        //Test Case 1: (44-23)*2
        //Test Case 2: 55+3
        //Test Case 3: 4+(5*(66-2/2))
        //Input: 1 String, an expression of numbers and operators        
        //Process to Print: Read in thie input and go character by character
        String infix = JOptionPane.showInputDialog("Enter Infix");
        for(int i = 0; i < infix.length(); i++){
            char item = infix.charAt(i);
            //System.out.println(i);
            //System.out.println("charat: "+item);
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
            }
            //If the item is not an integer or space, check if it is an operator (in hashmap) and if so, either stack it or place the operator
            else{
                for(int j = 0; j < keyArray.length; j++){
                    if(item == (char)keyArray[j]){
                        //Check for each operator
                        if(item == '('){
                            operatorStack.push(item);
                        }
                        else if(item == ')'){//If you are reaching a closed parenthesis, then all the operators before it in the stack must have been popped already so the top of the stack should be the open parenthesis
                            while(operatorStack.top() != '('){
                                postfixString = highestPrec(operatorStack, precedenceHash, operatorStack.top(), postfixString);
                            }
                            operatorStack.pop();// will delete the open parenthesis
                        }
                        else{
                            postfixString = highestPrec(operatorStack, precedenceHash, item, postfixString);
                            operatorStack.push(item);
                        }
                        break;
                    }
                }
            }
        }
        while(!operatorStack.empty()){
            postfixString.append(operatorStack.top());
            postfixString.append(' ');
            operatorStack.pop();
        }
        System.out.println(infix);
        System.out.println(postfixString);
        //Process to Evaluate: Go character by character through the postfix string
        /* 
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
    */
    }
}