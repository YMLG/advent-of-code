package advent.of.code.a2021.day2;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay2 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static int getReponseQuestion1() {
		Position position = LectureFichiersUtils.getData("2021/input2.txt")
				.reduce(new Position(), MainDay2::move, Position::add);
		return position.getDepth() * position.getPosition();
	}
	
	private static int getReponseQuestion2() {
		PositionWithAim position = LectureFichiersUtils.getData("2021/input2.txt")
				.reduce(new PositionWithAim(), MainDay2::move, PositionWithAim::add);
		return position.getDepth() * position.getPosition();

	}




	private static <P extends PositionInterface> P move(P p, String e) {
		String[] order = e.split(" ");
		int n = Integer.parseInt(order[1]);
		switch (order[0]) {
		case "forward":
			p.forward(n);
			break;
		case "up":
			p.up(n);
			break;
		case "down":
			p.down(n);
			break;
		default:
			break;
		}
		return p;
	}

	
	

}
