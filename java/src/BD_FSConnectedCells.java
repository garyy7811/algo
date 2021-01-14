import java.util.HashSet;
import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class BD_FSConnectedCells{

    private static final int[][] grid = {
            //   { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            /*0*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            /*1*/{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            /*2*/{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            /*3*/{ 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
            /*4*/{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
            /*5*/{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            /*6*/{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            /*7*/{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            /*8*/{ 0, 1, 1, 0, 0, 0, 1, 1, 1, 0 },
            /*9*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

    private static HashSet<String> visitedSet = new HashSet<>();

    public static void main( String[] args ){

        int max = 0;
        for( int i = 0; i < grid.length; i++ ){
            int[] row = grid[ i ];
            for( int j = 0; j < row.length; j++ ){
                final String ijKey = getIjKey( i, j );
                if( visitedSet.contains( ijKey ) ){
                    continue;
                }
                final int ijVal = row[ j ];
                if( ijVal > 0 ){
                    int c = bfs( i, j );
                    //                    int c = dfs( i, j, 0 );
                    max = Math.max( c, max );
                }
                visitedSet.add( ijKey );
            }
        }
        System.out.println( max );
    }

    private static int xLen = 10;
    private static int yLen = 10;

    private static int bfs( int i, int j ){
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add( new int[]{ i, j } );
        int count = 0;
        while( queue.size() > 0 ){
            int[] el = queue.removeLast();
            i = el[ 0 ];
            j = el[ 1 ];
            if( i < 0 || i >= xLen || j < 0 || j >= yLen ){
                continue;
            }

            final String ijKey = getIjKey( i, j );
            final int ijVal = grid[ i ][ j ];
            if( ijVal <= 0 || visitedSet.contains( ijKey ) ){
                continue;
            }
            visitedSet.add( ijKey );
            count++;

            queue.addFirst( new int[]{ i - 1, j - 1 } );
            queue.addFirst( new int[]{ i - 1, j } );
            queue.addFirst( new int[]{ i - 1, j + 1 } );
            queue.addFirst( new int[]{ i, j - 1 } );
            queue.addFirst( new int[]{ i, j + 1 } );
            queue.addFirst( new int[]{ i + 1, j - 1 } );
            queue.addFirst( new int[]{ i + 1, j } );
            queue.addFirst( new int[]{ i + 1, j + 1 } );
        }

        return count;
    }


    private static String getIjKey( int i, int j ){
        return i + "-" + j;
    }

    private static int dfs( int i, int j, int count ){
        if( i < 0 || i >= xLen || j < 0 || j >= yLen ){
            return count;
        }

        final String ijKey = getIjKey( i, j );
        final int ijVal = grid[ i ][ j ];
        if( ijVal <= 0 || visitedSet.contains( ijKey ) ){
            return count;
        }

        visitedSet.add( ijKey );
        count++;

        count = dfs( i - 1, j - 1, count );
        count = dfs( i - 1, j, count );
        count = dfs( i - 1, j + 1, count );
        count = dfs( i, j - 1, count );
        count = dfs( i, j + 1, count );
        count = dfs( i + 1, j - 1, count );
        count = dfs( i + 1, j, count );
        count = dfs( i + 1, j + 1, count );
        return count;
    }


}
