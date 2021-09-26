import java.util.*;

public class Knapsack{

    public static void main( String[] args ){
        HashMap<Integer, Integer> weightToValue = new HashMap<>();
        weightToValue.put( 2, 3 );
        weightToValue.put( 3, 7 );
        weightToValue.put( 4, 9 );
        weightToValue.put( 5, 10 );

//        int rslt = maxValueWithLeastWeightBottomUp( 8, weightToValue );
//        int rslt = maxValueWithLeastWeight1( 8, weightToValue );
        int rslt = maxValueWithLeastWeight2( 8, weightToValue );
        System.out.println( rslt + "<<<" + MAX_DEPTH );

    }




    private static int maxValueWithLeastWeightBottomUp( int limit, HashMap<Integer, Integer> weightToValue ){
        Set<Integer> weights = weightToValue.keySet();

        Set<Set<Integer>> prevCombSet = new HashSet<>();
        int maxVal = 0;
        Set<Set<Integer>> maxWeightSet = null;

        for( Integer w: weights ){
            Integer v = weightToValue.get( w );
            if( v >= maxVal && w <= limit ){
                if( v > maxVal ){
                    maxVal = v;
                    maxWeightSet = new HashSet<>();
                }

                HashSet<Integer> tmp = new HashSet<>();
                prevCombSet.add( tmp );
                assert maxWeightSet != null;
                maxWeightSet.add( tmp );

                if( w < limit ){
                    tmp.add( w );
                }
            }
        }

        while( prevCombSet.size() > 0 ){
            Set<Set<Integer>> newCombSet = new HashSet<>();
            for( int w: weights ){
                for( Set<Integer> lc: prevCombSet ){
                    if( lc.contains( w ) ){
                        continue;
                    }
                    int wSum = w + lc.stream().mapToInt( Integer::intValue ).sum();
                    int vSum = weightToValue.get( w ) + lc.stream().mapToInt( weightToValue::get ).sum();
                    if( wSum <= limit && vSum >= maxVal ){
                        if( vSum > maxVal ){
                            maxVal = vSum;
                            maxWeightSet = new HashSet<>();
                        }
                        HashSet<Integer> tmp = new HashSet<>( lc );
                        tmp.add( w );
                        maxWeightSet.add( tmp );

                        if( wSum < limit ){
                            newCombSet.add( tmp );
                        }
                    }
                }
            }
            prevCombSet = newCombSet;
        }
        return maxVal;
    }

    private static int maxValueWithLeastWeight1( int limit, HashMap<Integer, Integer> weightToValue ){
        Object[] rslt = maxValueWithLeastWeight11( limit, weightToValue );
        return ( int )rslt[ 0 ];
    }

    private static Object[] maxValueWithLeastWeight11( int limit, HashMap<Integer, Integer> weightToValue ){
        print( 1, ">>limit>>" + limit + ">>WTV:" + weightToValue );
        int max = 0;
        List<Integer> keyLst = new ArrayList<>();
        for( Map.Entry<Integer, Integer> entry: weightToValue.entrySet() ){
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            if( k <= limit ){
                HashMap<Integer, Integer> left = new HashMap<>( weightToValue );
                left.remove( k );
                Object[] kRstl = maxValueWithLeastWeight11( limit - k, left );
                if( max < v + ( int )kRstl[ 0 ] ){
                    max = v + ( int )kRstl[ 0 ];
                    keyLst = ( List )kRstl[ 1 ];
                    keyLst.add( k );
                }
            }
        }
        print( - 1, "<<limit<<" + limit + "<<max::" + max );
        return new Object[]{ max, keyLst };
    }

    private static int maxValueWithLeastWeight2( int limit, HashMap<Integer, Integer> weightToValue ){
        ArrayList<Integer> lst = new ArrayList<>( weightToValue.keySet() );
//        Collections.sort( lst );
//        Collections.reverse( lst );
//        List<Integer> integers = maxValueWithLeastWeight222( limit, lst, weightToValue );
//        return integers.stream().mapToInt( weightToValue::get ).sum();
        return maxValWithLess( limit, lst, weightToValue );
    }

    private static List<Integer> maxValueWithLeastWeight222( int limit, List<Integer> lst, HashMap<Integer, Integer> weightToValue ){

        int size = lst.size();
        if( size == 0 || limit <= 0 ){
            print( - 1, "limit:" + limit + "<<<<<<<<<<" );

            Set<Integer> ss = new HashSet<>();
            return new ArrayList<>();
        }
        List<Integer> subLst = lst.subList( 0, size - 1 );
        print( 1, "limit:" + limit + ">>>>>>>>>>lst>>>>" + subLst );

        List<Integer> maxValExcludeLastLst = maxValueWithLeastWeight222( limit, subLst, weightToValue );

        int maxValExcludeLast = maxValExcludeLastLst.stream().mapToInt( weightToValue::get ).sum();

        Integer lastWeight = lst.get( size - 1 );
        if( lastWeight > limit ){
            print( - 1, "limit:" + limit + "<<<<<<<<<<size:" + size + ", lw:" + lastWeight + ",rtVal: " + maxValExcludeLast );
            return maxValExcludeLastLst;
        }
        List<Integer> excluLessLst = maxValueWithLeastWeight222( limit - lastWeight, subLst, weightToValue );
        int maxValIncludeLast = weightToValue.get( lastWeight ) + excluLessLst.stream().mapToInt( weightToValue::get ).sum();
        excluLessLst.add( lastWeight );

        List<Integer> rt = ( maxValExcludeLast > maxValIncludeLast ) ? maxValExcludeLastLst : excluLessLst;

        print( - 1, "limit:" + limit + "<<<<<<<<<<size:" + size + "<<<<" + subLst + "<<<lastweight:"
                + lastWeight + ", maxValExcludeLast:" + maxValExcludeLast + ", maxValIncludeLast:" + maxValIncludeLast
                + ", rtVal:" + rt + "::" + Math.max( maxValExcludeLast, maxValIncludeLast ) );
        return rt;
    }


    private static int maxValWithLess( int limit, List<
            Integer> lst, HashMap<Integer, Integer> weightToValue ){
        print( 1, "limit:" + limit + ">>>>>>>>>>>>>>" + lst );

        if( lst.size() == 0 || limit <= 0 ){
            print( - 1, "limit:" + limit + "<<<<<<<<<<size:" + lst.size() + ",rtVal: 00" );
            return 0;
        }

        int maxValExcludeLast = maxValWithLess( limit, lst.subList( 0, lst.size() - 1 ), weightToValue );

        Integer lastWeight = lst.get( lst.size() - 1 );
        int maxValIncludeLast = lastWeight > limit ? 0 : weightToValue.get( lastWeight ) + maxValWithLess( limit - lastWeight, lst, weightToValue );
        int rt = Math.max( maxValIncludeLast, maxValExcludeLast );
        print( - 1, "limit:" + limit + "<<<<<<<<<<<<<<" + lst + "<<<lastweight:" + lastWeight + ", maxValExcludeLast:" + maxValExcludeLast + ", maxValIncludeLast:" + maxValIncludeLast + ", rtVal:" + rt );
        return rt;
    }


    private static int DEPTH = - 1;
    private static int MAX_DEPTH = - 1;

    private static void print( int depth, String str ){
        if( depth > 0 ){
            DEPTH += depth;
            MAX_DEPTH = Math.max( DEPTH, MAX_DEPTH );
        }
        StringBuilder sb = new StringBuilder();
        for( int i = 1; i <= DEPTH; i++ ){
            sb.append( i );
            sb.append( "--->" );
        }
        System.out.println( sb.toString() + str );
        if( depth < 0 ){
            DEPTH += depth;
        }
    }
}
