public class Ad implements Comparable<Ad>,Profitable { //interface comparable, profitable

	private int pricePerPractice;
	private String content;
	private int minAge;
	private int maxAge;
	private int adCount=0; //count the appearance of specific ad


	public Ad(String content,int pricePerPractice,int minAge,int maxAge) { //constructor
		this.content = content;
		this.pricePerPractice = pricePerPractice;
		this.minAge = minAge;
		this.maxAge = maxAge;
		if(isAdultThemedAd() && minAge<18) //inappropriate ad
			throw new Under18AdException(); //run time exception
	}

	public int getProfit() { //profit of the ad for the company
		return this.pricePerPractice*adCount; 
	}
	
	public void setAdCount() { //set the count of the appearance of specific ad
		this.adCount=this.adCount+1;
	}

	public String getContent() { //content of the ad
		return this.content;
	}
	public String toString() {
		return this.content;
	}

	public int compareTo(Ad other) { //the ad can be compared by it's profit for the company
		if(this.getProfit()<other.getProfit())
			return -1;
		if(this.getProfit()>other.getProfit())
			return 1;
		else
			return 0;
	}

	public boolean isAdultThemedAd() { //method that checks if the ad is for adult
		return this.content.contains("xxxx") || this.content.contains("XXXX");
	}

	public boolean suitableForStudent(Student s) { //method that checks if the ad is appropriate for the student
		if(s.getAge()>=this.minAge && s.getAge()<=this.maxAge)
			return true;
		return false;
	}
}
