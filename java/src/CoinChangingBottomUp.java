import java.io.*;
import java.util.*;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class CoinChangingBottomUp{


    public static void main( String[] args ){

        //        System.out.println( changeCoinsNum( 10 ) );
        //        System.out.println( "==========================================================" );

        Set<Map<Integer, Integer>> rt = changeCoins( 10 );
        System.out.println( rt );

    }

    private static final int[] coins = new int[]{ 7, 4, 3, 2 };

    private static HashMap<Integer, Set<Map<Integer, Integer>>> moneyToSetOfMapCoin2amt = new HashMap<>();

    private static Set<Map<Integer, Integer>> changeCoins( final int money ){
        for( int coin: coins ){
            final Map<Integer, Integer> singletonMap = new HashMap<>();
            singletonMap.put( coin, 1 );
            final Set<Map<Integer, Integer>> set = new HashSet<>();
            set.add( singletonMap );
            moneyToSetOfMapCoin2amt.put( coin, set );

            System.out.println( "\ncoin>>>" + coin );
            for( int iCoin = coin + 1; iCoin <= money; iCoin++ ){
                final int increasedPrevCoin = iCoin - coin;
                Set<Map<Integer, Integer>> increasedPrevSets = moneyToSetOfMapCoin2amt.get( increasedPrevCoin );
                if( increasedPrevSets == null ){
                    continue;
                }
                increasedPrevSets = clone( increasedPrevSets );
                String increasedPrevSetStr = increasedPrevSets.toString();
                System.out.println( "increasedPrevCoin=" + increasedPrevSetStr );
                Set<Map<Integer, Integer>> iCoinSet = moneyToSetOfMapCoin2amt.getOrDefault( iCoin, new HashSet<>() );

                for( Map<Integer, Integer> coin2amt: increasedPrevSets ){
                    coin2amt.put( coin, coin2amt.getOrDefault( coin, 0 ) );
                }

                String oldVal = iCoinSet.toString();
                iCoinSet.addAll( increasedPrevSets );
                moneyToSetOfMapCoin2amt.put( iCoin, iCoinSet );
                System.out.println( "iCoin[" + iCoin + "]->increasedPrevCoin[" + increasedPrevCoin + "]:" + increasedPrevSetStr );
                System.out.println( "iCoin[" + iCoin + "]->" + oldVal + ">~~~~~~~>" + iCoinSet );
                System.out.println( "~" );
            }
            System.out.println( "coin<<<" + coin );
        }
        return moneyToSetOfMapCoin2amt.getOrDefault( money, new HashSet<>() );
    }


    static HashMap<Integer, Integer> moneyToAmt = new HashMap<>();

    public static int changeCoinsNum( int money ){
        moneyToAmt.put( 0, 1 );
        for( int coin: coins ){
            System.out.println( "coin>>>" + coin );
            for( int i = coin; i <= money; i++ ){
                final int increased = i - coin;
                final int incrVal = moneyToAmt.getOrDefault( increased, 0 );
                if( incrVal == 0 ){
                    continue;
                }

                final int value = moneyToAmt.getOrDefault( i, 0 );
                moneyToAmt.put( i, value + incrVal );
                System.out.println( coin + "->increased[" + increased + "]:" + incrVal + "; val[" + i + "]:" + value + "==>>" + ( value + incrVal ) );
            }
            System.out.println( "coin<<<" + coin );
        }
        return moneyToAmt.getOrDefault( money, 0 );
    }

    private static <T> T clone( T src ){
        final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        final ObjectOutputStream objOut;
        try{
            objOut = new ObjectOutputStream( bOut );
            objOut.writeObject( src );

            final ByteArrayInputStream in = new ByteArrayInputStream( bOut.toByteArray() );
            ObjectInputStream objIn = new ObjectInputStream( in );
            return ( T )objIn.readObject();
        }
        catch( Exception e ){
            throw new Error( e );
        }
    }

}
