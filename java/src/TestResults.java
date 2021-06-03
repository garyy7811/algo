import java.util.*;
import java.util.stream.Collectors;

public class TestResults{

    public static void main( String[] args ){


        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        treeMap.put( 1, 13 );
        treeMap.put( 9, 93 );
        treeMap.put( 8, 83 );
        treeMap.put( 5, 53 );
        treeMap.put( 7, 73 );

        for( Integer i : treeMap.navigableKeySet() ){
            System.out.println( i );
        }

        Set<Map.Entry<Integer, Integer>> entries = treeMap.entrySet();
        entries.iterator().forEachRemaining( e->{
            System.out.println( e );
        } );

        treeMap.forEach( ( key, value ) -> {
        } );

        int[] ints = new int[]{};

        StringBuilder ss = new StringBuilder();
    }

    private static void intervalTree(){
        class Node{
            Node( int f, int t, int m ){
                from = f;
                to = t;
                max = m;
            }

            Node left;
            Node right;
            int from;
            int to;
            int max;

            void addRange( int f, int t ){
                if( f > from ){
                    if( right != null ){
                        right.addRange( f, t );
                    }
                    else{
                        right = new Node( f, t, t );
                    }
                    max = Math.max( max, right.max );
                }
                else if( f < from ){
                    if( left != null ){
                        left.addRange( f, t );
                    }
                    else{
                        left = new Node( f, t, t );
                    }
                    max = Math.max( max, left.max );
                }
            }

            /**
             * -------------[ this ]--------)-----
             * -----{---}----------------------------done
             *
             * -----{----------}---------------------done
             * -----{-------------------}------------done
             * -----{-----------------------------}--done
             *
             * --------------{---}--------------------------
             * --------------{--------}--------------------------
             * --------------{-------------------}--------------------------
             * ----------------------{----}-----------------
             * ----------------------{-------------}-----------------
             * ----------------------------------{--}-----------------
             * @param f
             * @param t
             * @return
             */

            List<Node> find( int f, int t ){
                if( f > from && t < to ){
                    return List.of( this );
                }
                if( t < from && left != null ){
                    if( left.max > f )
                        return left.find( f, t );
                    else {
                        return null;
                    }
                }
                if( from > to && right != null ){
                    return right.find( f, t );
                }
                List<Node> rt = new ArrayList<>();
                if( f < from && left != null ){
                    rt.addAll( left.find( f, t ) );
                }
                if( t > to && right != null ){
                    rt.addAll( right.find( f, t ) );
                }
                rt.add( this );
                return rt;
            }
        }



    }

    private static void maxContinues( int[] ints ){

        Map<String, Integer> cache = new HashMap<>();
        int rslt = Integer.MIN_VALUE;
        for( int i = 0; i < ints.length; i++ ){
            for( int j = 0; j < ints.length; j++ ){
                String key = i + "," + j;
                System.out.println( key );
                int sum = 0;
                if( cache.containsKey( key ) ){
                    System.out.println( "cache hit" );
                    sum = cache.get( key );
                }
                else{
                    for( int k = j; k >= i; k-- ){
                        sum += ints[ k ];
                        cache.put( k + "," + j, sum );

                        System.out.println( "cache put:" + k + "," + j );
                    }
                }
                rslt = Math.max( rslt, sum );
            }
        }
    }

    private static String evalStr( String s ){
        class Node{
            Node parent;
            List<Node> children;
            String val;

            public Node(){
                children = new ArrayList<>();
            }

            public Node( String ea ){
                val = ea;
            }

            public String eval(){
                if( val != null ){
                    return val;
                }
                List<String> lst = this.children.stream().map( Node::eval ).collect( Collectors.toList() );

                int i = 0;
                while( true ){
                    String nv = null;
                    if( lst.get( i ).equals( lst.get( i + 2 ) ) ){
                        nv = lst.get( i + 1 );
                    }
                    else{
                        if( lst.get( i + 1 ).equals( "&&" ) ){
                            nv = "false";
                        }
                        nv = "true";
                    }
                    i = i + 2;
                    lst.set( i, nv );
                    if( i >= lst.size() - 1 ){
                        return nv;
                    }
                }
            }
        }
        Node root = new Node();
        Node current = root;
        for( String ea: s.split( " " ) ){
            if( ea.equals( "(" ) ){
                Node nn = new Node();
                current.children.add( nn );
                nn.parent = current;
                current = nn;
            }
            else if( ea.equals( ")" ) ){
                current = current.parent;
            }
            else{
                current.children.add( new Node( ea ) );
            }
        }

        return root.eval();
    }

    static int rt = 0;

    private static int findMin( int weight, int[] sizes ){
        System.out.println( "w>>" + weight );
        for( int i = 0; i < sizes.length; i++ ){
            if( sizes[ i ] <= weight ){
                if( sizes[ i ] == weight ){
                    return 1;
                }
                int tmp = findMin( weight - sizes[ i ], sizes );
                rt++;
                return rt;
            }
        }
        return - 1;
    }


}
