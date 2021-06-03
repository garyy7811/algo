import java.util.concurrent.Callable;

interface Bird {
    Egg lay();
}

class Chicken implements Bird{
    public Chicken() {
    }

    public static void main(String[] args) throws Exception {
        Chicken chicken = new Chicken();
        System.out.println(chicken instanceof Bird);
    }

    @Override
    public Egg lay() {
        return new Egg( Chicken::new) ;
    }
}

class Egg {
    private final Callable<Bird> birdType;

    public Egg(Callable<Bird> createBird) {
        this.birdType = createBird;
    }

    private Bird hatched;

    public Bird getHatched() {
        return hatched;
    }

    public Bird hatch() throws Exception {
        if( hatched != null ){
            throw new IllegalStateException( "Already Hatched, use getHatched for it." );
        }
        hatched = birdType.call();
        return hatched;
    }
}