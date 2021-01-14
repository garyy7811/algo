import java.util.Arrays;
import java.util.PriorityQueue;

public class ConnectRopes{
    public static void main( String[] args ){
        connectRopes( new int[]{ 8, 4, 6, 12 } );
        connectRopes( new int[]{ 2, 2, 3, 3 } );
        connectRopes( new int[]{ 1, 2, 5, 10, 35, 89 } );
        connectRopes( new int[]{ 20, 4, 8, 2 } );
    }

    private static int connectRopes( int[] ropes ){

        if( ropes.length < 2 ){
            return ropes[ 0 ];
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for( int r: ropes ){
            q.add( r );
        }

        int cost = 0;
        while( true ){
            Integer tmp1 = q.poll();
            Integer tmp2 = q.poll();
            cost += tmp1;
            cost += tmp2;
            if( q.isEmpty() ){
                break;
            }
            q.add( tmp1 + tmp2 );
        }
        System.out.println( Arrays.toString( ropes ) + ">>>" + cost );
        return cost;
    }
}
