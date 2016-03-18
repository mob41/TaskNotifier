package com.mob41.taskn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Conf {
	
	public static String propertiesLocation = System.getProperty("user.dir") + "\\" + "properties";
	
	public static List<String> categories = new ArrayList<String>(50);
	
	public static void addCategory(String name){
		categories.add(name);
		writeIn();
	}
	
	public static void removeCategory(int index){
		categories.remove(index);
		writeIn();
	}
	
	public static Object[] getAllCategories(){
		return categories.toArray();
	}
	
	public static void load(){
		try {
			File filedir = new File(propertiesLocation);
			if(!filedir.exists()){
				filedir.mkdir();
			}
			File file = new File(filedir.getAbsolutePath() + "\\settings.properties");
			if (!file.exists()){
				writeIn();
				return;
			}
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
			
			propertiesLocation = prop.getProperty("propertiesLocation");
			
			int categories = Integer.parseInt(prop.getProperty("categories"));
			for (int i = 0; i < categories; i++){
				Conf.categories.add(prop.getProperty("category-" + i));
			}
			
			in.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void writeIn(){
		try {
			File filedir = new File(propertiesLocation);
			if(!filedir.exists()){
				filedir.mkdir();
			}
			File file = new File(filedir.getAbsolutePath() + "\\settings.properties");
			if (!file.exists()){
				file.createNewFile();
				return;
			}
			Properties prop = new Properties();
			
			prop.setProperty("propertiesLocation", propertiesLocation);
			
			prop.setProperty("categories", Integer.toString(categories.size()));
			for (int i = 0; i < categories.size(); i++){
				prop.setProperty("category-" + i, categories.get(i));
			}
			
			FileOutputStream out = new FileOutputStream(file);		
			prop.store(out, "TaskNotifier settings");
			out.flush();
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
