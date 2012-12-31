package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;

import ar.edu.itba.sia.agent.message.ontology.commerce.element.CreditCard;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Sell;

@SuppressWarnings("serial")
public class RequestSellBehaviour extends OneShotBehaviour {

	private Item item;
	private Ontology ontology;
	private Codec codec;
	
	public RequestSellBehaviour(Agent owner, Ontology ontology, Codec codec, Item item) {
		super(owner);
		this.item = item;
		this.ontology = ontology;
		this.codec = codec;
	}
	
	@Override
	public void action() {
		try {
			System.out.println("\nBUYER: Request seller to sell item " + item);

			// Prepare the message
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			AID receiver = myAgent.getAID(); // Send the message to myself

			msg.setSender(myAgent.getAID());
			msg.addReceiver(receiver);
			msg.setLanguage(codec.getName());
			msg.setOntology(ontology.getName());

			// Fill the content
			Sell sell = new Sell();
			sell.setBuyer(myAgent.getAID());
			sell.setItem(item);
			sell.setCreditCard(new CreditCard("VISA", 3378892003L, new Date()));

			// SL requires actions to be included into the ACTION construct
			Action a = new Action(myAgent.getAID(), sell);
			myAgent.getContentManager().fillContent(msg, a);

			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
