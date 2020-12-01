package advent.of.code.a2020.day1;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay1 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static int getReponseQuestion1() {
	return getIntData().stream()
			.map(i -> getIntData().stream()
					.filter(j -> i+j == 2020)
					.findAny()
					.map(j -> i*j))
		.flatMap(Optional::stream)
		.findAny()
		.get();
  }
  
  private static int getReponseQuestion2() {
	return getIntData().stream().map(i -> getIntData().stream()
											.filter(k -> k+i < 2020)
											.map(k -> getIntData().stream()
													.filter(j -> k+i+j == 2020)
													.findAny()
													.map(j -> i * j * k))
											.flatMap(Optional::stream))
			.flatMap(Function.identity()).findAny().get();
  }



  private static Set<Integer> getIntData() {
    return LectureFichiersUtils.getData("2020/input1.txt").map(Integer::valueOf).collect(Collectors.toSet());
  }
}
