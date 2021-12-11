package advent.of.code.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grille<T> {
	
	private List<List<T>> map;
	
	
	public Grille(List<List<T>> map) {
		this.map = map;
	}

	public Optional<T> get(int i, int j) {
		if(i>= 0 && i< map.size() && j >= 0 && j < map.get(i).size()) {
			return Optional.of(map.get(i).get(j));
		}
		return Optional.empty();
	}
	
	public List<T> getVoisines(int i, int j){
		List<Optional<T>> voisines = new ArrayList<>();
		voisines.add(get(i-1, j-1));
		voisines.add(get(i-1, j));
		voisines.add(get(i-1, j+1));
		voisines.add(get(i, j-1));
		voisines.add(get(i, j+1));
		voisines.add(get(i+1, j-1));
		voisines.add(get(i+1, j));
		voisines.add(get(i+1, j+1));
		return voisines.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
	}

	public List<List<T>> getMap() {
		return map;
	}
	
	public Stream<T> getAll(){
		return map.stream().flatMap(List::stream);
	}
	

}
