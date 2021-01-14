public class RemoveValsFromLinkedLst{
    public static void main( String[] args ){
        int[] vals = new int[]{ 1, 2, 3, 4, 4, 3, 6, 7 };
        Node prev = null;
        Node head = null;
        for( int val: vals ){
            Node tmp = new Node();
            if( head == null ){
                head = tmp;
            }
            tmp.val = val;
            if( prev != null ){
                prev.next = tmp;
            }
            prev = tmp;
        }

        Node rslt = removeNodesByVal( head, 4 );
        System.out.println( rslt );
    }

    private static Node removeNodesByVal( Node head, int val ){
        Node tmp = head;
        while( tmp != null ){
            if( tmp.val != val ){
                break;
            }
            tmp = tmp.next;
        }
        if( tmp == null ){
            return null;
        }
        head = tmp;
        tmp = head.next;
        Node prev = head;
        while( tmp != null ){
            if( tmp.val != val ){
                prev.next = tmp;
                prev = tmp;
            }
            else{
                prev.next = null;
            }

            tmp = tmp.next;
        }

        return head;
    }

    private static class Node{
        int val;
        Node next;
    }
}
