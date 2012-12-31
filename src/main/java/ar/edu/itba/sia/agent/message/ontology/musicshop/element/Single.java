package ar.edu.itba.sia.agent.message.ontology.musicshop.element;

import jade.content.onto.annotations.AggregateSlot;

import java.util.List;

public class Single extends CD {

	@AggregateSlot(cardMin = 2, cardMax = 2)
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
}
