package com.mob41.taskn.ui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mob41.taskn.Conf;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WizardFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public WizardFrame() {
		setTitle("Wizard");
		setBounds(100, 100, 664, 461);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		getContentPane().add(panel1, "name_93944400249407");
		
		JLabel lblTitle = new JLabel("TaskNotifier Setup Wizard");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 38));
		
		JLabel lblDbsettingsLocation = new JLabel("DB/Settings location:");
		
		textField = new JTextField(Conf.propertiesLocation);
		textField.setColumns(10);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Settings writein
				dispose();
			}
		});
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel1.createSequentialGroup()
					.addContainerGap(549, Short.MAX_VALUE)
					.addComponent(btnFinish)
					.addContainerGap())
				.addGroup(gl_panel1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
						.addGroup(gl_panel1.createSequentialGroup()
							.addComponent(lblDbsettingsLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel1.setVerticalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addGap(18)
					.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDbsettingsLocation)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
					.addComponent(btnFinish)
					.addContainerGap())
		);
		panel1.setLayout(gl_panel1);

	}
}
