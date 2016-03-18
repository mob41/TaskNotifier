package com.mob41.taskn.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mob41.taskn.Conf;
import com.mob41.taskn.cal.CalendarChooser;
import com.mob41.taskn.docrec.DocumentRecord;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class AddDocumentFrame extends JInternalFrame {
	private JTextField nameFld;
	private CalendarChooser calchoose;
	private JLabel lblDate;
	private JComboBox category;
	private JComboBox relatedtask;
	private static final String[] prority = {"High", "Medium", "Low"};
	private JComboBox proritybox;

	/**
	 * Create the frame.
	 */
	public AddDocumentFrame() {
		setClosable(true);
		setTitle("Add new document record");
		setBounds(100, 100, 318, 206);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel details = new JPanel();
		getContentPane().add(details, "name_110358058619806");
		
		JLabel lblName = new JLabel("Name:");
		
		nameFld = new JTextField();
		nameFld.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		
		category = new JComboBox(Conf.getAllCategories());
		
		JLabel lblDeadline = new JLabel("Deadline:");
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details.setVisible(false);
				calchoose.setVisible(true);
			}
		});
		
		JLabel lblRelatedTask = new JLabel("Related Task:");
		
		relatedtask = new JComboBox();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameFld.getText().isEmpty()){
					JOptionPane.showInternalMessageDialog(AddDocumentFrame.this, "The record's name can not be blank.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String cty = (String) category.getSelectedItem();
				String reltask = (String)relatedtask.getSelectedItem();
				if (cty == null || cty.isEmpty() || category.getSelectedIndex() == -1){
					JOptionPane.showInternalMessageDialog(AddDocumentFrame.this, "No category selected.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (reltask == null || reltask.isEmpty() || relatedtask.getSelectedIndex() == -1){
					reltask = "None";
				}
				Calendar cal = Calendar.getInstance();
				DocumentRecord.add(nameFld.getText(), calchoose.getString(), Long.toString(cal.getTimeInMillis()), (String) category.getSelectedItem(), reltask, (String) proritybox.getSelectedItem());
				dispose();
			}
		});
		
		lblDate = new JLabel("2016/1/1");
		
		JLabel lblPrority = new JLabel("Prority:");
		
		proritybox = new JComboBox(prority);
		GroupLayout gl_details = new GroupLayout(details);
		gl_details.setHorizontalGroup(
			gl_details.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_details.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_details.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_details.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameFld, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_details.createSequentialGroup()
							.addComponent(lblDeadline)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChoose)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDate))
						.addGroup(gl_details.createSequentialGroup()
							.addComponent(lblRelatedTask)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(relatedtask, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_details.createSequentialGroup()
							.addComponent(lblCategory)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(category, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
				.addGroup(gl_details.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(btnFinish)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_details.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrority)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(proritybox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_details.setVerticalGroup(
			gl_details.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_details.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory)
						.addComponent(category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeadline)
						.addComponent(btnChoose)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelatedTask)
						.addComponent(relatedtask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrority)
						.addComponent(proritybox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnFinish))
					.addContainerGap())
		);
		details.setLayout(gl_details);

		calchoose = new CalendarChooser(details, lblDate);
		getContentPane().add(calchoose);
		calchoose.setVisible(false);
	}

}
