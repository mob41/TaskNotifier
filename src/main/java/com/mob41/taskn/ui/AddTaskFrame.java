package com.mob41.taskn.ui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.mob41.taskn.cal.CalendarChooser;
import com.mob41.taskn.tasks.Tasks;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class AddTaskFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] prority = {"High", "Medium", "Low"};
	private JTextField nameFld;

	private JPanel details;

	private CalendarChooser calchoose;
	private JLabel lblDate;

	public AddTaskFrame() {
		setClosable(true);
		setTitle("Add new task");
		setBounds(100, 100, 300, 202);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		
		details = new JPanel();
		details.setBounds(0, 0, 284, 172);
		layeredPane.add(details);
		
		JLabel lblName = new JLabel("Name:");
		
		nameFld = new JTextField();
		nameFld.setColumns(10);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details.setVisible(false);
				calchoose.setVisible(true);
			}
		});
		
		JLabel lblPrority = new JLabel("Prority:");
		
		final JComboBox proritybox = new JComboBox(prority);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				if (nameFld.getText().isEmpty()){
					JOptionPane.showInternalMessageDialog(AddTaskFrame.this, "The task's name can not be blank.", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Tasks.add(nameFld.getText(), calchoose.getString(), Long.toString(cal.getTimeInMillis()), (String) proritybox.getSelectedItem());
				TaskList.updateNow();
				dispose();
			}
		});
		
		lblDate = new JLabel("2016/1/1");
		GroupLayout gl_details = new GroupLayout(details);
		gl_details.setHorizontalGroup(
			gl_details.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_details.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_details.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_details.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_details.createSequentialGroup()
							.addGroup(gl_details.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_details.createSequentialGroup()
									.addComponent(lblPrority)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(proritybox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_details.createSequentialGroup()
									.addComponent(lblDeadline)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnChoose)))
							.addGap(4)
							.addComponent(lblDate)))
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(gl_details.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(btnFinish)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
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
						.addComponent(lblDeadline)
						.addComponent(btnChoose)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrority)
						.addComponent(proritybox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(gl_details.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnFinish))
					.addContainerGap())
		);
		details.setLayout(gl_details);
		
		calchoose = new CalendarChooser(details, lblDate);
		calchoose.setBounds(0, 0, 284, 172);
		layeredPane.add(calchoose);
		calchoose.setVisible(false);

	}
}
