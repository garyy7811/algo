import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra{

    private static class Node{
        final int id;
        int cost = - 1;
        public Map<Node, Integer> nexts = new HashMap<>();

        public Node( int id ){
            this.id = id;
        }

        @Override
        public boolean equals( Object o ){
            if( this == o ) return true;
            if( o == null || getClass() != o.getClass() ) return false;
            Node node = ( Node )o;
            return id == node.id;
        }

        @Override
        public int hashCode(){
            return Objects.hash( id );
        }

        @Override
        public String toString(){
            return "{\n" +
                    "id=" + id +
                    ", nexts=" + nexts.entrySet().stream().map( nk -> nk.getKey().id + "->" + nk.getValue() ).collect( Collectors.joining( "," ) ) +
                    "\n}\n";
        }
    }

    public static void main( String[] args ){

        addEdge( 0, 1, 4 );
        addEdge( 0, 7, 8 );
        addEdge( 1, 2, 8 );
        addEdge( 1, 7, 11 );
        addEdge( 2, 3, 7 );
        addEdge( 2, 8, 2 );
        addEdge( 2, 5, 4 );
        addEdge( 3, 4, 9 );
        addEdge( 3, 5, 14 );
        addEdge( 4, 5, 10 );
        addEdge( 5, 6, 2 );
        addEdge( 6, 7, 1 );
        addEdge( 6, 8, 6 );
        addEdge( 7, 8, 7 );
//        addEdge( 8, 1, 7 );

        for( Integer id: idMap.keySet() ){
            idToNode.put( id, new Node( id ) );
        }

        for( Node node: idToNode.values() ){
            wireNode( node );
        }

        System.out.println( idToNode );

        dijkstraFrom( idToNode.get( 0 ) );
    }

    private static void dijkstraFrom( Node pNode ){
        class Edge{
            public Edge( Node from, Node to, Integer val ){
                this.from = from;
                this.to = to;
                this.val = val;
            }

            Node from;
            Node to;
            Integer val;

            @Override
            public String toString(){
                return "Edge{" +
                        "from=" + from.id +
                        ", to=" + to.id +
                        ", val=" + val +
                        '}';
            }
        }
        pNode.cost = 0;

        Node currentNode = pNode;

        PriorityQueue<Edge> heap = new PriorityQueue<>( ( a, b ) -> {
            return a.val.compareTo( b.val );
        } );
        Map<Node, Set<Edge>> fromEdges = new HashMap<>();
        while( true ){
            for( Map.Entry<Node, Integer> nxtEtr: currentNode.nexts.entrySet() ){
                if( nxtEtr.getKey().cost < 0 ){
                    Edge edge = new Edge( currentNode, nxtEtr.getKey(), nxtEtr.getValue() + currentNode.cost );
                    heap.add( edge );
                    fromEdges.computeIfAbsent( nxtEtr.getKey(), k -> new HashSet<>() ).add( edge );
                }
            }

            Edge ne = heap.poll();

            Set<Edge> edges = fromEdges.get( currentNode );
            if( edges != null )
                edges.forEach( heap::remove );
            currentNode = ne.to;
            currentNode.cost = ne.val;
            if( heap.size() == 0 ){
                break;
            }

            System.out.println( heap );
            System.out.println( "edge:" + ne + "; current(" + currentNode.id + "),cost:" + currentNode.cost );
            System.out.println( "--------");


        }

        System.out.println( "done" );

    }

    private static void wireNode( Node node ){
        idMap.get( node.id ).forEach( ( t, w ) -> node.nexts.put( idToNode.get( t ), w ) );
    }

    private static Map<Integer, Node> idToNode = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> idMap = new HashMap<>();

    private static void addEdge( int from, int to, int weight ){
        idMap.computeIfAbsent( from, id -> new HashMap<>() ).put( to, weight );
        idMap.computeIfAbsent( to, id -> new HashMap<>() );
    }
}
