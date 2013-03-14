package ar.edu.itba.sia.agent.message.ontology;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class WriterAgent extends Agent {

	private List<AID> evaluatorAgents;
	private Ontology ontology = PublicationOntology.getInstance();
	private Codec codec = new SLCodec();

	@Override
	protected void setup() {
		if (getArguments() == null) {
			throw new IllegalArgumentException("No Readers specified!");
		}
		evaluatorAgents = new LinkedList<>();
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		System.out.println("Readers: ");
		for (Object o : getArguments()) {
			evaluatorAgents.add(new AID(o.toString(), AID.ISLOCALNAME));
			System.out.println("\t->" + o.toString());
		}
		addBehaviour(new SendPublicationsBehaviour(this));
		addBehaviour(new RecieveEvaluationsBehaviour(this));
	}

	@Override
	protected void takeDown() {
		evaluatorAgents = null;
	}

	private class SendPublicationsBehaviour extends TickerBehaviour {

		public SendPublicationsBehaviour(Agent agent) {
			super(agent, 2000);
		}

		@Override
		protected void onTick() {
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology(ontology.getName());
			msg.setLanguage(codec.getName());
			msg.setSender(myAgent.getAID());
			for (AID evaluator : evaluatorAgents) {
				msg.addReceiver(evaluator);
			}
			Publication publication = new Publication("Some text: " + (Math.random() * 100));
			Action action = new Action(myAgent.getAID(), publication);
			try {
				myAgent.getContentManager().fillContent(msg, action);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
			myAgent.send(msg);
		}
	}

	private class RecieveEvaluationsBehaviour extends TickerBehaviour {

		public RecieveEvaluationsBehaviour(Agent agent) {
			super(agent, 2000);
		}

		@Override
		protected void onTick() {
			ACLMessage msg;
			do {
				msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				if (msg == null) {
					block();
					return;
				}
				try {
					Action a = (Action) myAgent.getContentManager().extractContent(msg);
					PublicationResult result = (PublicationResult) a.getAction();
					System.out.println("Recieved result from : " + msg.getSender());
					System.out.println("Evalutation : " + result.getScore());
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			} while (msg != null);
		}
	}
}
