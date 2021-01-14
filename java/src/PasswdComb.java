import java.util.*;

/**
 * [1]
 * [2]
 * [3]
 * [4]
 *
 * <p>
 * [11]
 * [21]
 * [31]
 * [41]
 * <p>
 * [12]
 * [22]
 * [32]
 * [42]
 * <p>
 * [13]
 * [23]
 * [33]
 * [43]
 * <p>
 * [14]
 * [24]
 * [34]
 * [44]
 * <p>
 * <p>
 * User: GaryY
 * Date: 4/13/2018
 */
public class PasswdComb{


    public static void main( String[] args ){
        //        topDown( charArr, 8 );
        char[][] rslt = bottomUp( charArr, 5 );
        System.out.println( rslt );
    }

    private static final char[] charArr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private static char[][] topDown( char[] charr, int passLen ){
        depth.push( "^" );
        print( ">>" + new String( charr ) + ">>" + passLen );

        char[][] rt;
        if( passLen == 1 ){
            rt = new char[ charr.length ][ 1 ];
            for( int i = 0; i < charr.length; i++ ){
                char c = charr[ i ];
                rt[ i ][ 0 ] = c;
            }
        }
        else{
            char[][] lowComb = topDown( charr, passLen - 1 );
            rt = new char[ lowComb.length * charr.length ][];
            int idx = 0;
            for( int i = 0; i < lowComb.length; i++ ){
                char[] lowChars = lowComb[ i ];
                for( int j = 0; j < charr.length; j++ ){
                    char c = charr[ j ];
                    rt[ idx ] = Arrays.copyOf( lowChars, lowChars.length + 1 );
                    rt[ idx ][ lowChars.length ] = c;
                    idx++;
                }
            }

        }


        String[] rts = new String[ rt.length ];
        int len = 0;
        for( int i = 0; i < rt.length; i++ ){
            rts[ i ] = "[" + new String( rt[ i ] ) + "]";
            len += rt[ i ].length;
        }

        print( "<<" + new String( charr ) + "<<" + passLen + "::" + len + ":" + Arrays.toString( rts ) );
        depth.pop();
        return rt;
    }

    private static boolean UNIQUE = false;

    private static char[][] bottomUp( char[] chararr, int passLen ){
        HashMap<Integer, List<List<Character>>> lenToCombLst = new HashMap<>();

        for( int i = 0; i < passLen; i++ ){
            if( i == 0 ){
                final LinkedList<List<Character>> lstOfComb = new LinkedList<>();
                for( char c: chararr ){
                    ArrayList<Character> nlst = new ArrayList<>();
                    nlst.add( c );
                    lstOfComb.addFirst( nlst );
                }
                System.out.println( i + ">>" + lstOfComb.size() + ">>>>" + lstOfComb );
                lenToCombLst.put( i, lstOfComb );
            }
            else{
                final List<List<Character>> prevLstOfComb = lenToCombLst.get( i - 1 );
                final LinkedList<List<Character>> currentLstOfComb = new LinkedList<>();
                for( List<Character> aPrevComb: prevLstOfComb ){
                    for( char aChararr: chararr ){
                        if( ! UNIQUE || ! aPrevComb.contains( aChararr ) ){
                            ArrayList<Character> theNewComb = new ArrayList<>( aPrevComb.size() + 1 );
                            theNewComb.add( aChararr );
                            theNewComb.addAll( aPrevComb );
                            currentLstOfComb.addFirst( theNewComb );
                        }
                    }
                }
                System.out.println( i + ">>" + currentLstOfComb.size() + ">>>>" + currentLstOfComb );
                lenToCombLst.put( i, currentLstOfComb );
            }
        }

        return null;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + a.toString() );
    }
}
