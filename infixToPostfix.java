//Done in collaboration between Aj Botticelli & Zach Brown
import java.util.*;
import javax.swing.JOptionPane;

public class infixToPostfix {
    public static void main(String[] args) {
        Stack<Character> operatorStack = new Stack<Character>();
        Stack<Double> valueStack = new Stack<Double>();
        String infix = JOptionPane.showInputDialog("Enter Infix: ");
        postfixEvaluator(infixConverter(infix,operatorStack),valueStack);
    }

    public static StringBuilder infixConverter(String infix, Stack<Character> operatorStack){
        StringBuilder postfixExpression = new StringBuilder();
        // could use a switch statement in a method, but this is much cleaner
        // set a precedence value to each operator
        HashMap<Character, Integer> precedenceHash = new HashMap<Character, Integer>() {
            {
                put('*', 2);
                put('/', 2);
                put('%', 2);
                put('+', 1);
                put('-', 1);
                put('(', 0);
                put(')', 0);
            }
        };
        for (int i = 0; i < infix.length(); i++) {
            char value = infix.charAt(i);
            switch (value) {
                case' ':
                    break;
                case '*':
                case '/':
                case '%':
                case '+':
                case '-':
                    // ----------------everything from here on out refers to an operator----------------
                    // if operator stack is empty push it
                    if (operatorStack.empty())
                        operatorStack.push(value);
                    else {
                    
                    // if precedence of value is greater than the precedence of top of the operator stack, push it
                        if (precedenceHash.get(value) > precedenceHash.get(operatorStack.peek()))
                            operatorStack.push(value);
                        // if precedence of value is less than or equal to the precedence of top of operator stack,
                        // pop operators into operatorstack until precedence of top of operator stack is lower than the
                        // original value's precedent
                        else if (precedenceHash.get(value) <= precedenceHash.get(operatorStack.peek())) {
                            while (!operatorStack.empty() 
                                    && precedenceHash.containsKey(operatorStack.peek()) 
                                    && precedenceHash.get(value) <= precedenceHash.get(operatorStack.peek())) 
                            {
                                postfixExpression.append(operatorStack.pop()+" ");
                            }
                            operatorStack.push(value);
                        }
                    }
                    break;
                    
                case ')':
                    // pop operators off the stack until you encounter an open parenthesis, and pop that out of the stack
                    while (!operatorStack.empty() && operatorStack.peek() != '(') postfixExpression.append(operatorStack.pop()+" ");
                    operatorStack.pop();
                    break;
                case '(':
                    // push no matter what
                    operatorStack.push(value);
                    break;
                default:
                    // operand
                    while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                        postfixExpression.append(infix.charAt(i));
                        i++;
                    }
                    postfixExpression.append(' '); 
                    i--; // account for extra loop
                    break;
            }
        }
        //pop the rest of the stack to get rest of operators left in the stack
        while (!operatorStack.empty()) {
            postfixExpression.append(operatorStack.pop()+" ");
        }
        // print and return the postfix expression
        System.out.println("Postfix Expression: " + postfixExpression);
        return postfixExpression;
    }

    public static Double postfixEvaluator(StringBuilder postfix, Stack<Double> valueStack){
        Double postfixEval;
        String[] postfixArray = postfix.toString().split(" "); //seperate postfix expression

        for(int j=0;j<postfixArray.length;j++){
            // Manipulating data types to seperate operands and operators
            String operand = postfixArray[j];
            char operator = operand.charAt(0);
            // Push operands into the stack
            if(Character.isDigit(operator)){
                //System.out.println("operand: "+Double.parseDouble(operand));
                valueStack.push(Double.parseDouble(operand));
            }
            // Handle operators and arithmetic
            else{
                Double temp1 = valueStack.pop();
                Double temp2 = valueStack.pop();
                switch(operator){
                    case'*':valueStack.push(temp2*temp1);
                        break;
                    case'/':valueStack.push(temp2/temp1);
                        break;
                    case'%':valueStack.push(temp2%temp1);
                        break;
                    case'+':valueStack.push(temp2+temp1);
                        break;
                    case'-':valueStack.push(temp2-temp1);
                        break;
                }
            }
            //System.out.println("        Top of stack: "+valueStack.peek());
        }
        // pop, print, and return the postfix evaluation
        postfixEval=valueStack.pop();
        System.out.println("Evaluated postfix expression: "+ postfixEval);
        return postfixEval;
    }
}