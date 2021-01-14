import java.util.*;
import java.util.stream.Collectors;

/**
 * User: GaryY
 * Date: 9/9/2018
 */
public class Flatiron2{

    static Flatiron2 question = new Flatiron2();

    public static void main( String[] args ){
        question.new Solution().solution( new int[]{ 29, 50 }, new int[]{ 61, 37 }, new int[]{ 37, 70 } );
        question.new Solution().solution( new int[]{ 29, 29 }, new int[]{ 61, 61 }, new int[]{ 70, 70 } );
    }


    class Solution{
        public int solution( int[] A, int[] B, int[] C ){
            Arrays.sort( A );
            Arrays.sort( B );
            Arrays.sort( C );

            int rt = 0;
            for( int i = 0; i < A.length; i++ ){
                int ap = A[ i ];
                for( int j = binarySearcy( B, ap ); j < B.length; j++ ){
                    int bp = B[ j ];
                    for( int k = binarySearcy( C, bp ); k < C.length; k++ ){
                        rt++;
                        if( rt > 1000000000 ){
                            return - 1;
                        }
                    }
                }
            }
            return rt;
        }

        private int binarySearcy( int[] ints, int greater ){
            int p = Collections.binarySearch( Arrays.stream( ints ).boxed().collect( Collectors.toList() ), greater );
            if( p < 0 ){
                p = - p - 1;
            }


            if( p > ints.length - 1 ){
                return - 1;
            }
            return p;

        }


    }

}
