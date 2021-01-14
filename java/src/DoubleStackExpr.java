import java.util.Stack;

public class DoubleStackExpr{

    public static void main( String[] args ){
        String expr = "( 1 + ( 3 - 1 ) - (5 +6) )";

        Stack<Character> oStack = new Stack<>();
        Stack<Integer> nStack = new Stack<>();
        for( char c : expr.toCharArray() ){
            if( c == '+' || c == '-' || c == '*' ){
                oStack.push( c );
            }
            else if( c == ')' ){
                Integer n2 = nStack.pop();
                Integer n1 = nStack.pop();
                Character o = oStack.pop();
                if( o == '+' ){
                    nStack.push( n1 + n2 );
                    System.out.println( n1 + "+" + n2 + "=" + nStack.peek() );
                }
                else if( o == '-' ){
                    nStack.push( n1 - n2 );
                    System.out.println( n1 + "-" + n2 + "=" + nStack.peek() );
                }
                else if( o == '*' ){
                    nStack.push( n1 * n2 );
                    System.out.println( n1 + "*" + n2 + "=" + nStack.peek() );
                }
                else{
                    throw new RuntimeException( "unknown operator:" + o );
                }

            }
            else{
                try{
                    nStack.push( Integer.parseInt( ( String.valueOf( c ) ) ) );
                }
                catch( NumberFormatException e ){
                    System.out.println( "ignore :" + c );

                }
            }

        }
        System.out.println( "final answer:" + nStack.pop() );
    }
}
