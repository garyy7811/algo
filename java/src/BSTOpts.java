import java.util.LinkedList;

public class BSTOpts{

    /**
     * *
     * *                                                    16
     * *                        8                                                      24
     * *            4                        12                           20                         28
     * *      2           6           10           14              18            22           26           30
     * *   1    3      5     7     9      11    13     15       17    19      21    23    25      27    29     31
     * *
     * *   1  2  3  4  5  6  7  8  9  a  b  c  d  e  f
     *
     *
     *
     * *                                                    1
     * *                         2                                                      3
     * *            4                        5                            6                         7
     * *      8            9           10          11              12            23           14           15
     * *   16   17     18     19    20    21   22      23       24    25      26    27    28      29    30     31
     *
     * *                                                    1
     * *                         2                                                      3
     * *            3                        10                            6                         7
     * *      4            7           11          11              12            23           14           15
     * *   5    6      8      9    12     13   22      23       24    25      26    27    28      29    30     31
     *
     *
     *
     *
     * @param args
     */

    public static void main( String[] args ){
        BSTOpts inst1 = new BSTOpts();
        int[] arr = new int[ 23 ];
        for( int i = 0; i < arr.length; i++ ){
            arr[ i ] = i;
        }
        inst1.nodeArr = new Node[ arr.length ];
        Node root = inst1.makeBstFromArray( arr, 0, arr.length - 1 );
        System.out.println( root );

        boolean is = inst1.checkBST( root );

        System.out.println( is );
        inst1.bfs( root );
        System.out.println( "----------------------------" );
        inst1.dfs( root );
        System.out.println( "----------------------------" );
        inst1.leftRootRight( root );
        System.out.println( "----------------------------" );
    }


    private Node makeBstFromArray( int[] arr, int fromIndex, int toIndex ){

        Node rt = new Node();

        if( toIndex == fromIndex ){
            rt.data = arr[ toIndex ];
        }
        else if( toIndex - fromIndex > 1 ){
            int midIdx = fromIndex + ( toIndex - fromIndex + 1 ) / 2;
            rt.data = arr[ midIdx ];
            rt.left = makeBstFromArray( arr, fromIndex, midIdx - 1 );
            rt.left.parent = rt;
            rt.right = makeBstFromArray( arr, midIdx + 1, toIndex );
            rt.right.parent = rt;
        }
        else if( toIndex - fromIndex == 1 ){
            rt.data = arr[ toIndex ];
            rt.left = makeBstFromArray( arr, fromIndex, fromIndex );
            rt.left.parent = rt;
        }

        return rt;
    }


    public boolean checkBST( Node root ){
        if( root == null ){
            throw new IllegalArgumentException();
        }

        return chk( root, Integer.MIN_VALUE, Integer.MAX_VALUE );
    }

    boolean chk( Node node, int min, int max ){
        boolean rt = node.data > min && node.data < max;

        if( rt ){
            boolean l = true;
            if( node.left != null ){
                l = ( node.data > node.left.data ) && chk( node.left, min, node.data );
            }

            boolean r = true;
            if( node.right != null ){
                r = node.data < node.right.data && chk( node.right, node.data, max );
            }
            rt = l && r;
        }
        return rt;
    }


    private int isPresent( Node root, int val ){
        if( root.data == val ){
            return 1;
        }

        if( val < root.data ){
            if( root.left == null ){
                return 0;
            }
            return isPresent( root.left, val );
        }


        if( root.right == null ){
            return 0;
        }
        return isPresent( root.right, val );

    }

    private void bfs( Node root ){
        LinkedList<Node> q = new LinkedList<>();
        q.add( root );
        root.depth = 0;
        while( q.size() > 0 ){
            final Node c = q.removeFirst();
            System.out.println( c.depth + " >>> " + c.data );
            if( c.left != null ){
                c.left.depth = c.depth + 1;
                q.addLast( c.left );
            }
            if( c.right != null ){
                c.right.depth = c.depth + 1;
                q.addLast( c.right );
            }
        }
    }

    private void dfs( Node n ){
        System.out.println( n.depth + ">>-->>" + n.data );
        if( n.left != null ){
            n.left.depth = n.depth + 1;
            dfs( n.left );
        }

        if( n.right != null ){
            n.right.depth = n.depth + 1;
            dfs( n.right );
        }
        if( n.depth > maxDepth ){
            maxDepth = n.depth;
        }
    }

    private Node prevNode = null;

    private Node[] nodeArr;

    private void leftRootRight( Node n ){
        if( n.left != null ){
            leftRootRight( n.left );
        }
        if( prevNode != null ){
            nodeArr[ prevNode.index ] = prevNode;
            n.index = prevNode.index + 1;
            nodeArr[ n.index ] = n;
        }
        System.out.println( "index=" + n.index + "; depth=" + n.depth + "; >>> " + n.data );/*
        if( ( Math.log( n.index + 1 ) / Math.log( 2 ) ) % 1 != 0 ){
            Node ll = nodeArr[ n.index - new Long( Math.round( Math.pow( 2, maxDepth - n.depth + 1 ) ) ).intValue() ];
        }*/

        if( n.index > maxIndex ){
            maxIndex = n.index;
        }
        prevNode = n;
        if( n.right != null ){
            leftRootRight( n.right );
        }
    }


    private Node copyNode( Node root ){
        final Node rt = new Node();
        rt.data = root.data;
        if( root.left != null ){
            rt.left = copyNode( root.left );
        }
        if( root.right != null ){
            rt.right = copyNode( root.right );
        }
        return rt;
    }

    int maxDepth;
    int maxIndex;


    class Node{
        int  data;
        Node left;
        Node right;

        Node parent;

        int depth;
        int index;
    }

    private Node findNextLeft( Node n ){
        //root
        if( n.parent == null ){
            return null;
        }

        int level = 0;

        Node tmp = n;
        //go up until tmp is right child
        while( true ){
            if( tmp == null ){
                return null;
            }
            if( tmp.parent.left == tmp ){
                tmp = tmp.parent;
                level ++;
            }
            else{
                tmp = tmp.parent.left;
                break;
            }
        }


        //go down level times
        while( level > 0 ){
            level --;
            tmp = tmp.right!=null?tmp.right:tmp.left;
            if( tmp == null ){
                return null;
            }
        }

        return tmp;
    }

}
