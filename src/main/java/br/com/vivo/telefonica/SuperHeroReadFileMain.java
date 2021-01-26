package br.com.vivo.telefonica;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class SuperHeroReadFileMain {

	public static void main(String[] args) throws IOException, CsvException {

        String fileName = "/home/jorge.garcia/src/workspace-springtools/TestFileUpload/filesfile.csv";
		
		//File fileName = new File("/home/jorge.garcia/src/workspace-springtools/TestFileUpload/filesfile.csv");

        List<String[]> r;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            r = reader.readAll();
        }

        int listIndex = 0;
        for (String[] arrays : r) {
            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays));

            int index = 0;
            for (String array : arrays) {
                System.out.println(index++ + " : " + array);
            }

        }
    }

}
