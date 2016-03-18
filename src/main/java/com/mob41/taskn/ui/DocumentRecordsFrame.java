package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.mob41.taskn.Conf;
import com.mob41.taskn.docrec.DocumentRecord;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class DocumentRecordsFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] colident = {"Name", "Deadline", "Import Time", "Category", "Related Task", "Prority"};
	
	private JTable table;
	private JDesktopPane desktop;
	private DefaultTableModel tablemodel;
	
	public void updateTable(){
		List<String[]> data = DocumentRecord.getTableData();
		tablemodel.setRowCount(0);
		for (int i = 0; i < data.size(); i++){
			tablemodel.addRow(data.get(i));
		}
		tablemodel.fireTableDataChanged();
	}

	/**
	 * Create the frame.
	 */
	public DocumentRecordsFrame() {
		setClosable(true);
		setTitle("Document Records Manager");
		setBounds(100, 100, 652, 529);
		
		desktop = new JDesktopPane();
		desktop.setBackground(UIManager.getColor("Label.background"));
		getContentPane().add(desktop, BorderLayout.CENTER);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		
		JComboBox sortbybox = new JComboBox();
		
		JLabel lblCategory = new JLabel("Category:");
		
		JComboBox selectcate = new JComboBox();
		selectcate.setEnabled(false);
		
		JButton btnNewRecord = new JButton("New record");
		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDocumentFrame adddoc = new AddDocumentFrame();
				desktop.add(adddoc);
				adddoc.setVisible(true);
				//TODO This function (here) doesn't causing the table to update. Cos the AddDocumentFrame function is another thread.
				//updateTable();
			}
		});
		
		JButton btnRemoveRecord = new JButton("Remove record");
		btnRemoveRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1){
					JOptionPane.showInternalMessageDialog(desktop, "No record selected.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				DocumentRecord.remove(table.getSelectedRow());
				updateTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_desktop = new GroupLayout(desktop);
		gl_desktop.setHorizontalGroup(
			gl_desktop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktop.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSortBy, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(sortbybox, 0, 114, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(lblCategory, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(selectcate, 0, 121, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewRecord, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoveRecord, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
		);
		gl_desktop.setVerticalGroup(
			gl_desktop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktop.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_desktop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktop.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSortBy))
						.addComponent(sortbybox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_desktop.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCategory))
						.addGroup(gl_desktop.createParallelGroup(Alignment.BASELINE)
							.addComponent(selectcate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewRecord)
							.addComponent(btnRemoveRecord)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
		);
		
		tablemodel = new DefaultTableModel();
		table = new JTable(tablemodel);
		tablemodel.setColumnIdentifiers(colident);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		popupMenu.add(mntmRefresh);
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
