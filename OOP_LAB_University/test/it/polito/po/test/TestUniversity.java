package it.polito.po.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import university.University;

public class TestUniversity {
	
	static final String universityName = "Politecnico di Torino";
	private University poli;
	
	@Before
	public void setUp(){ 		// FIXTURE, 
								// the name 'setUp' is convention deriving from JUnit 3
		poli = new University(universityName);
		poli.setRector("Guido", "Saracco");
	}

	@Test
	public void testNameRector(){
		assertEquals("Wrong university name", // msg in case of failure
						universityName,		  // expected value
						poli.getName());	  // actual value
		
		assertNotNull("Missing rector name",poli.getRector());
		
		assertContained("Wrong rector name","Saracco",poli.getRector());
	}
	
	@Test
	public void testEnroll(){				
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		
		assertEquals("Wrong id number for first enrolled student",10000,s1);
		assertEquals("Wrong id number for second enrolled student",10001,s2);		
	}

	@Test
	public void testStudents(){				
		int s1 = poli.enroll("Vilfredo","Pareto");
		int s2 = poli.enroll("Galileo","Ferraris");
		int s3 = poli.enroll("Leo", "Da Vinci");
	
		String ss1 = poli.student(s1);
		assertNotNull(ss1);
		assertContained("Pareto",ss1);

		String ss2 = poli.student(s2);
		assertNotNull(ss1);
		assertContained("Galileo",ss2);

		String ss3 = poli.student(s3);
		assertNotNull(ss1);
		assertContained("Da",ss3);
	}

	@Test
	public void testCourseActivation(){
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		
		assertEquals("Wrong id number for first activated course",10,macro);
		assertEquals("Wrong id number for second activated course",11,oop);		
	}
	
	@Test
	public void testCourses(){
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		
		assertNotNull("Missing course description",poli.course(macro));
		assertContained("Wrong description of course", "Macro Economics", poli.course(macro));
		assertContained("Wrong description of course", "Oriented", poli.course(oop));
		assertContained("Wrong description of course", "James", poli.course(oop));
	}
	
	@Test
	public void testAttendees(){
		poli.enroll("Mario","Rossi");
		poli.enroll("Giuseppe","Verdi");
		
		poli.activate("Macro Economics", "Paul Krugman");
		poli.activate("Object Oriented Programming", "James Gosling");
		
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		
		String attendees = poli.listAttendees(10);
		assertNotNull("Missing attendees list",attendees);
		assertEquals("Wrong number of attendees for course 10",2,countLines(attendees));

		attendees = poli.listAttendees(11);
		assertEquals("Wrong number of attendees for course 11",1,countLines(attendees));
	}
	
	@Test
	public void testStudyPlan(){
		poli.enroll("Mario","Rossi");
		poli.enroll("Giuseppe","Verdi");
		
		poli.activate("Macro Economics", "Paul Krugman");
		poli.activate("Object Oriented Programming", "James Gosling");
		
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		
		String plan = poli.studyPlan(10001);
		assertNotNull("Missing study plan",plan);
		assertEquals("Wrong number of courses for student 10001",2,countLines(plan));

		plan = poli.studyPlan(10000);
		assertEquals("Wrong number of courses for student 10000",1,countLines(plan));
	}
	
	
	// -------------------- Utility methods ------------------------------------------
	
	// specialized assert methods, make the test easier to read
	
	/**
	 * Assert that a sub string is contained in the actual string
	 * 
	 * @param expectedSubStr the expected sub string
	 * @param actualStr      the actual string
	 */
	private static void assertContained(String expectedSubStr, String actualStr) {
		assertContained(null,expectedSubStr,actualStr);
	}
	
	/**
	 * Assert that a sub string is contained in the actual string
	 * 
	 * @param expectedSubStr the expected sub string
	 * @param actualStr      the actual string
	 */
	private static void assertContained(String msg, String expectedSubStr, String actualStr) {
		assertTrue((msg==null?"":msg+": ") + "expected sub string [" + expectedSubStr + "] is not contained in ["+actualStr+"]",
					(actualStr==null?false:actualStr.contains(expectedSubStr)));
	}
	
	// other support methods
	
	private static int countLines(String s) {
		if(s==null) return 0;
		return 1 + s.trim().replaceAll("[^\n]", "").length();
	}
}
