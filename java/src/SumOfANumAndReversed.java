import java.util.*;

public class SumOfANumAndReversed{

    public static void main( String[] a ){


        long time = System.currentTimeMillis();
        long sum = 0;
        int start = 0;
        final int end = 999999999;
        HashSet<Long> set = new HashSet<>();
        for( int i = start; i < Integer.MAX_VALUE; i++ ){
            int reversed = Integer.parseInt( new StringBuilder( Integer.toString( i ) ).reverse().toString() );
            sum = i + reversed;
            if( sum > end ){
                break;
            }
            set.add( sum );
        }

        long cost = System.currentTimeMillis() - time;
        System.out.println( "String[" + start + "==>>>" + end + "]>>sum>>" + sum + ">>ss>>" + set.size() + "time>" + cost );

        limit = set.size();
        time = System.currentTimeMillis();

        set = new HashSet<>();

        for( int i = start; i < Integer.MAX_VALUE; i++ ){

            int reversed = 0;
            if( i >= 10 ){
                int f = i;
                while( f > 0 ){
                    int m = f % 10;
                    if( f >= 10 ){
                        f = f / 10;
                    }
                    else{
                        f = 0;
                    }
                    if( reversed > 0 ){
                        reversed *= 10;
                    }
                    reversed += m;
                }
            }
            else{
                reversed = i;
            }
            sum = i + reversed;
            if( sum > end ){
                break;
            }
            set.add( sum );
        }

        cost = System.currentTimeMillis() - time;
        System.out.println( "Math[" + start + "==>>>" + end + "]>>sum>>" + sum + ">>ss>>" + set.size() + "time>" + cost );



        /*
         * 10000*a+ 1000*b+ 100*c + 10*d + e
         * 10000*e+ 1000*d+ 100*c + 10*b + a
         *
         * 10001*a+ 1010*b+ 200*c + 1010*b + 10001*e
         * */
        int len = 10;

        long[] tenArr = new long[ len ];
        tenArr[ 0 ] = 1;
        for( int i = 1; i < len; i++ ){
            tenArr[ i ] = tenArr[ i - 1 ] * 10;
        }

        long[] eighteenArr = new long[ len ];

        for( int i = 0; i < len; i++ ){
            eighteenArr[ i ] = tenArr[ i ] + tenArr[ len - i - 1 ];
        }

        HashSet<Long> rslt = treeWalk( 0, eighteenArr );

        cost = System.currentTimeMillis() - time;
        System.out.println( "Recursive:::" + rslt.size() + "@@@time>" + cost );


    }

    static int limit;

    private static HashSet<Long> treeWalk( int position, long[] eighteenArr ){
        HashSet<Long> theMap;
        long currentVal = eighteenArr[ position ];
        if( position == eighteenArr.length - 1 ){
            theMap = new HashSet<>();
            for( int i = 0; i < 19; i++ ){
                long baseI = i * currentVal;
                theMap.add( baseI );
            }
        }
        else{
            theMap = treeWalk( position + 1, eighteenArr );

            HashSet<Long> prevSumKeySet = new HashSet( theMap );
            for( int i = 0; i < 19; i++ ){
                long newVal = currentVal * i;
                for( Long prevSumKey: prevSumKeySet ){
                    theMap.add( newVal + prevSumKey );
                    if( theMap.size() > limit ){
                        return theMap;
                    }
                }
            }
        }
        return theMap;
    }


}
