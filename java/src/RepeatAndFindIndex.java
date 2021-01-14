import java.util.LinkedList;

public class RepeatAndFindIndex{

    /*
encoded: "hello2world3"
decoded: "hellohelloworldhellohelloworldhellohelloworld"

p = 16
rtn: "h"

prevLen = 5 x 2 = 10

prevLen +=world.len = 15 x 3 > 16

 "hellohello" 10 < 16

 p=6^ world2

 hellohelloworld 15

 16
encoded: "hello2world3anna4"

>>
 36
encoded: "hellohelloworld3anna4"




encoded: "hello23world3"
decoded: "hellohellohellohellohellohello ~~~~"


encoded: "2hello23world3"
decoded: "hellohellohellohellohellohello ~~~~"

encoded: "a9999999999999999999"
decoded: "aaaaa"

*/

    public static void main( String[] args ){
        for( int i = 0; i < 5; i ++ ){
            decode( "Abc2Def3Ghi3", i );
        }

        for( int i = 39; i < 89; i ++ ){
            decode( "Abc2Def3Ghi3", i );
        }
    }

    public static char decode( final String str, final int index ){

        class Node{
            public Node( Node prev, int num, int numIdx ){
                this.prev = prev;
                this.num = num;
                this.numIdx = numIdx;
                if( prev != null ){
                    this.wordLen = numIdx - prev.numIdx - 1;
                    this.substr = str.substring( prev.numIdx + 1, numIdx );
                    prev.next = this;
                    this.total = ( prev.total + wordLen ) * num;
                }
                else{
                    this.wordLen = numIdx;
                    this.substr = str.substring( 0, numIdx );
                    this.total = wordLen * num;
                }
            }

            Node prev;
            Node next;

            String substr = null;
            int num = 0;
            int numIdx = 0;
            int wordLen = 0;
            int total = 0;

            public char getChar( int index ){
                System.out.println( toString() );
                if( prev != null ){
                    while( index + 1 > prev.total + wordLen ){
                        index -= ( prev.total + wordLen );
                    }
                    if( index + 1 > prev.total ){
                        return this.substr.charAt( index - prev.total );
                    }
                    return prev.getChar( index );
                }

                while( index + 1 > wordLen ){
                    index -= wordLen;
                }
                return str.charAt( index );
            }

            @Override
            public String toString(){
                return "Node{" +
                        "substr='" + substr + '\'' +
                        ", num=" + num +
                        ", numIdx=" + numIdx +
                        ", wordLen=" + wordLen +
                        ", total=" + total +
                        '}';
            }
        }

        LinkedList<Node> lst = new LinkedList<>();
        StringBuilder builder = new StringBuilder( "" );
        for( int i = 0; i < str.length(); i++ ){
            char c = str.charAt( i );
            if( c > '1' && c <= '9' ){
                int num = c - 48;
                Node nwnd = new Node( lst.size() > 0 ? lst.getLast() : null, num, i );
                lst.addLast( nwnd );


                StringBuilder nb = new StringBuilder( "" );
                for( int j = num; j > 0; j-- ){
                    nb.append( builder );
                }
                builder = nb;
                if( nwnd.total > index ){
                    System.out.println( str );
                    char corr = builder.charAt( index );
                    System.out.println( "builder:" + builder + ">[" + index + "]=" + corr );
                    char rt = nwnd.getChar( index );
                    if( rt != corr ){
                        throw new Error( "wrong!" );
                    }
                    return rt;
                }
            }
            else{
                builder.append( c );
            }
        }

        return 0;
    }

}
