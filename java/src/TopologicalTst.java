import java.util.*;

public class TopologicalTst{

    public static void main( String[] args ){

        /*
         *   0     1
         *   |\  / |
         *   | 4   3
         *   |  \ | \
         *   |   2   \
         *   | /   \  \
         *   5       \ \
         *             6
         * */

        Node n0 = new Node( "0", null );
        Node n1 = new Node( "1", null );
        Node n3 = new Node( "3", n1 );
        Node n4 = new Node( "4", n1, n0 );
        Node n2 = new Node( "2", n3, n4 );
        Node n5 = new Node( "5", n2, n0 );
        Node n6 = new Node( "6", n2, n3 );

        //n3---n5
//        n3.dependencies.add( n5 );

        Set<List<Node>> paths;
        ArrayList<Node> reslt = new ArrayList<>();
//        paths = dfss( n2, null, reslt );
//        paths = dfss( n5, null, reslt );
        paths = dfss( n6, null, reslt );


        System.out.println( paths + "--" + reslt );


    }

    private static Set<Node> visited = new HashSet<>();

    private static Set<List<Node>> dfss( Node n, LinkedList<Node> stack, List<Node> reslt ){
        System.out.println( "::" + n.name );
        if( stack != null && stack.contains( n ) ){
            throw new Error( "loop!" );
        }
        if( visited.contains( n ) ){
            System.out.println( "visited ..." );
            return Set.of( new ArrayList<>( stack ) );
        }
        visited.add( n );

        if( stack == null ){
            stack = new LinkedList<>();
        }
        stack.addFirst( n );
        Set<List<Node>> pathes = new HashSet<>();
        if( n.dependencies != null ){
            for( Node dependency: n.dependencies ){
                pathes.addAll( dfss( dependency, stack, reslt ) );
            }
        }
        else{
            pathes.add( new ArrayList<>( stack ) );
        }
        if( stack.removeFirst() != n ){
            System.out.println( "ppppppppppppppp" );
        }

        System.out.println( ">>>>>>>>>>" + n.name );
        reslt.add( n );
        return pathes;
    }

    private static class Node{
        static Set<Node> allNodes = new HashSet<>();

        private Node( String name, Node... dependons ){
            this.name = name;
            if( dependons != null ){
                dependencies = new HashSet<>( Arrays.asList( dependons ) );
            }
            allNodes.add( this );
        }

        public String name;
        public Set<Node> dependencies;

    }

}
