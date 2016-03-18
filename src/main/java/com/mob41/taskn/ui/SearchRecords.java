package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchRecords extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] colident = {"Name", "Deadline", "Import Time", "Category", "Related Task", "Prority"};
	
	private static final String[] searchbychoices = {"Keyword", "Category", "Similar"};
	
	private static final String[] targetchoices = {"All","Tasks","Document Records"};
	
	private static final String[] sortbychoices = {"Index", "Name", "Category", "Most Similar", "Most keywords"};
	
	private JTextField queryFld;
	private JTable table;
	private JComboBox searchbybox;
	private JComboBox targetbox;
	private JComboBox sortbybox;

	private DefaultTableModel tablemodel;

	/**
	 * Create the frame.
	 */
	public SearchRecords() {
		setTitle("Search records");
		setBounds(100, 100, 656, 533);
		
		JLabel lblSearchBy = new JLabel("Search by:");
		
		searchbybox = new JComboBox(searchbychoices);
		
		JLabel lblTarget = new JLabel("Target:");
		
		targetbox = new JComboBox(targetchoices);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		
		sortbybox = new JComboBox(sortbychoices);
		sortbybox.setEnabled(false);
		
		JLabel lblQuery = new JLabel("Query:");
		
		queryFld = new JTextField();
		queryFld.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSearchBy, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchbybox, 0, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(targetbox, 0, 147, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSortBy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sortbybox, 0, 171, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuery)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(queryFld, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)))
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchBy)
						.addComponent(searchbybox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget)
						.addComponent(targetbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSortBy)
						.addComponent(sortbybox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuery)
						.addComponent(queryFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
		);
		
		tablemodel = new DefaultTableModel();
		table = new JTable(tablemodel);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}

}
