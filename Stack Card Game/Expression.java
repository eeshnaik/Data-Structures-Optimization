import java.util.*;

/**
 * Expression class to store infix expression and methods
 * @author eeshn
 * @version 1.0
 */
public class Expression {
    /**
     * Private datafield infix
     */
    
    private String infix;
    
    /**
     * Constructor for Expression
     * @param in String that is being passed as infix
     */
    
    public Expression(String in) {
        this.infix = in;
    }
    
    /**
     * Method to convert to postfix
     * @return A postfix expression as an ArrayList of tokens
     */
    
    public ArrayList<String> infixTopostfix() {
        GenericStack<String> s = new GenericStack();
       ArrayList<String> postfix = new ArrayList<>();
       
       ArrayList<String> in = this.tokenizer();
       
       /**
        * Scrolls through arraylist and follows procedure described in project directions to convert to postfix
        */
       
        for (int i = 0; i < in.size(); i++) {
            
            if (Character.isDigit(in.get(i).charAt(0))) {
                postfix.add(in.get(i));
            }
            
            
            else if (in.get(i).charAt(0) == '(') {
                s.push(in.get(i));
            }
            
            else if (isOp(in.get(i).charAt(0))) {
                if (s.isEmpty()) {
                    s.push(in.get(i));
                }
                
                else { //Stack not empty
                    while (!s.isEmpty() && opvalue(s.peek()) >= opvalue(in.get(i))) {
                        postfix.add(s.pop());
                    }
                    s.push(in.get(i));
                }
            }
            
            else if (in.get(i).charAt(0) == ')') {
                
                while (!s.isEmpty() && s.peek().charAt(0) != '(') {
                    postfix.add(s.pop());
                }
               if (!s.isEmpty() && s.peek().charAt(0) == '(')
                    s.pop();
            }
                 
        }
        /**
         * Empties remaining elements in stack
         */
        while (!s.isEmpty()) {
            postfix.add(s.pop());
        }
        
        postfix.remove(String.valueOf('('));
        postfix.remove(String.valueOf(')'));
        return postfix;
        
    }
    
    /**
     * Method that evaluates postfix expression
     * @return Integer value of expression
     */
    
    public int evaluate() {
        
        ArrayList<String> pf = this.infixTopostfix();
        
        GenericStack <String> s = new GenericStack();
        
        /**
         * Scrolls through arraylist and performs operations on postfix to evaluate
         */
        
        for (int i = 0; i < pf.size(); i++) {
            
            if (Character.isDigit(pf.get(i).charAt(0))) {
                s.push(pf.get(i));
                
            }
            
            else if (isOp(pf.get(i).charAt(0))) {
                String op2 = s.pop();
                
                String op1 = s.pop();
                
                int res = perform(pf.get(i),op1,op2);
                
                s.push(Integer.toString(res));
            }
            
        }
            
            return Integer.parseInt(String.valueOf(s.peek()));
        }
    
    /**
     * Tokenizes the infix expression
     * @return A tokenized infix expression as an ArrayList
     */
        
    private ArrayList<String> tokenizer()  {
       
       // Uses StringTokenizer to tokenize string
       infix = infix.replaceAll("\\s", "");
       StringTokenizer st = new StringTokenizer(infix,"+-*/^() ",true);
        
        ArrayList<String> list = new ArrayList<>();
        
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        
      return list;
        
    }
    /**
     * Method that determines precedence of operator
     * @param op The operator in question
     * @return An integer that tells degree of precedence of operator
     */
    
    private int opvalue (String op) {
        
        /**
         * Assigns integers to operators to sort precedence
         * 
         */
        if (op.charAt(0) == '+' || op.charAt(0) == '-') {
            return 1;
           
        }
        
        else if (op.charAt(0) == '*' || op.charAt(0) == '/') {
            return 2;
        }
        
        return 0;
        
    }
    
    /**
     * Tells whether character is an operator
     * @param ch The character in question
     * @return Boolean for whether it is an operator
     */
    
    private boolean isOp(char ch) {
        
        return ch == '+' || ch == '-' || ch == '/'|| ch == '*';
    }
    
    /**
     * Method that performs operations given String versions of number/ops
     * @param op The operator
     * @param num1 The 1st operand
     * @param num2 The 2nd operand
     * @return The result
     */
    
    private int perform(String op, String num1, String num2) {
        
        int n1 = Integer.parseInt(String.valueOf(num1));
        int n2 = Integer.parseInt(String.valueOf(num2));
        
        /**
         * Performs appropriate arithmetic based on operator
         */
        
        switch(op.charAt(0)) {
            case '+':
                return n1 + n2;
                
            case '-':
                return n1-n2;
                
            case '*':
                return n1*n2;
                
            case '/':
                return n1/n2;
            
            default:
                return -1;
             
        }
        
    }
    public static void main(String [] args) {
        Expression e1 = new Expression("x1+z1*(w1/k1+z1*(y1*u1))");
        
        System.out.println(Arrays.toString(e1.infixTopostfix().toArray()));
    }
    
}



