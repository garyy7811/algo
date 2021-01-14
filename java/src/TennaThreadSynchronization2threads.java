import java.util.concurrent.atomic.AtomicInteger;

public class TennaThreadSynchronization2threads{


    public static void main( String[] args ){

        AtomicInteger count = new AtomicInteger( 0 );

        class CountingThread extends Thread{

            private CountingThread( AtomicInteger count, int id ){
                this.count = count;
                this.id = id;
            }

            private final int id;
            private final AtomicInteger count;

            @Override
            public void run(){
                try{
                    synchronized( count ){
                        while( count.get() <= 100 ){
                            if( count.get() % 2 == 0 && id == 2 ){
                                count.incrementAndGet();
                                System.out.println( "Thread " + id + " notify >>.." + count.get() );
                                count.notify();
                                System.out.println( "Thread " + id + " notify <<.." + count.get() );
                            }
                            else if( count.get() % 2 == 1 && id == 1 ){
                                count.incrementAndGet();
                                System.out.println( "Thread " + id + " notify >>.." + count.get() );
                                count.notify();
                                System.out.println( "Thread " + id + " notify >>.." + count.get() );
                            }
                            else{
                                System.out.println( "Thread " + id + " wait >>.." + count.get() );
                                count.wait();
                                System.out.println( "Thread " + id + " wait <<.." + count.get() );
                            }
                        }
                    }
                }
                catch( InterruptedException e ){
                    e.printStackTrace();
                }
            }
        }

        CountingThread odd = new CountingThread( count, 1 );
        CountingThread even = new CountingThread( count, 2 );
        odd.start();
        even.start();
    }

}
