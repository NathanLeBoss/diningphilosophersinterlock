package diningphiloLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private static int stickCount = 0;

    private boolean iAmFree = true;
    private final int myNumber;
    private Lock lock = new ReentrantLock();

    public ChopStick() {
        myNumber = ++stickCount;
    }

    synchronized public boolean tryTake(int delai) throws InterruptedException {
        // essai de prendre, sinon false
        if( lock.tryLock(delai, TimeUnit.MILLISECONDS)) {
            System.out.println("Stick " + myNumber + " taken");
            return true;
        }
        return false;
    }

    synchronized public void release() {
        // unlock
        System.out.println("Stick " + myNumber + " released");
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
