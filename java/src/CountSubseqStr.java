import java.math.BigInteger;
import java.util.*;

public class CountSubseqStr{


    public static void main( String[] args ){

//        final String text = "zxabcefgabcd aaalmmm";
        final String text = "02927163485928375896599383761";
        final String[] sArr = { "alm", "12", "d a", "14", "a5", "bb", "m" };

//        byTries( text, sArr );

        final long thePrimeNum = 10;
//        final long thePrimeNum = BigInteger.probablePrime( 64, new Random() ).longValue();
        search( "38727", text, thePrimeNum );


        System.out.println( "==================================" );


    }

    final static int RADIX = 10;

    static void search( String pat, String txt, long thePrimeNum ){
        int patternLen = pat.length();
        int txtLen = txt.length();
        int i, j;
        long patternHash = 0; // hash value for pattern
        long txtHash = 0; // hash value for txt

        long h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for( i = 0; i < patternLen - 1; i++ ){
            h = ( h * RADIX ) % thePrimeNum;
        }

        // Calculate the hash value of pattern and first
        // window of text
        for( i = 0; i < patternLen; i++ ){
            patternHash = ( RADIX * patternHash + pat.charAt( i ) ) % thePrimeNum;
            txtHash = ( RADIX * txtHash + txt.charAt( i ) ) % thePrimeNum;
        }

        // Slide the pattern over text one by one
        for( i = 0; i <= txtLen - patternLen; i++ ){

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if( patternHash == txtHash ){
                /* Check for characters one by one */
                for( j = 0; j < patternLen; j++ ){
                    if( txt.charAt( i + j ) != pat.charAt( j ) ){
                        break;
                    }
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if( j == patternLen ){
                    System.out.println( "Pattern found at index " + i );
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if( i < txtLen - patternLen ){
                txtHash = ( RADIX * ( txtHash - txt.charAt( i ) * h ) + txt.charAt( i + patternLen ) ) % thePrimeNum;

                // We might get negative value of t, converting it
                // to positive
                if( txtHash < 0 ){
                    txtHash = ( txtHash + thePrimeNum );
                }
            }
        }
    }


    private static void byTries( String text, String[] sArr ){
        TrNode trRoot = new TrNode();
        for( int i = 0; i < text.length(); i++ ){
            addToTries( trRoot, text, i );
        }

        for( int i = 0; i < sArr.length; i++ ){

            int rslt = findInTries( trRoot, sArr[ i ], 0 );
            System.out.println( rslt );
        }
    }

    private static int findInTries( TrNode tr, String s, int index ){
        final char currentChar = s.charAt( index );
        TrNode childTr = tr.children.get( currentChar );
        if( childTr == null ){
            return 0;
        }

        if( index + 1 >= s.length() ){
            return childTr.count;
        }

        int cc = findInTries( childTr, s, index + 1 );
        System.out.println( "aaaa>>" + childTr.count + ">>bb>>" + cc );
        return Math.min( childTr.count, cc );
    }

    private static void addToTries( TrNode tr, String charArray, int i ){
        final char currentChar = charArray.charAt( i );
        depth.push( "`" );
        print( ">>from(" + i + ")>>" + currentChar );

        TrNode currentCharNode = tr.children.get( currentChar );
        if( currentCharNode != null ){
            currentCharNode.count++;
        }
        else{
            currentCharNode = new TrNode();
            currentCharNode.count = 1;
            tr.children.put( currentChar, currentCharNode );
        }
        if( i < charArray.length() - 1 ){
            addToTries( currentCharNode, charArray, i + 1 );
        }

        print( "<<from(" + i + ")<<" + currentChar + " ... " );
        depth.pop();
    }

    public static class TrNode{
        int                              count;
        LinkedHashMap<Character, TrNode> children = new LinkedHashMap<>();
    }

    public static class RefStr{

        public RefStr( char[] value, int idxFrom, int idxTo ){
            this.value = value;
            this.idxFrom = idxFrom;
            this.idxTo = idxTo;
        }

        char[] value;
        private int idxFrom;
        private int idxTo;


        public char charAt( int i ){
            return value[ idxFrom + i ];
        }

        public int length(){
            return idxTo - idxFrom;
        }

        @Override
        public String toString(){
            return new String( value, idxFrom, idxTo - idxFrom );
        }
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + a.toString() );
    }

}
