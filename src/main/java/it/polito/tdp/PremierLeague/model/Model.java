package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private SimpleWeightedGraph<Match, DefaultWeightedEdge> grafo;
	private List<Match> vertici;	
	private Map<Integer, Match> idMap;
	
	//dao
	private PremierLeagueDAO dao;
	
	public Model() {
		dao = new PremierLeagueDAO();
		idMap = new HashMap<Integer, Match>();
		dao.listAllMatches(idMap);
	}
	
	public String creaGrafo(Integer mese) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo vertici, filtrati
		Graphs.addAllVertices(grafo, dao.getMatchesOfMounth(mese, idMap));
		
		//aggiungo gli archi
		
		


		
		
		
		
		
		return null;		
	}
	
	
}
