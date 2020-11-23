package diningphilosophers;

public class ChopStick {

    private static int stickCount = 0;

    private boolean iAmFree = true;
    private final int myNumber;

    public ChopStick() {
        myNumber = ++stickCount;
    }

    synchronized public boolean tryTake(int delai) throws InterruptedException {
        if(!iAmFree){
            try{
                wait(delai);
            } catch (InterruptedException exception){}
            if(!iAmFree) return false;
        }
        iAmFree = false;
        return true;
    }

    synchronized public void release() {
        // assert !iAmFree;
        System.out.println("Stick " + myNumber + " Released");
        iAmFree = true;
        notifyAll(); // On prévient ceux qui attendent que la baguette soit libre
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
