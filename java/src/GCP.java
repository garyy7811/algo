public class GCP{

    public static void main( String[] args ){
        long rslt = gcd( 111111L, 147L );
        System.out.println( "result:" + rslt );
    }

    private static long gcd( long a, long b ){

        System.out.println( "gcp>>" + a + ">>" + b );

        if( a == 0 ){
            return b;
        }
        if( b == 0 ){
            return a;
        }
        if( a == b ){
            return a;
        }
        long lg = Math.max( a, b );
        long sm = Math.min( a, b );

        return gcd( lg % sm, sm );
    }
}
