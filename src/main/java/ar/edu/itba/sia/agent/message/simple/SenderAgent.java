package ar.edu.itba.sia.agent.message.simple;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class SenderAgent extends Agent {

	private String receiverName;

	@Override
	protected void setup() {
		super.setup();
		System.out.println("Sender agent " + getLocalName());
		receiverName = getArguments()[0].toString();
		System.out.println("Sending message to " + receiverName);
		sendMessage();
	}

	private void sendMessage() {
		AID receiver = new AID(receiverName, AID.ISLOCALNAME);
		// AID receiver = new AID(receiverName + "@" + getHap(), AID.ISGUID);
		ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
		aclMessage.addReceiver(receiver);
		aclMessage.setContent("Hello! How are you jack?");
		send(aclMessage);
	}

}
