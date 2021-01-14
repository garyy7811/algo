import java.util.*;
import java.util.stream.Collectors;

/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class CoinChangingBottomUp2{

    public static void main( String[] args ){

        Set<Map<Integer, Integer>> setOfCoin2Amt = getAllCombinationsOfCoins( 10, new int[]{ 2, 3, 5, 7 } );

        System.out.println( setOfCoin2Amt );


    }

    static HashMap<Integer, Set<Map<Integer, Integer>>> totalMapToCoin2AmtSet = new HashMap<>();

    private static Set<Map<Integer, Integer>> getAllCombinationsOfCoins( final int total, final int[] coins ){

        for( int coin : coins ){
            final HashSet<Map<Integer, Integer>> coin2AmtSet = new HashSet<>();
            coin2AmtSet.add( Collections.singletonMap( coin, 1 ) );
            totalMapToCoin2AmtSet.put( coin, coin2AmtSet );

            for( int nextCoin = coin + 1; nextCoin <= total; nextCoin++ ){
                int incr = nextCoin - coin;
                Set<HashMap<Integer, Integer>> incrCoin2AmtSet = copySet( totalMapToCoin2AmtSet.get( incr ) );
                if( incrCoin2AmtSet == null ){
                    continue;
                }
                incrCoin2AmtSet.forEach( coint2AMT -> {
                    coint2AMT.put( coin, coint2AMT.getOrDefault( coin, 0 ) + 1 );
                } );
                Set<Map<Integer, Integer>> jSet = totalMapToCoin2AmtSet.getOrDefault( nextCoin, new HashSet<>() );
                jSet.addAll( incrCoin2AmtSet );
                totalMapToCoin2AmtSet.put( nextCoin, jSet );
            }
            System.out.println( "done>>" + coin);
        }


        Set<Map<Integer, Integer>> rt = totalMapToCoin2AmtSet.get( total );
        return rt;
    }

    private static Set<HashMap<Integer, Integer>> copySet( Set<Map<Integer, Integer>> hashMaps ){
        if( hashMaps == null ){
            return null;
        }
        return hashMaps.stream().map( i -> copyMap( i ) ).collect( Collectors.toSet() );
    }

    private static HashMap<Integer, Integer> copyMap( Map<Integer, Integer> i ){
        final HashMap<Integer, Integer> rt = new HashMap<>();
        i.forEach( ( key, value ) -> rt.put( key, value ) );
        return rt;
    }


}
