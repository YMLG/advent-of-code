package advent.of.code.a2021.day2;

public class PositionWithAim implements PositionInterface{
	
		private int depth = 0 ;

		private int position = 0;
		
		private int aim = 0;

		public static PositionWithAim add(PositionWithAim p1, PositionWithAim p2) {
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
			depth += aim * n;
			
		}

		public void up(int n) {
			aim -= n ;
		}
		public void down(int n) {
			aim += n ;
		}
	}