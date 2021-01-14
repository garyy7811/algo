import java.util.*;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class RangeIntervalTree{

    public static void main( String[] args ){


        IntervalNode root = new IntervalNode();
        root.rangeFrom = 10;
        root.rangeTo = 11;
        addInterval( root, 4, 5 );
        addInterval( root, 2, 3 );
        addInterval( root, 8, 9 );
        addInterval( root, 0, 1 );
        addInterval( root, 3, 4 );
        addInterval( root, 1, 4 );
        addInterval( root, 21, 32 );
        addInterval( root, 11, 12 );
        addInterval( root, 22, 31 );
        addInterval( root, 17, 22 );
        addInterval( root, 18, 22 );
        addInterval( root, 23, 24 );
        addInterval( root, 19, 22 );
        addInterval( root, 24, 25 );

        System.out.println( root );

        List<IntervalNode> rslt = findIntervals( root, 41, 52 );
        System.out.println( rslt );
    }

    static void addInterval( IntervalNode node, long rangeFrom, long rangeTo ){
        if( rangeFrom > rangeTo ){
            throw new IllegalArgumentException( "range error" );
        }
        long max = 0;
        if( node.rangeFrom > rangeFrom ){
            if( node.left != null ){
                addInterval( node.left, rangeFrom, rangeTo );
                max = Math.max( node.left.rangeToMax, max );
            }
            else{
                node.left = new IntervalNode();
                node.left.rangeFrom = rangeFrom;
                node.left.rangeTo = rangeTo;
                max = rangeTo;
            }
        }
        else{
            if( node.right != null ){
                addInterval( node.right, rangeFrom, rangeTo );
                max = Math.max( node.right.rangeToMax, max );
            }
            else{
                node.right = new IntervalNode();
                node.right.rangeFrom = rangeFrom;
                node.right.rangeTo = rangeTo;
                max = rangeTo;
            }
        }
        node.rangeToMax = Math.max( max, node.rangeToMax );
    }

    /**
     * ----------from-----to
     * ----------from------------------to
     * ----------from----------------------------------------to
     * --------------------------------from---to
     * --------------------------------from------------------to
     * ------------------------------------------------------from-------to
     * -----------------------NodeFrom----------NodeTo ?????????max
     * --------------NodeFrom--------     -NF
     * -------NodeFrom---------NF  NodeFrom---------NF
     *
     * @param node
     * @param rangeFrom
     * @param rangeTo
     * @return
     */
    static List<IntervalNode> findIntervals( IntervalNode node, final long rangeFrom, final long rangeTo ){
        List<IntervalNode> rt = null;
        if( node.rangeFrom > rangeTo || rangeFrom > node.rangeTo ){//no intersection
            if( node.rangeToMax > rangeFrom && node.right != null ){
                rt = findIntervals( node.right, rangeFrom, rangeTo );
            }
            else{
                rt = new LinkedList<>();
            }
        }
        else{
            if( node.left != null ){
                rt = findIntervals( node.left, rangeFrom, rangeTo );
            }
            else if( node.right != null ){
                rt = findIntervals( node.right, rangeFrom, rangeTo );
            }
            else{
                rt = new LinkedList<>();
            }
            rt.add( node );
        }

        return rt;
    }


    static class IntervalNode{

        long rangeFrom;
        long rangeTo;
        long rangeToMax;

        IntervalNode left;
        IntervalNode right;
    }

}
