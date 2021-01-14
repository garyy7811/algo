import java.util.Arrays;

public class CesllStateAfterNdays{

    public static void main( String[] arges ){

        System.out.println( Arrays.toString( transform( new int[]{ 1, 0, 0, 0, 0, 1, 0, 0 }, 1 ) ) );
        System.out.println( Arrays.toString( transform( new int[]{ 1, 1, 1, 0, 1, 1, 1, 1 }, 2 ) ) );
    }

    private static int[] transform( int[] cells, int n ){
        if( cells.length < 2 ){
            throw new IllegalArgumentException( "len < 2: " + cells.length );
        }

        int[] rt = null;
        for( int d = 0; d < n; d++ ){
            rt = new int[ cells.length ];
            for( int i = 0; i < cells.length; i++ ){
                if( i == 0 ){
                    rt[ 0 ] = ( cells[ 1 ] == 0 ? 0 : 1 );
                }
                else if( i == cells.length - 1 ){
                    rt[ i ] = ( cells[ cells.length - 2 ] == 0 ? 0 : 1 );
                }
                else{
                    rt[ i ] = ( cells[ i - 1 ] == cells[ i + 1 ] ? 0 : 1 );
                }
            }
            cells = rt;
        }

        return rt;
    }
}
