import java.util.LinkedList;

/**
 * User: GaryY
 * Date: 7/5/2018
 */
public class FindLevelEggDropLimitTmp{


    public static void main( String[] args ){
        findLimitByDroppingEgges( 300, 2 );
    }

    private static void findLimitByDroppingEgges( int levelNum, int eggNum ){


        int total = 0;
        for( int limit_i = 1; limit_i <= levelNum; limit_i++ ){
            final int drop_i = findLimitByDroppingEggsRecursively( 1, levelNum, limit_i, eggNum );
            total += drop_i;
            System.out.println( "\nlimit>" + limit_i + "=>" + drop_i + "\n" );
        }
        System.out.println( "\n>>>>" + total + "\n" );

    }

    private static int whereToTry( int levelNum, int fromLevel, int eggNum ){
        int div = ( 8 * ( levelNum - fromLevel ) ) / 18;
        return fromLevel + div;
    }

    private static int findLimitByDroppingEggsRecursively( int fromLevel, int toLevel, int levelLimit, int eggNum ){
        print( "<<<<fromLevel>>" + fromLevel + ">>>toLevel>>" + toLevel + ",eggNum>>" + eggNum + ",levelLimit>>" + levelLimit );
        depth.push( " - " );

        int rt = 0;

        if( eggNum == 1 ){
            rt = Math.max( levelLimit - fromLevel, 0 ) + 1;
        }
        else if( fromLevel == toLevel ){
            rt = 1;
        }
        else{
            int trying = whereToTry( toLevel, fromLevel, eggNum );
            rt += 1;
            if( trying > levelLimit ){
                rt += findLimitByDroppingEggsRecursively( 1, trying - 1, levelLimit, eggNum - 1 );
            }
            else{
                rt += findLimitByDroppingEggsRecursively( trying + 1, toLevel, levelLimit, eggNum - 1 );
            }

        }


        depth.pop();
        print( "<<<<fromLevel>>" + fromLevel + ">>>toLevel>>" + toLevel + ",eggNum>>" + eggNum + ",levelLimit>>" + levelLimit + "}})" + rt );

        return rt;
    }


    private static LinkedList<String> depth = new LinkedList<>();

    private static void print( Object a ){
        System.out.println( depth.toString() + " : " + a.toString() );
    }


}
