package ar.edu.itba.sia.agent.message.ontology;

import jade.content.Concept;

public class Publication implements Concept {

	private static final long serialVersionUID = 1L;

	private String text;
	
	public Publication() {
	}

	public Publication(String text) {
		setText(text);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
