package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.ContentManager;
import jade.content.abs.AbsConcept;
import jade.content.abs.AbsIRE;
import jade.content.abs.AbsPredicate;
import jade.content.abs.AbsVariable;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLVocabulary;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Costs;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;

/**
 *  SELLER handles queries received from BUYER
 */
@SuppressWarnings("serial")
public class HandleQueryBehaviour extends CyclicBehaviour {

	private Ontology ontology;
	private Codec codec;
	
	public HandleQueryBehaviour(Agent owner, Ontology ontology, Codec codec) {
		super(owner);
		this.ontology = ontology;
		this.codec = codec;
	}
	
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.QUERY_REF));
		if (msg == null) {
			block();
			return;
		}
		try {
			System.out.println("\nSELLER: Received query from BUYER. Message is");
			System.out.println(msg);
			// The content of a QUERY_REF is certainly an abstract
			// descriptor
			// representing an IRE
			ContentManager manager = myAgent.getContentManager();
			AbsIRE ire = (AbsIRE) manager.extractAbsContent(msg);
			if (ire.getTypeName().equals(SLVocabulary.IOTA)) {
				AbsPredicate p = (AbsPredicate) ire.getProposition();
				if (p.getTypeName().equals(Costs.NAME)
						&& p.getAbsTerm(Costs.PRICE) instanceof AbsVariable) {
					AbsConcept absIt = (AbsConcept) p.getAbsTerm(Costs.ITEM);
					Item it = (Item) ontology.toObject(absIt);

					myAgent.addBehaviour(new InformCostsBehaviour(myAgent, ontology, codec, it));
				} else {
					System.out.println("Can't answer to query!!");
				}
			} else {
				System.out.println("Can't manage IRE of type " + ire.getTypeName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
