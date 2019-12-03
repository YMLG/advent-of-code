package advent.of.code.a2019.day3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay3 {

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }
  
  
  private static int getReponseQuestion1() {
    Map<Point, Long> pointsTouches = getData().map(MainDay3::getPositions)
             .flatMap(Set::stream)
             .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return pointsTouches.entrySet().stream()
        .filter(e -> e.getValue() > 1)//on prend les cases où plus d'une voiture est passée
        .mapToInt(e -> e.getKey().getDistanceToCenter())
        .min()
        .orElse(0);
  }
  
  private static Set<Point> getPositions(List<String> parcourt){
    Set<Point> positions = new HashSet<>();
    Point currentPosition = new Point(0, 0);
    parcourt.forEach(instruction ->{
      positions.addAll(currentPosition.move(instruction));
    });
    return positions;
  }
  
  private static int getReponseQuestion2() {
    List<Set<Point>> parcourts = getData().map(MainDay3::getPositions).collect(Collectors.toList());
    Map<Point, Long> pointsTouches = parcourts.stream()
                                .flatMap(Set::stream)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Set<Point> croisements = pointsTouches.entrySet().stream().filter(e -> e.getValue() > 1).map(Entry::getKey).collect(Collectors.toSet());
    return croisements.stream().mapToInt(p -> parcourts.stream().mapToInt(parcourt -> parcourt.stream().filter(point -> point.equals(p)).findFirst().get().getDistance()).sum()).min().orElse(0);
  }
  
  private static Stream<List<String>> getData() {
    return LectureFichiersUtils.getData("2019/input3.txt").map(l -> Arrays.asList(l.split(",")));
  }
}
