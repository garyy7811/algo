import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User: GaryY
 * Date: 5/13/2018
 */
public class BnbPagenation{

    public static void main( String[] args ){
        int resultsPerPage = 5;
        String[] results = new String[]{
                //                0,  1,     2    ,  3
                //               "1,  28,  300.6,   San Francisco",
                "1,28,300.6,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,203.4,Oakland",
                "6,8,202.9,San Francisco",
                "6,10,199.8,San Francisco",
                "1,16,190.5,San Francisco",
                "6,29,185.3,San Francisco",
                "7,20,180.0,Oakland", "6,21,162.2,San Francisco", "2,18,161.7,San Jose", "2,30,149.8,San Jose", "3,76,146.7,San Francisco",
                "2,14,141.8,San Jose" };

        String[] rslt = paginate( resultsPerPage, results );
        Stream.of( rslt ).forEach( s -> {
            System.out.println( s );
        } );
    }


    static String[] paginate( int num, String[] results ){
        int pageNum = results.length / num;
        LinkedList<Page> pageLst = new LinkedList<>();
        for( int i = 0; i < pageNum; i++ ){
            final Page size = new Page();
            size.size = num;
            pageLst.add( size );
        }
        final Page lastPage = new Page();
        lastPage.size = results.length % num;
        pageLst.add( lastPage );

        for( String record: results ){
            String[] recordArr = record.split( "," );
            int recordHost = Integer.parseInt( recordArr[ 0 ] );

            Page page = null;
            for( Page p: pageLst ){
                if( ( ! p.hostSet.contains( recordHost ) ) && p.records.size() < p.size ){
                    page = p;
                    break;
                }
            }
            if( page == null ){
                for( Page p: pageLst ){
                    if( p.records.size() < p.size ){
                        page = p;
                        break;
                    }
                }
            }
            if( page == null ){
                throw new Error();
            }
            page.hostSet.add( recordHost );
            page.records.add( record );
        }

        final List<String> rtLst = pageLst.stream().map( l -> {
            l.records.add( "" );
            return l.records;
        } ).flatMap( Collection::stream ).collect( Collectors.toList() );
        rtLst.remove( rtLst.size() - 1 );

        return rtLst.toArray( new String[ 0 ] );
    }


    static class Page{
        int size;
        Set<Integer> hostSet = new HashSet<>();
        ArrayList<String> records = new ArrayList<>();
    }

}
