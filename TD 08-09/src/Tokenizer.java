public class Tokenizer {
    
    public boolean isStart;
    public boolean isIntNum;
    public boolean isNonIntNum;
    public double num;
    public int decimalDigits;
    public boolean isMinUnary;
    public boolean isNeg;
    public boolean isVar;
    public Calculator calc;

    Tokenizer() {
        isStart = true;
        isIntNum = false;
        isNonIntNum = false;
        num = 0.;
        decimalDigits = 0;
        isMinUnary = true;
        isNeg = false;
        isVar = false;
        calc = new Calculator();
    }

    public void readChar(char c) {
        if (Character.isDigit(c)) {
            int n = Character.getNumericValue(c);
            if (isNeg) {n = -n;}
            if (!isIntNum) {isIntNum = true; num = n; isStart = false; isMinUnary = false;}
            else if (!isNonIntNum) {num = num*10 + n;}
                 else {decimalDigits ++; num += n*Math.pow(10, -decimalDigits);}
        }

        else {
            if (c == 'C') {
                calc.commandInit();
                isStart = true;
                isIntNum = false;
                isNonIntNum = false;
                num = 0.;
                decimalDigits = 0;
                isMinUnary = true;
                isNeg = false;
                isVar = false;
            }

            if (c == '.') {
                isIntNum = true;
                isNonIntNum = true;
                isMinUnary = false;
            }

            if (isVar) {
                calc.commandReadMemory((int)num);
                isIntNum = false;
                num = 0;
                isVar = false;
            }

            if (c == '=') {
                if (isIntNum) {calc.commandDouble(num); resetNumberState();}
                isStart = true;
                calc.commandEqual();
                isMinUnary = true;
            }

            if (c == '+') {
                if (isIntNum) {calc.commandDouble(num); resetNumberState();}
                isStart = false;
                calc.commandOperator(Operator.PLUS);
                isMinUnary = true;
            }

            if (c == '-') {
                if (isMinUnary) {isNeg = true;}
                else {if (isIntNum) {calc.commandDouble(num); resetNumberState();}; isStart = false; calc.commandOperator(Operator.MINUS); isMinUnary = true;}
            }

            if (c == '*') {
                if (isIntNum) {calc.commandDouble(num); resetNumberState();}
                isStart = false;
                calc.commandOperator(Operator.MULT);
                isMinUnary = true;
            }

            if (c == '/') {
                if (isIntNum) {calc.commandDouble(num); resetNumberState();}
                isStart = false;
                calc.commandOperator(Operator.DIV);
                isMinUnary = true;
            }

            if (c == '(') {
                calc.commandLPar();
                isStart = false;
                isMinUnary = true;
            }

            if (c == ')') {
                if (isIntNum) {calc.commandDouble(num); resetNumberState();}
                calc.commandRPar();
                isMinUnary = false;
            }

            if (c == '$') {
                isVar = true;
            }
        }
    }

    public void readString(String s) {
        for (int i = 0; i < s.length(); i++) {readChar(s.charAt(i));}
    }


    private void resetNumberState() {
        isIntNum = false;
        isNonIntNum = false;
        isNeg = false;
        num = 0.0;
        decimalDigits = 0;
    }




    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        String expression = "(2+3)*-2=";

        tokenizer.readString(expression);

        System.out.println("Result: " + tokenizer.calc.getResult());
    }
}
