package br.com.vivo.telefonica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SuperHEroReadFileMainTest {

	public static void main(String[] args) throws IOException {

		  List<String[]> collect =
		          Files.lines(Paths.get("/home/jorge.garcia/src/workspace-springtools/TestFileUpload/filesfile.csv"))
		                .map(line -> line.split(";"))
		                .collect(Collectors.toList());
		  
		  /*String line;
		  try (BufferedReader br = new BufferedReader(
		          new FileReader("/home/jorge.garcia/src/workspace-springtools/TestFileUpload/filesfile.csv"))) {

		      while ((line = br.readLine()) != null) {

		          // split by a comma separator
		          String[] split = line.split(",");
		          System.out.println("\nLength : " + split.length);
		          System.out.println("split[0] : " + split[0]);
		          System.out.println("split[1] : " + split[1]);
		          System.out.println("split[2] : " + split[2]);
		          System.out.println("split[3] : " + split[3]);
		      }

		  } catch (IOException e) {
		      e.printStackTrace();
		  }

	}*/
	}

}
