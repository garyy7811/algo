public class ReadNumber{

    public static void main( String[] a ){
        System.out.println( readNum( 99790123 ) );
        System.out.println( readNum( 3 ) );
    }

    private static String readNum( int num ){
        int numOfMillon = num / 1000000;
        int numOfThousand = ( num - numOfMillon * 1000000 ) / 1000;
        int numOfHundred = ( num - numOfMillon * 1000 * 1000 - numOfThousand * 1000 ) / 100;
        int lessHundred = num % 100;


        return readLessHundred( numOfMillon, "M" ) + readLessHundred( numOfThousand, "K" ) + readLessHundred( numOfHundred, "hundred" ) + lessHundred;
    }

    private static String readLessHundred( int lessHundred, String s ){
        if( lessHundred > 0 ){
            return lessHundred + s;
        }
        else{
            return "";
        }
    }

}
