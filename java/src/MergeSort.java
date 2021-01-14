import java.util.Arrays;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class MergeSort{

    public static void main( String[] args ){

        int[] arr = new int[]{ 111, 3, 5, 9, 12, 39, 93, 2, 6, 7, 100, 8 };
        int[] rslt = mergeSort( arr, 0, arr.length - 1 );
        System.out.println( rslt );
    }

    private static int[] mergeSort( int[] arr, final int indexFrom, final int indexTo ){
        if( indexFrom == indexTo ){
            return new int[]{ arr[ indexFrom ] };
        }
        int mid = indexFrom + ( indexTo - indexFrom ) / 2;
        return merge( mergeSort( arr, indexFrom, mid ), mergeSort( arr, mid + 1, indexTo ) );
    }

    private static int[] merge( int[] leftArr, int[] rightArr ){
        final int[] rt = new int[ leftArr.length + rightArr.length ];
        int leftIdx = 0;
        int rightIdx = 0;
        while( leftIdx < leftArr.length || rightIdx < rightArr.length ){
            if( leftIdx >= leftArr.length ){
                rt[ leftIdx + rightIdx ] = rightArr[ rightIdx ];
                rightIdx++;
                continue;
            }
            if( rightIdx >= rightArr.length ){
                rt[ leftIdx + rightIdx ] = leftArr[ leftIdx ];
                leftIdx++;
                continue;
            }
            int leftEle = leftArr[ leftIdx ];
            int rightEle = rightArr[ rightIdx ];
            if( leftEle > rightEle ){
                rt[ leftIdx + rightIdx ] = leftEle;
                leftIdx++;
            }
            else{
                rt[ leftIdx + rightIdx ] = rightEle;
                rightIdx++;
            }
        }
        return rt;
    }
}
