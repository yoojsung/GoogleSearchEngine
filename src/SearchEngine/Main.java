package SearchEngine;

import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Search: "); 
		WebCrawler webCrawler = new WebCrawler(in.nextLine()); //creates a web crawler with the keyword that user has typed
		webCrawler.search(); //searches for the words
		Set<String> urls = webCrawler.getUrls(); // stores the urls into the Set
		
		Result[] res = new Result[30];
		
		int cnt = 0;
		for (String s : urls) { //inserts the results from the web crawler to the array
			if (cnt == 30 || cnt == urls.size()) 
				break;
			res[cnt] = new Result(webCrawler.getDomainName(s));
			cnt ++;
		}
		printArr(res, res.length);
		
		MaxHeapPriorityQueue mhpq = new MaxHeapPriorityQueue();
		for (int i = 0; i < 20; i ++) { // stores 20 results into the priority queue created 
			mhpq.maxHeapInsert(res[i]);
		}
		
		while(true) { // program repeats forever until user enters exit
			System.out.println(" \nENTER: \n0. To maxHeapSort with PageRank Score \n1. To store results in a max heap priority queue \n2. To view the list of urls \nexit. To exit"); //please read this when testing out
			String ans = in.nextLine();
			if (ans.equals("0")) { // if user enters 0
				HeapSort sorter = new HeapSort();
				sorter.heapSort(res); //sorts the results from web crawler using heap sort
				printArr(res, res.length);
			}
			else if (ans.equals("1")) { //if user enters 1
				while(true) {
					System.out.println(" \nENTER: \n0. To print the url  with the greatest PageRank \n1. To extract the url with the greatest PageRank "
							+ "\n2. To insert a new url web link into heap \n3. To increase a url link's PageRank \n4. To view the maxHeapPriorityQueue \nexit. To exit"); 
					String ans2 = in.nextLine();
					if (ans2.equals("0") && mhpq.getHeapSize() >= 0) //if user enters 0 and checks if the heapsize is greater than or equal to 0
						System.out.println(mhpq.heapMaximum().print());
					else if (ans2.equals("1") && mhpq.getHeapSize() >= 0) // if user enters 1 and checks if the heapsize is greater than or equal to 0
						System.out.println(mhpq.heapExtractMax().print());
					else if (ans2.equals("2") && mhpq.getHeapSize() < 50) { //if user enters 2 and checks if the heapsize has exceeded the maximum size for the priority queue
						System.out.println("Url web link: ");
						mhpq.maxHeapInsert(new Result(in.nextLine()));
					}
					else if (ans2.equals("3") && mhpq.getHeapSize() >= 0) { // if user enters 3 and checks if the heapsize is greater than or equals to 0
						printArr(mhpq.getArray(), mhpq.getHeapSize());
						System.out.println(" \nChoose which url link to incrase rank");
						int inc_ind = Integer.parseInt(in.nextLine());
						System.out.println("Enter the new PageRank score (has to be greater than the previous score)");
						int inc_val = Integer.parseInt(in.nextLine());
						if (inc_val <= 400 && inc_ind <= mhpq.getHeapSize() && inc_ind >= 1 && inc_val >= mhpq.getArray()[inc_ind - 1].getScore()) //checks if the user has entered a value (score) that is less than the previous value
							mhpq.heapIncreaseKey((inc_ind) - 1, inc_val);
						else
							System.out.println("error: input(s) not satisfying the requirements");
					}
					else if (ans2.equals("4")) { //if user enters 4
						printArr(mhpq.getArray(), mhpq.getHeapSize());
					}
					else if (ans2.toLowerCase().equals("exit")) // breaks out of the look when user enters exit
						break;
					else 
						System.out.println("error: wrong input...");
				}
			}
			else if (ans.equals("2")) //if user enters 2
				printArr(res, res.length);
			else if (ans.toLowerCase().equals("exit")) //breaks out of the loop
				break;
			else 
				System.out.println("error: wrong input...");
		}
	}
	
	//prints the array with the correct format
	public static void printArr(Result[] a, int size) {
		for (int i = 0; i < size; i ++) {
			System.out.println(i + 1 + ". " + a[i].print());
		}
	}
}
