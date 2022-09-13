abstract public class Question implements Comparable<Question> { //abstract class, interface comparable

	private String content; 
	private int level;
	private char answer;
	private String[] choices;
	protected int moneQuestion=0; //count the questions
	protected int moneSuccess=0; //count the correct answers
	protected int moneFalse=0; //count the wrong answers
	protected boolean isUpdate; //if the question was update

	public Question(String content, int level, char answer, String[] choices) { //constructor
		this.content = content;
		this.level = level;
		this.answer = answer;
		this.choices = choices;
		if(level<1 || level>10) 
			throw new LevelNotValidException(); //run time exception
	}


	public int getMoneFalse() { //get the wrong answers
		return this.moneFalse;
	}

	public int getMoneQuestions() { //get the number of questions
		return this.moneQuestion;
	}

	public void setMoneSuccess() { //update the correct answers
		this.moneSuccess=this.moneSuccess+1;
	}

	public void setMoneFalse() { //update the wrong answers
		this.moneFalse=this.moneFalse+1;
	}

	public void setMoneQuestion() { //update the number of questions
		this.moneQuestion=this.moneQuestion+1;
	}

	public int getLevel() { //get the level of question
		return this.level;
	}

	public char getAnswer() { //get the answer of question
		return this.answer;
	}

	public String[] getChoices() { //get the options for answer
		return this.choices;
	}

	public String getChoices(int i) { //get specific option for answer
		return this.choices[i];
	}

	public int compareTo(Question other) { //the question can be compared by it's level
		if(this.getLevel()<other.getLevel())
			return -1;
		if(this.getLevel()>other.getLevel())
			return 1;
		else
			return 0;
	}
	
	abstract public void updateLevel(); //abstract method, needs to be implemented in all it's extends classes
	
	public boolean getIsUpdate() {
		return isUpdate;
	}
	
	public void SetIsUpdate() {
		isUpdate = false;
	}
	
	public void upLevel() { //raise the level of the question
		if(level<10) {
			 	this.level = this.level++;
			 	isUpdate = true;
			}
	}

	public void downLevel() { //low the level of the question
		if(level>1) {
			this.level = this.level--;
			isUpdate = true;
		}
	}

	public void restart() { //zero all the counters (get the last update)
		moneQuestion = 0;
		moneSuccess = 0;
		moneFalse = 0;
	}

	public String toString() {
		return this.content;
	}
}