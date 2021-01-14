import java.util.*;

public class JdkDataStructrue{


    public static void main( String[] args ) throws NoSuchFieldException{

        final TreeMap<Integer, String> treeMap = new TreeMap<>();


        for( int i = 0; i < 100; i += 2 ){
            treeMap.put( i, i + "--Val" );
        }
        NavigableMap<Integer, String> integerStringNavigableMap = treeMap.subMap( 4, true, 7, true );
        System.out.println( ">>>>>" + integerStringNavigableMap.size() );
        treeMap.put( 5, "555" );
        System.out.println( "<<<<<" + integerStringNavigableMap.size() );
        System.out.println( treeMap );


        final LinkedHashMap<Long, String> linkedHashMap = new ACache<>( 3, 0.8f, false );
        for( long i = 0; i < 99999; i++ ){
            linkedHashMap.put( i, "b" + i );
        }

        System.out.println( linkedHashMap );

        final Set<Object> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add( 0 );
        linkedHashSet.add( 9 );
        linkedHashSet.add( 3 );
        linkedHashSet.add( 2 );
        linkedHashSet.add( 7 );
        linkedHashSet.add( null );
        linkedHashSet.add( null );

        for( Iterator<Object> iterator = linkedHashSet.iterator(); iterator.hasNext(); ){
            Object next = iterator.next();
            System.out.println( next );
        }

    }

    static class ACache<K, V> extends LinkedHashMap<K, V>{

        public ACache( int initialCapacity, float loadFactor ){
            super( initialCapacity, loadFactor );
        }

        public ACache( int initialCapacity ){
            super( initialCapacity );
        }

        public ACache(){
        }

        public ACache( Map<? extends K, ? extends V> m ){
            super( m );
        }

        public ACache( int initialCapacity, float loadFactor, boolean accessOrder ){
            super( initialCapacity, loadFactor, accessOrder );
        }

        @Override
        protected boolean removeEldestEntry( Map.Entry<K, V> eldest ){
            return size() > 3;
        }
    }

}
