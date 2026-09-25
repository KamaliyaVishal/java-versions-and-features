package java8.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * Java 8 StampedLock Demo
 * 
 * StampedLock provides three modes of locking:
 * 1. Writing: Exclusive write lock
 * 2. Reading: Pessimistic read lock (blocks writers)
 * 3. Optimistic Reading: Non-blocking read (validates stamp)
 * 
 * Benefits over ReadWriteLock:
 * - Optimistic reads don't block writers
 * - Better performance for read-heavy scenarios
 * - Supports tryConvertToWriteLock()
 */
public class StampedLockDemo {
    
    private double x, y;
    private final StampedLock lock = new StampedLock();
    
    // Write operation
    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();  // Exclusive write lock
        try {
            x += deltaX;
            y += deltaY;
            System.out.println("Moved to: (" + x + ", " + y + ")");
        } finally {
            lock.unlockWrite(stamp);
        }
    }
    
    // Optimistic read
    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();  // Non-blocking optimistic read
        double currentX = x, currentY = y;
        
        // Validate stamp (check if write occurred)
        if (!lock.validate(stamp)) {
            // Optimistic read failed, get pessimistic read lock
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
    
    // Pessimistic read
    public double[] getCoordinates() {
        long stamp = lock.readLock();  // Pessimistic read lock
        try {
            return new double[]{x, y};
        } finally {
            lock.unlockRead(stamp);
        }
    }
    
    // Try to convert read lock to write lock
    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = lock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long writeStamp = lock.tryConvertToWriteLock(stamp);
                if (writeStamp != 0L) {
                    // Successfully converted
                    stamp = writeStamp;
                    x = newX;
                    y = newY;
                    System.out.println("Moved from origin to: (" + x + ", " + y + ")");
                    break;
                } else {
                    // Conversion failed, release read lock and acquire write lock
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
            }
        } finally {
            lock.unlock(stamp);  // Unlock based on current stamp type
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== StampedLock Demo ===");
        
        StampedLockDemo demo = new StampedLockDemo();
        
        // 1. Write operations
        System.out.println("\n--- Write Operations ---");
        demo.move(3.0, 4.0);
        demo.move(1.0, 1.0);
        
        // 2. Optimistic read
        System.out.println("\n--- Optimistic Read ---");
        double distance = demo.distanceFromOrigin();
        System.out.println("Distance from origin: " + distance);
        
        // 3. Pessimistic read
        System.out.println("\n--- Pessimistic Read ---");
        double[] coords = demo.getCoordinates();
        System.out.println("Coordinates: (" + coords[0] + ", " + coords[1] + ")");
        
        // 4. Lock conversion
        System.out.println("\n--- Lock Conversion ---");
        StampedLockDemo demo2 = new StampedLockDemo();
        demo2.moveIfAtOrigin(5.0, 5.0);
        
        // 5. Multi-threaded example
        System.out.println("\n--- Multi-threaded Example ---");
        StampedLockDemo sharedDemo = new StampedLockDemo();
        
        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedDemo.move(1.0, 1.0);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Reader threads
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                double dist = sharedDemo.distanceFromOrigin();
                System.out.println("Reader1 - Distance: " + dist);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                double[] coords2 = sharedDemo.getCoordinates();
                System.out.println("Reader2 - Coordinates: (" + coords2[0] + ", " + coords2[1] + ")");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        writer.start();
        reader1.start();
        reader2.start();
        
        writer.join();
        reader1.join();
        reader2.join();
        
        System.out.println("\n=== StampedLock vs ReadWriteLock ===");
        System.out.println("StampedLock advantages:");
        System.out.println("1. Optimistic reads don't block writers");
        System.out.println("2. Better performance for read-heavy scenarios");
        System.out.println("3. Supports lock conversion (read to write)");
        System.out.println("4. Three modes: write, read, optimistic read");
        System.out.println("\nReadWriteLock:");
        System.out.println("1. Simpler API");
        System.out.println("2. Read locks block writers");
        System.out.println("3. No optimistic reading");
        
        System.out.println("\n=== Best Practices ===");
        System.out.println("1. Always validate optimistic read stamps");
        System.out.println("2. Always unlock in finally blocks");
        System.out.println("3. Use optimistic reads when reads are frequent");
        System.out.println("4. Use pessimistic reads when consistency is critical");
        System.out.println("5. Stamps are not reentrant - don't use same stamp twice");
    }
}

