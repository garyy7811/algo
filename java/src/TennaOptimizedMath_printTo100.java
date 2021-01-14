public class TennaOptimizedMath_printTo100{

    public static void main( String[] args ){
        for( int i = 1; i <= 100; i++ ){
            if( i % 2 == 0 ){
                if( i % 3 == 0 ){
                    System.out.println( "The number'" + i + "'is divisible by two and three." );
                }
                else{
                    System.out.println( "The number'" + i + "'is even." );
                }
            }
            else if( i % 3 == 0 ){
                System.out.println( "The number'" + i + "'is divisible by three" );
            }
            else{
                System.out.println( "The number'" + i + "'is odd" );
            }
        }
    }

}
