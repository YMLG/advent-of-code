package advent.of.code.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;
import advent.of.code.utils.StringUtils;

public class MainDay13 {

  private static List<Position> positions;
  private static List<Voiture> voitures;

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());
  }

  private static void init() {
    voitures = new ArrayList<>();
    AtomicInteger indexY = new AtomicInteger();
    positions = LectureFichiersUtils.getData("fichier13.txt")
        .map(StringUtils::toCharStream).map(s -> {
              int y = indexY.getAndIncrement();
              AtomicInteger indexX = new AtomicInteger();
              return s.peek(c ->{
                if('<' == c || '>' == c || '^'==c || 'v' ==c) {
                  voitures.add(new Voiture(indexX.get() ,y , c));
                }
              }).map(c -> new Position(indexX.getAndIncrement(), y, c));
        }).flatMap(Function.identity())
        .collect(Collectors.toList());

  }

  private static String getReponseQuestion2() {
    init() ;
    while(voitures.size() > 1) {  
      Collections.sort(voitures);
      List<Voiture> oldvoitures = new ArrayList<>(voitures);
      for(Voiture voiture : oldvoitures) {
        voiture.run(positions);
        if(getCrash(oldvoitures).isPresent()) {
          voitures.removeIf(v -> v.getPosition().equals(getCrash(oldvoitures).get())) ;
        }
      }
    }
    return voitures.get(0).getPosition().toString();
  }

  private static String getReponseQuestion1() {
    init();
    while(!getCrash(voitures).isPresent()) {      
      int current = 0;
      Collections.sort(voitures);
      while(current < voitures.size() && !getCrash(voitures).isPresent()) {
        voitures.get(current).run(positions);
        current++;
      }
    }
    return getCrash(voitures).get().toString();

  }

  private static Optional<Position> getCrash(List<Voiture> voitures) {
    Set<Position> positions = new HashSet<>();
    return voitures.stream().map(Voiture::getPosition).filter(p -> !positions.add(p)).findFirst();
  }
}
