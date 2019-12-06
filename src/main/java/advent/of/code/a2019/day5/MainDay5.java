package advent.of.code.a2019.day5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay5 {
  

  private static Scanner in = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    getReponseQuestion1();
    getReponseQuestion2();
    in.close();
  }


  private static void getReponseQuestion1() throws IOException {
    System.out.println("Taper 1");
    test();
  }


  private static void getReponseQuestion2() throws IOException {
    System.out.println("Taper 5");
    test();
  }

 
  private static void test() throws IOException {
    Integer[] tableau = getIntData();
    int opcode = 0, pointer = 0;
    while(opcode != 99) {
      int[] modes = String.format("%05d", tableau[pointer]).chars().map(Character::getNumericValue).toArray();
      opcode = Integer.valueOf(modes[3]+""+modes[4]);
      Integer param1 = getParam(1, pointer, modes, tableau);
      Integer param2 = getParam(2, pointer, modes, tableau);
      Integer param3 = getParam(3, pointer, modes, tableau);      
      pointer = action(tableau, opcode, pointer, param1, param2, param3);
    };
  }


  private static int action(Integer[] tableau, int opcode, int pointer, Integer param1, Integer param2, Integer param3) {
    switch (opcode) {
    case 1:
      tableau[param3] = param1 + param2;
      break;
    case 2:
      tableau[param3] = param1 * param2;
      break;
    case 3:
      System.out.println("input :");
      tableau[tableau[pointer+1]] =  in.nextInt();
      return pointer + 2;
    case 4:
      System.out.println(param1);
      return pointer + 2;
    case 5:
      if(param1 != 0) {
        return param2;
      }
      return pointer + 3;
    case 6:
      if(param1 == 0) {
        return param2;
      }
      return pointer + 3;
    case 7:
      tableau[param3] = param2 > param1 ? 1 : 0;
      break;
    case 8:
      tableau[param3] = param2.equals(param1) ? 1 : 0;
      break;
    default:
      break;
    }
    return pointer + 4;
  }
  
  private static Integer getParam(int numero, int pointer, int[] modes, Integer[] tableau) {
    try {
      if(numero == 3) {
        return modes[0]==0 ? tableau[pointer+3] : pointer+3;
      }
      return  modes[3-numero]==0 ? tableau[tableau[pointer+numero]] : tableau[pointer+numero];
    }catch(IndexOutOfBoundsException e) {
      return null;
    }
  }

  private static Integer[] getIntData() {
    return LectureFichiersUtils.getData("2019/input5.txt")
        .map(l -> Arrays.asList(l.split(",")))
        .flatMap(List::stream)
        .map(Integer::valueOf)
        .toArray(Integer[]::new)
        ;
  }
}
