package ar.edu.itba.sia.agent.message.ontology.musicshop.element;

import jade.content.onto.annotations.AggregateSlot;

import java.util.List;

import ar.edu.itba.sia.agent.message.ontology.commerce.element.Item;

public class CD extends Item {

	private String title;
	protected List<Track> tracks;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@AggregateSlot(cardMin = 1)
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(title);
		if (tracks != null) {
			int i = 0;
			for (Track track : tracks) {
				sb.append(" ");
				sb.append("track-" + i + ": " + track);
				i++;
			}
		}
		return sb.toString();
	}
}
