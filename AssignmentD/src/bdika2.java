import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class bdika2 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student tal = new Student ("tal@" , "tal" , "s" , 26);
		Ad ad = new Ad("xx" , 3 , 25 , 27);
		Vector <Question> l1 = new Vector<Question>();
		
		System.out.println(tal.getStudentLevel());
		System.out.println(tal.getTotalGrade());
		
		String s = "hi";
		int a = 3;
		char t = 't';
		String [] c = new String [4];
		c[0] = "a";
		c[1] = "b";
		c[2] = "c";
		c[3] = "d";
		String f = "tal";
		
		String s1 = "hi";
		int a1 = 5;
		char t1 = t;
		String [] c1 = new String [4];
		c1[0] = "a";
		c1[1] = "b";
		c1[2] = "c";
		c1[3] = "d";
		String f1 = "tal";
		int a2=7;
		
		Question q1 = new EnglishQuestion(s1 ,9 , t1 , c1 , f1);
		Question q2 = new EnglishQuestion(s ,7 , t , c , f);
		Question q3 = new QuantitativeQuestion(s1 ,2 , t1 , c , f1);
		
		l1.add(q1);
		l1.add(q2);
		l1.add(q3);
		
		Practice p = new Practice (tal , l1 , ad);
		
		//p.order();
		//p.getQuestionByNumber(1);
		//p.numOfQuestions();
		//p.runPractice();
		System.out.println(tal.getTotalGrade());
		System.out.println(tal.getStudentLevel());
		Application aaa=new Application("Questions_1.txt");
		//Application.getMax();
		
		
		Student Dor = new Student("Dor@","Dor","K",17);
		Student Omer = new Student("Omer@","Omer","A",20);
		aaa.addStudent(Dor);
		aaa.addStudent(Omer);
		//System.out.println(aaa.students.elementAt(aaa.students.size()-1).getEmail());
		System.out.println();
		Ad ad1= new Ad("x5f",2,2,30);
		Ad ad2= new Ad("xxx",3,18,19);
		aaa.addAd(ad1);
		aaa.addAd(ad2);
		String[] choices = {"a","b","c","d"};
		//QuantitativeQuestion what = new QuantitativeQuestion("what",5,'b',choices,"hi");
		//aaa.addQuestion(what);
		//System.out.println(aaa.questions.elementAt(aaa.questions.size()-1).getAnswer());
		//aaa.importQuestions("Questions.txt");
		aaa.createPractice("Dor@");
		Vector w = new Vector<>();
		w.add(Dor);
		//aaa.createPractice(Dor@);
		//w.add(ad2);
		//w.add(ad1);
		w.add(Omer);
		//int bas=aaa.totalRevenues(w);
	//	System.out.println(bas);
		//aaa.bestStudents();
		System.out.println("Dsadas" + Dor.getStudentLevel());
		System.out.println(aaa.updateQuestionsLevel());
		//aaa.createPractice("Dor@");
		//System.out.println(aaa.updateQuestionsLevel());
		//System.out.println(aaa.getMin(w));
	}

}