import java.util.HashSet;
import java.util.Set;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class CountConsecutiveDP{


    public static void main( String[] args ){
        System.out.println( consecutiveDp( 2000L ) );
        System.out.println( consecutive( 2000L ) );
    }

    private static int consecutiveDp( long num ){
        int rt = 0;
        long fromIdx = 1;
        long toIdx = 2;
        long sum = 3;

        while( fromIdx < toIdx ){
            if( sum < num ){
                toIdx++;
                sum += toIdx;
            }
            else if( sum > num ){
                sum -= fromIdx;
                fromIdx++;
            }
            else{
                System.out.println( fromIdx + ">>>>" + toIdx );
                toIdx++;
                sum += toIdx;
                rt++;
            }
        }
        return rt;
    }


    // Complete the consecutive function below.
    static int consecutive( long num ){

        long limit = ( num / 2 );
        int rt = 0;

        for( long i = 1; i <= limit; i++ ){
            long j = i;
            long temp = num;

            Set<Long> s = new HashSet<>();
            while( j < temp ){
                s.add( j );
                temp = temp - j;
                j++;
            }
            if( j == temp ){
                s.add( j );
                System.out.println( "i:" + i + ",j" + j + ",temp:" + temp + "::::" + s.toString() );
                rt++;
            }
            else{
                System.out.println( "i:" + i + ",j" + j + ",temp:" + temp + "::::" + s.toString() + ":" + ( temp - j ) );
            }
        }
        return rt;
    }


}
