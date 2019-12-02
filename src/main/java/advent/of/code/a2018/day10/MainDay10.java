package advent.of.code.a2018.day10;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay10 {

  public static void main(String[] args) {
    List<Point> points = LectureFichiersUtils.getData("2018/fichier10.txt").map(Point::new).collect(Collectors.toList());
    int i=0;
    while(Collections.max(points.stream().collect(Collectors.groupingBy(Point::getX, Collectors.counting())).values())<20) {
      points.forEach(Point::run);
      i++;
    }
    System.out.println("Question 1 - ");
    afficheCarte(points);
    System.out.println("Question 2 - "+ i);

  }


  public static void afficheCarte(List<Point> points) {
    int maxX =  points.stream().mapToInt(Point::getX).max().orElse(0);
    int minX =  points.stream().mapToInt(Point::getX).min().orElse(0);
    int maxY =  points.stream().mapToInt(Point::getY).max().orElse(0);
    int minY =  points.stream().mapToInt(Point::getY).min().orElse(0);
    for(int j = minY; j< maxY+1; j++) {
      for(int i = minX; i< maxX+1; i++) {
        if(points.contains(new Point(i, j))) {
          System.out.print("#");
        }else {
          System.out.print(".");
        }

      }
      System.out.println();;
    }

  }

}
