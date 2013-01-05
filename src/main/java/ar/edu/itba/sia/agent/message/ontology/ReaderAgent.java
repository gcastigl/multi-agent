package ar.edu.itba.sia.agent.message.ontology;

import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ReaderAgent extends Agent {

	private Ontology ontology = PublicationOntology.getInstance();
	private Codec codec = new SLCodec();
	
	@Override
	protected void setup() {
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		addBehaviour(new RecievePublicationsBehaviour(this));
	}

	private class RecievePublicationsBehaviour extends TickerBehaviour {
		public RecievePublicationsBehaviour(Agent agent) {
			super(agent, 2000);
		}
		
		@Override
		protected void onTick() {
			ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			if (msg == null) {
				block();
				return;
			}
			try {
				Action a = (Action) myAgent.getContentManager().extractContent(msg);
				Publication publication = (Publication) a.getAction();
				System.out.println("READER: Received request from BUYER. Message is : " + publication.getText());
				respondEvaluation(msg, publication);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		
		private void respondEvaluation(ACLMessage originalMsg, Publication publication) throws CodecException, OntologyException {
			ACLMessage responseMessage = new ACLMessage(ACLMessage.INFORM);
			responseMessage.setOntology(ontology.getName());
			responseMessage.setLanguage(codec.getName());
			responseMessage.setSender(myAgent.getAID());
			responseMessage.addReceiver(originalMsg.getSender());
			PublicationResult result = new PublicationResult((int) (Math.random() * 10));
			Action action = new Action(myAgent.getAID(), result);
			myAgent.getContentManager().fillContent(responseMessage, action);
			myAgent.send(responseMessage);
		}
	}
}
