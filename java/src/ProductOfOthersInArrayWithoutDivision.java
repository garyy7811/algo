import java.util.Arrays;

/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class ProductOfOthersInArrayWithoutDivision{


    public static void main( String[] args ){
        long[] l = productOfElse( new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } );
        System.out.println( Arrays.toString( l ) );
    }

    private static long[] productOfElse( int[] ints ){

        long[] right = new long[ ints.length ];
        for( int i = right.length - 1; i >= 0; i-- ){
            if( i == right.length - 1 ){
                right[ i ] = ints[ i ];
            }
            else{
                right[ i ] = right[ i + 1 ] * ints[ i ];
            }
        }

        System.out.println( "right:" + Arrays.toString( right ) );

        long[] left = new long[ ints.length ];
        for( int i = 0; i < ints.length; i++ ){
            if( i == 0 ){
                left[ i ] = ints[ i ];
            }
            else{
                left[ i ] = ints[ i ] * left[ i - 1 ];
            }
        }

        System.out.println( "left:" + Arrays.toString( left ) );


        long[] rt = new long[ ints.length ];
        rt[ 0 ] = right[ 1 ];
        rt[ rt.length - 1 ] = left[ rt.length - 2 ];

        for( int i = 1; i < rt.length - 1; i++ ){
            rt[ i ] = left[ i - 1 ] * right[ i + 1 ];
        }

        return rt;

    }
}
