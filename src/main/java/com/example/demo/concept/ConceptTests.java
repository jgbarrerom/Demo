package com.example.demo.concept;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ConceptTests {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicReference<String> atomicString = new AtomicReference<>("Initial Value");

    public void incrementCounter() {
        //It is like a counter++ but threadsafe
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
