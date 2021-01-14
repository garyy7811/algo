import java.util.*;
import java.util.stream.Collectors;

public class SubStringWithExactlyKDistinctChars{
    private static final SubStringWithExactlyKDistinctChars instance = new SubStringWithExactlyKDistinctChars();

    public static void main( String[] args ){
        verify( resovle( "aa", 2 ), new String[]{ "a", "a", "aa" } );
        verify( resovle( "abc", 2 ), new String[]{ "ab", "bc" } );
        verify( resovle( "aba", 2 ), new String[]{ "ab", "aba", "ba" } );
    }

    private static void verify( String[] rslt, String[] expect ){
        for( int i = 0; i < rslt.length; i++ ){
            String s = rslt[ i ];
            if( ! s.equals( expect[ i ] ) ){
                throw new RuntimeException( s + "<-->" + expect[ i ] );
            }

        }
    }

    private static String[] resovle( final String s, final int k ){

        LinkedList<Character> lst = new LinkedList<>();
        HashMap<Character, Integer> char2count = new HashMap<>();

        TreeSet<String> rt = new TreeSet<>();
        int idx = 0;
        while( true ){
            if( char2count.size() > k || ( char2count.size() == k && idx == s.length() ) ){
                Character rm = lst.removeFirst();
                changeCount( char2count, rm, - 1 );
            }
            else if( idx < s.length() ){
                char tmp = s.charAt( idx );
                lst.addLast( tmp );
                changeCount( char2count, tmp, 1 );
                idx++;
            }
            else{
                break;
            }

            if( char2count.size() == k ){
                rt.add( lst.stream().map( c -> String.valueOf( c ) ).collect( Collectors.joining() ) );
            }
        }

        return rt.toArray( new String[ rt.size() ] );

    }

    private static void changeCount( HashMap<Character, Integer> char2count, Character ch, int change ){
        Integer old = char2count.computeIfAbsent( ch, i -> 0 );
        int newVal = old + change;
        if( newVal == 0 ){
            char2count.remove( ch );
            return;
        }
        char2count.put( ch, newVal );
    }

}
