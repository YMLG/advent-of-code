package advent.of.code.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay7 {

  public static void main(String[] args) {

    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static RelationInterator createIterator() {
    Set<String> letters = new HashSet<>();
    List<Relation> relations = LectureFichiersUtils.getData("fichier7.txt")
        .map(Relation::new)
        .peek(r -> {
          letters.add(r.getFirst()); 
          letters.add(r.getLast());
        })
        .collect(Collectors.toList());
    return new RelationInterator(letters, relations);
  }

  private static String getReponseQuestion1() {
    RelationInterator iter = createIterator();
    String res = "";
    while(iter.hasNext()) {
      String s = iter.next();
      iter.finish(s);
      res +=s;
    }
    return res;
  } 




  private static int getReponseQuestion2() {
    RelationInterator iter = createIterator();
    int nbWorkers = 5;
    int res = 0;
    List<Work> workers = new ArrayList<Work>(Collections.nCopies(nbWorkers, new Work()));
    while(!workers.isEmpty()) {
      Work min = Collections.min(workers);
      iter.finish(min.getLettre());
      workers.remove(min);
      workers.stream().forEach(w -> w.removeTime(min.getTime()));
      while(workers.size() <nbWorkers && iter.hasNext()) {
        workers.add(new Work(iter.next()));
      }
      res += min.getTime();
    }
    return res;
  }


}
