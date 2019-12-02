package advent.of.code.a2018.day8;

import java.util.Arrays;
import java.util.Iterator;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay8 {

  public static void main(String[] args) {
    String input = LectureFichiersUtils.getData("2018/fichier8.txt").findFirst().orElse("");
    Iterator<Integer> l = Arrays.asList(input.split(" ")).stream().map(Integer::valueOf).iterator();
	Node node = getNode(l);

    System.out.println("Question 1 - "+ node.getSumMetadata());
    System.out.println("Question 2 - "+ node.getValue());

  }
  
  
  
  private static Node getNode(Iterator<Integer> l) {
	  int nbChild = l.next();
	  int nbMetadata = l.next();
	  Node n = new Node();
	  for(int j = 0; j< nbChild ; j++) {
		  n.addChild(getNode(l));
	  }
	  for(int j = 0; j< nbMetadata ; j++) {
		  n.addMetadata(l.next());
	  }
	  return n;
  }


}
