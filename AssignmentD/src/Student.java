import java.lang.Math;
public class Student implements Comparable<Student>,Profitable { //interface comparable, profitable

	private int profit=10; //initial payment by signing up to the application
	private int practice=0; //count the practices of the student
	private String email;
	private String firstName;
	private String lastName;
	private int age;
	private double mathGrade;
	private double englishGrade;
	private int totalGrade; //total grade of the student
	private int studentLevel; //level of the student


	public Student(String email, String firstName, String lastName, int age) { //constructor
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		if(!email.contains("@")) //if the email doesn't contain @
			throw new EmailNotValidException(); //run time exception
	}

	public int getTotalGrade() { //total grade of the student
		this.totalGrade=(int)(mathGrade*700+englishGrade*300); 
		return totalGrade;
	}
	
	public void setPractice() { //update the number of practices and profit of the student for the company
		this.practice=this.practice+1;	
		this.profit=this.profit+2; //each practice, the student pay 2 dollars except for the initial payment
	}

	public int getPractice() { //number of practices of the student
		return this.practice;
	}

	public int getProfit() { //profit of the student for the company
		return this.profit;
	}

	public int getAge() { //age of the student
		return this.age;
	}

	public int getStudentLevel() { //level of the student
		this.studentLevel=Math.max((int)(totalGrade/100),1);
		return this.studentLevel;
	}

	public void setMathGrade(double grade) { //math grade 
		this.mathGrade = grade;
	}

	public void setEnglishGrade(double grade) { //English grade 
		this.englishGrade = grade;
	}

	public int compareTo(Student other) { //the student can be compared by his/her profit for the company
		if(this.getProfit()<other.getProfit())
			return -1;
		if(this.getProfit()>other.getProfit())
			return 1;
		else
			return 0;
	}
	public String getEmail() { //email of the student
		return email;
	}
	
	public String toString() {
		return this.firstName +" "+ this.lastName;
	}
}
