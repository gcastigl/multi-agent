package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import ar.edu.itba.sia.agent.message.ontology.commerce.ECommerceOntology;
import ar.edu.itba.sia.agent.message.ontology.musicshop.element.CD;
import ar.edu.itba.sia.agent.message.ontology.musicshop.element.Single;
import ar.edu.itba.sia.agent.message.ontology.musicshop.element.Track;

@SuppressWarnings("serial")
public class MusicShopOntology extends BeanOntology {

	public static final String NAME = "Music-shop-ontology";
	
	private static MusicShopOntology instance;

	public synchronized static MusicShopOntology getInstance() throws BeanOntologyException {
		if (instance == null) {
			instance = new MusicShopOntology();
		}
		return instance;
	}
	
	private MusicShopOntology() throws BeanOntologyException {
		super(NAME, ECommerceOntology.getInstance());
		add(Track.class);
		add(CD.class);
		add(Single.class);
	}

}
