package advent.of.code.a2021.day11;

public class Dumbo {

	private static int nbFash = 0;
	
	private int energy;
	private boolean flashed = false;

	public Dumbo(int energy) {
		this.energy = energy;
	}
	
	public boolean flash() {
		if(energy > 9) {
			energy = 0;
			nbFash++;
			flashed=true;
			return true;
		}
		return false;
	}
	
	public void increment() {
		if(!flashed) {
			energy++;
		}
	}

	public void unFlash() {
		flashed = false;
	}
	
	public boolean isFlashed() {
		return flashed;
	}


	public static int getNbFash() {
		return nbFash;
	}
	
	
	
}
