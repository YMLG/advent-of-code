package advent.of.code.day6;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay6 {

  public static void main(String[] args) {
    List<Position> positions = LectureFichiersUtils.getData("fichier6.txt").map(Position::new).collect(Collectors.toList());
    int maxX = positions.stream().map(Position::getX).max(Comparator.naturalOrder()).orElse(0);
    int maxY = positions.stream().map(Position::getY).max(Comparator.naturalOrder()).orElse(0);
    
    System.out.println("Question 1 - "+ getReponseQuestion1(positions, maxX, maxY));
    System.out.println("Question 2 - "+ getReponseQuestion2(positions, maxX, maxY));

  }

  private static Integer getReponseQuestion1(List<Position> positions, int maxX, int maxY) {
    Map<String, Integer> area = new HashMap<>();
    Set<String> infiniteAreas = new HashSet<>();
    for(int i = 0; i < maxX +1; i++) {
      for(int j = 0; j< maxY +1; j++) {
        Position position = new Position(i, j);
        Optional<Entry<Integer, List<Position>>> closest = positions.stream()
                                          .collect(Collectors.groupingBy(p -> p.distance(position), Collectors.toList()))
                                          .entrySet().stream()
                                          .min(Comparator.comparing(Entry::getKey))                                      
                                          ;
        if(closest.isPresent() && closest.get().getValue().size() == 1) {
            String areaId = closest.get().getValue().get(0).getIndent();
            if(area.containsKey(areaId)) {
              area.put(areaId, area.get(areaId) + 1);
            }
            else {
              area.put(areaId,1);
            }
            if(i == 0 || i == maxX || j == 0 || j==maxY) {
              infiniteAreas.add(areaId);
            }
        }
         
      }
    }
   return area.entrySet().stream()
                         .filter(e -> !infiniteAreas.contains(e.getKey()))
                         .map(Entry::getValue)
                         .max(Comparator.naturalOrder())
                         .orElse(0);
  }

  private static Integer getReponseQuestion2(List<Position> positions, int maxX, int maxY) {
    int size = 0;
    for(int i = 0; i < maxX +1; i++) {
      for(int j = 0; j< maxY +1; j++) {
        Position position = new Position(i, j);
        if(positions.stream()
            .map(p -> p.distance(position))
            .mapToInt(Integer::valueOf)
            .sum() < 10000 ){
          size++;
        }
      }
    }
    return size;
  }
}
