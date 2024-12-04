import java.util.Stack;
import java.util.LinkedList;

public class Calculator {
    
    public Stack<Double> numbers;
    public Stack<Operator> operators;
    public LinkedList<Double> results;

    public Calculator() {
        numbers = new Stack<Double>();
        operators = new Stack<Operator>();
        results = new LinkedList<Double>();
    }

    @Override
    public String toString(){
        return numbers.toString() + operators.toString();
    }

    public void pushDouble(double d) {
        numbers.push(d);
    }

    public void pushOperator(Operator o) {
        operators.push(o);
    }

    public double getResult() {
        if (numbers.empty()) {throw new RuntimeException();}
        return numbers.peek();
    }


    public void executeBinOperator(Operator op) {
        Double b = numbers.pop();
        Double a = numbers.pop();

        switch (op) {
            case PLUS:
                pushDouble(a + b);
                break;
        
            case MINUS:
                pushDouble(a - b);
                break;

            case MULT:
                pushDouble(a * b);
                break;

            case DIV:
                pushDouble(a/b);
                break;

            case OPEN:
                break;
        }
    }

    
    private int precedence(Operator op) {
        int i = 0;
        switch (op) {
            case PLUS:
                i = 1;
                break;
        
            case MINUS:
                i = 1;
                break;

            case MULT:
                i = 2;
                break;

            case DIV:
                i = 2;
                break;

            case OPEN:
                i = 0;
                break;
        }
        return i;
    }

    public void commandOperator(Operator op) {
        while (!operators.isEmpty() && precedence(op)<= precedence(operators.peek())) {
            executeBinOperator(operators.pop());
        }
        pushOperator(op);
    }

    public void commandDouble(double d) {pushDouble(d);}

    public void commandEqual() {
        while (!operators.isEmpty()) {executeBinOperator(operators.pop());}
        results.add(numbers.peek());
    }

    public void commandLPar() {pushOperator(Operator.OPEN);}

    public void commandRPar() {
        if (operators.isEmpty()) {throw new RuntimeException();}
        Operator op = operators.pop();
        while (op != Operator.OPEN) {
        if (operators.isEmpty()) {throw new RuntimeException();} 
        executeBinOperator(op);
        op = operators.pop();
        }
    }

    public void commandInit() {
        numbers.clear();
        operators.clear();
    }

    public void commandReadMemory(int i) {
        if (results.size() < i) {throw new RuntimeException();}
        pushDouble(results.get(results.size() - i));
    }


    public static void main(String[] args) {
    }
}