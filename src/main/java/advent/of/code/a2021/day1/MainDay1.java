package advent.of.code.a2021.day1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay1 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
	List<Integer> data = getIntData();
	return getNbIncrease(data);
  }
  
  private static long getReponseQuestion2() {
	  List<Integer> data = getIntData();
	  List<Integer> dataSum = IntStream.range(2,  data.size())
			  .map(i-> data.get(i) + data.get(i-1) + data.get(i-2))
			  .boxed()
			  .collect(Collectors.toList());
		return getNbIncrease(dataSum);
	  
  }

  private static long getNbIncrease(List<Integer> data) {
		return IntStream.range(1,  data.size())
				.filter(i -> data.get(i) > data.get(i-1))
				.count();
  }

  private static List<Integer> getIntData() {
    return LectureFichiersUtils.getData("2021/input1.txt").map(Integer::valueOf).collect(Collectors.toList());
  }
}
