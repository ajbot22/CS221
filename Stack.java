public class Stack<DataType> {
	DataType[] stack;
	int sp;
	int size = 100;
	
	public Stack(){			//constructor
		stack = (DataType[]) (new Object[size]);
		sp = -1;				// could be 0
	}
	
	public void push(DataType item) {
		stack[++sp]=item;
								// might need something to check if stack is full
	}
	
	public DataType pop() {
		return stack[sp--];
	}
	
	public DataType peek() {
		return stack[sp];
	}
	
	public boolean empty() {
		return sp==-1;
	}
	
	public int size() {
        return sp + 1;
    }
	
}
