package java9.reactivestreams;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Java 9 Reactive Streams
 * Demonstrates the Flow API for asynchronous stream processing
 * 
 * The Flow API provides interfaces for implementing reactive streams:
 * - Publisher: Produces items
 * - Subscriber: Consumes items
 * - Subscription: Controls flow between Publisher and Subscriber
 * - Processor: Combines Publisher and Subscriber
 */
public class ReactiveStreamsDemo {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Reactive Streams Demo ===");
        
        // 1. Simple Publisher-Subscriber
        System.out.println("\n--- Simple Publisher-Subscriber ---");
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;
            
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("Subscriber: Subscribed");
                // Request first item
                subscription.request(1);
            }
            
            @Override
            public void onNext(String item) {
                System.out.println("Subscriber: Received - " + item);
                // Request next item
                subscription.request(1);
            }
            
            @Override
            public void onError(Throwable throwable) {
                System.err.println("Subscriber: Error - " + throwable.getMessage());
            }
            
            @Override
            public void onComplete() {
                System.out.println("Subscriber: Completed");
            }
        };
        
        publisher.subscribe(subscriber);
        
        // Publish items
        publisher.submit("Item 1");
        publisher.submit("Item 2");
        publisher.submit("Item 3");
        publisher.close();
        
        // Wait for processing
        Thread.sleep(100);
        
        // 2. Backpressure Example
        System.out.println("\n--- Backpressure Example ---");
        SubmissionPublisher<Integer> numberPublisher = new SubmissionPublisher<>();
        
        Flow.Subscriber<Integer> slowSubscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;
            private int count = 0;
            
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("Slow Subscriber: Subscribed, requesting 2 items");
                subscription.request(2); // Request only 2 items initially
            }
            
            @Override
            public void onNext(Integer item) {
                System.out.println("Slow Subscriber: Processing " + item + " (slowly)");
                count++;
                try {
                    Thread.sleep(200); // Simulate slow processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // Request next item after processing
                if (count < 5) {
                    subscription.request(1);
                }
            }
            
            @Override
            public void onError(Throwable throwable) {
                System.err.println("Slow Subscriber: Error - " + throwable.getMessage());
            }
            
            @Override
            public void onComplete() {
                System.out.println("Slow Subscriber: Completed processing " + count + " items");
            }
        };
        
        numberPublisher.subscribe(slowSubscriber);
        
        // Try to publish many items quickly
        for (int i = 1; i <= 10; i++) {
            numberPublisher.submit(i);
            System.out.println("Publisher: Submitted " + i);
        }
        numberPublisher.close();
        
        Thread.sleep(2000);
        
        // 3. Processor Example (combines Publisher and Subscriber)
        System.out.println("\n--- Processor Example (Transform) ---");
        SubmissionPublisher<String> sourcePublisher = new SubmissionPublisher<>();
        
        // Create a processor that transforms strings to uppercase
        Flow.Processor<String, String> uppercaseProcessor = new Flow.Processor<String, String>() {
            private Flow.Subscription subscription;
            private Flow.Subscriber<? super String> subscriber;
            
            @Override
            public void subscribe(Flow.Subscriber<? super String> subscriber) {
                this.subscriber = subscriber;
                subscriber.onSubscribe(new Flow.Subscription() {
                    @Override
                    public void request(long n) {
                        if (subscription != null) {
                            subscription.request(n);
                        }
                    }
                    
                    @Override
                    public void cancel() {
                        if (subscription != null) {
                            subscription.cancel();
                        }
                    }
                });
            }
            
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }
            
            @Override
            public void onNext(String item) {
                if (subscriber != null) {
                    subscriber.onNext(item.toUpperCase());
                    subscription.request(1);
                }
            }
            
            @Override
            public void onError(Throwable throwable) {
                if (subscriber != null) {
                    subscriber.onError(throwable);
                }
            }
            
            @Override
            public void onComplete() {
                if (subscriber != null) {
                    subscriber.onComplete();
                }
            }
        };
        
        Flow.Subscriber<String> finalSubscriber = new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }
            
            @Override
            public void onNext(String item) {
                System.out.println("Final Subscriber: " + item);
            }
            
            @Override
            public void onError(Throwable throwable) {
                System.err.println("Final Subscriber: Error");
            }
            
            @Override
            public void onComplete() {
                System.out.println("Final Subscriber: Completed");
            }
        };
        
        uppercaseProcessor.subscribe(finalSubscriber);
        sourcePublisher.subscribe(uppercaseProcessor);
        
        sourcePublisher.submit("hello");
        sourcePublisher.submit("world");
        sourcePublisher.submit("java");
        sourcePublisher.close();
        
        Thread.sleep(200);
        
        System.out.println("\n=== Reactive Streams Benefits ===");
        System.out.println("1. Asynchronous processing");
        System.out.println("2. Backpressure handling");
        System.out.println("3. Non-blocking operations");
        System.out.println("4. Composable streams");
        System.out.println("5. Standard API for reactive programming");
    }
}

