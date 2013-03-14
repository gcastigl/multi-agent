package ar.edu.itba.sia.agent.message.ontology;

import jade.content.Concept;

@SuppressWarnings("serial")
public class PublicationResult implements Concept {

	private int score;
	
	public PublicationResult() {
	}
	
	public PublicationResult(int score) {
		setScore(score);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}
