package advent.of.code.day3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay3 {

  private static Map<Position, Set<Integer>> casesPrises = new HashMap<>(); 
  
  public static void main(String[] args) {
    LectureFichiersUtils.getData("fichier3.txt")
      .map(Claim::new)
      .forEach(MainDay3::placeClaim);
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());
  }

  private static int getReponseQuestion2() {
    Map<Boolean, Set<Integer>> cases = casesPrises.values().stream()
                                                .collect(Collectors.partitioningBy(c -> c.size()==1, 
                                                         Collector.of(HashSet::new, Set::addAll, (a, b) ->{ 
                                                                                                     a.addAll(b);
                                                                                                     return a;
                                                                                                     })));
    Set<Integer> goodClaim = cases.get(true);
    goodClaim.removeAll(cases.get(false));
    return goodClaim.iterator().next();
  }

  private static long getReponseQuestion1() {
    return casesPrises.values().stream()
                .filter(c -> c.size()>1)
                .count();
  }
  
  private static void placeClaim(Claim d) {
    
    for(int i=d.getX(); i<d.getX()+d.getL();i++) {
      for(int j=d.getY(); j<d.getY()+d.getH(); j++) {
        addCase(d.getId(), i, j);
      }
    }
  }

  private static void addCase(int id, int i, int j) {
    Position p = new Position(i, j);
    if(casesPrises.containsKey(p)) {
      casesPrises.get(p).add(id);
    }else {
      Set<Integer> s = new HashSet<>();
      s.add(id);
      casesPrises.put(p, s);
    }
    
  }

}
