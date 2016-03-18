package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.mob41.taskn.tasks.Tasks;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class TaskList extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tasks;
	private DefaultTableModel tasksmodel;
	private JRadioButtonMenuItem rdbtnmntmName;
	private JRadioButtonMenuItem rdbtnmntmImportTime;
	private JRadioButtonMenuItem rdbtnmntmDeadline;
	private static boolean updatenow = false;
	
	private ActionListener update = new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			updateTaskList();
		}
		
	};
	private ActionListener clock = new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			if(updatenow){
				updatenow = false;
				updateTaskList();
			}
		}
		
	};
	private JPopupMenu popupMenu;
	private JMenuItem mntmRemove;
	
	public void updateTaskList(){
		List<String[]> tasklist = Tasks.getTableData();
		tasksmodel.setRowCount(0);
		for (int i = 0; i < tasklist.size(); i++){
			tasksmodel.addRow(tasklist.get(i));
		}
		tasksmodel.fireTableDataChanged();
	}

	public TaskList() {
		setTitle("Tasks List");
		setBounds(100, 100, 429, 347);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JScrollPane scrolltasks = new JScrollPane();
		getContentPane().add(scrolltasks, BorderLayout.CENTER);
		
		tasksmodel = new DefaultTableModel();
		tasks = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tasks.setModel(tasksmodel);
		tasksmodel.setColumnIdentifiers(Tasks.taskslist_colident);
		
		popupMenu = new JPopupMenu();
		addPopup(scrolltasks, popupMenu);
		
		mntmRemove = new JMenuItem("Remove");
		mntmRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tasks.getSelectedRow() == -1){
					JOptionPane.showInternalMessageDialog(TaskList.this, "No Task selected to be removed.", "Removing task", JOptionPane.ERROR_MESSAGE);
				} else {
					Tasks.remove(tasks.getSelectedRow());
					updateTaskList();
				}
			}
		});
		popupMenu.add(mntmRemove);
		
		scrolltasks.setViewportView(tasks);
		
		JMenu mnSortBy = new JMenu("Sort by");
		menuBar.add(mnSortBy);
		
		rdbtnmntmName = new JRadioButtonMenuItem("Name");;
		rdbtnmntmImportTime = new JRadioButtonMenuItem("Import time");;
		rdbtnmntmDeadline = new JRadioButtonMenuItem("Deadline");;
		
		rdbtnmntmName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnmntmDeadline.setSelected(false);
				rdbtnmntmImportTime.setSelected(false);
			}
		});
		mnSortBy.add(rdbtnmntmName);
		
		rdbtnmntmImportTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnmntmName.setSelected(false);
				rdbtnmntmDeadline.setSelected(false);
			}
		});
		mnSortBy.add(rdbtnmntmImportTime);
		
		rdbtnmntmDeadline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnmntmImportTime.setSelected(false);
				rdbtnmntmName.setSelected(false);
			}
		});
		rdbtnmntmDeadline.setSelected(true);
		mnSortBy.add(rdbtnmntmDeadline);

		Timer timer1 = new Timer(60000, update);
		timer1.start();
		updateTaskList();
		
		Timer timer2 = new Timer(1000, clock);
		timer2.start();
	}
	
	public static void updateNow(){
		updatenow = true;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
