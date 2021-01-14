public class MaxSubArraySum{

    public static int[] input = new int[]{ 1, - 1, 0, 3, 5, 0, - 9, - 1, 2, 3, - 5, };

    public static int MAX = Integer.MIN_VALUE;

    public static void main( String[] args ){
        int tmpMax = MAX;
        for( int val : input ){
            if( tmpMax < 0 ){
                tmpMax = val;
            }
            else{
                tmpMax += val;
            }

            MAX = Math.max( MAX, tmpMax );
        }
    }
}
