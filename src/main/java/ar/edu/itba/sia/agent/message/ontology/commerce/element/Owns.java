package ar.edu.itba.sia.agent.message.ontology.commerce.element;

import jade.content.Predicate;
import jade.core.AID;

@SuppressWarnings("serial")
public class Owns implements Predicate {

	private AID owner;
	private Item item;

	public AID getOwner() {
		return owner;
	}

	public void setOwner(AID owner) {
		this.owner = owner;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
