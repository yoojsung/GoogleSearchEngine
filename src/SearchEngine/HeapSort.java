package SearchEngine;

public class HeapSort {
	public int heapSize = 0;
	//this method will be called for sorting
	public void heapSort (Result[] a) {
		buildMaxHeap(a);
		for (int i = a.length - 1; i > 0; i--)
		{
			Result temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			heapSize --;
			maxHeapify(a, 0);
		}
	}
	//builds max heap and runs the maxHeapify method only for the parent nodes
	public void buildMaxHeap(Result[] a) {
		heapSize = a.length - 1;
		for (int i = a.length / 2 - 1; i >= 0; i --)
			maxHeapify(a, i);
	}
	
	// heapifies so that the heap has the properties of a max heap
	public void maxHeapify(Result[] a, int index) {
		int left = 2 * index + 1;
		int largest;
		if (2 * index + 1 <= heapSize && index <= heapSize && 400 - a[left].getScore() > 400 - a[index].getScore())
			largest = left;
		else 
			largest = index;
		
		int right = 2 * index + 2;
		if (2 * index + 2 <= heapSize && right <= heapSize && 400 - a[right].getScore() > 400 - a[largest].getScore())
			largest = right;
		if (largest != index)
		{
			Result temp = a[index];
			a[index] = a[largest];
			a[largest] = temp;
			maxHeapify(a, largest);
		}
		
	}
}
