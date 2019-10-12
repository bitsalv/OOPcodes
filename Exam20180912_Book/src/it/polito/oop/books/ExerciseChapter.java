package it.polito.oop.books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class ExerciseChapter {
	
	private String titolo; 
	private int numPagine;
	private Map<String,Question> domande= new HashMap<>();
	
	public ExerciseChapter(String titolo, int numPagine) {
		
		this.titolo=titolo;
		this.numPagine=numPagine;
	}

    public List<Topic> getTopics() {
    	
    	return domande.values().stream().map(t->t.getMainTopic()).distinct().sorted(comparing(t->t.toString(),naturalOrder())).collect(toList());
    	
    	
    };
	

    public String getTitle() {
        return this.titolo;
    }

    public void setTitle(String newTitle) {
    	this.titolo=newTitle;
    }

    public int getNumPages() {
        return numPagine;
    }
    
    public void setNumPages(int newPages) {
    	this.numPagine=newPages;
    }
    

	public void addQuestion(Question question) {
		domande.put(question.getQuestion(), question);
	}	
}
