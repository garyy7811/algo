import java.util.*;

/**
 * User: GaryY
 * Date: 9/9/2018
 */
public class Flatiron1{

    static Flatiron1 question = new Flatiron1();

    public static void main( String[] args ){

        question.new Solution().solution( new int[]{ 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 } );
    }

    private static void check( String input, int k, String expected ){
    }


    class Solution{
        public int[] solution( int[] T ){
            Map<Integer, Set<Integer>> routes = new HashMap<>();
            int captal = - 1;
            for( int origin = 0; origin < T.length; origin++ ){
                int dest = T[ origin ];

                if( dest == origin ){
                    captal = origin;
                    continue;
                }

                Set<Integer> originLst = routes.computeIfAbsent( dest, k -> new HashSet<>() );
                originLst.add( origin );

                Set<Integer> destLst = routes.computeIfAbsent( origin, k -> new HashSet<>() );
                destLst.add( dest );
            }

            HashMap<Integer, Set<Integer>> rslt = new HashMap<>();

            LinkedList<int[]> queue = new LinkedList<>();

            queue.addLast( new int[]{ captal, 0 } );
            while( queue.size() > 0 ){
                int[] tmp = queue.removeFirst();

                int distance = tmp[ 1 ];
                Set<Integer> count = rslt.computeIfAbsent( distance, k -> new HashSet<>() );

                Set<Integer> nexts = routes.remove( tmp[ 0 ] );
                if( nexts != null ){
                    for( Integer next: nexts ){
                        queue.addLast( new int[]{ next, distance + 1 } );
                        if( routes.containsKey( next ) )
                            count.add( next );
                    }

                }
            }
            int[] rt = new int[ T.length ];
            for( Map.Entry<Integer, Set<Integer>> aa: rslt.entrySet() ){
                rt[ aa.getKey() ] = aa.getValue().size();
            }
            return rt;
        }


    }

}
