package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mob41.taskn.Conf;
import com.mob41.taskn.docrec.DocumentRecord;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class CategoryFrame extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] colident = {"Name", "Type", "Deadline", "Import Time", "Category", "Related Task", "Prority"};
	
	private JTable table;
	private JLabel lblTotalLinkedObjects;
	private JLabel lblLinkedTasks;
	private JLabel lblLinkedDocuments;
	private JComboBox selectcate;
	private JDesktopPane desktop;

	private DefaultTableModel tablemodel;
	
	private void updateTable(){
		if (selectcate.getSelectedItem() == null){
			return;
		}
		List<String[]> data = DocumentRecord.getRecordsByCategory((String) selectcate.getSelectedItem());
		tablemodel.setRowCount(0);
		int totalsize = data.size();
		lblLinkedDocuments.setText("Linked Documents: " + data.size());
		String[] original;
		String[] build;
		for (int i = 0; i < data.size(); i++){
			original = data.get(i);
			build = new String[7];
			build[0] = original[0];
			build[1] = "Document";
			build[2] = original[1];
			build[3] = original[2];
			build[4] = original[3];
			build[5] = original[4];
			build[6] = original[5];
			tablemodel.addRow(build);
		}
		tablemodel.fireTableDataChanged();
		lblTotalLinkedObjects.setText("Total linked objects: " + totalsize);
	}

	/**
	 * Create the frame.
	 */
	public CategoryFrame() {
		setClosable(true);
		setTitle("Category Manager");
		setBounds(100, 100, 605, 547);
		
		desktop = new JDesktopPane();
		desktop.setBackground(UIManager.getColor("Label.background"));
		getContentPane().add(desktop, BorderLayout.CENTER);
		
		JLabel lblSelectedCategory = new JLabel("Selected Category:");
		
		selectcate = new JComboBox(Conf.getAllCategories());
		selectcate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		
		JButton btnNewCategory = new JButton("New Category");
		btnNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInternalInputDialog(desktop, "New category's name:", "Adding new category", JOptionPane.INFORMATION_MESSAGE);
				if (input == null){
					return;
				} else if (input.isEmpty()){
					JOptionPane.showInternalMessageDialog(desktop, "The name should not be blank.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Conf.addCategory(input);
				Object[] data = Conf.getAllCategories();
				selectcate.removeAllItems();
				for (int i = 0; i < data.length; i++){
					selectcate.addItem(data[i]);
				}
				updateTable();
			}
		});
		
		JButton btnRemoveCategory = new JButton("Remove Category");
		btnRemoveCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String) selectcate.getSelectedItem();
				if (selectcate.getSelectedIndex() == -1 || item == null || item.isEmpty()){
					JOptionPane.showInternalMessageDialog(desktop, "No category selected.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Conf.removeCategory(selectcate.getSelectedIndex());
				Object[] data = Conf.getAllCategories();
				selectcate.removeAllItems();
				for (int i = 0; i < data.length; i++){
					selectcate.addItem(data[i]);
				}
				updateTable();
			}
		});
		
		lblTotalLinkedObjects = new JLabel("Total linked objects: 0");
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblLinkedTasks = new JLabel("Linked Tasks: 0");
		
		lblLinkedDocuments = new JLabel("Linked Documents: 0");
		GroupLayout gl_desktop = new GroupLayout(desktop);
		gl_desktop.setHorizontalGroup(
			gl_desktop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktop.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktop.createSequentialGroup()
							.addComponent(lblSelectedCategory, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectcate, 0, 161, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_desktop.createSequentialGroup()
							.addComponent(lblTotalLinkedObjects)
							.addGap(35)
							.addComponent(lblLinkedTasks)
							.addGap(51)))
					.addGroup(gl_desktop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktop.createSequentialGroup()
							.addComponent(btnNewCategory, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveCategory, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
						.addGroup(gl_desktop.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLinkedDocuments)))
					.addContainerGap())
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
		);
		gl_desktop.setVerticalGroup(
			gl_desktop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktop.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktop.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectedCategory)
						.addComponent(selectcate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewCategory)
						.addComponent(btnRemoveCategory))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_desktop.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalLinkedObjects)
						.addComponent(lblLinkedTasks)
						.addComponent(lblLinkedDocuments))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
		);
		
		tablemodel = new DefaultTableModel();
		table = new JTable(tablemodel){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tablemodel.setColumnIdentifiers(colident);
		scrollPane.setViewportView(table);
		desktop.setLayout(gl_desktop);
		
		updateTable();
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
