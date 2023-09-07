import java.util.HashMap;

public class infixToPostfix {
    public static void main(String[] args){
        Stack operatorStack = new Stack();
        HashMap<String, Integer> precedenceHash = new HashMap<String, Integer>();
        StringBuilder postfixString = new StringBuilder();
        int finalValue;
        //Input: 1 String, an expression of numbers and operators
        // 4 * (24/2 + (9-5)/3), Use this for a test case

        //Process to Print: Read in thie input and go character by character
        //If the item is not an integer or space, put it in the operator stack
        //If the item is an operand, put it onto the string and put a space
            //if the following item is also an operand, concatonate it to the prior until the next item is not
            //Make a hashmap(basically a dict) which has name of operator and its precedence weight as values 
            //Check for 3 things on putting a number in the string
                //Is there a number behind it
                //Is there an operator in the stack
                //If there is, is the next operator lower or equal precedence then the one in the stack
                    //If not, push the higher precedence operator to the stack
                //If these are all true, pop the operator out of the stack and put it on the line

        //Process to Evaluate: Go character by character through the postfix string
            //Push operands into the stack
            //Execute operators on the top two items of the stack in the order 2nd item opr 1st item
            //Top to check item, store it in a variable, pop. Do this twice then push the result
    }
}
