import java.util.*;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class WordLadder{

    public static void main( String[] args ){


        bst( false );
        System.out.println( "-------" );
        bst( true);
        System.out.println( "-------" );
        System.out.println( "-------" );
        dst( false );
        System.out.println( "-------" );
        dst( true );


    }

    private static void bst( boolean rmv){
        List<String> wordLst = new LinkedList<>( Arrays.asList( wordArr ) );

        LinkedList<Node> queue = new LinkedList<>();

        final Node root = new Node( beginWord );
        queue.add( root );

        while( queue.size() > 0 ){
            final Node n = queue.removeLast();
            List<String> nexts = findNexts( wordLst, n.value, rmv );
            Node nextNode = null;
            for( int i = 0; i < nexts.size(); i++ ){
                String next = nexts.get( i );
                if( n.contains(  next )){
                    continue;
                }
                nextNode = new Node( next, n );
                n.nexts.add( nextNode );

                queue.addFirst( nextNode );
            }
            if( nextNode == null ){
                n.printUp();
            }
        }
    }
    private static void dst( boolean rmv ){
        List<String> wordLst = new LinkedList<>( Arrays.asList( wordArr ) );
        final Node root = new Node( beginWord );
        goDeep( root, wordLst, rmv );
    }

    private static void goDeep( Node node, List<String> wordLst, boolean rmv ){

        List<String> nexts = findNexts( wordLst, node.value, rmv );
        Node nextNode = null;
        for( int i = 0; i < nexts.size(); i++ ){
            String next = nexts.get( i );
            if( node.contains(  next )){
                continue;
            }
            nextNode = new Node( next, node );
            node.nexts.add( nextNode );
            goDeep( nextNode, wordLst, rmv );
        }
        if( nextNode == null ){
            node.printUp();
        }


    }



    private static List<String> findNexts( List<String> wordsLst, String target, boolean rmv ){

        List<String> lst = new LinkedList<>();
        for( int i = wordsLst.size() - 1; i >= 0; i-- ){
            String w = wordsLst.get( i );
            int dif = 0;
            for( int j = 0; j < w.length(); j++ ){
                if( w.charAt( j ) != target.charAt( j ) ){
                    dif++;
                }
            }
            if( dif == 1 ){
                lst.add( w );
                if( rmv ){
                    wordsLst.remove( i );
                }
            }
        }
        return lst;
    }


    private static final String[] wordArr   = { "hot", "dot", "dog", "lot", "log", "cog" };
    private static final String   beginWord = "hit";
    private static final String   endWord   = "cog";

    static class Node{
        Node parent;
        String value;
        List<Node> nexts = new LinkedList<>();

        public Node( String v ){
            value = v;
        }

        public Node( String next, Node n ){
            value = next;
            parent = n;
        }

        boolean contains( String s ){
            Node tmp = this;
            while( tmp != null ){
                if( s.equals( tmp.value) ){
                    return true;
                }
                tmp = tmp.parent;
            }
            return false;
        }
        void printUp(){
            LinkedList<String> lst = new LinkedList<>(  );
            Node tmp = this;
            while( tmp != null ){
                lst.addFirst( tmp.value );
                tmp = tmp.parent;
            }

            System.out.println( lst );
        }
    }


}
