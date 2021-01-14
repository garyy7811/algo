import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SparseMatrixMultiply{


    public static void main( String[] args ){
        int[][] A = {
                { 3, 4, 2 },
                { 2, 3, 4 }
        };
        int[][] B = {
                { 13, 9, 7, 15 },
                { 8, 7, 4, 6 },
                { 6, 4, 0, 3 }
        };
        int[][] result = multiply( A, B );
        for( int i = 0; i < result.length; i++ ){
            for( int j = 0; j < result[ 0 ].length; j++ ){
                System.out.print( " " + result[ i ][ j ] );
            }
            System.out.println();
        }

        System.out.println( "---------------------------" );

        result = multiplyOpt( A, B );
        for( int i = 0; i < result.length; i++ ){
            for( int j = 0; j < result[ 0 ].length; j++ ){
                System.out.print( " " + result[ i ][ j ] );
            }
            System.out.println();
        }
    }

    public static int[][] multiply( int[][] A, int[][] B ){
        int[][] rslt = new int[ A.length ][ B[ 0 ].length ];
        for( int aRowIdx = 0; aRowIdx < A.length; aRowIdx++ ){
            for( int bColIdx = 0; bColIdx < B[ 0 ].length; bColIdx++ ){
                int sum = 0;
                for( int aColIdx = 0; aColIdx < A[ 0 ].length; aColIdx++ ){
                    int tm = A[ aRowIdx ][ aColIdx ] * B[ aColIdx ][ bColIdx ];
                    sum += tm;
                    System.out.println( "A[" + aRowIdx + "][" + aColIdx + "] x " + "B[" + aColIdx + "][" + bColIdx + "]" + ">>>" + tm );
                }
                rslt[ aRowIdx ][ bColIdx ] = sum;
                System.out.println( sum + "<<<<rslt[ " + aRowIdx + " ][ " + bColIdx + " ]" );
            }
        }
        return rslt;
    }


    public static int[][] multiplyOpt( int[][] A, int[][] B ){
        Map<Integer, Map<Integer, Integer>> aByRow = compressByRow( A );
        System.out.println( "aByRow>>>");
        System.out.println( aByRow);
        System.out.println( "aByRow<<<");
        Map<Integer, Map<Integer, Integer>> bByCol = compressByCol( B );
        System.out.println( "bByCol>>" );
        System.out.println( bByCol );
        System.out.println( "bByCol<<" );

        int[][] rslt = new int[ A.length ][ B[ 0 ].length ];
        aByRow.forEach( ( aRowIdx, aRowMap ) -> {
            bByCol.forEach( ( bColIdx, bColMap ) -> {
                AtomicInteger sum = new AtomicInteger();
                aRowMap.forEach( ( aRowColIdx, val ) -> {
                    if( bColMap.containsKey( aRowColIdx ) ){
                        sum.addAndGet( aRowMap.get( aRowColIdx ) * bColMap.get( aRowColIdx ) );
                    }
                } );
                rslt[ aRowIdx ][ bColIdx ] = sum.get();
            } );
        } );

        return rslt;
    }

    private static Map<Integer, Map<Integer, Integer>> compressByRow( int[][] grid ){
        Map<Integer, Map<Integer, Integer>> mapOfMap = new HashMap<>( grid.length );
        for( int rowIdx = 0; rowIdx < grid.length; rowIdx++ ){
            int[] row = grid[ rowIdx ];
            Map<Integer, Integer> rowMap = new HashMap<>( row.length );
            for( int colIdx = 0; colIdx < row.length; colIdx++ ){
                int val = row[ colIdx ];
                if( val > 0 ){
                    rowMap.put( colIdx, val );
                }
            }
            if( rowMap.size() > 0 ){
                mapOfMap.put( rowIdx, rowMap );
            }
        }

        return mapOfMap;
    }

    private static Map<Integer, Map<Integer, Integer>> compressByCol( int[][] grid ){
        Map<Integer, Map<Integer, Integer>> mapOfMap = new HashMap<>( grid[ 0 ].length );
        for( int colIdx = 0; colIdx < grid[ 0 ].length; colIdx++ ){
            Map<Integer, Integer> colMap = new HashMap<>( grid[ 0 ].length );
            for( int rowIdx = 0; rowIdx < grid.length; rowIdx++ ){
                int val = grid[ rowIdx ][ colIdx ];
                if( val > 0 ){
                    colMap.put( rowIdx, val );
                }
            }
            if( colMap.size() > 0 ){
                mapOfMap.put( colIdx, colMap );
            }
        }

        return mapOfMap;
    }


    private static List<List<int[]>> compressByCol2( int[][] grid ){
        List<List<int[]>> lsOfLs = new ArrayList<>( grid[ 0 ].length );
        for( int colIdx = 0; colIdx < grid[ 0 ].length; colIdx++ ){
            List<int[]> colLst = new ArrayList<>( grid.length );
            for( int rowIdx = 0; rowIdx < grid.length; rowIdx++ ){
                int val = grid[ rowIdx ][ colIdx ];
                if( val > 0 ){
                    colLst.add( new int[]{ rowIdx, colIdx, val } );
                }
            }
            if( colLst.size() > 0 ){
                lsOfLs.add( colLst );
            }
        }
        return lsOfLs;
    }

}
