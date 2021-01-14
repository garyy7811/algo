import java.util.HashSet;

public class SquareToOne{


    public static void main( String[] args ){

        System.out.println( doitnow( 17, new HashSet<>() ) );
    }


    private static <E> boolean doitnow( Integer n, HashSet<HashSet<Character>> already ){
        if( n == 1 ){
            return true;
        }
        if( n < 1 ){
            throw new IllegalArgumentException( "no less than 2" );
        }

        final String sOfn = n.toString();
        final HashSet<Character> ssofn = new HashSet<>(  );
        for( int i = 0; i < sOfn.length(); i++ ){
            ssofn.add( sOfn.charAt( i ) );
        }
        if( already.contains( ssofn ) ){
            return false;
        }

        System.out.println( "doitnow>>>>" + n );

        already.add( ssofn );

        String[] arr = sOfn.split( "" );

        int sum = 0;
        for( String s : arr ){
            int eachI = Integer.parseInt( s );
            sum = eachI * eachI;
        }

        return doitnow( sum, already );
    }
}
