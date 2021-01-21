package br.com.vivo.telefonica.controller;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MatrixController {
	
	/**
	 * <h1>Matrix Json</h1>
	 * 
	 * @author Jorge Sevilla
	 * @version 1.0
	 * @since 2021
	 * 
	 */
	
public static void main(String[] args) {
		
		ArrayList<String> matrixAn = new ArrayList<String>();
		matrixAn.add("1");
		matrixAn.add("2");
		matrixAn.add("3");
		matrixAn.add("4");
		matrixAn.add("5");
		
		log("Ray ArrayList: " + matrixAn);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		Gson gson = gsonBuilder.create();
		
		String JSONObjectString = gson.toJson(matrixAn);
		log("Converter to Json: " + JSONObjectString);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		String prettyJson = prettyGson.toJson(matrixAn);
		
		log(" Pretty JSONObject: " + prettyJson);
	}
	
	private static void log(Object print) {
		System.out.println(print);
	}

}
