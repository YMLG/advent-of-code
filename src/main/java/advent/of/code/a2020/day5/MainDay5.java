package advent.of.code.a2020.day5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay5 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		return getData().mapToLong(MainDay5::seatId).max().getAsLong();
	}




	private static long getReponseQuestion2() {
		List<Long> seats = getData().map(MainDay5::seatId).collect(Collectors.toList());
		for(Long id : seats) {
			if(!seats.contains(id + 1) && seats.contains(id+2)) {
					return id + 1;
			}
		}
		return 0;
	}


	private static long seatId(String code) {
		long rowMin = 0;
		long rowMax = 127;
		long colMin = 0;
		long colMax = 7;
		for(char c : code.toCharArray()) {
			switch (c) {
			case 'F':
				rowMax -= (rowMax - rowMin) /2.;
				break;
			case 'B':
				rowMin += (rowMax - rowMin) /2.;
				break;
			case 'L':
				colMax -= (colMax - colMin) /2.;
				break;
			case 'R':
				colMin += (colMax - colMin) /2.;
				break;
			default:
				break;
			} 
		}
		return rowMax * 8 + colMax;

	}



	private static Stream<String> getData() {
		return LectureFichiersUtils.getData("2020/input5.txt");
	}
}
