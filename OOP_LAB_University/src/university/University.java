package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	/**
	 * Constructor
	 * @param name name of the university
	 */
	private String name;
	private String firstNameRector;
	private String lastNameRector;
	final int maxStudent=1000;
	final int maxCourse=50;
	int idOfStudent=9999;
	int codCourse=9;
	public int numberOfStudent=0;
	public int numberOfCourse=0;
	public Student[] student = new Student[maxStudent];
	public Course[] course= new Course[maxCourse];
	int maxStudentForCourse=100;
	final int maxCourseStudent=25;


	
	
	public University(String name){
		//TODO: to be implemented
		this.name=name;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		firstNameRector=first;
		lastNameRector=last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		//TODO: to be implemented
		if(firstNameRector.isEmpty() && lastNameRector.isEmpty()) {
			return null;
		}
		
		String completeNameRector=firstNameRector+" "+lastNameRector;
		return completeNameRector;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		if(numberOfStudent<=1000) {
		idOfStudent++;
		student[numberOfStudent]= new Student(first,last,idOfStudent);
		numberOfStudent++;

		return idOfStudent;
		}
		
		return -1;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented		
		if(id>=10000 && id<=11000) {
			int j=id-10000;
			if(id==student[j].getMatricola()) {
			return student[j].getStudent();
			}
		}
		return null;
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		if(numberOfCourse<=50) {
			codCourse++;
			course[numberOfCourse]= new Course(title,teacher,codCourse);
			numberOfCourse++;
			return codCourse;
		}
		return -1;
	}
	
	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		if(code>=10 && code<=60) {
			int k=code-10;
			if(code==course[k].getIdCourse()) {
				return course[k].getInfoCourse();
			}
		}
		return null;
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		student[studentID-10000].getCourse(course[courseCode-10]);
		course[courseCode-10].getStudent(student[studentID-10000]);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		if(courseCode>=10 && courseCode<=60) {
			return course[courseCode-10].getAllStudent();
			}
		
		return null;
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		if(studentID>=10000 && studentID<=11000) {
			return student[studentID-10000].getAllCourse();
		}
		return null;
	}
}
