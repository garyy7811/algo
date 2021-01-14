import java.util.HashMap;
import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class Fibonnaci{


    public static void main( String[] args ){
//        fibonaci( 10 );
        fibonaciStck( 10 );

        int rt = bottomUpFib( 10 );
        System.out.println( rt );
    }

    private static int fibonaci( int i ){
        depth.push( " " );
        print( "i-->>" + i );
        int rt = - 1;
        if( i == 0 || i == 1 ){
            rt = 1;
        }
        else{
            rt = fibonaci( -- i ) + fibonaci( -- i );
        }
        print( "rt->>" + rt );
        depth.pop();
        return rt;
    }

    private static HashMap<Integer, Integer> resultMap = new HashMap<>();

    private static int fibonaciStck( int i ){
        resultMap.put( 0, 1 );
        resultMap.put( 1, 1 );

        if( i < 0 ){
            return - 1;
        }
        if( ! resultMap.containsKey( i ) ){
            for( int j = 2; j <= i; j++ ){
                resultMap.put( j, resultMap.get( j - 1 ) + resultMap.get( j - 2 ) );
            }
        }
        final Integer rt = resultMap.get( i );
        System.out.println( rt );
        return rt;
    }
    private static int bottomUpFib( int n){
        if(n == 1 || n == 0){
            return n;
        }
        int twoBehind = 0;
        int oneBehind = 0;
        int fib = 1;

        for(int i = 1; i < n; i++){
            fib = twoBehind + oneBehind;
            twoBehind = oneBehind;
            oneBehind = fib;
        }
        return fib;
    }


    private static LinkedList<String> depth = new LinkedList<>();
    private static void print( Object a ){

        System.out.println( depth.toString() + a.toString() );
    }
}
