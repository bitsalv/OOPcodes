package it.polito.oop.books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class Book {

    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	
	private Map<String,Topic> argomenti = new TreeMap<>();
	private Map<String,Question> domande= new HashMap<>();
	private List<TheoryChapter> capitoliTeoria= new ArrayList<>();
	private List<ExerciseChapter> capitoliEsercizi= new ArrayList<>();
	private List<Assignment> compiti= new ArrayList<>();
	
	
	public Topic getTopic(String keyword) throws BookException {
	    
		if(keyword==null) {
			throw new BookException();
		}
		
		else if(keyword.isEmpty()) {
			throw new BookException();
		}
		
		else if(argomenti.containsKey(keyword)) {
			argomenti.get(keyword);
		}
		
		else {
			argomenti.put(keyword, new Topic(keyword));
		}
		
		
		return argomenti.get(keyword);
	}

	public Question createQuestion(String question, Topic mainTopic) {
		
		domande.put(question, new Question(question, mainTopic));
        return domande.get(question);
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		TheoryChapter capitolo= new TheoryChapter(title, numPages, text);
		capitoliTeoria.add(capitolo);
        return capitolo;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter capitolo= new ExerciseChapter(title, numPages);
        capitoliEsercizi.add(capitolo);
		return capitolo;
	}

	public List<Topic> getAllTopics() {		
		
		List<Topic> list= new ArrayList<>();
		
		
		for(TheoryChapter temp : capitoliTeoria) {
			list.addAll(temp.getTopics());
		}
		
		for(ExerciseChapter temp: capitoliEsercizi) {
			list.addAll(temp.getTopics());
		}
			
        return list.stream().distinct().sorted(comparing(t->t.toString(),naturalOrder())).collect(toList());
	}

	public boolean checkTopics() {
        
		Set<Topic> t = capitoliTeoria.stream().flatMap(c -> c.getTopics().stream()).collect(Collectors.toSet());
		Set<Topic> e = capitoliEsercizi.stream().flatMap(c -> c.getTopics().stream()).collect(Collectors.toSet());
		
		return t.containsAll(e);
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		
		Assignment compito= new Assignment(ID, chapter);
		compiti.add(compito);
        return compito;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
    	
    	return domande.values().stream().collect(groupingBy(t->t.numAnswers(),toList()));
    	
    }
}
