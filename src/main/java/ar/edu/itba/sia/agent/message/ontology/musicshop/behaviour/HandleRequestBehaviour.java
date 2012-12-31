package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Sell;

@SuppressWarnings("serial")
public class HandleRequestBehaviour extends CyclicBehaviour {

	private Ontology ontology;
	private Codec codec;
	
	public HandleRequestBehaviour(Agent owner, Ontology ontology, Codec codec) {
		super(owner);
		this.ontology = ontology;
		this.codec = codec;
	}
	
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		if (msg == null) {
			block();
			return;
		}
		try {
			System.out.println("\nSELLER: Received request from BUYER. Message is");
			System.out.println(msg);
			Action a = (Action) myAgent.getContentManager().extractContent(msg);
			Sell sell = (Sell) a.getAction();

			System.out.println("Buyer is: " + sell.getBuyer());
			System.out.println("Item is: " + sell.getItem());
			System.out.println("Credit Card is: " + sell.getCreditCard());

			// Do the action. Not implemented as it is out of the scope
			// of this example

			myAgent.addBehaviour(new InformDoneBehaviour(myAgent, a, ontology, codec));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
