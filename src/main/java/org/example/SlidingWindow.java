package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimiter{
    Queue<Integer> slidingWindow;
    int time;
    int bucketCap;

    public SlidingWindow(int time, int cap){
        this.time = time;
        this.bucketCap=cap;
        slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        updateQueue(currentTime);
        if(slidingWindow.size() < bucketCap){
            slidingWindow.offer((int) currentTime);
            System.out.println("Granted");
            return true;
        }
        System.out.println("Not Granted");
        return false;
    }

    private void updateQueue(long currentTime) {
        if(slidingWindow.isEmpty()) return;
        long time = (currentTime - slidingWindow.peek())/1000;
        while(time>= this.time){
            slidingWindow.poll();
            if(slidingWindow.isEmpty()) break;
            time = (currentTime - slidingWindow.peek())/1000;
        }
    }


    public static void main(String[] args){
        SlidingWindow slidingWindow = new SlidingWindow(10, 1);
        for(int i = 0; i < 10000;i++){
            Runnable r1 = () -> {slidingWindow.grantAccess();};
            new Thread(r1).start();
        }
    }
}
