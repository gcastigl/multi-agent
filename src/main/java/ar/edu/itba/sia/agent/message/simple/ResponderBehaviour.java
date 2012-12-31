package ar.edu.itba.sia.agent.message.simple;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ResponderBehaviour extends SimpleBehaviour {

	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

	public ResponderBehaviour(Agent owner) {
		super(owner);
	}

	@Override
	public void action() {
		ACLMessage message = myAgent.receive(mt);
		if (message != null) {
			System.out.println("Message to : " + myAgent.getLocalName());
			System.out.println("Message: " + message);
		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
