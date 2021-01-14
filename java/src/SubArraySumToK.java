import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class SubArraySumToK{

        public final static int[] input = new int[]{ 1, 2, 5, 4, 3, 6, 7, 8, 9, 10, 11, 12, 13 };
//    public final static int[] input = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        public final static int   k     = 13;
//    public final static int k = 0;

    public static void main( String[] args ){
        assdf();
//        ijsum();
    }

    private static void assdf(){
        HashMap<Integer, Set<Integer>> sumToIdx = new LinkedHashMap<>();
        int lastSum = 0;
        int rt = 0;
        for( int i = 0; i < input.length; i++ ){
            sumToIdx.computeIfAbsent( lastSum, k -> new HashSet<>() ).add( i );

            lastSum += input[ i ];

            Set<Integer> t = sumToIdx.get( lastSum - k );
            if( t != null ){
                rt += t.size();
                System.out.println( t + ">>>" + i + "}}}" + rt );
            }
        }
        System.out.println( " " );
    }

    private static void ijsum(){
        int fromIdx = 0;
        int toIdx = 1;
        int sum = 3;
        while( fromIdx <= toIdx ){
            if( sum < k ){
                toIdx++;
                sum += input[ toIdx ];
            }
            else if( sum > k ){
                sum -= input[ fromIdx ];
                fromIdx++;
            }
            else{
                System.out.println( fromIdx + ">-->" + toIdx );
                if( toIdx < input.length - 1 ){
                    toIdx++;
                    sum += input[ toIdx ];
                }
                else{
                    break;
                }
            }
        }


    }
}
