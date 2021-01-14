import java.util.Arrays;
import java.util.LinkedList;

public class TreasureIslandII{
    public static void main( String[] args ){
        char[][] island = new char[][]{
                new char[]{ 'S', 'O', 'O', 'S', 'S' },
                new char[]{ 'D', 'O', 'D', 'O', 'D' },
                new char[]{ 'O', 'O', 'O', 'O', 'X' },
                new char[]{ 'X', 'D', 'D', 'O', 'O' },
                new char[]{ 'X', 'D', 'D', 'D', 'O' }
        };
        // S -- start
        // D -- danger
        // X -- target
        // O -- safe

        for( int i = 0; i < island.length; i++ ){
            for( int j = 0; j < island[ 0 ].length; j++ ){
                if( island[ i ][ j ] == 'S' ){
                    System.out.println( ">>>>" + i + ">>>>" + j );
                    bfs( island, i, j );
                    System.out.println( "<<<<" + i + "<<<<" + j );
                }
            }
        }
    }

    private static final int[][] DIRs = new int[][]{ new int[]{ - 1, 0 }, new int[]{ 1, 0 }, new int[]{ 0, - 1 }, new int[]{ 0, 1 } };

    private static int bfs( char[][] island, int i, int j ){
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addFirst( new int[]{ i, j } );
        boolean[][] inQueue = new boolean[ island.length ][ island[ 0 ].length ];
        inQueue[ i ][ j ] = true;
        int step = 0;
        boolean found = false;
        w:
        while( queue.size() > 0 ){
            int lsz = queue.size();
            StringBuilder sb = new StringBuilder( "" );
            for( int k = 0; k < lsz; k++ ){
                int[] tmp = queue.removeLast();
                int row = tmp[ 0 ];
                int col = tmp[ 1 ];
                sb.append( "[" + row + ", " + col + "], " );
                int v = island[ row ][ col ];
                if( v == 'X' ){
                    found = true;
                    break w;
                }
                for( int[] dir: DIRs ){
                    int nRow = dir[ 0 ] + row;
                    int nCol = dir[ 1 ] + col;
                    if( nRow >= 0 && nRow <= island.length - 1 && nCol >= 0 && nCol <= island[ 0 ].length - 1 && ! inQueue[ nRow ][ nCol ] ){
                        char nv = island[ nRow ][ nCol ];
                        if( nv == 'O' || nv == 'X' ){
                            queue.addFirst( new int[]{ nRow, nCol } );
                            inQueue[ nRow ][ nCol ] = true;
                        }
                    }
                }
            }
            step++;
            sb.append( ", step:" + step );
            System.out.println( sb.toString() );
        }
        if( found ){
            return step;
        }
        else{
            return - 1;
        }
    }
}
