package com.verizon;

import java.nio.charset.Charset;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.*;
import java.io.*;

import java.util.Scanner;

public class ReadWriteTextFileJDK7 {
	static String FILE_NAME;
  public static String mainReadWrite(String filename, String savePath) throws IOException{
    ReadWriteTextFileJDK7 text = new ReadWriteTextFileJDK7();
    String filePath=filename;
    int ind=filePath.lastIndexOf("\\");
    if (ind==-1)
    	ind=filePath.lastIndexOf("/");
    
    
    FILE_NAME = filePath.substring(ind+1);
     System.out.println("file path is "+filePath);
    OUTPUT_FILE_NAME=new File(savePath, FILE_NAME).getPath();
    System.out.println("out_file_in"+OUTPUT_FILE_NAME);
     List<String> lines = text.readSmallTextFile(filePath);
    log(lines);
     //text.writeSmallTextFile(lines, FILE_NAME);
     Path path = Paths.get(OUTPUT_FILE_NAME);
     System.out.println(path);
     Files.write(path, lines, ENCODING);
    //text.readLargerTextFile(FILE_NAME);
   // text.writeLargerTextFile(FILE_NAME, lines); 
     return OUTPUT_FILE_NAME;
  }

 
static String OUTPUT_FILE_NAME ;
  final static Charset ENCODING = StandardCharsets.UTF_8;
  
  //For smaller files

  /**
   Note: the javadoc of Files.readAllLines says it's intended for small
   files. But its implementation uses buffering, so it's likely good 
   even for fairly large files.
  */  
  List<String> readSmallTextFile(String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    return Files.readAllLines(path, ENCODING);
  }
  
  void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    Files.write(path, aLines, ENCODING);
  }

  //For larger files
  
  /*void readLargerTextFile(String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
      while (scanner.hasNextLine()){
        //process each line in some way
        log(scanner.nextLine());
      }      
    }
  }
  
  void readLargerTextFileAlternate(String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
      String line = null;
      while ((line = reader.readLine()) != null) {
        //process each line in some way
        log(line);
      }      
    }
  }
  
  void writeLargerTextFile(String aFileName, List<String> aLines) throws IOException {
    Path path = Paths.get(aFileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
      for(String line : aLines){
        writer.write(line);
        writer.newLine();
      }
    }
  }*/

  private static void log(Object aMsg){
    System.out.println(String.valueOf(aMsg));
  }
}