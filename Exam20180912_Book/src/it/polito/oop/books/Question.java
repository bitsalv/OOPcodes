package it.polito.oop.books;

import java.util.Set;
import java.util.TreeSet;

public class Question {
	
	private String domanda;
	private Topic argomento;
	private int contatore=0;
	private Set<String> corrette= new TreeSet<>();
	private Set<String> sbagliate= new TreeSet<>();

	public Question(String s, Topic t) {
		domanda=s;
		argomento=t;
	}
	
	public String getQuestion() {
		return domanda;
	}
	
	public Topic getMainTopic() {
		return argomento;
	}

	public void addAnswer(String answer, boolean correct) {
		
		if(correct==true) {
			corrette.add(answer);	
		}
		else {
			sbagliate.add(answer);
		}
		
		contatore++;
	}
	
    @Override
    public String toString() {
        return domanda+" ("+argomento+")";
    }

	public long numAnswers() {
	    return contatore;
	}

	public Set<String> getCorrectAnswers() {
		return corrette;
	}

	public Set<String> getIncorrectAnswers() {
        return sbagliate;
	}
}
