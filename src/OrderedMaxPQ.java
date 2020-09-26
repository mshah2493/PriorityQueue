import java.util.NoSuchElementException;

public class OrderedMaxPQ <Key extends Comparable<Key>>
{
	private Key[] pq;
	private int N;
	
	public OrderedMaxPQ()
	{
		pq = (Key[]) new Comparable[1];
		N = 0;
	}
	
	public OrderedMaxPQ(Key[] a) 
	{
		if (a == null) throw new NullPointerException("Input array is null.");
		
		int N = a.length - 1;
        
		pq = (Key[]) new Comparable[a.length + 1];
        
		for (int i = 0; i <= N; i++)
            pq[i+1] = a[i];
	}
	
	public void insert(Key v) 
	{
		if (N == pq.length - 1) resize(2 * pq.length);
		
		pq[++N] = v;
		swim(N);
	}
	
	public Key delMax() 
	{
		 if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		
		Key value = pq[1];
		pq[1] = pq[N];
		pq[N--] = null;
		
		sink(1);
		
		if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);
		
		return value;
	}
	
	public boolean isEmpty() 
	{
		return N == 0;
	}
	
	public Key max() 
	{
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		
		return pq[0];
	}
	
	public int size()
	{
		return N;
	}
	
	private void resize(int capacity) 
	{
        assert capacity > N;
        
        Key[] temp = (Key[]) new Comparable[capacity];
        
        for (int i = 1; i <= N; i++) 
        {
            temp[i] = pq[i];
        }
        
        pq = temp;
    }
	
	public void sort() // heap sort down
	{
		for (int k = N/2; k >= 1; k--) 
        	sink(k);
		
		while (N > 1)
		{
			Key value = pq[1];
			pq[N] = pq[1];
			pq[N--] = value;
			
			sink(1);
		}
	}
	
	private void swim(int k)
	{
		while (k > 1 && pq[k/2].compareTo(pq[k]) < 0)
		{
			Key temp = pq[k];
			pq[k] = pq[k/2];
			pq[k/2] = temp;
			
			k = k / 2;
		}
	}
	
	private void sink(int k)
	{
		while (2 * k <= N)
		{
			int j = 2 * k;
			
			if (j < N && pq[j].compareTo(pq[j + 1]) < 0) ++j;
			if (pq[k].compareTo(pq[j]) < 0) break;
			
			Key temp = pq[k];
			pq[k] = pq[j];
			pq[j] = temp;
			
			k = j;
		}
	}
}
