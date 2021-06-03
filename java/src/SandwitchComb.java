import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SandwitchComb{

    public static void main( String[] args ){
        class Solution{
            private Map<Character, Set<Character>> typeToIngredients;

            private Solution(){
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

        System.out.println( new Solution().getAllCombine( 5 ) );
    }
}
