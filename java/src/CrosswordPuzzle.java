import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrosswordPuzzle{
    private static final CrosswordPuzzle instance = new CrosswordPuzzle();

    public static void main( String[] args ){

        String input = "" +
                "+-++++++++\n" +
                "+-++++++++\n" +
                "+-++++++++\n" +
                "+-----++++\n" +
                "+-+++-++++\n" +
                "+-+++-++++\n" +
                "+++++-++++\n" +
                "++------++\n" +
                "+++++-++++\n" +
                "+++++-++++\n" +
                "ANKARA;ICELAND;DELHI;LONDON";
        String expect = "" +
                "+L++++++++\n" +
                "+O++++++++\n" +
                "+N++++++++\n" +
                "+DELHI++++\n" +
                "+O+++C++++\n" +
                "+N+++E++++\n" +
                "+++++L++++\n" +
                "++ANKARA++\n" +
                "+++++N++++\n" +
                "+++++D++++";
        instance.verify(
                input,
                expect );

        String input1 = "" +
                "XXXXXX-XXX\n" +
                "XX------XX\n" +
                "XXXXXX-XXX\n" +
                "XXXXXX-XXX\n" +
                "XXX------X\n" +
                "XXXXXX-X-X\n" +
                "XXXXXX-X-X\n" +
                "XXXXXXXX-X\n" +
                "XXXXXXXX-X\n" +
                "XXXXXXXX-X\n" +
                "ICELAND;MEXICO;PANAMA;ALMATY";
        String expect1 = "" +
                "XXXXXXIXXX\n" +
                "XXMEXICOXX\n" +
                "XXXXXXEXXX\n" +
                "XXXXXXLXXX\n" +
                "XXXPANAMAX\n" +
                "XXXXXXNXLX\n" +
                "XXXXXXDXMX\n" +
                "XXXXXXXXAX\n" +
                "XXXXXXXXTX\n" +
                "XXXXXXXXYX";
        instance.verify(
                input1,
                expect1 );

    }

    private void verify( String input, String expect ){
        String[] arr = input.split( "\n" );

        String[] inputArr = Arrays.copyOfRange( arr, 0, arr.length - 1 );
        String inputwords = arr[ arr.length - 1 ];

        String[] outputArr = Solution.crosswordPuzzle( inputArr, inputwords );

        String outputStr = String.join( "\n", outputArr );
        if( ! expect.equals( outputStr ) ){
            throw new Error( "Expect:\n" + expect + "\nOutput:\n" + outputStr );
        }
    }

    static class Solution{
        static String[] crosswordPuzzle( String[] crossword, String words ){
            String[] wordArr = words.split( ";" );
            char[][] wordGrid = new char[ crossword.length ][ crossword[ 0 ].length() ];

            for( int i = 0; i < crossword.length; i++ ){
                wordGrid[ i ] = cloneCharArr( crossword[ i ].toCharArray() );
            }

            char[][] rslt = tryFit( wordGrid, Arrays.asList( wordArr ) );

            String[] rt = new String[ rslt.length ];
            for( int i = 0; i < rslt.length; i++ ){
                rt[ i ] = new String( rslt[ i ] );
            }
            return rt;
        }

        static boolean isEmpty( char c ){
            return c != 'X' && c != '+';
        }

        static private char[][] tryFit( char[][] crossword, List<String> wordArr ){
            for( int i = 0; i < wordArr.size(); i++ ){
                String s = wordArr.get( i );

                char[][] cw = cloneCrossword( crossword );
                char[][] rslt = fitWord( cw, s );
                if( rslt != null ){
                    List<String> wlst = new ArrayList<>( wordArr );
                    wlst.remove( i );
                    if( wlst.size() == 0 ){
                        return rslt;
                    }
                    char[][] rt = tryFit( rslt, wlst );
                    if( rt != null ){
                        return rt;
                    }
                }
            }
            return null;
        }

        static private char[][] fitWord( char[][] cw, String s ){

            for( int i = 0; i < cw.length; i++ ){
                char[] rowI = cw[ i ];
                for( int j = 0; j < rowI.length; j++ ){
                    char cj = rowI[ j ];

                    if( cj == '-' || cj == s.charAt( 0 ) ){
                        if( j < rowI.length - 1 && isEmpty( rowI[ j + 1 ] ) ){
                            char[] filled = tryFillRow( cw[ i ], j, s );
                            if( filled != null ){
                                cw[ i ] = filled;
                                return cw;
                            }
                        }

                        if( i < cw.length - 1 && isEmpty( cw[ i + 1 ][ j ] ) ){
                            char[][] found = tryFillCol( cw, i, j, s );
                            if( found != null ){
                                return found;
                            }
                        }
                    }
                }
            }
            return null;
        }

        static private char[] tryFillRow( char[] row, int j, String s ){
            if( row.length < j + s.length() ){
                return null;
            }

            if( row.length > j + s.length() && isEmpty( row[ j + s.length() ] ) ){
                return null;
            }

            char[] sr = cloneCharArr( row );

            for( int k = 0; k < s.length(); k++ ){
                int idx = j + k;
                char c = sr[ idx ];
                if( c == '-' ){
                    sr[ idx ] = s.charAt( k );
                }
                else if( c != s.charAt( k ) ){
                    return null;
                }
            }


            return sr;
        }

        static private char[][] tryFillCol( char[][] cw, int i, int j, String s ){
            if( cw.length < i + s.length() ){
                return null;
            }
            if( cw.length > i + s.length() && isEmpty( cw[ i + s.length() ][ j ] ) ){
                return null;
            }

            char[][] cp = cloneCrossword( cw );

            for( int k = 0; k < s.length(); k++ ){
                int rowIdx = i + k;
                char c = cw[ rowIdx ][ j ];
                if( c == '-' ){
                    cp[ rowIdx ][ j ] = s.charAt( k );
                }
                else if( c != s.charAt( k ) ){
                    return null;
                }
            }

            return cp;
        }


        static char[][] cloneCrossword( char[][] crossword ){
            char[][] rt = new char[ crossword.length ][ crossword[ 0 ].length ];
            for( int i = 0; i < rt.length; i++ ){
                rt[ i ] = cloneCharArr( crossword[ i ] );
            }
            return rt;
        }

        static char[] cloneCharArr( char[] row ){
            char[] rt = new char[ row.length ];
            System.arraycopy( row, 0, rt, 0, row.length );
            return rt;
        }
    }

    /*
        void printGrid( char[][] grid ){
            System.out.println( ">>" );
            for( int i = 0; i < grid.length; i++ ){
                System.out.println( new String( grid[ i ] ) );
            }
            System.out.println( "<<" );
        }*/


}
