package ar.edu.itba.sia.agent.message.ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;

@SuppressWarnings("serial")
public class PublicationOntology extends BeanOntology {

	private static PublicationOntology instance;
	
	public synchronized static PublicationOntology getInstance() {
		if (instance == null) {
			instance = new PublicationOntology();
		}
		return instance;
	}
	
	private PublicationOntology() {
		super(PublicationOntology.class.getSimpleName());
		try {
			add(Publication.class);
			add(PublicationResult.class);
		} catch (BeanOntologyException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
