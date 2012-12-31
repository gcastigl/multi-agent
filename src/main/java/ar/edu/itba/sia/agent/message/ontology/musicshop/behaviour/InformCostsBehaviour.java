package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.abs.AbsConcept;
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

@SuppressWarnings("serial")
/**
 * SELLER informs BUYER about the cost of a given Item
 */
public class InformCostsBehaviour extends OneShotBehaviour {

	private Item item;
	private Codec codec;
	private Ontology ontology;
	
	public InformCostsBehaviour(Agent owner, Ontology ontology, Codec codec, Item item) {
		super(owner);
		this.item = item;
		this.ontology = ontology;
		this.codec = codec;
	}
	
	@Override
	public void action() {
		try {
			System.out.println("\nSELLER: Inform Buyer about price of item " + item);

			// Prepare the message
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			AID receiver = myAgent.getAID(); // Send the message to myself

			msg.setSender(myAgent.getAID());
			msg.addReceiver(receiver);
			msg.setLanguage(codec.getName());
			msg.setOntology(ontology.getName());

			// Fill the content
			AbsVariable x = new AbsVariable("x", Price.NAME);

			AbsPredicate costs = new AbsPredicate(Costs.NAME);
			costs.set(Costs.ITEM, (AbsTerm) ontology.fromObject(item));
			costs.set(Costs.PRICE, x);

			AbsIRE iota = new AbsIRE(SLVocabulary.IOTA);
			iota.setVariable(x);
			iota.setProposition(costs);

			AbsPredicate equals = new AbsPredicate(SLVocabulary.EQUALS);
			equals.set(SLVocabulary.EQUALS_LEFT, iota);
			AbsConcept price = (AbsConcept) ontology.fromObject(new Price(20.5F, "EURO"));
			equals.set(SLVocabulary.EQUALS_RIGHT, price);

			myAgent.getContentManager().fillContent(msg, equals);
			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
