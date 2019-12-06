package advent.of.code.a2019.day6;

import java.util.HashSet;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay6 {

  private static Map<String, Set<String>> relationsDirectes = getData();

  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static int getReponseQuestion1() {
    return relationsDirectes.keySet().stream()
        .mapToInt(MainDay6::getNbRelations)
        .sum();
  }

  private static int getNbRelations(String idObjet) {
    if(relationsDirectes.containsKey(idObjet)) {
      int nbRelationIndirecte = relationsDirectes.get(idObjet).stream()
                                  .mapToInt(MainDay6::getNbRelations)
                                  .sum();
      return relationsDirectes.get(idObjet).size() + nbRelationIndirecte;
    }
    return 0;
  }

  private static int getReponseQuestion2() {
     return getNbCheminToSan("YOU", new HashSet<String>()).getAsInt() - 1;
  }

  private static Set<String> getRelations2Sens(String objet){
    Set<String> relations = new HashSet<>();
    if(relationsDirectes.get(objet) != null) {
      relations.addAll(relationsDirectes.get(objet));
    }
    relations.addAll(relationsDirectes.keySet().stream()
        .filter(k -> relationsDirectes.get(k).contains(objet))
        .collect(Collectors.toSet()));
    return relations;
  }
  
  private static OptionalInt getNbCheminToSan(String objet, Set<String> objetsVisites) {
    objetsVisites.add(objet);
    Set<String> relations = getRelations2Sens(objet);
    if(relations.isEmpty()) {
      return OptionalInt.empty();
    }
    if(relations.contains("SAN")) {
      return OptionalInt.of(0);
    }
    return relations.stream()
        .filter(o -> !objetsVisites.contains(o))
        .map(o -> getNbCheminToSan(o, objetsVisites))
        .filter(OptionalInt::isPresent)
        .mapToInt(OptionalInt::getAsInt)
        .map(i-> i+1)
        .min();

  }

  private static Map<String, Set<String>> getData() {
    return LectureFichiersUtils.getData("2019/input6.txt")
        .map(s -> s.split("\\)"))
        .collect(Collectors.groupingBy(relation -> relation[0], Collectors.mapping(relation -> relation[1], Collectors.toSet())));
  }
}
