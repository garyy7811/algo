import java.util.HashMap;
import java.util.HashSet;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class WpTst{


    public static void main( String[] args ){

        //        int r = longest_cycle( new int[]{ 5, 2, 3, 4, 1, 0 } );

        System.out.println( "3>>>>" + stickers( "nnnnn" ) );
        System.out.println( "2>>>>" + stickers( "weeennn" ) );
    }

    static final String ticket = "wpengine";

    private static int stickers( final String i ){

        System.out.println( ">>-iii->>" + i );
        HashMap<Character, Integer> ticketChar2Count = stickerStrChar2Count( ticket );
        HashMap<Character, Integer> targetChar2Count = stickerStrChar2Count( i );

        int[] needTicket = new int[]{0};

        targetChar2Count.forEach( ( targetChar, targetCharCount ) ->{
            if( !ticketChar2Count.containsKey( targetChar )){
                throw new RuntimeException( "No answer!" );
            }

            final Integer ticketCharCount = ticketChar2Count.get( targetChar );

            int ttt = targetCharCount / ticketCharCount;
            if( targetCharCount % ticketCharCount > 0 ){
                ttt += 1;
            }
            System.out.println( targetChar + ">>-->>" +targetCharCount  + "/" + ticketCharCount + "ttt>" + ttt);

            needTicket[0] = Math.max( ttt, needTicket[0] );

        } );

        return needTicket[0];
    }

    private static HashMap<Character, Integer> stickerStrChar2Count( String ticket ){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        final char[] ticketCharArr = ticket.toCharArray();
        for( int j = 0; j < ticketCharArr.length; j++ ){
            char c = ticketCharArr[ j ];
            Integer n = hashMap.getOrDefault( c, 0 );
            hashMap.put( c, n + 1 );
        }
        return hashMap;
    }

    /*
     * Complete the longest_cycle function below.
     */
    static int longest_cycle( int[] indices ){
        if( indices.length == 0 ){
            return 0;
        }
        theIndices = indices;
        processCycle( 0 );
        return max;

    }

    static int[]                     theIndices;
    static HashMap<Integer, HashSet> idxToSet = new HashMap();

    static int max = 0;

    static void processCycle( Integer i ){
        System.out.println( "processCycle>>>>" + i );
        HashSet set = idxToSet.getOrDefault( i, new HashSet() );

        if( set.contains( i ) ){
            System.out.println( "processed" + set.size() );
            return;
        }

        set.add( i );
        idxToSet.put( theIndices[ i ], set );
        processCycle( theIndices[ i ] );

        int sz = set.size();
        if( sz > max ){
            max = sz;
        }
    }
}
