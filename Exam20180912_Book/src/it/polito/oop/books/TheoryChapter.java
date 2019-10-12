package it.polito.oop.books;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class TheoryChapter {
	
	private String titolo;
	private int numPagine;
	private String testo;
	private Map<String,Topic> argomenti= new TreeMap<>();
	
	
	public TheoryChapter(String titolo, int numPagine, String testo) {
		this.titolo=titolo;
		this.numPagine=numPagine;
		this.testo=testo;
	}

    public String getText() {
		return testo;
	}

    public void setText(String newText) {
    	this.testo=newText;
    }


	public List<Topic> getTopics() {
		return argomenti.values().stream().collect(Collectors.toList());
	}

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
    
    public void addTopic(Topic topic) {
    	
    	topic.addTopic(this);
    
    }
    
    public void addR(Topic top) {
    	argomenti.put(top.getKeyword(), top);
    }
    
}
