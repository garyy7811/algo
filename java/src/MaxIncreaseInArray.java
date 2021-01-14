/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class MaxIncreaseInArray{


    public static void main( String[] args ){

        int result = findTheLargestIncrease( new int[]{ 7, 5, 3, 2, 9, 7, 6, 5, 1, 6, 9 } );
        System.out.println( "--" );
        System.out.println( result );
    }

    private static int findTheLargestIncrease( int[] vals ){
        int rt = 0;

        int fromIdx = 0;

        for( int i = 1; i < vals.length; i++ ){
            final int val_i = vals[ i ];
            final int val_from = vals[ fromIdx ];
            if( val_i > val_from ){
                rt = Math.max( rt, val_i - val_from );
                System.out.println( "fromIdx=" + fromIdx + " toIdx=" + i + ">rt=" + rt );
            }
            if( val_i < val_from ){
                fromIdx = i;
                System.out.println( "fromIdx=" + fromIdx );
            }

        }


        return rt;
    }


}
