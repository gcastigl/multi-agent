package ar.edu.itba.sia.helloworld;

import jade.Boot;

public class Main {

	public static void main(String[] args) {
		args = new String[] {"-agents", "Test_agent_1:" + HelloWorldAgent.class.getCanonicalName()};
		Boot.main(args);
	}
}
