import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 5/15/2018
 */
public class KedgesPath{


    public static void main( String[] args ){
        for( int i = 0; i < graph.length; i++ ){
            int[] ints = graph[ i ];
            for( int j = 0; j < ints.length; j++ ){
                ints[ j ] = Integer.MAX_VALUE;
            }
        }
        graph[ 0 ][ 1 ] = 10;
        graph[ 0 ][ 2 ] = 3;
        graph[ 0 ][ 3 ] = 2;
        graph[ 1 ][ 3 ] = 3;
        graph[ 2 ][ 3 ] = 4;
        graph[ 2 ][ 4 ] = 2;
        graph[ 2 ][ 7 ] = 2;
        graph[ 1 ][ 7 ] = 3;
        int rt = kEdgepath( 0, 7, 2 );
        System.out.println( rt );
    }

    private static int[][] graph = new int[ 8 ][ 8 ];


    private static int kEdgepath( int u, int v, int stepLeft ){
        print( ">>fromU(" + u + ")--toV(" + v + ")>>k>>" + stepLeft );
        depth.push( " . " );

        int rt = Integer.MAX_VALUE;
        if( graph[ u ][ v ] < Integer.MAX_VALUE && stepLeft == 1 ){
            rt = graph[ u ][ v ];
        }
        if( stepLeft > 0 ){
            final int[] fromU = graph[ u ];
            for( int i = 0; i < fromU.length; i++ ){
                if( i == u || i == v ){
                    continue;
                }
                final int cw = fromU[ i ];
                int chrt = kEdgepath( i, v, stepLeft - 1 );
                print( "cw[" + u + "]->[" + i + "]=" + cw + "; chrt->" + chrt + "; rt:" + rt);

                if( chrt == Integer.MAX_VALUE || cw == Integer.MAX_VALUE ){
                    continue;
                }
                rt = Math.min( rt, chrt + cw );
            }
        }

        depth.pop();
        print( "<<fromU(" + u + ")--toV(" + v + ")<<k<<" + stepLeft + ":rt:" + rt );
        return rt;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + a.toString() );
    }

}
