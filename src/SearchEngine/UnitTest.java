package SearchEngine;

public class UnitTest {
	int heapSize;
	int[] a;
	
	public UnitTest()
	{
		heapSize = 0;
		a = new int[30];
	}
	
	// heapifies so that the heap has the properties of a max heap
	public void maxHeapify(int index) {
		int left = 2 * index + 1;
		int largest;
		if (2 * index + 1 < heapSize && index <= heapSize && a[left] > a[index])
			largest = left;
		else 
			largest = index;
		
		int right = 2 * index + 2;
		if (2 * index + 2 < heapSize && right <= heapSize && a[right] > a[largest])
			largest = right;
		if (largest != index)
		{
			int temp = a[index];
			a[index] = a[largest];
			a[largest] = temp;
			maxHeapify(largest);
		}
		
	}
	
	public int heapMaximum() {
		return a[0];
	}
	
	public void maxHeapInsert(int r) {
		heapSize ++;
		a[heapSize - 1] = -2147483648;
		heapIncreaseKey(heapSize - 1, r);
	}
	
	public int heapExtractMax() {
		if (heapSize <= 0) 
		{
			System.out.println("error: empty");
			System.exit(0);
		}
		int max = a[0];
		a[0] = a[heapSize - 1];
		heapSize --;
		maxHeapify(0);
		return max;
	}
	
	public void heapIncreaseKey(int index, int key) {
		if (key < a[index]) 
		{
			System.out.println("error: key smaller");
		}
		
		a[index] = key;
		while (index > 0 && a[(index - 1) / 2] < a[index]) {
			int temp = a[(index - 1) / 2];
			a[(index - 1) / 2] = a[index];
			a[index] = temp;
			index = (index - 1) / 2;
		}
	}
	
	
	public static void main(String[] args) {

		UnitTest q = new UnitTest();
		q.maxHeapInsert(1);
		q.maxHeapInsert(6);
		q.maxHeapInsert(8);
		q.maxHeapInsert(3);
		System.out.println(q.heapExtractMax());
		System.out.println(q.heapExtractMax());
		System.out.println(q.heapExtractMax());
		q.maxHeapInsert(10);
		System.out.println(q.heapExtractMax());
		System.out.println(q.heapExtractMax());
		System.out.println(q.heapExtractMax());
		
	}
	public static void toString(int[] a) {
		for (int i : a) 
			System.out.print(i + " ");
	}
}