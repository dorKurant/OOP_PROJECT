import java.lang.Math;
import java.util.Vector;
public class Practice {

	private Student student; 
	private Vector<Question> questions; //vector of questions 
	private Ad ad; 
	private Question question;
	private int moneMathTrue=0; //count all math correct answers questions
	private int moneEnglishTrue=0; //count all English correct answers
	private int moneMathQuestion=0; //count all math questions
	private int moneEnglishQuestion=0; //count all English questions
	boolean isMath; 
	boolean isEnglish;

	public Practice(Student student, Vector<Question> questions, Ad ad) { //constructor for practice  
		this.student = student;
		this.questions = questions;
		this.ad = ad;
		order(); //method that order the questions by level 
		student.setPractice(); //set the counter of practice for student 
		ad.setAdCount(); //set the counter for specific ad  
	}

	private void order() { //method that order the questions vector from easy to difficult
		for(int j=0;j<questions.size()-1;j++) {	//bubble sort, run all object and replace it by the next one (if it needed) 
			for(int i=0;i<questions.size()-j-1;i++) {
				if(questions.elementAt(i).compareTo(questions.elementAt(i+1))==1) {
					question=questions.elementAt(i); //save the element 
					questions.remove(i); //remove it from the previous place
					questions.add(i+1,question); //insert it to the suitable place
				}
			}
		}
	}

	public Question getQuestionByNumber(int number) { //return question by number 
		return questions.elementAt(number);
	}

	public int numOfQuestions() { //method that return the amount of questions 
		return questions.size();
	}

	public void runPractice() { //method that run the practice 
		System.out.println(ad.getContent());; //show the ad at the beginning of the practice 
		System.out.println("Welcome to the Practice");
		for(int i=0;i<questions.size();i++) {
			isMath=false; 
			isEnglish=false;
			if(questions.elementAt(i) instanceof QuantitativeQuestion) { //type question is math  
				isMath=true; //question in math
				moneMathQuestion++; //count math questions 
				questions.elementAt(i).setMoneQuestion(); //count the number of every question 			
				System.out.println("Question number "+i+": "+ ((QuantitativeQuestion)questions.elementAt(i)).getFormula()+" "+ questions.elementAt(i));
				//print the question number + the question at specific location with it's formula   
			}
			if(questions.elementAt(i) instanceof EnglishQuestion) { //type question is English  
				isEnglish=true; //question in English
				moneEnglishQuestion++; //count English questions 
				questions.elementAt(i).setMoneQuestion();				
				System.out.println("Question number "+i+": "+ questions.elementAt(i)+" "+ ((EnglishQuestion)questions.elementAt(i)).getHint());
				//print the question number + the question at specific location with it's hint
			}
			CymulateAnswer(i); //simulation of question at place i
		}
		gradePractice(); //give grades for each student 
	}

	private void CymulateAnswer(int i) { //method of simulation of question at place i
		double chance=Math.random(); //random number between 0 to 1, the chance to correct answer
		double trueAns=(double) student.getStudentLevel()/10; //the chance of the student to answer right 
		if(chance<=trueAns) { // if the random number is smaller, then the student is in the range and he answered right  
			System.out.println("Your answer:" + questions.elementAt(i).getChoices(answerContent(i))); 
			if(isMath) { //if the question is math question
				moneMathTrue++;  
				questions.elementAt(i).setMoneSuccess(); //count the successes
			}
			else {
				moneEnglishTrue++; 
				questions.elementAt(i).setMoneSuccess();
			} 
		}
		else {
			int chanceFalse= (int)((Math.random()*4)); //if the answer isn't correct, random a number between 0-4 
			while(questions.elementAt(i).getChoices(chanceFalse).equals(questions.elementAt(i).getAnswer())) { //check if it is the correct answer
				chanceFalse= (int)((Math.random()*4)); //random again until it will be a wrong answer 
			}
			System.out.println("Your answer:" + questions.elementAt(i).getChoices(chanceFalse));  
			questions.elementAt(i).setMoneFalse(); //count the false answers
		}
		System.out.println();
	}
	
	private int answerContent(int i) { //method that return the content of the answer (a/b/c/d)
		if(questions.elementAt(i).getAnswer()=='a')
			return 0;
		if(questions.elementAt(i).getAnswer()=='b')
			return 1;
		if(questions.elementAt(i).getAnswer()=='c')
			return 2;
		else
			return 3;	
	}

	private void gradePractice() { //method that gives grades for students at the end of the practice by percentage  
		student.setMathGrade((double)moneMathTrue/moneMathQuestion); 
		System.out.println("Practice was finished, your math score is: " +((double) moneMathTrue/moneMathQuestion)*100 + " %");
		student.setEnglishGrade((double) moneEnglishTrue/moneEnglishQuestion);
		System.out.println("Practice was finished, your English score is: " +((double) moneEnglishTrue/moneEnglishQuestion)*100 + " %");
	}
}