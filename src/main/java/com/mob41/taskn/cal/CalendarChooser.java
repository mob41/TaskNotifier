package com.mob41.taskn.cal;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class CalendarChooser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	private static final String[] years = {"2016","2017","2018","2019","2020","2021","2022","2023","2024"};
	
	private static final String[] dates_28 = {
			"01","02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28"};
	
	private static final String[] dates_29 = {
			"01","02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29"};
	
	private static final String[] dates_30 = {
			"01","02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30"};

	private static final String[] dates_31 = {
			"01","02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30","31"};

	private JComboBox year;

	private JComboBox month;

	private JComboBox date;
	
	public CalendarChooser(final JPanel panel){
		this(panel, null);
	}
	
	public CalendarChooser(final JPanel panel, final JLabel status) {
		
		year = new JComboBox(years);
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month.setSelectedIndex(0);
				date.setSelectedIndex(0);
			}
		});
		
		month = new JComboBox(months);
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int monthInteger = Integer.parseInt((String) month.getSelectedItem());
				if (monthInteger == 2){
					GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
					String selectedyear = (String) year.getSelectedItem();
					int selectedyearint = Integer.parseInt(selectedyear);
					if (cal.isLeapYear(selectedyearint)){
						date.removeAllItems();
						for (int i = 0; i < dates_29.length; i++){
							date.addItem((String) dates_29[i]);
						}
					}
					else
					{
						date.removeAllItems();
						for (int i = 0; i < dates_28.length; i++){
							date.addItem((String) dates_28[i]);
						}
					}
					return;
				}
				boolean big = false;
				System.out.println(monthInteger);
				switch (monthInteger){
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					big = true;
					break;
				default:
					big = false;
				}
				if (big){
					date.removeAllItems();
					for (int i = 0; i < dates_31.length; i++){
						date.addItem((String) dates_31[i]);
					}
				}
				else {
					date.removeAllItems();
					for (int i = 0; i < dates_30.length; i++){
						date.addItem((String) dates_30[i]);
					}
				}
			}
		});
		
		date = new JComboBox(dates_31);
		
		JLabel label = new JLabel("/");
		
		JLabel label_1 = new JLabel("/");
		
		JLabel lblYyyyMm = new JLabel("YYYY / MM / DD");
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(status != null){
					status.setText(getString());
				}
				setVisible(false);
				panel.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(year, 0, 76, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(month, 0, 76, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(date, 0, 74, Short.MAX_VALUE))
						.addComponent(lblYyyyMm)
						.addComponent(btnChoose, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(year, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(month, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblYyyyMm)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnChoose)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public Calendar getSelectedCalendar(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year.getSelectedIndex() + 1);
		cal.set(Calendar.MONTH, month.getSelectedIndex());
		cal.set(Calendar.DATE, date.getSelectedIndex() + 1);
		return cal;
	}
	
	public String getString(){
		return year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + date.getSelectedItem();
	}
	
	public String getStringForData(){
		return year.getSelectedItem() + "/" + month.getSelectedItem() + "/" + date.getSelectedItem() + "/";
	}
}
