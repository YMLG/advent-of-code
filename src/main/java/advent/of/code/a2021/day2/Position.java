package advent.of.code.a2021.day2;

public class Position implements PositionInterface{
	private int depth = 0 ;

	private int position = 0;

	public static Position add(Position p1, Position p2) {
		return null;
	}
	
	public int getDepth() {
		return depth;
	}

	public int getPosition() {
		return position;
	}

	public void forward(int n) {
		position +=n;
		
	}

	public void up(int n) {
		depth -= n ;
	}
	public void down(int n) {
		depth += n ;
	}
}