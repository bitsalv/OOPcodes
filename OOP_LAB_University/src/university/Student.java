package university;

public class Student {

	private String nome;
	private String cognome;
	private int matricola;
	final int maxCourseStudent=25;
	private Course[] corso= new Course[maxCourseStudent];
	public int ncorsi;
	
	public Student(){
		nome="";
		cognome="";
		matricola=0;
	}
	
	public Student(String n,String c, int m) {
		nome=n;
		cognome=c;
		matricola=m;
		ncorsi=0;
	}
	
	public String getStudent() {
		return(matricola+" "+nome+" "+cognome+"\n");
	}
	
	public int getMatricola() {
		return matricola;
	}
	
	public void getCourse(Course c) {
		corso[ncorsi]=c;
		ncorsi++;
	}
	
	public String getAllCourse() {
		StringBuffer s=new StringBuffer("");
		for(int i=0;i<ncorsi;i++) {
			s=s.append(corso[i].getInfoCourse());
		}
		return s.toString();
	}
}
	
	
