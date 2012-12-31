package ar.edu.itba.sia.agent.message.simple;

import jade.core.Agent;

@SuppressWarnings("serial")
public class ReceiverAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println("Receiver agent " + getLocalName());
		addBehaviour(new ResponderBehaviour(this));
	}
	
}
