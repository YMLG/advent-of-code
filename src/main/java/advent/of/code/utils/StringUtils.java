package advent.of.code.utils;

import java.util.stream.Stream;

public class StringUtils {

  public static Stream<Character> toCharStream(String s) {
    return s.chars().mapToObj(c -> (char) c);
  }
}
