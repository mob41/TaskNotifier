package com.mob41.taskn;

import javax.swing.UIManager;

import com.mob41.taskn.docrec.DocumentRecord;
import com.mob41.taskn.tasks.Tasks;
import com.mob41.taskn.ui.UI;

public class Main {

	public static void main(String[] args) {
		try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
			e.printStackTrace();
		}
		Tasks.load();
		DocumentRecord.load();
		Conf.load();
		UI.start();

	}

}
