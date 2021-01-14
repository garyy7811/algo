import java.util.HashMap;

public class TriesContacts{


    public static void main( String[] args ){


        String op = "add";
        String contact = "hack";



        add( tries, "hack".toCharArray(), 0 );
        add( tries, "hackerrank".toCharArray(), 0 );
        System.out.println( find( tries, "hack".toCharArray(), 0 ) );
        System.out.println( find( tries, "hak".toCharArray(), 0 ) );
    }

    private static int find( HashMap<Character, Node> char2tree, char[] str, int position ){
        char currentChar = str[ position ];
        Node n = char2tree.get( currentChar );
        if( n == null ){
            return 0;
        }
        int currentCount = n.count;
        final int nextPosi = position + 1;
        if( nextPosi >= str.length ){
            return currentCount;
        }
        int childCount = find( n.tries, str, nextPosi );

        return Math.min( currentCount, childCount );
    }


    static HashMap<Character, Node> tries = new HashMap<>();

    static void add( HashMap<Character, Node> char2tree, char[] str, int position ){
        if( position >= str.length ){
            return;
        }
        char currentChar = str[ position ];
        Node n = char2tree.getOrDefault( currentChar, new Node() );

        if( n.count == 0 ){

            char2tree.put( currentChar, n );
        }

        n.count++;

        add( n.tries, str, ++ position );
    }


    static class Node{
        int                      count = 0;
        HashMap<Character, Node> tries = new HashMap<>();
    }
}
