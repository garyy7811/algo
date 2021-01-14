import java.util.*;
import java.util.stream.Collectors;

public class ListPrimeNums{

    public static void main( String[] args ){

        final int max = 2000;
        List<Integer> numLst = new ArrayList<>();
        for( int i = 2; i < max; i++ ){
            numLst.add( i );
        }

        int idx = 0;
        while( true ){
            if( idx >= numLst.size() - 1 ){
                break;
            }
            int s = numLst.get( idx );

            int t = 2;
            while( s * t < max ){
                numLst.remove( Integer.valueOf( s * t ) );
                t++;
            }
            idx++;
        }

        String rt = numLst.stream().map( Objects::toString ).collect( Collectors.joining( ", " ) );

        System.out.println( rt );


    }
}
