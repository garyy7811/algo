import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class RangeSearch{


    public static void main( String[] args ){
        TreeMap<Integer, Integer> tmRow = new TreeMap<>();
        TreeMap<Integer, Integer> tmCol = new TreeMap<>();

        for( int i = 0; i < twoDm.length; i++ ){
            int[] rowI = twoDm[ i ];
            for( int j = 0; j < rowI.length; j++ ){
                int rowIcolJ = rowI[ j ];
                if( rowIcolJ > 0 ){
                    tmRow.put( i, j );
                    tmCol.put( j, i );
                }
            }
        }

        SortedMap<Integer, Integer> sr = tmRow.subMap( 3, true, 6, true );
        new TreeMap<>( sr ).subMap( 3, true, 6, true );

    }

    private static final int[][] twoDm =
            {
//                       { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                    /*0*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    /*1*/{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
                    /*2*/{ 0, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
                    /*3*/{ 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                    /*4*/{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
                    /*5*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    /*6*/{ 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                    /*7*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    /*8*/{ 0, 0, 2, 0, 0, 0, 0, 0, 2, 0 },
                    /*9*/{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + a.toString() );
    }
}
