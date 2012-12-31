package ar.edu.itba.sia.agent.message.ontology.commerce.element;

import jade.content.Predicate;

@SuppressWarnings("serial")
public class Costs implements Predicate {

	public static final String NAME = "Costs";

	public static final String ITEM = "item";
	public static final String PRICE = "price";

	private Item item;
	private Price price;

	public Item getItem() {
		return item;
	}

	public void setItem(Item i) {
		item = i;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price p) {
		price = p;
	}
}
