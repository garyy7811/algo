import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 5/15/2018
 */
public class AllEdgesPath{


    public static void main( String[] args ){
        graph[ 0 ][ 1 ] = 10;
        graph[ 0 ][ 2 ] = 3;
        graph[ 0 ][ 3 ] = 2;
        graph[ 1 ][ 3 ] = 3;
        graph[ 2 ][ 3 ] = 4;
        graph[ 2 ][ 4 ] = 2;
        graph[ 2 ][ 7 ] = 2;
        graph[ 1 ][ 7 ] = 3;
        graph[ 4 ][ 7 ] = 13;
        int rt = allEdgepath( 0, 7 );
        System.out.println( rt );
    }

    private static int[][] graph = new int[ 8 ][ 8 ];


    private static int allEdgepath( int u, int v ){
        print( ">>fromU(" + u + ")--toV(" + v + ")>>" );
        depth.push( " . " );

        int rt = 0;
        final int[] fromU = graph[ u ];
        if( fromU[ v ] > 0 ){
            rt = 1;
        }
        else{
            for( int i = 0; i < fromU.length; i++ ){
                if( i == u ){
                    continue;
                }
                final int u2i = fromU[ i ];
                if( u2i == 0 ){
                    continue;
                }
                int chrt = allEdgepath( i, v );
                print( "u2i[" + u + "]->[" + i + "]=" + u2i + "; chrt->" + chrt + "; rt:" + rt );

                if( chrt == 0 ){
                    continue;
                }
                rt += chrt;
            }
        }

        depth.pop();
        print( "<<fromU(" + u + ")--toV(" + v + ")<<:rt:" + rt );
        return rt;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + a.toString() );
    }

}
