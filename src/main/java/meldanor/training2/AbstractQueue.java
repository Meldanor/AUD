package meldanor.training2;

import java.util.NoSuchElementException;

/**
 * Interface for an ADT queue.
 */
public abstract class AbstractQueue<T> {
    
  /// create empty queue
  protected AbstractQueue() {}
    
  /// Is queue empty?
  public abstract boolean is_empty();

  /** Get front element of queue.
      Requires <code>!is_empty()</code>.
      @throws NoSuchElementException
      @return front element
  */
  public abstract T front();

  /** Remove front element from queue.
      Requires <code>!is_empty()</code>.
      @throws NoSuchElementException
      @return removed element
  */
  public abstract T dequeue();

  /** Enqueue element at end of queue.        
      @param x new element
  */
  public abstract void enqueue(T x);        

  @Override
  public String toString() { return "AbstractQueue"; }
}
