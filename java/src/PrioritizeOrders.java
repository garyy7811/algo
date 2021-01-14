import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrioritizeOrders{

    public static void main( String[] args ){
        List<String> ls = sortOrders( Arrays.asList(
                "Zid 93 12",
                "fp kindle book",
                "IOa echo show",
                "17g 12 256",
                "abl kindle book",
                "125 echo dot second generation" ) );

        System.out.println( ls );
    }

    private static List<String> sortOrders( List<String> asList ){
        return asList.stream().map( s -> {
            int si = s.indexOf( " " );
            return new String[]{ s.substring( 0, si ), s.substring( si + 1 ) };
        } ).sorted( ( a, b ) -> {
            char ca = a[ 1 ].charAt( 0 );
            char cb = b[ 1 ].charAt( 0 );
            boolean aIsP = ca >= 'a' && ca <= 'z';
            boolean bIsP = cb >= 'a' && cb <= 'z';
            if( aIsP && bIsP ){//both prime
                int cmp = a[ 1 ].compareTo( b[ 1 ] );
                if( cmp == 0 ){
                    return a[ 0 ].compareTo( b[ 0 ] );
                }
                return cmp;
            }
            else if( aIsP && ! bIsP ){
                return - 1;
            }
            else if( ! aIsP && bIsP ){
                return 1;
            }
            return 0;
        } ).map( sa -> {
            return sa[ 0 ] + sa[ 1 ];
        } ).collect( Collectors.toList() );
    }
}
