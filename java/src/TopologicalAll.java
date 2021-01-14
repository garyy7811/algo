import java.util.*;

/**
 * https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-graph/
 * Java program to print all topolgical sorts of a graph
 */
public class TopologicalAll{

    // Driver code
    public static void main( String[] args ){

        // Create a graph given in the above diagram
        Graph graph = new Graph( 6 );
        graph.addEdge( 5, 2 );
        graph.addEdge( 5, 0 );
        graph.addEdge( 4, 0 );
        graph.addEdge( 4, 1 );
        graph.addEdge( 2, 3 );
        graph.addEdge( 3, 1 );

        System.out.println( "All Topological sorts" );
        graph.allTopologicalSorts();
    }

    static class Graph{
        int size; // No. of vertices

        List<Integer>[] adjListArray;

        public Graph( int size ){
            this.size = size;
            this.adjListArray = new LinkedList[ size ];
            for( int i = 0; i < size; i++ ){
                adjListArray[ i ] = new LinkedList<>();
            }
        }

        // Utility function to add edge
        public void addEdge( int src, int dest ){
            this.adjListArray[ src ].add( dest );
        }

        // Main recursive function to print all possible
        // topological sorts
        private void allTopologicalSortsUtil( boolean[] visited,
                                              int[] indegree, ArrayList<Integer> stack ){
            // To indicate whether all topological are found
            // or not
            boolean flag = false;

            for( int i = 0; i < this.size; i++ ){
                // If indegree is 0 and not yet visited then
                // only choose that vertex
                if( ! visited[ i ] && indegree[ i ] == 0 ){

                    System.out.println( "visiting>>" + i );
                    // including in result
                    visited[ i ] = true;
                    stack.add( i );
                    for( int adjacent: this.adjListArray[ i ] ){
                        indegree[ adjacent ]--;
                    }
                    allTopologicalSortsUtil( visited, indegree, stack );

                    // resetting visited, res and indegree for
                    // backtracking
                    visited[ i ] = false;
                    stack.remove( stack.size() - 1 );
                    for( int adjacent: this.adjListArray[ i ] ){
                        indegree[ adjacent ]++;
                    }

                    flag = true;
                }
            }
            // We reach here if all vertices are visited.
            // So we print the solution here
            if( ! flag ){
                stack.forEach( i -> System.out.print( i + " " ) );
                System.out.println();
            }

        }

        // The function does all Topological Sort.
        // It uses recursive alltopologicalSortUtil()
        public void allTopologicalSorts(){
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[ this.size ];

            int[] indegree = new int[ this.size ];

            for( List<Integer> lst: this.adjListArray ){
                for( int var: lst ){
                    indegree[ var ]++;
                }
            }
            for( int i = 0; i < indegree.length; i++ ){
                System.out.println( "Indegree[" + i + "]:" + indegree[ i ] );
            }

            ArrayList<Integer> stack = new ArrayList<>();

            allTopologicalSortsUtil( visited, indegree, stack );
        }

    }
}

