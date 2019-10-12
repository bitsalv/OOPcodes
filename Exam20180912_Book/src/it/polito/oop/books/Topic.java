package it.polito.oop.books;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Topic {
	
	private String keyword;
	private Map<String,Topic> subTopics= new TreeMap<>();
	 
	public Topic(String keyword) {
		this.keyword=keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	
	@Override
	public String toString() {
	    return keyword;
	}

	public boolean addSubTopic(Topic topic) {
		
		if(subTopics.containsKey(topic.getKeyword())) {
			return false;
		}
		
		subTopics.put(topic.getKeyword(), topic);
        return true;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
		 return subTopics.values().stream().collect(Collectors.toList());
		 }
	
	
	public void addTopic(TheoryChapter t) {
		
		if(subTopics.isEmpty()==false) {
			for(Topic temp : this.getSubTopics()) {
				temp.addTopic(t);
			}
			t.addR(this);
		}
		else {
			t.addR(this);
		}
	}
}
