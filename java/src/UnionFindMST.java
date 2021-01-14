import java.util.*;
import java.util.stream.Collectors;

/*

Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
Output: 7
Explanation:
There are 3 connected components [1, 4, 5], [2, 3] and [6].
We can connect these components into a single component by connecting node 1 to node 2 and node 1 to node 6 at a minimum cost of 5 + 2 = 7.

 */
public class UnionFindMST{
    public static void main( String[] args ){
        int n = 6;
        int[][] edges = new int[][]{ { 1, 4 }, { 4, 5 }, { 2, 3 } };
        int[][] newEdges = { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 }, { 5, 6, 5 } };

        int[][] rslt = mst( edges, newEdges );

        System.out.println( rslt );
    }

    private static int[][] mst( int[][] edges, int[][] newEdges ){

        List<int[]> edgeLst = new ArrayList( Arrays.asList( newEdges ) );

        edgeLst.addAll( Arrays.asList( edges ).stream().map( a -> new int[]{ a[ 0 ], a[ 1 ], 0 } ).collect( Collectors.toList() ) );

        Collections.sort( edgeLst, Comparator.comparingInt( a -> a[ 2 ] ) );

        Map<Integer, Set<Integer>> nodeToUnion = new HashMap<>();
        List<int[]> rt = new ArrayList<>();
        for( int[] edge: edgeLst ){
            Set<Integer> fu = nodeToUnion.get( edge[ 0 ] );
            Set<Integer> tu = nodeToUnion.get( edge[ 1 ] );
            if( fu == null && tu == null ){
                fu = new HashSet<>();
                fu.add( edge[ 0 ] );
                fu.add( edge[ 1 ] );
                nodeToUnion.put( edge[ 0 ], fu );
                nodeToUnion.put( edge[ 1 ], fu );
            }
            else if( fu != null && tu == null ){
                fu.add( edge[ 1 ] );
                nodeToUnion.put( edge[ 1 ], fu );
            }
            else if( fu == null && tu != null ){
                tu.add( edge[ 0 ] );
                nodeToUnion.put( edge[ 0 ], tu );
            }
            else if( fu != tu ){
                Set<Integer> big = fu.size() > tu.size() ? fu : tu;
                Set<Integer> sml = fu.size() < tu.size() ? fu : tu;

                for( Integer si: sml ){
                    big.add( si );
                    nodeToUnion.put( si, big );
                }
            }
            else if( fu == tu ){
                continue;
            }
            rt.add( edge );
        }

        return rt.toArray( new int[ rt.size() ][] );
    }
}
