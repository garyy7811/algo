import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptimizeMemUsage{

    public static void main( String[] args ){
        System.out.println( Stream.of( optimize( new int[]{ 1, 7, 2, 4, 5, 6 }, new int[]{ 3, 1, 2 }, 6 ) ).map( it -> "[" + Arrays.toString( it ) + "]" ).collect( Collectors.toList() ) );
        System.out.println( Stream.of( optimize( new int[]{ 1, 7, 2, 4, 5, 6 }, new int[]{ 3, 1, 2 }, 10 ) ).map( it -> "[" + Arrays.toString( it ) + "]" ).collect( Collectors.toList() ) );
    }

    private static int[][] optimize( int[] foregroundTasks, int[] backgroundTasks, int total ){
        Arrays.sort( foregroundTasks );
        Arrays.sort( backgroundTasks );

        int foreIdx = 0;
        int backIdx = backgroundTasks.length - 1;
        List<int[]> rslt = new ArrayList<>();
        while( foreIdx < foregroundTasks.length && backIdx >= 0 ){
            int foreVal = foregroundTasks[ foreIdx ];
            int backVal = backgroundTasks[ backIdx ];
            if( foreVal + backVal >= total ){
                backIdx--;
            }
            else{
                if( rslt.size() == 0 ){
                    rslt.add( new int[]{ foreVal, backVal } );
                }
                else{
                    int[] sample = rslt.get( 0 );
                    if( sample[ 0 ] + sample[ 1 ] < foreVal + backVal ){
                        rslt = new ArrayList<>();
                        rslt.add( new int[]{ foreVal, backVal } );
                    }
                    else if( sample[ 0 ] + sample[ 1 ] == foreVal + backVal ){
                        rslt.add( new int[]{ foreVal, backVal } );
                    }
                }
                foreIdx++;
            }
        }
        return rslt.toArray( new int[ rslt.size() ][] );
    }
}
