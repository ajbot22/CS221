public class Stack {
    char[] stack;
    int sp;

    public Stack(){
        stack = new char[1000];
        sp = -1;
    }

    public void push(char ch){
        stack[++sp] = ch;
    }

    public char pop(){
        return stack[sp--];
    }

    public char top(){
        return stack[sp];
    }

    public boolean empty(){
        return sp == -1;
    }
}