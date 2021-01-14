import java.util.LinkedList;

public class TreasureIslandIIPath{
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
                    Node n = bfs( island, i, j );
                    System.out.println( "<<<<" + i + "<<<<" + j );
                }
            }
        }
    }

    private static final int[][] DIRs = new int[][]{ new int[]{ - 1, 0 }, new int[]{ 1, 0 }, new int[]{ 0, - 1 }, new int[]{ 0, 1 } };

    private static Node bfs( char[][] island, int i, int j ){
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst( new Node( i, j, 0, null ) );
        boolean[][] inQueue = new boolean[ island.length ][ island[ 0 ].length ];
        inQueue[ i ][ j ] = true;
        while( queue.size() > 0 ){
            Node n = queue.removeLast();
            int v = island[ n.row ][ n.col ];
            if( v == 'X' ){
                return n;
            }
            for( int[] dir: DIRs ){
                int nRow = dir[ 0 ] + n.row;
                int nCol = dir[ 1 ] + n.col;
                if( nRow >= 0 && nRow <= island.length - 1 && nCol >= 0 && nCol <= island[ 0 ].length - 1 && ! inQueue[ nRow ][ nCol ] ){
                    char nv = island[ nRow ][ nCol ];
                    if( nv == 'O' || nv == 'X' ){
                        queue.addFirst( new Node( nRow, nCol, n.depth + 1, n ) );
                        inQueue[ nRow ][ nCol ] = true;
                    }
                }
            }
        }
        return null;
    }

    private static class Node{
        public Node( int row, int col, int depth, Node prev ){
            this.row = row;
            this.col = col;
            this.depth = depth;
            this.prev = prev;
        }

        int row;
        int col;
        int depth;
        Node prev;
    }
}
