package advent.of.code.day2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class Mainday2 {

    
    public static void main(String[] args) {
        System.out.println("Question 1 - "+ getReponseQuestion1());
        System.out.println("Question 2 - "+ getReponseQuestion2());
    }
    

    private static long getReponseQuestion1() {
        Map<Long, Long> count = LectureFichiersUtils.getData("fichier2.txt")
                                    .map(Mainday2::getCountsLetter)                                 
                                    .map(HashSet::new)
                                    .flatMap(Set::stream)
                                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(count.get(2L));
        System.out.println(count.get(3L));
        return count.get(2L)*count.get(3L);
    }
    
    private static String getReponseQuestion2() {
      List<String> data = LectureFichiersUtils.getData("fichier2.txt").collect(Collectors.toList());
      for(String a :  data) {
        for(String b :  data)  {
          if(nbDiff(a,b)==1 ) {
            System.out.println(a);
            System.out.println(b);
            return getCommun(a,b);
          }
        }
      }
      return null;
      
    }
    
    private static String getCommun(String a, String b) {
      String commun = "";
      for(int i=0; i<a.length(); i++) {
        if(a.charAt(i) == b.charAt(i)) {
          commun+=a.charAt(i);
        }
      }
      return commun;
    }
    
    private static Collection<Long> getCountsLetter(String s){
        return s.chars().boxed().
            collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values()
            ;
    }
    
    private static int nbDiff(String a, String b) {
        if(a.length() == b.length()) {
            int diff = 0;
            for(int i=0;  i< a.length(); i++) {
                if(a.charAt(i) != b.charAt(i)) {
                    diff++;
                }
            }
            return diff;
        }
        return 0;
    }
    
}
