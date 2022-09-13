public class QuantitativeQuestion extends Question { //quantitative question is kind of question 
	private String formula;

	public QuantitativeQuestion(String content, int level, char answer, String[] choices, String formula) { //constructor
		super(content,level,answer,choices);
		this.formula = formula;
	}

	public String getFormula() { //get the formula of the question
		return this.formula;
	}
	
	

	public void updateLevel() { //implementation of abstract method, update the level of the question by the method conditions
		boolean needRestart=false;
		if(getMoneQuestions()>0) {
			if((getMoneFalse()/getMoneQuestions())>0.7) {
				upLevel();
				needRestart=true;
			}
			if((getMoneFalse()/getMoneQuestions())<0.25) {
				downLevel();
				needRestart=true;
			}
			if(needRestart==true) 
				restart(); //zero all the counters (get the last update)
		}
	}
}