package advent.of.code.a2018.day7;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RelationInterator implements Iterator<String>{

  private Set<String> letters; 
  private List<Relation> relations;
  
  
  
  
  public RelationInterator(Set<String> letters, List<Relation> relations) {
    this.letters = letters;
    this.relations = relations;
  }

  @Override
  public boolean hasNext() {
    return letters.stream().filter(l -> isEarly(l)).sorted().findFirst().isPresent();
  }

  @Override
  public String next() {
    String letter = letters.stream().filter(l -> isEarly(l)).sorted().findFirst().get();
    letters.remove(letter);
    return letter;
  }
  
  
  public boolean finish(String letter) {
    return relations.removeIf(p -> p.getFirst().equals(letter));
  }
  
  private boolean isEarly(String letter) {
    for(Relation r : relations) {
      if(r.getLast().equals(letter)) {
        return false;
      }
    }
    return true;
  }

}
