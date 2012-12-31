package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Owns;

@SuppressWarnings("serial")
/**
 * SELLER informs BUYER that he owns a given Item
 */
public class InformOwnsBehaviour extends OneShotBehaviour {

	private Item item;
	private Ontology ontology;
	private Codec codec;
	
	public InformOwnsBehaviour(Agent owner, Ontology ontology, Codec codec, Item item) {
		super(owner);
		this.ontology = ontology;
		this.codec = codec;
		this.item = item;
	}
	
	@Override
	public void action() {
		try {
			System.out.println("\nSELLER: Inform BUYER that I own " + item);

			// Prepare the message
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			AID receiver = myAgent.getAID(); // Send the message to myself

			msg.setSender(myAgent.getAID());
			msg.addReceiver(receiver);
			msg.setLanguage(codec.getName());
			msg.setOntology(ontology.getName());

			// Fill the content
			Owns owns = new Owns();
			owns.setOwner(myAgent.getAID());
			owns.setItem(item);

			myAgent.getContentManager().fillContent(msg, owns);
			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
