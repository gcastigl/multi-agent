package ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.abs.AbsConcept;
import jade.content.abs.AbsContentElement;
import jade.content.abs.AbsIRE;
import jade.content.abs.AbsPredicate;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLVocabulary;
import jade.content.onto.Ontology;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Costs;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Owns;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Price;
import ar.edu.itba.sia.agent.message.ontology.commerce.element.Sell;

/**
 *  BUYER handles informations received from the SELLER
 */
@SuppressWarnings("serial")
public class HandleInformBehaviour extends CyclicBehaviour {

	private Ontology ontology;
	private Codec codec;
	
	public HandleInformBehaviour(Agent owner, Ontology ontology, Codec codec) {
		super(owner);
		this.codec = codec;
		this.ontology = ontology;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		if (msg == null) {
			block();
			return;
		}
		System.out.println("\nBUYER: Information received from SELLER. Message is");
		System.out.println(msg);
		ContentManager manager = myAgent.getContentManager();
		try {
			ContentElement ce = manager.extractContent(msg);
			if (ce instanceof Owns) {
				Owns owns = (Owns) ce;
				AID owner = owns.getOwner();
				System.out.println("Owner is: " + owner);
				Item it = owns.getItem();
				System.out.println("Item is: " + it);

				myAgent.addBehaviour(new QueryPriceBehaviour(myAgent, ontology, codec, it));
			} else if (ce instanceof Costs) {
				Costs c = (Costs) ce;
				Item it = c.getItem();
				Price p = c.getPrice();
				System.out.println("Item ");
				System.out.println(it);
				System.out.println("costs " + p);

				myAgent.addBehaviour(new RequestSellBehaviour(myAgent, ontology, codec, it));
			} else if (ce instanceof Done) {
				Done d = (Done) ce;
				Action aa = (Action) d.getAction();
				Sell s = (Sell) aa.getAction();
				System.out.println("OK! Now I own Item " + s.getItem());
				myAgent.doDelete();
			} else {
				System.out.println("Unknown predicate " + ce.getClass().getName());
			}
		} catch (UngroundedException ue) {
			// The message content includes variables --> It must be an
			// abs descriptor
			handleUngroundedException(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleUngroundedException(ACLMessage msg) {
		try {
			ContentManager manager = myAgent.getContentManager();
			AbsContentElement ce = manager.extractAbsContent(msg);
			if (ce.getTypeName().equals(SLVocabulary.EQUALS)) {
				AbsIRE iota = (AbsIRE) ce.getAbsObject(SLVocabulary.EQUALS_LEFT);
				AbsPredicate costs = iota.getProposition();
				AbsConcept absIt = (AbsConcept) costs.getAbsObject(Costs.ITEM);
				Item it = (Item) ontology.toObject(absIt);

				AbsConcept absP = (AbsConcept) ce.getAbsObject(SLVocabulary.EQUALS_RIGHT);
				Price p = (Price) ontology.toObject(absP);

				System.out.println("Item ");
				System.out.println(it);
				System.out.println("costs " + p);

				myAgent.addBehaviour(new RequestSellBehaviour(myAgent, ontology, codec, it));
			} else {
				System.out.println("Unknown predicate " + ce.getTypeName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
