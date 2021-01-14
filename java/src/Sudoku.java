import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sudoku{
    private static final Sudoku instance = new Sudoku();

    public static void main( String[] args ){
        String input = "2\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 8 0 0 0 0 4 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 6 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "2 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 2 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +

                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 8 0 0 0 0 4 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 6 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "2 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 2 0 0\n" +
                "0 0 0 0 0 0 0 0 0";
        String expect = "" +
                "3 4 5 6 7 8 9 1 2\n" +
                "6 7 8 9 1 2 3 4 5\n" +
                "9 1 2 3 4 5 6 7 8\n" +
                "1 2 3 4 5 6 7 8 9\n" +
                "4 5 6 7 8 9 1 2 3\n" +
                "7 8 9 1 2 3 4 5 6\n" +
                "2 3 4 5 6 7 8 9 1\n" +
                "5 6 7 8 9 1 2 3 4\n" +
                "8 9 1 2 3 4 5 6 7\n" +

                "3 4 5 6 7 8 9 1 2\n" +
                "6 7 8 9 1 2 3 4 5\n" +
                "9 1 2 3 4 5 6 7 8\n" +
                "1 2 3 4 5 6 7 8 9\n" +
                "4 5 6 7 8 9 1 2 3\n" +
                "7 8 9 1 2 3 4 5 6\n" +
                "2 3 4 5 6 7 8 9 1\n" +
                "5 6 7 8 9 1 2 3 4\n" +
                "8 9 1 2 3 4 5 6 7";
        instance.verify( input, expect );
/*
        String input1 = "1\n" +
                "4 0 0 0 0 6 0 0 0\n" +
                "0 6 0 0 0 0 0 0 9\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 2 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n" +
                "0 0 3 0 6 0 0 2 0\n" +
                "1 0 0 0 0 0 9 0 0\n" +
                "8 0 0 0 0 5 0 0 0\n" +
                "0 0 0 0 0 0 0 0 5";
        instance.verify( input1, "" +
                "4 1 5 2 3 6 7 9 8\n" +
                "2 6 1 3 4 7 5 8 9\n" +
                "3 2 4 1 5 9 8 6 7\n" +
                "5 3 2 8 9 1 4 7 6\n" +
                "6 4 7 9 8 2 3 5 1\n" +
                "7 9 3 5 6 8 1 2 4\n" +
                "1 5 8 6 7 3 9 4 2\n" +
                "8 7 9 4 2 5 6 1 3\n" +
                "9 8 6 7 1 4 2 3 5" );*/
    }

    private void verify( String input, String expect ){
        input = input.replaceAll( " ", "" );
        String[] inputArr = input.split( "\n" );
        HashMap<Integer, char[][]> sudokuGridMap = new HashMap<>();

        for( int i = 1; i < inputArr.length; i++ ){
            int idxOfSudoku = ( i - 1 ) / 9;
            char[][] tmp = sudokuGridMap.computeIfAbsent( idxOfSudoku, v -> new char[ 9 ][] );

            int idxIn9 = ( i - 1 ) % 9;
            tmp[ idxIn9 ] = inputArr[ i ].toCharArray();
        }


        String outputStr = sudokuGridMap.values().stream().map( grid -> {
            return Solution.sudoku( grid );
        } ).flatMap( grid -> {
            List<String> lst = new ArrayList<>( 9 );

            grid.forEach( ec->{
                for( int i = 0; i < ec.length; i++ ){
                    String row = new String( ec[ i ] );
                    row = String.join( " ", row.split( "" ) );
                    lst.add( row );
                }
            } );

            return lst.stream();
        } ).collect( Collectors.joining( "\n" ) );


        if( ! expect.equals( outputStr ) ){
            throw new Error( "Expect:\n" + expect + "\nOutput:\n" + outputStr );
        }
    }

    static class Solution{


        static List<char[][]> sudoku( char[][] grid ){
            List<char[][]> rt = new ArrayList<>();
            for( int i = 1; i <= 9; i++ ){
                char[][] g = cloneGrid( grid );
                int[] filledPosition = fillOneMore( g, ( i + "" ).charAt( 0 ) );
                if( filledPosition == null ){
                    continue;
                }
                System.out.println( ">>>>>>>>>>>>>>>>>>>>>>" );
                print( g );
                if( filledPosition[ 0 ] < 0 ){
                    System.out.println( "<<<<<<<<<<<<<<<<<<<<<<<<" );
                    return Collections.singletonList( g );
                }
                List<char[][]> tmp = sudoku( g );
                rt.addAll( tmp );
            }
            return rt;
        }

        private static int[] fillOneMore( char[][] g, char digit ){
            for( int rowIdx = 0; rowIdx < g.length; rowIdx++ ){
                char[] sRow = g[ rowIdx ];
                for( int colIdx = 0; colIdx < sRow.length; colIdx++ ){
                    if( sRow[ colIdx ] == '0' ){
                        if( canFill( g, rowIdx, colIdx, digit ) ){
                            sRow[ colIdx ] = digit;
                            return new int[]{ rowIdx, colIdx };
                        }
                        else{
                            return null;
                        }
                    }
                }
            }
            return new int[]{ - 1 };
        }

        private static boolean canFill( char[][] g, int rowIdx, int colIdx, char digit ){
            char[] row = g[ rowIdx ];
            for( int i = 0; i < row.length; i++ ){
                if( row[ i ] == digit ){
                    return false;
                }
            }

            for( int i = 0; i < g.length; i++ ){
                if( g[ i ][ colIdx ] == digit ){
                    return false;
                }
            }
            return true;
        }


        static char[][] cloneGrid( char[][] g ){
            char[][] rt = new char[ 9 ][ 9 ];
            for( int i = 0; i < 9; i++ ){
                System.arraycopy( g[ i ], 0, rt[ i ], 0, 9 );
            }
            return rt;
        }


        private static void print( char[][] oneMoreFilled ){
            System.out.println( ">>" );
            for( int i = 0; i < oneMoreFilled.length; i++ ){
                System.out.println( oneMoreFilled[ i ] );
            }
            System.out.println( "<<" );
        }

    }

}
