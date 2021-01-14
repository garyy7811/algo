import java.util.Arrays;
import java.util.List;

public class SubtreeWithMaxAve{

    public static void main( String[] args ){
        Node root = new Node( 1, Arrays.asList( new Node( - 5, Arrays.asList( new Node( 1 ), new Node( 2 ) ) ), new Node( 13, Arrays.asList( new Node( 4 ), new Node( - 2 ) ) ), new Node( 4 ) ) );


        Rslt rt = calcChildrenSumCountMaxAve( root );
        System.out.println( rt );

        root = new Node( 1, Arrays.asList( new Node( - 1 ), new Node( 21 ), new Node( 5 ), new Node( - 1 ) ) );
        rt = calcChildrenSumCountMaxAve( root );
        System.out.println( rt );
    }


    private static Rslt calcChildrenSumCountMaxAve( Node node ){
        Rslt rt = new Rslt();
        rt.count = 1;
        rt.ave = node.value;
        rt.sum = node.value;

        if( node.children == null ){
            rt.maxAve = node.value;
            return rt;
        }

        double maxChildAv = Double.MIN_VALUE;
        for( Node n: node.children ){
            Rslt nRst = calcChildrenSumCountMaxAve( n );
            rt.count += nRst.count;
            rt.sum += nRst.sum;
            maxChildAv = Math.max( nRst.maxAve, maxChildAv );
        }

        rt.ave = ( double )rt.sum / rt.count;
        rt.maxAve = Math.max( rt.ave, maxChildAv );

        return rt;
    }

    private static class Rslt{
        int sum;
        int count;
        double ave;

        double maxAve;
    }

    private static class Node{
        public Node( int value ){
            this.value = value;
        }

        private Node( int v, List<Node> children ){
            value = v;
            this.children = children;
        }

        List<Node> children;



        int value;
    }

}
