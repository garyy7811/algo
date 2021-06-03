import java.util.Arrays;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class QuickSort{

    public static void main( String[] args ){

        findKthLargest( new int[]{ 111, 3, 5, 9, 12, 39, 93, 2, 6, 7, 100, 8 }, 3, 93 );
        findKthLargest( new int[]{ 2, 1 }, 2, 1 );
        findKthLargest( new int[]{ 2, 1 }, 1, 2 );
        findKthLargest( new int[]{ 7, 6, 5, 4, 3, 2, 1 }, 1, 7 );
        findKthLargest( new int[]{ 7, 6, 5, 4, 3, 2, 1 }, 2, 6 );
        findKthLargest( new int[]{ 7, 6, 5, 4, 3, 2, 1 }, 6, 2 );
        findKthLargest( new int[]{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, 1, 3 );
    }


    public static void findKthLargest( int[] nums, int k, int expected ){
        int kthLargest = findKthLargest( nums, k, 0, nums.length - 1 );
        if( kthLargest != expected ){
            throw new RuntimeException( kthLargest + "<<return--expected>>" + expected );
        }
    }

    public static int findKthLargest( final int[] nums, final int k, final int orgF, final int orgT ){
//        System.out.println( ">>>" + Arrays.toString( nums ) + ";from:" + rFrom + ";to:" + rTo );
        if( orgT - orgF < 5 ){
            Arrays.sort( nums, orgF, orgT + 1 );
            return nums[ nums.length - k ];
        }

        int rFrom = orgF + 1;
        int rTo = orgT;

        int pValue = nums[ orgF ];
        while( true ){
            while( nums[ rFrom ] <= pValue && rFrom < rTo ){
                rFrom++;
            }
            while( nums[ rTo ] >= pValue && rFrom < rTo ){
                rTo--;
            }

            if( rFrom == rTo ){
                break;
            }
            int tmp = nums[ rFrom ];
            nums[ rFrom ] = nums[ rTo ];
            nums[ rTo ] = tmp;
        }

        if( pValue > nums[ rTo ] ){
            nums[ orgF ] = nums[ rTo ];
            nums[ rTo ] = pValue;
        }

//        System.out.println( "<<<" + Arrays.toString( nums ) + "; p:" + pIdx + ";from:" + rFrom + ";to:" + rTo );


        if( rTo + k > nums.length ){
            return findKthLargest( nums, k, orgF, rTo - 1 );
        }
        else{
            return findKthLargest( nums, k, rTo, orgT );
        }
    }


}
