package com.mob41.taskn.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.mob41.taskn.Conf;

public class Tasks {
	public static final String[] taskslist_colident = {"Name", "Deadline", "Import Time", "Prority"};
	
	public static boolean isNew = false;
	
	private static List<String[]> tasks = new ArrayList<String[]>(100);
	
	public static boolean isNew(){
		return isNew;
	}
	
	public static List<String[]> getTableData(){
		return tasks;
	}
	
	public static void add(String[] data){
		tasks.add(data);
		writeIn();
	}
	
	public static void add(String name, String deadline, String importtime, String prority){
		String[] build = new String[]{name, deadline, importtime, prority};
		add(build);
	}
	
	public static void remove(int index){
		System.out.println(Arrays.deepToString(tasks.toArray()));
		tasks.remove(index);
		writeIn();
	}
	
	public static boolean remove(String name, String deadline, String importtime){
		int index = getIndex(name, deadline, importtime);
		if (index == -1){
			return false;
		}
		remove(index);
		return true;
	}
	
	public static int getIndex(String name, String deadline, String importtime){
		String[] data;
		for (int i = 0; i < tasks.size(); i++){
			data = tasks.get(i);
			if (data[0].equals(name) && data[1].equals(deadline) && data[2].equals(importtime)){
				return i;
			}
		}
		return -1;
	}
	
	public static void load(){
		try {
			File file = new File(Conf.propertiesLocation + "\\tasks.properties");
			if (!file.exists()){
				create();
				return;
			}
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
			int alltasks = Integer.parseInt(prop.getProperty("tasks"));
			tasks.clear();
			String[] build;
			for (int i = 0; i < alltasks; i++){
				build = new String[4];
				build[0] = prop.getProperty("task-" + i + "-name");
				build[1] = prop.getProperty("task-" + i + "-deadline");
				build[2] = prop.getProperty("task-" + i + "-importtime");
				build[3] = prop.getProperty("task-" + i + "-prority");
				tasks.add(build);
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
			File file = new File(filedir.getAbsolutePath() + "\\tasks.properties");
			if (!file.exists()){
				file.createNewFile();
			}
			Properties prop = new Properties();
			String[] data;
			prop.setProperty("tasks", Integer.toString(tasks.size()));
			for (int i = 0; i < tasks.size(); i++){
				data = tasks.get(i);
				prop.setProperty("task-" + i + "-name", data[0]);
				prop.setProperty("task-" + i + "-deadline", data[1]);
				prop.setProperty("task-" + i + "-importtime", data[2]);
				prop.setProperty("task-" + i + "-prority", data[3]);
			}
			FileOutputStream out = new FileOutputStream(file);
			prop.store(out, "TaskNotifier tasks");
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
			File file = new File(filedir.getAbsolutePath() + "\\tasks.properties");
			if (!file.exists()){
				file.createNewFile();
				isNew = true;
			}
			Properties prop = new Properties();
			prop.setProperty("tasks", "0");
			FileOutputStream out = new FileOutputStream(file);
			prop.store(out, "TaskNotifier tasks");
			out.flush();
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
