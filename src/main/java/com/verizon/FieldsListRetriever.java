package com.verizon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FieldsListRetriever {
	
	public static List<String> getFieldsList(String filePath){
		List<String> ls= new ArrayList<String>();
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filePath));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		String fileout="";
		String[] div=content.split(">");
		for(int i=0;i<div.length;i++)
		{ 	//System.out.println(div[i]);
		  div[i].replaceAll("\\s+","");
			if(div[i].contains("input"))
			{
				if(div[i].contains("id="))
				{
					System.out.println(div[i].substring(div[i].toLowerCase().indexOf("id=\"")+4,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("id=\"")+4)) ));
				ls.add(div[i].substring(div[i].toLowerCase().indexOf("id=\"")+4,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("id=\"")+4)) ));
				}
			else if(div[i].contains("name="))
				{
					String name=div[i].substring(div[i].toLowerCase().indexOf("name=\"")+6,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("name=\"")+6)) );
				div[i]=div[i]+" "+"id="+"\"id_"+name+"\" ";
				ls.add("id_"+name);
				System.out.println("id_"+name);
				}
			}
		if(i>0)
		{
			fileout=fileout+"> "+"\n"+div[i];
		}
		else
		{
		fileout=""+div[i];
		}
		
	}
		System.out.println("file out is"+fileout);
		try {
			FileWriter fout=new FileWriter(new File(filePath));
			fout.append(fileout);
			fout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ls);
		return ls;
		}
	
		
	}


