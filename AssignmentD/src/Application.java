import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class Application {

	private Vector <Student> students; //vector of students
	private Vector <Ad> ads; //vector of ads
	private Vector <Question> questions;//vector of questions
	private Vector <Practice> practices; //vector of practices

	public Application(String import_questions) { //constructor 
		students = new Vector<Student>();
		ads = new Vector<Ad>();
		questions = new Vector<Question>();
		practices = new Vector<Practice>();
		setQuestions(import_questions);	
	}

	public void addStudent(Student s) { //add student to the vector
		students.add(s);
	}

	public void addAd(Ad ad) { //add ad to the vector
		ads.add(ad);
	}

	public void addQuestion(Question q) { //add question to the vector
		questions.add(q);
	}

	private void setQuestions(String import_questions) { //read text from file 
		File file = new File(import_questions); //new string of long questions 
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file)); 
			String st = br.readLine(); 
			st = br.readLine(); //skip the first line 
			while(st != null) {
				//System.out.println(st); //print the next line мозеч
				String[] arr = st.split("\t"); //turn the next line to a new question
				String content = arr[1]; //skip the first line (title)
				int level = Integer.parseInt(arr[2]); //convert from string to int
				String answerr = arr[3]; //in line 50-->change to char
				String [] choices = {arr[4],arr[5], arr[6],arr[7]}; //options for questions
				char answer = answerr.charAt(0); //the answer option
				if(arr[8].length()!=0) { //split for English or math questions
					String help = arr[8]; //formula
					QuantitativeQuestion mq = new QuantitativeQuestion(content,level,answer,choices,help);
					questions.add(mq);
				}
				else {
					String help = arr[8]; //hint
					EnglishQuestion eq = new EnglishQuestion(content,level,answer,choices,help);
					questions.add(eq);
				}
				st=br.readLine();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void importQuestions(String import_questions) {
		setQuestions(import_questions);
	}

	public void createPractice(String email) { //method that create a new practice from the last line in the text 
		Student s = checkIfEmailExist(email); //send to a method that check if the mail is already exist 
		Vector<Question> q = CreateQuestions(s); //send to a method that create vector of questions for the students by level 
		Ad a = randomAd(s); //"random" ad that appropriate for the student age 
		Practice p = new Practice(s,q,a); //create practice for a,b,c
		p.runPractice(); //run the practice 
		practices.add(p); //add the practice to the practices vector
	}

	private Ad randomAd(Student s) { //random an ad 

		Vector<Ad> listOfAppropriate = new Vector<Ad>(); //vector of ads that appropriate for the student age 
		for(int i=0; i<ads.size(); i++) {
			if(ads.elementAt(i).suitableForStudent(s)) {//check if the current ad from the vector appropriate for the student 
				listOfAppropriate.add(ads.elementAt(i)); //add the ad to a new list 
			}
		}
		int rand=(int) (Math.random()*listOfAppropriate.size()); //random a new ad for the student 
		return listOfAppropriate.elementAt(rand);
	}

	private Vector<Question> CreateQuestions(Student s) { //method that do a new list of questions for out student 
		Vector<Question> newQuestionsList=new Vector<>(); //the new vector that will return 
		newQuestionsList=sameLevelQuestions(1,newQuestionsList); //random 8 easy questions 
		newQuestionsList=sameLevelQuestions(s.getStudentLevel(),newQuestionsList); //random 8 questions by the student level
		newQuestionsList=sameLevelQuestions(10,newQuestionsList); //random 8 hard questions
		return newQuestionsList;
	}
	private Vector<Question> sameLevelQuestions(int studentLevel,Vector<Question> newQuestionsList) { //method that create new vector from the same level of questions
		Vector<Question> sameLevel = new Vector<>(); //the questions will be rand from that vector
		for(int i=0;i<questions.size();i++) { //run over all the questions list 
			if(questions.elementAt(i).getLevel()==studentLevel) { //check if a single question is by the student level
				Question q = questions.elementAt(i); //save the question at a new pointer 
				sameLevel.add(q); //add the question to new list 
			}
		}
		return newVectors(sameLevel,newQuestionsList);
	}

	private Vector<Question> newVectors(Vector <Question> sameLevel,Vector<Question> newQuestionsList) { //method that split for 2 types of vectors
		Vector<Question> English = new Vector<>(); //new vector for English question
		Vector<Question> Mathmatic = new Vector<>(); //new vector for math question
		for(int i=0; i<sameLevel.size();i++) { //split all the questions for 2 groups
			if(sameLevel.elementAt(i) instanceof EnglishQuestion)
				English.add(sameLevel.elementAt(i));
			else
				Mathmatic.add(sameLevel.elementAt(i));
		}
		return randomQuestion(English,Mathmatic,newQuestionsList);
	}

	private Vector<Question> randomQuestion(Vector <Question> English,Vector <Question> Mathmatic,Vector<Question> newQuestionsList) { //method that random 8 questions list by the student level
		for(int i=0; i<4; i++) { //random 4 question from every group and put it at the right vector
			int x=(int) (Math.random()*English.size());
			newQuestionsList.add(English.elementAt(x));
			English.remove(x); //make sure that questions doesn't repeat by remove it from the new group 
			int y=(int) (Math.random()*Mathmatic.size());
			newQuestionsList.add(Mathmatic.elementAt(y));
			Mathmatic.remove(y); //make sure that questions doesn't repeat by remove it from the new group
		}
		return newQuestionsList;
	}

	private Student checkIfEmailExist(String email) { //method that check if email exist or not and return the student with the mail
		int place=0; //the place of the student with the email at the vector 
		Boolean flag=false; //will change to true if find the email
		for(int i=0;i<students.size();i++) {
			if(students.elementAt(i).getEmail().equals(email)) { //run all over the list and check if exist 
				flag=true;	
				place=i;
			}
		}
		if(flag==false) //if the email doesn't found
			throw new EmailNotExistException(); //run time exception
		return students.elementAt(place);
	}

	public void bestStudents() { //method that check who is the best student
		Student studentMaxProfit = Collections.max(students); //student that provided the max profit (natural compare)
		System.out.println("The most profitable student is:" + " " + studentMaxProfit.toString());
		System.out.println("The max profit is:" + " " + studentMaxProfit.getProfit());
		Student studentMaxGrade = Collections.max(students,new StudentByGradeComparator()); //student with the highest total grade (comparator)
		System.out.println("The student with the highest grade is:" + " " + studentMaxGrade.toString());
		System.out.println("His grade is:" + " " + studentMaxGrade.getTotalGrade());
	}

	public static int totalRevenues (Vector <? extends Profitable> p) { //method that gets all the profitable objects and return the total profit
		int sum = 0;
		for(int i=0;i<p.size();i++)
			sum = sum + p.elementAt(i).getProfit();
		System.out.print("The total profit is:" + " ");
		return sum;
	}

	public int updateQuestionsLevel() { //method that run all over the program and update all questions level, return the number of the questions that updated 
		int moneUpdateQuestions = 0;
		for(int i=0;i<questions.size();i++) {
			questions.elementAt(i).updateLevel();
			if(questions.elementAt(i).getIsUpdate()) { //if the question updated
				moneUpdateQuestions++;
				questions.elementAt(i).SetIsUpdate();
			}
		}
		return moneUpdateQuestions;
	}

	public static <X extends Comparable<X>> X getMax(Vector <X> vec) { //the maximum object from each object that has natural compare
		X max=vec.elementAt(0); //first, the maximum object is the one that is in the first index
		for(int i=0;i<vec.size();i++) {
			if(vec.elementAt(i).compareTo(max)>=0) //if the next one is bigger-->replace
				max=vec.elementAt(i);
		}
		return max;
	}

	public static <X extends Comparable<X>> X getMin(Vector <X> vec) { //the minimum object from each object that has natural compare
		X min=vec.elementAt(0); //first, the minimum object is the one that is in the first index
		for(int i=0;i<vec.size();i++) {
			if(vec.elementAt(i).compareTo(min)<=0) //if the next one is smaller-->replace
				min=vec.elementAt(i);
		}
		return min;
	}
}