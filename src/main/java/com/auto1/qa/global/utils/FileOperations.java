package com.auto1.qa.global.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * This class provides instance of FileOperations for reading/writing data from a text file
*/
public class FileOperations {
	
	private static final LogUtils LOGGER = LogUtils.getInstance(FileOperations.class);
	
	/*
	 * This method writes 'content' to file specified by 'filePath'
	*/
	public static void writeToFile(String filePath, String content) {
		try {

			FileWriter fr = new FileWriter(new File(filePath));

			BufferedWriter br = new BufferedWriter(fr);

			br.append(content);
			br.flush();
			br.close();
			fr.close();

		} catch (IOException e) {
			LOGGER.error("Problem in Writing");

		}
	}
	
	/*
	 * This method reads 'content' from file specified by 'filePath'
	*/
	@SuppressWarnings("resource")
	public static String readFromFile(String filePath) throws FileNotFoundException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(new File(filePath)));

		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
