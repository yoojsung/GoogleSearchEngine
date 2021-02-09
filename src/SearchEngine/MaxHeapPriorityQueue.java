package SearchEngine;

public class MaxHeapPriorityQueue {
	int heapSize;
	private Result[] a;
	
	public MaxHeapPriorityQueue()
	{
		heapSize = 0;
		a = new Result[50];
	}
	
	/**
	 * compares the index with left and right children (if it has one) and switches with the largest value 
	 * if switched, this method recursively called again with the index of the either left or right child (whichever it switched with)
	 * when completed, the heap will have the properties of a max heap
	 * @param index
	 * index to heapify
	 */
	public void maxHeapify(int index) {
		int left = 2 * index + 1;
		int largest;
		if (2 * index + 1 < heapSize && index <= heapSize && a[left].getScore() > a[index].getScore())
			largest = left;
		else 
			largest = index;
		
		int right = 2 * index + 2;
		if (2 * index + 2 < heapSize && right <= heapSize && a[right].getScore() > a[largest].getScore())
			largest = right;
		if (largest != index)
		{
			Result temp = a[index];
			a[index] = a[largest];
			a[largest] = temp;
			maxHeapify(largest);
		}
		
	}
	
	/**
	 * 
	 * @return
	 * result with the greatest score from the heap
	 */
	public Result heapMaximum() {
		return a[0];
	}
	
	/**
	 * inserts a result using the heapIncreaseKey method
	 * @param r
	 * result to be inserted into the heap
	 */
	public void maxHeapInsert(Result r) {
		heapSize ++;
		a[heapSize - 1] = new Result(r.getUrl());
		a[heapSize - 1].setScore(-2147483648);
		heapIncreaseKey(heapSize - 1, r.getScore());
	}
	
	/**
	 * extracts/removes the result from the heap
	 * @return
	 * result with the greatest score from the heap
	 */
	public Result heapExtractMax() {
		if (heapSize <= 0) 
		{
			System.out.println("error: empty");
			System.exit(0);
		}
		Result max = a[0];
		a[0] = a[heapSize - 1];
		heapSize --;
		maxHeapify(0);
		return max;
	}
	
	/**
	 * increases the score of a result 
	 * @param index 
	 * index of the element to be changed, or rather, increased
	 * @param key 
	 * the score
	 */
	public void heapIncreaseKey(int index, int key) {
		if (key < a[index].getScore()) 
		{
			System.out.println("error: key smaller");
		}
		
		a[index].setScore(key);
		while (index > 0 && a[(index - 1) / 2].getScore() < a[index].getScore()) {
			Result temp = a[(index - 1) / 2];
			a[(index - 1) / 2] = a[index];
			a[index] = temp;
			index = (index - 1) / 2;
		}
	}
	
	//This method returns the array a which is private
	public Result[] getArray() {
		return a;
	}
	
	//This method returns the heapSize
	public int getHeapSize() {
		return heapSize;
	}
}
