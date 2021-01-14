import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * User: GaryY
 * Date: 7/14/2018
 */
public class RollingHash{

    public RollingHash(){
    }

    public static void main( String[] args ){
        if( ! ecalpeResrever( "■ ■ ■ ■ \ttA\n■ ■ ■ ■■ ■ ■ ■Bt mmm\tt\nm神ABC DEF   \tt\n■ ■ ■ ■", "■ ■ ■ ■", "Æ" )
                .equals( "\tt\n" + "Æ DEF mmm\tt\n" + "m神ABC \ttA\n" + "ÆÆBt Æ" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "a", "b", "b" ).equals( "a" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "a", "a", "b" ).equals( "b" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "a", "a", "bcd" ).equals( "bcd" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "a ", "a", "bcd" ).equals( "bcd" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "a   ", "a", "bcd" ).equals( "bcd" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " a", "a", "bcd" ).equals( "bcd" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "   a", "a", "bcd" ).equals( "bcd" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "   a", "a", "" ).equals( "" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " tABt mmmmABC DEF   ", "AB", "  " ).equals( "DEF C mmmm t t" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " AB ABC DEF   ", "AB", "  " ).equals( "DEF C" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "AB ABC DEF   ", "Abc", "?" ).equals( "DEF ABC AB" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "AB ABC DEF   ", "AB", "" ).equals( "DEF C" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "AB ABC DEF   ", " A", "1234 " ).equals( "DEF BC AB1234" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " AAAAAAAA AAB BAA   ", " A", " t " ).equals( "BAA AB t AAAAAAA t" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " AAAAAAAA AAB BAA   ", "A ", "K K" ).equals( "K BAK KAAB AAAAAAAK" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " AAAAAAAA AAB BAA   ", "AA", "a" ).equals( "Ba aB aaaa" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( " AAAAAAAA AAAB BAAA   ", "AAA", "AAAA" ).equals( "BAAAA AAAAB AAAAAAAAAA" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "I Work.", "Work", "Play" ).equals( "Play. I" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "Tests are the best!", "the best!", "just ok." ).equals( "ok. just are Tests" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "AAA AAB BAA", "AA", "a" ).equals( "Ba aB aA" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
        if( ! ecalpeResrever( "I like cats", "cat", "dog" ).equals( "dogs like I" ) ){
            throw new RuntimeException( "TEST FAILED!" );
        }
    }

    private static final int  RADIX       = 256;
    private static final long LARGE_PRIME = BigInteger.probablePrime( 31, new Random() ).longValue();

    private static String ecalpeResrever( String text, String pattern, String replacement ){
        if( text == null || text.length() == 0 || pattern == null || pattern.length() == 0 || replacement == null ){
            throw new IllegalArgumentException( "Null or empty strings not allowed" );
        }

        final int textLen = text.length();
        final int patLen = pattern.length();
        if( patLen > textLen ){
            throw new IllegalArgumentException( "Pattern length bigger than text!" );
        }

        long LEADING_DIGIT = 1;
        for( int i = 1; i <= patLen - 1; i++ ){
            LEADING_DIGIT = ( RADIX * LEADING_DIGIT ) % LARGE_PRIME;
        }


        long patHash = hash( pattern, patLen );
        long textHash = hash( text, patLen );

        int lastAppended = - 1;
        final StringBuilder replacedStr = new StringBuilder( 1024 * 1024 * 2 );
        for( int idxToExclu = patLen; idxToExclu <= textLen; idxToExclu++ ){
            final int idxFrom = idxToExclu - patLen;

            boolean match = ( patHash == textHash );
            if( match ){
                for( int j = 0; j < pattern.length(); j++ ){
                    if( pattern.charAt( j ) != text.charAt( j + idxFrom ) ){
                        match = false;
                        break;
                    }
                }
            }
            if( idxFrom > lastAppended ){
                if( match ){
                    replacedStr.append( replacement );
                    lastAppended = idxToExclu - 1;
                }
                else{
                    replacedStr.append( text.charAt( idxFrom ) );
                    lastAppended = idxFrom;
                }
            }

            if( idxToExclu == textLen ){
                for( int j = lastAppended + 1; j < idxToExclu; j++ ){
                    replacedStr.append( text.charAt( j ) );
                }
                break;
            }
            // Remove leading digit, add trailing digit, check for match.
            textHash = ( textHash + LARGE_PRIME - LEADING_DIGIT * text.charAt( idxFrom ) % LARGE_PRIME ) % LARGE_PRIME;
            textHash = ( textHash * RADIX + text.charAt( idxToExclu ) ) % LARGE_PRIME;
        }

        LinkedList<StringBuffer> wordsStack = new LinkedList<>();
        StringBuffer wordBuffer = new StringBuffer();
        for( int i = 0; i < replacedStr.length(); i++ ){
            final char c = replacedStr.charAt( i );
            if( c == ' ' ){
                if( wordBuffer.length() > 0 ){
                    wordsStack.addLast( wordBuffer );
                    wordBuffer = new StringBuffer();
                }
            }
            else{
                wordBuffer.append( c );
            }
        }
        if( wordBuffer.length() > 0 ){
            wordsStack.addLast( wordBuffer );
        }

        StringBuilder rt = new StringBuilder();
        boolean first = true;
        while( wordsStack.size() > 0 ){
            if( first ){
                first = false;
            }
            else{
                rt.append( ' ' );
            }
            rt.append( wordsStack.removeLast() );
        }
        return rt.toString();
    }

    private static long hash( String str, int len ){
        long h = 0;
        for( int i = 0; i < len; i++ ){
            final long tmp = RADIX * h + str.charAt( i );
            h = tmp % LARGE_PRIME;
        }
        return h;
    }
}
