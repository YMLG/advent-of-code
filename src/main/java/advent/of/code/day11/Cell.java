package advent.of.code.day11;

public class Cell {
	
	private int x;
	
	private int y;
	
	private int serial;
	
	private Integer power;
	
	public Cell(int x, int y, int serial) {
		this.x = x;
		this.y = y;
		this.serial = serial;
	}

	public int getX() {
		return x;
	}
	
	public int getRackId() {
		return x+10;
	}
	
	public int getPower() {
		if(power == null) {
			power = getHundredsDigit((getRackId()*y+serial)* getRackId())-5;
		}
		return power;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	
	}
	
	private static int getHundredsDigit(Integer n) {
		return n/100 %10;
	}

	@Override
	public String toString() {
		return x + "," + y ;
	}	
	
}
