package ar.edu.itba.sia.agent.message.ontology.musicshop;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour.HandleInformBehaviour;
import ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour.HandleQueryBehaviour;
import ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour.HandleRequestBehaviour;
import ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour.InformOwnsBehaviour;
import ar.edu.itba.sia.agent.message.ontology.musicshop.behaviour.MusicShopOntology;
import ar.edu.itba.sia.agent.message.ontology.musicshop.element.CD;
import ar.edu.itba.sia.agent.message.ontology.musicshop.element.Track;

@SuppressWarnings("serial")
public class CDTraderAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		// This agent "speaks" the SL language
		Codec codec = new SLCodec();
		Ontology ontology;
		try {
			// This agent "knows" the Music-Shop ontology
			ontology = MusicShopOntology.getInstance();
		} catch (BeanOntologyException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		ContentManager manager = getContentManager();
		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		// BUYER PART
		addBehaviour(new HandleInformBehaviour(this, ontology, codec));

		// SELLER PART
		addBehaviour(new HandleQueryBehaviour(this, ontology, codec));
		addBehaviour(new HandleRequestBehaviour(this, ontology, codec));

		CD myCd = new CD();
		myCd.setSerial(123456);
		myCd.setTitle("Synchronicity");
		List<Track> tracks = new ArrayList<>();
		Track t = new Track("Synchronicity");
		tracks.add(t);
		t = new Track("Every breath you take");
		tracks.add(t);
		t = new Track("King of pain");
		t.setDuration(240);
		tracks.add(t);

		myCd.setTracks(tracks);

		addBehaviour(new InformOwnsBehaviour(this, ontology, codec, myCd));
	}
	
	@Override
	protected void takeDown() {
		System.out.println(getName() + " exiting ...");
	}
}
