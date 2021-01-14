import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class PowerXN{


    public static void main( String[] args ){
        System.out.println( Math.pow( 2, 1 ) );

        powerXN( 2, 31 );
        System.out.println( Math.pow( 2, 31 ) );

        powerXN( 2, 32 );
        System.out.println( Math.pow( 2, 32 ) );
    }

    private static long powerXN( final int x, final int n ){
        print( ">>>x=" + x + "; n=" + n );

        long rt = 0;
        if( n == 0 ){
            rt = 1;
        }
        else if( n == 1 ){
            rt = x;
        }
        else if( n % 2 == 0 ){
            final long half = powerXN( x, n / 2 );
            rt = half * half;
        }
        else{
            final long half = powerXN( x, ( n - 1 ) / 2 );
            rt = half * half * x;
        }
        print( "<<<x=" + x + "; n=" + n + " --" + rt );
        return rt;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + " : " + a.toString() );
    }
}
