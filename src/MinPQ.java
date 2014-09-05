/*************************************************************************
 *  Compilation:  javac MaxPQ.java
 *  Execution:    java MaxPQ
 *  
 *  Generic max priority queue implementation with a binary heap.
 *
 *  % java MaxPQ
 *
 *  This code is adapted from Chapter 9 in "Algorithms in Java", Third
 *  Edition, by Robert Sedgewick, Addison-Wesley, 2004.
 *
 *  The amortized performance guarantee is O(log N) per operation. It
 *  is only an amortized guarantee (and not a worst-case) because of
 *  the repeated doubling operation.
 *
 *************************************************************************/

public class MinPQ<T extends Comparable<T>> {
    private T[] pq;     // store element at indices 1 to N
    private int N;         // number of elements on priority queue

    // set initial capacity of heap to hold given number of elements
    @SuppressWarnings("unchecked")
	public MinPQ(int maxN) {
        pq = (T[]) new Comparable[maxN + 1];
        N = 0;
    }

    // set initial capacity of heap to hold 0 elements
    public MinPQ() { this(0); }

    public boolean isEmpty() { return N == 0;  }  // is the PQ empty?
    public int size()        { return N;       }  // # elements on PQ
    public T min()        { return pq[1];   }  // largest element

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];
        for (int i = 0; i <= N; i++) temp[i] = pq[i];
        pq = temp;
    }


    // add a new element to the priority queue
    public void insert(T x) {

        // double size of array if necessary
        if (N >= pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++N] = x;
        swim(N);
    }

    // delete and return the maximum element, restoring the heap-order invariant
    public T getmin() {
        if (N == 0) return null;
        T min = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;             // to help with garbage collection
        return min;
    }


   /***********************************************************************
    * Helper functions to restore the heap invariant.
    **********************************************************************/

    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j+1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

   /***********************************************************************
    * Helper functions for comparisons and swaps.
    **********************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }



}
