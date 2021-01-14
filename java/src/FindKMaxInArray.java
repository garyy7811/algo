import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class FindKMaxInArray{


    public static void main( String[] args ){

        Integer[] result = findTheLargestK( new int[]{ 7, 5, 3, 2, 9, 7, 6, 5, 1, 6, 9 }, 3 );
        System.out.println( "--" );
        System.out.println( result );
        String s = Stream.of( result ).map( Object::toString ).collect( Collectors.joining( "," ) );
        System.out.println( s );
    }

    private static Integer[] findTheLargestK( int[] vals, int k ){

        PriorityQueue<Integer> q = new PriorityQueue<>( new Comparator<Integer>(){
            @Override
            public int compare( Integer o1, Integer o2 ){
                return o1 > o2 ? 1 : - 1;
            }
        } );
        for( int i = 0; i < vals.length; i++ ){
            int val = vals[ i ];
            q.add( val );
            if( q.size() > k ){
                System.out.println( "poll>>" + q.poll() );
            }
        }
        return q.toArray( new Integer[ k ] );
    }


}
