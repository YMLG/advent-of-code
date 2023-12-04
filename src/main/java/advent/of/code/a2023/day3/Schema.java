package advent.of.code.a2023.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Schema {

	private List<List<String>> data = new ArrayList<>();

	public void addLigne(List<String> ligne ) {
		data.add(ligne);
	}

	public List<List<String>> getData() {
		return data;
	}

	public Optional<String> getCase(int i, int j) {
		if(i>= 0 && i < data.size() ) {
			List<String> ligne = data.get(i);
			if(j >=0 && j < ligne.size()) {
				String c = ligne.get(j);
				if(!".".equals(c)){
					return Optional.of(c);
				}
			}
		}
		return Optional.empty();
	}


	public boolean isNumber(int i, int j) {
		return getCase(i, j).isPresent() && getCase(i, j).get().matches("\\d+");
	}
	public boolean isEngrenage(int i, int j) {
		return getCase(i, j).isPresent() && getCase(i, j).get().equals("*");
	}


	public List<Optional<String>> getVoisines(int i, int j){
		return Arrays.asList(getCase(i-1, j),getCase(i-1, j-1), getCase(i-1, j+1),
				getCase(i, j-1), getCase(i, j+1),
				getCase(i+1, j-1), getCase(i+1, j), getCase(i+1, j+1))
				;
	}

	public List<Long> getNombresVoisins(int i, int j) {
		List<Long> voisins = new ArrayList<>();
		for(int x = i-1; x < i+2; x++ ) {
			List<String> ligne = data.get(x);
			for(int y = j-1; y < j+2; y++ ) {
				String c = ligne.get(y);
				if(c.matches("\\d")) {
					String nombre ="";
					while( y >=0 && ligne.get(y).matches("\\d")) {
						y--;
					}
					while(++y < ligne.size() && ligne.get(y).matches("\\d")) {
						nombre+=ligne.get(y);
					}
					voisins.add(Long.parseLong(nombre));
				}
			}
		}
		return voisins;
	}
}
