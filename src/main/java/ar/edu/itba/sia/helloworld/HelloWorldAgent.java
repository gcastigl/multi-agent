package ar.edu.itba.sia.helloworld;

import jade.core.Agent;

@SuppressWarnings("serial")
public class HelloWorldAgent extends Agent {

	@Override
	protected void setup() {
		System.out.println("Hello World. My name is " + this.getLocalName());
	}

}
