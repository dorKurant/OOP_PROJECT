import java.util.Comparator;
public class StudentByGradeComparator implements Comparator<Student> { //compare between 2 students by their total grade (not natural compare)

	public int compare(Student s1, Student s2) {
		return(s1.getTotalGrade()-s2.getTotalGrade());
	}

}
