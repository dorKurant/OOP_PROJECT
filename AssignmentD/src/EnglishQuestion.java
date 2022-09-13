public class EnglishQuestion extends Question { //English question is kind of question 
	private String hint;
	
	public EnglishQuestion (String content, int level, char answer, String[] choices, String hint) { //constructor
		super(content,level,answer,choices);
		this.hint = hint;
	}

	public String getHint() { //get the hint of the question
		return this.hint;
	}

	public void updateLevel() { //implementation of abstract method, update the level of the question by the method conditions
		boolean needRestart=false;
		if(getMoneQuestions()>0) {
			if((getMoneFalse()/getMoneQuestions())>0.8) {
				upLevel();
				needRestart=true;
			}
			if((getMoneFalse()/getMoneQuestions())<0.2) {
				downLevel();
				needRestart=true;
			}
			if(needRestart==true) 
				restart(); //zero all the counters (get the last update)
		}
	}
}
