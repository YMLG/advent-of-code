package advent.of.code.day12;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import advent.of.code.utils.LectureFichiersUtils;
import advent.of.code.utils.StringUtils;

public class MainDay12 {
 
  public static void main(String[] args) {
    String input = LectureFichiersUtils.getData("fichier12.txt").findFirst().orElse("").replaceAll("initial state: ", "");
    Map<String, Boolean> regles = LectureFichiersUtils.getData("fichier12.txt")
                        .filter(l -> !l.isEmpty() && !l.startsWith("initial state:"))
                        .map(l -> l.split(" => "))
                        .collect(Collectors.toConcurrentMap(e -> e[0], e -> "#".equals(e[1])));        
    System.out.println("Question 1 - "+ getReponseQuestion1(input, regles));
    System.out.println("Question 2 - "+ getReponseQuestion2(input, regles));
  }

  private static long getReponseQuestion1(String input,  Map<String, Boolean> regles) {
    LinkedList<Pot> pots =new LinkedList<>(StringUtils.toCharStream(input).map(Pot::new).collect(Collectors.toList()));
    int nbTours = 20;
    for(long i=0; i< nbTours ;i++) {
      run(pots, regles);
    }
    return Stream.iterate(0, i -> i += 1)   
                  .limit(pots.size())
                  .filter(i -> pots.get(i).isHavePlant())
                  .map(i -> i - nbTours)
                  .mapToInt(Integer::valueOf)
                  .sum();
  }

  private static long getReponseQuestion2(String input,  Map<String, Boolean> regles) {
    LinkedList<Pot> pots =new LinkedList<>(StringUtils.toCharStream(input).map(Pot::new).collect(Collectors.toList()));
    long nbTours = 50000000000L;
    long sum1 = 0;
    long diff = 0;
    int nbEquals = 0;
    AtomicInteger index = new AtomicInteger();
    while(index.getAndIncrement() < nbTours && nbEquals < 100) {
      run(pots, regles);
      long sum = Stream.iterate(0, j -> j += 1)
          .limit(pots.size())
          .filter(j -> pots.get(j).isHavePlant())
          .map(j-> j - index.get())
          .mapToLong(Long::valueOf)
          .sum();
      if(diff == sum -sum1) {
        nbEquals++;
      }else {
        diff = sum - sum1;
        nbEquals = 0;
      }
      sum1 = sum;
    }
    return (50000000000L-index.get()+1) * diff + sum1;
  }

  
  private static void run(LinkedList<Pot> pots,   Map<String, Boolean> regles) {
    pots.add(new Pot());pots.add(new Pot());pots.add(new Pot());
    pots.addFirst(new Pot());pots.addFirst(new Pot());pots.addFirst(new Pot());
    Pot l2 = pots.get(0);
    Pot l1 = pots.get(1);
    Pot c  = pots.get(2);
    Pot r1 = pots.get(3);
    Pot r2 = pots.get(4);
    for(int i = 2; i<pots.size()-2;i++) {
      r2 = pots.get(i+2);
      String pattern = ""+l2+l1+c+r1+r2;
      if(regles.containsKey(pattern)) {
        c.setWillHavePlant(regles.get(pattern));
      }
      l2.run();
      l2 = l1;
      l1= c;
      c= r1;
      r1=r2;
    }
    l2.run();
    l1.run();
    c.run();
    r1.run();
    pots.removeFirst();
    pots.removeFirst();
    pots.removeLast();
    pots.removeLast();
  }
}
