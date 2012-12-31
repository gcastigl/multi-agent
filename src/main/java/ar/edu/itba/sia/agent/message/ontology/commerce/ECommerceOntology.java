package ar.edu.itba.sia.agent.message.ontology.commerce;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Costs;

@SuppressWarnings("serial")
public class ECommerceOntology extends BeanOntology {

	public static final String NAME = "E-Commerce-ontology";
	
	private static ECommerceOntology instance;
	
	public static synchronized ECommerceOntology getInstance() throws BeanOntologyException {
		if (instance == null) {
			instance = new ECommerceOntology();
		}
		return instance;
	}
	
	private ECommerceOntology() throws BeanOntologyException {
		super(NAME);
		String pkgname = Costs.class.getName();
		pkgname = pkgname.substring(0, pkgname.lastIndexOf("."));
		add(pkgname);
	}

	
}
