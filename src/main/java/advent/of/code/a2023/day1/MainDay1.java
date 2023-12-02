package advent.of.code.a2023.day1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay1 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
	return getData().stream()
		.map(MainDay1::getOnlyInterger)
		.map(MainDay1::getFirstAndLast)
		.mapToLong(Long::parseLong)
		.sum();
  }	
  
  private static long getReponseQuestion2() {
	  return getData().stream()
				.map(MainDay1::replaceFirstSpelledNumbers)
				.map(MainDay1::replaceLastSpelledNumbers)
				.map(MainDay1::getOnlyInterger)
				.map(MainDay1::getFirstAndLast)
				.mapToLong(Long::parseLong)
				.sum();
	  
  }

  private static String getFirstAndLast(String ligne) {
	  return ligne.charAt(0)+""+ligne.charAt(ligne.length()-1);
  }
  
  private static String getOnlyInterger(String ligne) {
	  return ligne.replaceAll("\\D", "");
  }
 
  private static String replaceFirstSpelledNumbers(String ligne) {
	  Map<Number, Integer> firstIndex = Arrays.asList(Number.values()).stream()
	  	.collect(Collectors.toMap(Function.identity(), n -> ligne.indexOf(n.toString())));
	  
	  Entry<Number, Integer> min = null;
	  for (Entry<Number, Integer> entry : firstIndex.entrySet()) {
	      if (entry.getValue() != -1 && (min == null || min.getValue() > entry.getValue())) {
	          min = entry;
	      }
	  }
	  if(min != null) {
		  return  ligne.replaceFirst(min.getKey().toString(), String.valueOf(min.getKey().getValue())+min.getKey().toString());
	  }
	  return ligne;
  }

  private static String replaceLastSpelledNumbers(String ligne) {
	  Map<Number, Integer> lastIndex = Arrays.asList(Number.values()).stream()
	  	.collect(Collectors.toMap(Function.identity(), n -> ligne.lastIndexOf(n.toString())));
	  
	  Entry<Number, Integer> max = null;
	  for (Entry<Number, Integer> entry : lastIndex.entrySet()) {
	      if (entry.getValue() != -1 && (max == null || max.getValue() < entry.getValue())) {
	    	  max = entry;
	      }
	  }
	  if(max != null) {
		  return  ligne.replaceAll(max.getKey().toString(), String.valueOf(max.getKey().getValue()));
	  }
	  return ligne;
  }
  
  private static List<String> getData() {
    return LectureFichiersUtils.getData("2023/input1.txt").collect(Collectors.toList());
  }
}
