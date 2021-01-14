import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class RodCutting{

    public static void main( String[] args ){
        initLengthToPrice();
//        List<Integer> rslt = topDown( 10 );
//        System.out.println( "----------------" + rslt + "----------------" );
        bottomUp( 10 );
    }


    static HashMap<Integer, Integer> lengthToPrice = new HashMap<>();

    static void initLengthToPrice(){
        lengthToPrice.put( 1, 1 );
        lengthToPrice.put( 2, 2 );
        lengthToPrice.put( 3, 4 );
        lengthToPrice.put( 4, 5 );
        lengthToPrice.put( 5, 10 );
        lengthToPrice.put( 6, 7 );
        lengthToPrice.put( 7, 8 );
    }

    static void bottomUp( int givenLen ){

        HashMap<Integer, Integer> lenToMax = new HashMap<>();
        lenToMax.put( 0, 0 );
        for( int i = 1; i <= givenLen; i++ ){
            int iMax = 0;
            for( int j = 1; j <= i; j++ ){
                iMax = Math.max( iMax, lenToMax.get( i - j ) + lengthToPrice.getOrDefault( j, 0 ) );
            }
            lenToMax.put( i, iMax );
        }
        System.out.println( lenToMax.get( givenLen ) );

    }

    static List<Integer> topDown( int givenLength ){
        print( ">>>" + givenLength );
        depth.push( " - " );

        int max = 0;
        List<Integer> lengthLst = null;
        if( givenLength == 0 ){
            lengthLst = new LinkedList<>();
        }
        else if( givenLength == 1 ){
            lengthLst = Collections.singletonList( 1 );
            max = lengthToPrice.get( 1 );
        }
        else{
            for( int i = 1; i <= Math.min( givenLength, lengthToPrice.size() ); i++ ){

                final List<Integer> recurLst = topDown( givenLength - i );
                final int priceI = lengthToPrice.get( i );
                final int pi = priceI + sumLengthPrices( recurLst );

                print( "i=" + i + ", priceI=" + priceI + ", pi=" + pi );
                if( max < pi ){
                    max = pi;
                    lengthLst = new LinkedList<>( recurLst );
                    lengthLst.add( i );
                }
            }
        }

        depth.pop();
        print( "<<<" + givenLength + ", rt=" + lengthLst.toString() + ", max=" + max );
        return lengthLst;
    }


    static int sumLengthPrices( List<Integer> lenLst ){
        int rt = 0;
        for( Integer aLenLst : lenLst ){
            rt += lengthToPrice.get( aLenLst );
        }
        return rt;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + " : " + a.toString() );
    }


}
