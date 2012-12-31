package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class InformDoneBehaviour extends OneShotBehaviour {

	private Action action;
	private Codec codec;
	private Ontology ontology;
	
	public InformDoneBehaviour(Agent owner, Action action, Ontology ontology, Codec codec) {
		super(owner);
		this.action = action;
		this.ontology = ontology;
		this.codec = codec;
	}

	@Override
	public void action() {
		try {
			System.out.println("\nSELLER: Inform Buyer that the requested operation has been completed");

			// Prepare the message
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			AID receiver = myAgent.getAID(); // Send the message to myself

			msg.setSender(myAgent.getAID());
			msg.addReceiver(receiver);
			msg.setLanguage(codec.getName());
			msg.setOntology(ontology.getName());

			// Fill the content
			Done d = new Done(action);
			myAgent.getContentManager().fillContent(msg, d);
			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
