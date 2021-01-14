import java.util.HashMap;

/**
 * User: GaryY
 * Date: 4/13/2018
 */
public class IsRobotMovingInCircle{


    public static void main( String[] args ){
//        String[] rsult = doesCircleExist( new String[]{ "RGL" } );
        String[] rsult = doesCircleExist( new String[]{ "RG" } );
        System.out.println( rsult );
    }


    // Complete the doesCircleExist function below.
    static String[] doesCircleExist( String[] commands ){
        for( int i = 0; i < commands.length; i++ ){
            commands[ i ] = resultInCircle( commands[ i ] ) ? "YES" : "NO";
        }
        return commands;
    }

    private static boolean resultInCircle( String cmds ){
        System.out.println( cmds );
        HashMap<String,Integer> historyPosiToDir = new HashMap<>(  );
        historyPosiToDir.put( "0,0", 0 );

        int positionX = 0;
        int positionY = 0;
        int positionDir = 0;
        for( int i = 0; i <= 2500; i++ ){
            String nowPosi = execute( cmds, positionX, positionY, positionDir );
            final String[] split = nowPosi.split( "," );
            positionX = Integer.parseInt( split[ 0 ] );
            positionY = Integer.parseInt( split[ 1 ] );
            if( historyPosiToDir.containsKey( positionX + "," + positionY) ){
                return true;
            }
            positionDir = Integer.parseInt( split[ 2 ] );
            historyPosiToDir.put( positionX + "," + positionY, positionDir );
        }


        return false;
    }

    private static String execute( String cmds, int positionX, int positionY, int dir ){
        for( int i = 0; i < cmds.length(); i++ ){
            char cmd = cmds.charAt( i );
            if( cmd == 'G' ){
                if( dir == 0 ){
                    positionY++;
                }
                else if( dir == 1 ){
                    positionX++;
                }
                else if( dir == 2 ){
                    positionY--;
                }
                else if( dir == 3 ){
                    positionX--;
                }
            }
            else if( cmd == 'L' ){
                dir--;
                if( dir < 0 ){
                    dir = 3;
                }

            }
            else if( cmd == 'R' ){
                dir++;
                if( dir > 3 ){
                    dir = 0;
                }
            }
            else{
                throw new IllegalArgumentException( "unknown cmd:" + cmd );
            }
        }
        final String rt = positionX + "," + positionY + "," + dir;
        return rt;
    }

}
