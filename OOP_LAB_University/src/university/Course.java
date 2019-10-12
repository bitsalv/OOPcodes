package university;

public class Course {

	private String nameCourse;
	private String teacherCourse;
	private int idCourse;
	int maxStudentForCourse=100;
	private Student[] studente= new Student[maxStudentForCourse];
	public int nstudenti;
	
	public Course() {
		nameCourse="";
		teacherCourse="";
		idCourse=0;
		nstudenti=0;
	}
	
	public Course(String nC, String tC, int numC) {
		nameCourse=nC;
		teacherCourse=tC;
		idCourse=numC;
	}
	
	
	public String getInfoCourse() {
		return (idCourse+","+nameCourse+","+teacherCourse+"\n");
	}
	
	public int getIdCourse() {
		return idCourse;
	}
	
	public void getStudent(Student s) {
		studente[nstudenti]=s;
		nstudenti++;
	}
	
	public String getAllStudent() {
		StringBuffer s= new StringBuffer("");
		for(int i=0;i<nstudenti;i++) {
			s=s.append(studente[i].getStudent());
		}
		return s.toString();
	}
}
