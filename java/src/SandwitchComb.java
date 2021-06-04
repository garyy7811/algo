import java.util.*;
import java.util.stream.Collectors;

public class SandwitchComb{

    public static void main( String[] args ){
        class Solution1{
            private Map<Character, Set<Character>> typeToIngredients;

            private Solution1(){
                typeToIngredients = new HashMap<>();
                typeToIngredients.put( 'n', new HashSet<>( Arrays.asList( '1', '2' ) ) );
                typeToIngredients.put( 'c', new HashSet<>( Arrays.asList( 'a', 'b', 'c' ) ) );
                typeToIngredients.put( 's', new HashSet<>( Arrays.asList( '~', '@' ) ) );
                typeToIngredients.put( 'U', new HashSet<>( Arrays.asList( 'X', 'Y', 'Z' ) ) );
            }

            private List<String> getAllCombine( int layerNum ){
                Map<Character, Character> ingrToType = new HashMap<>();
                typeToIngredients.forEach( ( k, v ) -> {
                    v.forEach( i -> {
                        ingrToType.put( i, k );
                    } );
                } );

                List<String> rt = ingrToType.keySet().stream().map( Object::toString ).collect( Collectors.toList() );

                while( layerNum - 1 > 0 ){
                    List<String> newComb = new ArrayList<>();
                    for( String o: rt ){
                        Character prevType = ingrToType.get( o.charAt( o.length() - 1 ) );
                        typeToIngredients.forEach( ( k, v ) -> {
                            if( prevType.charValue() != k ){
                                v.forEach( nI -> {
                                    System.out.println( "[" + o + "]" + nI );
                                    newComb.add( o + nI );
                                } );
                            }
                        } );
                        //abandon the prev
                        rt = newComb;
                    }
                    layerNum--;
                }

                return rt;
            }
        }

        List<String> rslt1 = new Solution1().getAllCombine( 5 );

        class Solution2{
            private Map<Character, Set<Character>> typeToIngredients;

            private Solution2(){
                typeToIngredients = new HashMap<>();
                typeToIngredients.put( 'n', new HashSet<>( Arrays.asList( '1', '2' ) ) );
                typeToIngredients.put( 'c', new HashSet<>( Arrays.asList( 'a', 'b', 'c' ) ) );
                typeToIngredients.put( 's', new HashSet<>( Arrays.asList( '~', '@' ) ) );
                typeToIngredients.put( 'U', new HashSet<>( Arrays.asList( 'X', 'Y', 'Z' ) ) );
            }

            private List<String> getAllCombine( int layerNum ){
                Map<Character, Character> ingreToType = new HashMap<>();
                typeToIngredients.forEach( ( k, v ) -> {
                    v.forEach( i -> {
                        ingreToType.put( i, k );
                    } );
                } );
                List<String> rslt = new ArrayList<>();
                StringBuilder ss = new StringBuilder();
                getAllCombine( rslt, ss, ingreToType.keySet(), ingreToType, layerNum );
                return rslt;
            }

            private void getAllCombine( List<String> rslt, StringBuilder ss, Set<Character> keySet, Map<Character, Character> ingreToType, final int limit ){
                if( ss.length() == limit ){
                    rslt.add( ss.toString() );
                    return;
                }
                getAvailableIngres( ss, keySet, ingreToType ).forEach( ni -> {
                    ss.append( ni );
                    getAllCombine( rslt, ss, keySet, ingreToType, limit );
                    ss.deleteCharAt( ss.length() - 1 );
                } );
            }

            private Set<Character> getAvailableIngres( StringBuilder ss, Set<Character> keySet, Map<Character, Character> ingreToType ){
                return keySet.stream().filter( c -> ss.length() == 0 || ingreToType.get( c ) !=  ingreToType.get( ss.charAt( ss.length() - 1 ) ) ).collect( Collectors.toSet() );
            }
        }

        List<String> rslt2 = new Solution2().getAllCombine( 5 );

        System.out.println( rslt1 );
        System.out.println( "-------------------------------------" );
        System.out.println( rslt2 );


    }
}
