package ar.edu.itba.sia.agent.helloworld;

import jade.core.Agent;

import org.apache.commons.lang3.ArrayUtils;

@SuppressWarnings("serial")
public class HelloWorldAgent extends Agent {

	private String service;
	
	@Override
	protected void setup() {
		System.out.println("Hello World. My name is " + this.getLocalName());
		Object[] arguments = getArguments();
		service = String.valueOf(ArrayUtils.isEmpty(arguments) ? "[empty]" : arguments[0]);
		System.out.println("I provide service: " + service);
	}

}
