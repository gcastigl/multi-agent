package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.abs.AbsIRE;
import jade.content.abs.AbsPredicate;
import jade.content.abs.AbsTerm;
import jade.content.abs.AbsVariable;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLVocabulary;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Costs;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Price;

/**
 *  BUYER queries the SELLER how much a given item costs
 */
@SuppressWarnings("serial")
public class QueryPriceBehaviour extends OneShotBehaviour {

	private Item item;
	private Ontology ontology; 
	private Codec codec;
	
	public QueryPriceBehaviour(Agent owner, Ontology ontology, Codec codec, Item item) {
		super(owner);
		this.item = item;
		this.ontology = ontology;
		this.codec = codec;
	}

	@Override
	public void action() {
		try {
			System.out.println("\nBUYER: Query price of " + item);

			// Prepare the message
			ACLMessage msg = new ACLMessage(ACLMessage.QUERY_REF);
			AID receiver = myAgent.getAID(); // Send the message to myself

			msg.setSender(myAgent.getAID());
			msg.addReceiver(receiver);
			msg.setLanguage(codec.getName());
			msg.setOntology(ontology.getName());

			// Fill the content
			Ontology onto = MusicShopOntology.getInstance();
			AbsVariable x = new AbsVariable("x", Price.NAME);

			AbsPredicate costs = new AbsPredicate(Costs.NAME);
			costs.set(Costs.ITEM, (AbsTerm) onto.fromObject(item));
			costs.set(Costs.PRICE, x);

			AbsIRE iota = new AbsIRE(SLVocabulary.IOTA);
			iota.setVariable(x);
			iota.setProposition(costs);

			myAgent.getContentManager().fillContent(msg, iota);
			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
