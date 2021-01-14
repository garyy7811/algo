import java.io.*;
import java.util.*;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class CoinChangingAllCombine{


    public static void main( String[] args ) throws IOException, ClassNotFoundException{
        final Set<HashMap<Integer, Integer>> changeCoins = changeCoins( 10 );
        System.out.println( changeCoins );
    }

    static final int[] coins = new int[]{ 2, 8, 4, 6 };

    static final HashMap<Integer, byte[]> cache = new HashMap<>();

    private static Set<HashMap<Integer, Integer>> changeCoins( final int total ) throws IOException, ClassNotFoundException{
        print( ">>" + total + ">>" );
        depth.push( " - " );


        Set<HashMap<Integer, Integer>> rt = bytesToObj( cache.get( total ) );
        if( rt == null ){
            rt = new HashSet<>();
            for( int i = 0; i < coins.length; i++ ){
                int coin = coins[ i ];
                if( total >= coin ){
                    print( "coin[" + i + "]=" + coin );
                    if( total == coin ){
                        HashMap<Integer, Integer> coin2Amt = new HashMap<>();
                        coin2Amt.put( coin, 1 );
                        rt.add( coin2Amt );
                    }
                    else{
                        Set<HashMap<Integer, Integer>> crt = changeCoins( total - coin );
                        crt.forEach( s -> {
                            Integer e = s.get( coin );
                            if( e == null ){
                                e = 0;
                            }
                            s.put( coin, e + 1 );
                        } );
                        rt.addAll( crt );
                    }
                }
                else{
                    print( "--" + total + "lll" + coin + "--" );
                }
            }
            cache.put( total, objToBytes( rt ) );
        }
        else{
            print( "from_cache" );
        }

        depth.pop();
        print( "<<" + total + "<< : " + rt );
        return rt;
    }

    private static Set<HashMap<Integer, Integer>> bytesToObj( byte[] bytes ) throws IOException, ClassNotFoundException{
        if( bytes == null ){
            return null;
        }
        final ByteArrayInputStream in = new ByteArrayInputStream( bytes );
        ObjectInputStream objIn = new ObjectInputStream( in );
        return ( Set<HashMap<Integer, Integer>> )objIn.readObject();
    }

    private static byte[] objToBytes( Set<HashMap<Integer, Integer>> rt ) throws IOException{
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream( bOut );
        objOut.writeObject( rt );
        return bOut.toByteArray();
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + " : " + a.toString() );
    }
}
