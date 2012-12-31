package ar.edu.itba.sia.agent.message.ontology.musicshop.element;

import jade.content.Concept;
import jade.content.onto.annotations.Slot;

@SuppressWarnings("serial")
public class Track implements Concept {

	private String name;
	private Integer duration;
	// Pulse-Code Modulation
	private byte[] pcm;

	public Track() {
	}
	
	public Track(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Slot(mandatory = false)
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Slot(mandatory = false)
	public byte[] getPcm() {
		return pcm;
	}

	public void setPcm(byte[] pcm) {
		this.pcm = pcm;
	}

	public String toString() {
		return name + (duration != null ? ("[" + duration.intValue() + " sec]") : "");
	}
}
