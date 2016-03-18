package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.mob41.taskn.Conf;
import com.mob41.taskn.tasks.Tasks;

import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class UI {

	private JFrame frame;
	private TaskList tasklist;
	private JCheckBoxMenuItem chckbxmntmTaskList;
	private JDesktopPane desktop;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TaskNotifier");
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktop, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);
		
		JMenuItem mntmNewCategory = new JMenuItem("New category");
		mntmNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInternalInputDialog(desktop, "New category's name:", "Adding new category", JOptionPane.INFORMATION_MESSAGE);
				if (input == null){
					return;
				} else if (input.isEmpty()){
					JOptionPane.showInternalMessageDialog(desktop, "The name should not be blank.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Conf.addCategory(input);
			}
		});
		mnCategory.add(mntmNewCategory);
		
		JMenuItem mntmManageCategories = new JMenuItem("Manage categories");
		mntmManageCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryFrame cateframe = new CategoryFrame();
				desktop.add(cateframe);
				cateframe.setVisible(true);
			}
		});
		mnCategory.add(mntmManageCategories);
		
		JMenu mnTasks = new JMenu("Tasks");
		menuBar.add(mnTasks);
		
		JMenuItem mntmNewTask = new JMenuItem("New task");
		mntmNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskFrame addtask = new AddTaskFrame();
				desktop.add(addtask);
				addtask.setVisible(true);
			}
		});
		mnTasks.add(mntmNewTask);
		
		JMenu mnDocumentsRecord = new JMenu("Documents Record");
		menuBar.add(mnDocumentsRecord);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDocumentFrame adddoc = new AddDocumentFrame();
				desktop.add(adddoc);
				adddoc.setVisible(true);
			}
		});
		mnDocumentsRecord.add(mntmAdd);
		
		JMenuItem mntmManage = new JMenuItem("Manage");
		mntmManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DocumentRecordsFrame docrecframe = new DocumentRecordsFrame();
				desktop.add(docrecframe);
				docrecframe.setVisible(true);
			}
		});
		mnDocumentsRecord.add(mntmManage);
		
		JSeparator separator = new JSeparator();
		mnDocumentsRecord.add(separator);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mnDocumentsRecord.add(mntmSearch);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		chckbxmntmTaskList = new JCheckBoxMenuItem("Tasks List");
		chckbxmntmTaskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxmntmTaskList.isSelected()){
					tasklist = new TaskList();
					desktop.add(tasklist);
					tasklist.setLocation(10, 11);
					tasklist.setVisible(true);
				}
				else {
					tasklist.dispose();
					desktop.remove(tasklist);
					desktop.revalidate();
					desktop.repaint();
				}
			}
		});
		mnWindow.add(chckbxmntmTaskList);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutTasknotifier = new JMenuItem("About TaskNotifier");
		mntmAboutTasknotifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				desktop.add(about);
				about.setVisible(true);
			}
		});
		mnAbout.add(mntmAboutTasknotifier);
		
		if (Tasks.isNew()){
			WizardFrame wizard = new WizardFrame();
			wizard.setLocation(294, 209);
			wizard.setVisible(true);
			desktop.add(wizard);
		} else {
			openTaskList();
		}
	}
	
	public void openTaskList(){
		tasklist = new TaskList();
		desktop.add(tasklist);
		tasklist.setLocation(10, 11);
		tasklist.setVisible(true);
		chckbxmntmTaskList.setSelected(true);
	}
}
