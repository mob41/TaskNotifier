package com.mob41.taskn.docrec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.mob41.taskn.Conf;

public class DocumentRecord {
	
	private static List<String[]> documents = new ArrayList<String[]>(100);
	
	private static boolean isNew = false;
	
	public static void add(String[] data){
		documents.add(data);
		writeIn();
	}
	
	public static void add(String name, String deadline, String importtime, String category, String relatedtask, String prority){
		String[] data = new String[]{name, deadline, importtime, category, relatedtask, prority};
		add(data);
	}
	
	public static void remove(int index){
		documents.remove(index);
		writeIn();
	}
	
	public static List<String[]> getTableData(){
		return documents;
	}
	
	public static List<String[]> getRecordsByCategory(String category){
		List<String[]> output = new ArrayList<String[]>(100);
		String[] data;
		for (int i = 0; i < documents.size(); i++){
			data = documents.get(i);
			if (data[3].equals(category)){
				output.add(data);
			}
		}
		return output;
	}
	
	public static void load(){
		try {
			File file = new File(Conf.propertiesLocation + "\\documents.properties");
			if (!file.exists()){
				create();
				return;
			}
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
			int alldoc = Integer.parseInt(prop.getProperty("documents"));
			documents.clear();
			String[] build;
			for (int i = 0; i < alldoc; i++){
				build = new String[6];
				build[0] = prop.getProperty("doc-" + i + "-name");
				build[1] = prop.getProperty("doc-" + i + "-deadline");
				build[2] = prop.getProperty("doc-" + i + "-importtime");
				build[3] = prop.getProperty("doc-" + i + "-category");
				build[4] = prop.getProperty("doc-" + i + "-relatedtask");
				build[5] = prop.getProperty("doc-" + i + "-prority");
				documents.add(build);
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
	}
	
	public static void writeIn(){
		try {
			File filedir = new File(Conf.propertiesLocation);
			if (!filedir.exists()){
				filedir.mkdir();
			}
			File file = new File(filedir.getAbsolutePath() + "\\documents.properties");
			if (!file.exists()){
				file.createNewFile();
			}
			Properties prop = new Properties();
			String[] data;
			prop.setProperty("documents", Integer.toString(documents.size()));
			for (int i = 0; i < documents.size(); i++){
				data = documents.get(i);
				System.out.println(Arrays.deepToString(data));
				prop.setProperty("doc-" + i + "-name", data[0]);
				prop.setProperty("doc-" + i + "-deadline", data[1]);
				prop.setProperty("doc-" + i + "-importtime", data[2]);
				prop.setProperty("doc-" + i + "-category", data[3]);
				prop.setProperty("doc-" + i + "-relatedtask", data[4]);
				prop.setProperty("doc-" + i + "-prority", data[5]);
			}
			FileOutputStream out = new FileOutputStream(file);
			prop.store(out, "TaskNotifier documents");
			out.flush();
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Create a new Task Properties File
	 */
	public static void create(){
		try {
			File filedir = new File(Conf.propertiesLocation);
			if (!filedir.exists()){
				filedir.mkdir();
			}
			File file = new File(filedir.getAbsolutePath() + "\\documents.properties");
			if (!file.exists()){
				file.createNewFile();
				isNew = true;
			}
			Properties prop = new Properties();
			prop.setProperty("documents", "0");
			FileOutputStream out = new FileOutputStream(file);
			prop.store(out, "TaskNotifier documents");
			out.flush();
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
