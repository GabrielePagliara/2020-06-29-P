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
	//private List<Match> vertici;	
	private Map<Integer, Match> idMap;
	
	
	//dao
	private PremierLeagueDAO dao;
	
	
	public Model() {
		dao = new PremierLeagueDAO();
		idMap = new HashMap<Integer, Match>();
		dao.listAllMatches(idMap);
	}
	
	public void creaGrafo(Integer mese, Integer min) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo vertici, filtrati
		Graphs.addAllVertices(grafo, dao.getMatchesOfMounth(mese, idMap));
		
		//aggiungo gli archi
		for(LinkMatches l: dao.getLink(min, idMap, mese)) {
				Graphs.addEdgeWithVertices(this.grafo, l.getM1() , l.getM2(), l.getPeso());			
		}	
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();		
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<LinkMatches> connMax() {
		if (grafo == null) {
			return null;
		}
	    List<LinkMatches> best = new ArrayList<>();
		Integer max = Integer.MIN_VALUE;

	
			for (DefaultWeightedEdge e : this.grafo.edgeSet()) {
				if (this.grafo.getEdgeWeight(e) > max) {
					best.clear();
					max = (int) grafo.getEdgeWeight(e);
					best.add(new LinkMatches(grafo.getEdgeSource(e), grafo.getEdgeTarget(e), (int) grafo.getEdgeWeight(e)));
				} else if (this.grafo.getEdgeWeight(e) == max) {
					best.add(new LinkMatches(grafo.getEdgeSource(e), grafo.getEdgeTarget(e),
							(int) grafo.getEdgeWeight(e)));
				}
			}
		
		return best;
	}
	
	
}
