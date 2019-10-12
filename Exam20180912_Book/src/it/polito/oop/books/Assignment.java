package it.polito.oop.books;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;
//TODO

import java.util.HashMap;

public class Assignment {

	private String ID;
	private ExerciseChapter compito;
	private Map<Question, Double> scores = new HashMap<>();

	
	
	public Assignment(String s, ExerciseChapter c) {
	
			this.ID=s;
			this.compito=c;
	}
	
	
    public String getID() {
        return ID;
    }

    public ExerciseChapter getChapter() {
        return compito;
    }

    public double addResponse(Question q,List<String> answers) {
    	
    	Set<String> corrette = q.getCorrectAnswers();
    	Set<String> sbagliate= q.getIncorrectAnswers();
    	
    	double n= corrette.size()+sbagliate.size();
    	
    	double fp=answers.stream().filter(c->sbagliate.contains(c)).collect(counting());
    	double fn=corrette.stream().filter(c->!answers.contains(c)).collect(counting());
    	
    	double r=(n-fp-fn)/n;
    	
    	scores.put(q,r);
    	
        return r;
    }
    
    public double totalScore() {
            	
    	return scores.values().stream().mapToDouble(c->c).sum();
    }

}
