package hw20;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PetrolStation {
    private int amount;
    public PetrolStation(int initialAmount) {
        amount = initialAmount;
    }
    public synchronized void doRefuel(int requestedAmount) {
        System.out.println ("Refueling started");
        if (amount >= requestedAmount) {
            try {
                Thread.sleep (3000 + (int) (Math.random() * 8000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            amount -= requestedAmount;
            System.out.println ("Refueling completed. Remaining fuel: " + amount);
        } else {
            System.out.println ("Insufficient fuel at the station");
        }
    }

    public static void main (String[] args) {
        PetrolStation petrolStation = new PetrolStation(100);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            int requestedAmount = 20;
            executorService.execute (() -> petrolStation.doRefuel(requestedAmount));
        }
        executorService.shutdown ();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }
}
