import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class CountConsecutive{


    public static void main( String[] args ){
        int rslt = consecutive( 9999L );
        System.out.println( rslt );
    }


    // Complete the consecutive function below.
    static int consecutivef( long num ){
        int rt = 0;
        for( long i = 0; i <= num / 2; i++ ){
            long k = 2 * num + i * ( i - 1 );
            long j = ( long )( ( - 1 + Math.sqrt( 1 + 4 * k ) ) / 2 );
            if( j * ( j + 1 ) == k ){
                System.out.println( "sum [" + i + ".." + j + "] = " + num );
                rt++;
            }
        }
        return rt;
    }


    // Complete the consecutive function below.
    static int consecutive( final long num ){

        long limit = ( num / 2 );
        int rt = 0;

        for( long i = 1; i <= limit; i++ ){
            long j = i;
            long temp = num;

            Set<Long> s = new HashSet<>(  );
            while( j < temp ){
                s.add( j );
                temp = temp - j;
                j++;
            }
            if( j == temp ){
                s.add( j );
                System.out.println( s.toString() );
                rt++;
            }
        }
        return rt;
    }


}
