import java.util.NoSuchElementException;

public class UnorderedMaxPQ <Key extends Comparable<Key>>
{
	private Key[] pq;
	private int N;
	
	public UnorderedMaxPQ()
	{
		pq = (Key[]) new Comparable[1];
		N = -1;
	}
	
	public UnorderedMaxPQ(Key[] a) 
	{
		if (a == null) throw new NullPointerException("Input array is null.");
		
		pq = a;
		N = a.length - 1;
	}
	
	public void insert(Key v) 
	{
		if (N == pq.length - 1) resize(2 * pq.length);
		
		pq[++N] = v;
	}
	
	public Key delMax() 
	{
		 if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		 
		int max = 0;
		
		for (int i = 0; i < N; i++)
		{
			if (pq[max].compareTo(pq[i]) < 0) max = i; 
		}
		
		Key value = pq[max];
		pq[max] = pq[N - 1];
		pq[N--] = null;
		
		return value;
	}
	
	public boolean isEmpty() 
	{
		return N == -1;
	}
	
	public Key max() 
	{
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		
		int max = 0;
		
		for (int i = 0; i < N; i++)
		{
			if (pq[max].compareTo(pq[i]) < 0) max = i; 
		}
		
		Key value = pq[max];
		pq[max] = pq[N - 1];
		
		return value;
	}
	
	public int size()
	{
		return N + 1;
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
}
