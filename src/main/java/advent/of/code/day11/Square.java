package advent.of.code.day11;

public class Square {

	private Cell c;
	
	private int size;

	
	
	public Square(Cell c, int size) {
		super();
		this.c = c;
		this.size = size;
	}

	public Cell getC() {
		return c;
	}

	public void setC(Cell c) {
		this.c = c;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return c + "," + size ;
	}
	
	
	
	
}
