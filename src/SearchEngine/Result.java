package SearchEngine;

/**
 * This class stores the urls links and the random page rank scores from the web crawler
 * It takes in the url link as String and stores it 
 * Then it runs Math.random() method to get a random number between 1 and 100, repeats it 4 times and stores into the score variable added up together
 */
public class Result 
{
	private int score = 0;
	private String link = "";
	public Result(String url) {
		link = url;
		int freq_score = (int) (Math.random() * 100 + 1);
		int hist_score = (int) (Math.random() * 100 + 1);
		int link_score = (int) (Math.random() * 100 + 1);
		int advr_score = (int) (Math.random() * 100 + 1);
		score = freq_score + hist_score + link_score + advr_score;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getUrl() {
		return link;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String print() {
		return link + ", PageRank: " + score;
	}
}
