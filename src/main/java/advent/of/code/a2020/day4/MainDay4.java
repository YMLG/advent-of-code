package advent.of.code.a2020.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay4 {
	
  public static void main(String[] args) {
    System.out.println("Question 1 - "+ getReponseQuestion1());
    System.out.println("Question 2 - "+ getReponseQuestion2());

  }

  private static long getReponseQuestion1() {
	return getData().stream().filter(MainDay4::allFieldsPresents).count();
  }


  private static boolean allFieldsPresents(String passport) {
	  return  Arrays.asList(Fields.values()).stream().allMatch(k -> passport.contains(k+":"));
  }
  
  private static long getReponseQuestion2() {
		return getData().stream().filter(MainDay4::allFieldsPresents).filter(MainDay4::allFieldsValids).count();
  }
  
  private static boolean allFieldsValids(String passport) {
	  String[] fields = passport.trim().split(" ");
	  return allFieldsPresents(passport) && Arrays.asList(fields).stream().allMatch(f ->{
		  String[] kv = f.split(":");
		  if(kv[0].equals("cid")) {
			  return true;
		  }
		  return Fields.valueOf(kv[0]).rule.apply(kv[1]);
	  });
  }
  

  private static List<String> getData() {
	List<String> lignes = LectureFichiersUtils.getData("2020/input4.txt").collect(Collectors.toList());
	List<String> passports = new ArrayList<>();
	StringBuilder passport = new StringBuilder();
	for(String ligne : lignes) {
		if(ligne.length()==0) {
			passports.add(passport.toString());
			passport = new StringBuilder();
		}else {
			passport.append(" ").append(ligne);
		}
	}
	passports.add(passport.toString());
	return passports;
  }
}
