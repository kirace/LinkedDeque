// file: Deque.java
// author: Kevin Irace
// date: February 10, 2014
//
// This is an API for Deque
//
public interface Deque<T> {

  public void pushLeft(T item);
  public void pushRight(T item);
  public T popLeft();
  public T popRight();
  public int size();
  public boolean isEmpty();
  public String toString();
}
