import java.util.*;

public class BridgeWater{

    public static void main( String[] a ){
        Result.generateAndPrintConcordance( Arrays.asList(
                "3",
                "Given an arbitrary text document written in English, write a program that will generate a",
                "concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies.",
                "Bonus: label each word with the sentence numbers in which each occurrence appeared.",
                "Bonus: each\nword with the sentence numbers in which each occurrence appeared."
        ) );
    }


    static class Result{


        public static void generateAndPrintConcordance( List<String> inputLines ){
            TreeMap<String, List<String>> wordToLineNums = new TreeMap<>();
            int len = Integer.parseInt( inputLines.get( 0 ) );
            for( int i = 1; i < len; i++ ){
                String line = inputLines.get( i );
                String[] words = splitLineToWords( line );
                for( int j = 0; j < words.length; j++ ){
                    String word = words[ j ];
                    word = removePunc( word );
                    if( word.length() == 0 ){
                        continue;
                    }
                    word = word.toLowerCase();
                    List<String> wordLst = wordToLineNums.computeIfAbsent( word, s -> new ArrayList<>() );
                    wordLst.add( Integer.toString( i ) );
                }
            }

            wordToLineNums.forEach( ( key, value ) -> {
                String x = key + ": {" + value.size() + ":" + String.join( ", ", value ) + "}";
                if( x.contains( "1,1" ) ){
                    throw new RuntimeException( key + ">>>" + value );
                }
                System.out.println( x );
            } );

        }

        public static final String REG_SPLITTER = "[ ,\\n]";//space and comma for now.

        private static String[] splitLineToWords( String line ){
            return line.split( REG_SPLITTER );
        }

        //Need a dictionary to extend this one
        //.... 12:30 am
        public static final Set<String> SPECIAL_WORDS = new HashSet<>();

        {
            SPECIAL_WORDS.add( "i.e." );
            SPECIAL_WORDS.add( "Sq." );
            SPECIAL_WORDS.add( "Mon." );
            SPECIAL_WORDS.add( "U.S." );
        }

        /**
         * todo://should be an abstract method to be extended or overridden
         * @param word
         * @return
         */
        private static String removePunc( String word ){
            if( word.length() > 2 ){
                int lastIdx = word.length() - 1;
                if( isA2Z( word.charAt( lastIdx ) ) ){
                    return word;
                }
                if( SPECIAL_WORDS.contains( word ) ){
                    return word;
                }
                for( ; lastIdx > 0; lastIdx-- ){
                    if( isA2Z( word.charAt( lastIdx - 1 ) ) ){
                        return word.substring( 0, lastIdx );
                    }
                }

            }
            return word;
        }

        private static boolean isA2Z( char lastChar ){
            return ( lastChar >= 'a' && lastChar <= 'z' ) || ( lastChar >= 'A' && lastChar <= 'Z' );
        }

    }

}
