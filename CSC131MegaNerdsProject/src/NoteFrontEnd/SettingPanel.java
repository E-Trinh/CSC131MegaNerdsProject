package NoteFrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import NoteBackEnd.*;

public class SettingPanel extends JPanel{
	
	private NotesList data;
	
	public SettingPanel(NotesList data) {
		super();
		this.data = data;
		setupLayout();
	}
	
	public void setupLayout() {
		Button reset = new Button("Delete all data");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resetValue = JOptionPane.showConfirmDialog(null, "Delete data? Data will no longer be recoverable, program will close after reset.");
				if (resetValue == JOptionPane.YES_OPTION) {
					data.clearAll();
					data.exportNote();
					System.exit(0);
				}
			}
		});
		this.add(reset);
	}
}
