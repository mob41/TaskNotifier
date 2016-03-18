package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.mob41.taskn.docrec.DocumentRecord;
import com.mob41.taskn.tasks.Tasks;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

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
		setClosable(true);
		setTitle("Search records");
		setBounds(100, 100, 534, 528);
		
		JLabel lblSearchBy = new JLabel("Search by:");
		
		searchbybox = new JComboBox(searchbychoices);
		
		JLabel lblTarget = new JLabel("Target:");
		
		targetbox = new JComboBox(targetchoices);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		
		sortbybox = new JComboBox(sortbychoices);
		sortbybox.setEnabled(false);
		
		JLabel lblQuery = new JLabel("Query:");
		
		queryFld = new JTextField();
		queryFld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
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
		tablemodel.setColumnIdentifiers(colident);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	private void query(){
		if(queryFld.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Query should not be blank.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<String[]> result = new ArrayList<String[]>(100);
		List<String[]> data;
		String[] dataarr;
		String[] build;
		switch (targetbox.getSelectedIndex()){
		case 0:
			break;
		case 1:
			data = Tasks.getTableData();
			switch (searchbybox.getSelectedIndex()){
			case 0:
				for (int i = 0; i < data.size(); i++){
					dataarr = data.get(i);
					if (dataarr[0].contains(queryFld.getText())){
						build = new String[6];
						build[0] = dataarr[0];
						build[1] = dataarr[1];
						build[2] = dataarr[2];
						//TODO Categories on tasks.
						build[3] = "Not implemented";
						build[4] = "---";
						build[5] = dataarr[3];
						result.add(build);
					}
				}
				break;
			default:
				//TODO Other searching methods.
				System.out.println("Not implemented.");
			}
			break;
		case 2:
			data = DocumentRecord.getTableData();
			switch (searchbybox.getSelectedIndex()){
			case 0:
				for (int i = 0; i < data.size(); i++){
					dataarr = data.get(i);
					if (dataarr[0].contains(queryFld.getText())){
						result.add(dataarr);
					}
				}
				break;
			default:
				//TODO Other searching methods.
				System.out.println("Not implemented.");
			}
			break;		
		}
		tablemodel.setRowCount(0);
		for (int i = 0; i < result.size(); i++){
			tablemodel.addRow(result.get(i));
		}
		tablemodel.fireTableDataChanged();
	}

}
