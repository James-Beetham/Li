package version_6;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class GUI {

	static boolean ClassButtonPressed = false;
	static boolean ClassCreatorButtonPressed = false;
	static String ClassToAdd = null;

	public static void main(String[] args) throws InterruptedException, IOException {

		new File(System.getProperty("user.home") + "/.LearnIt").mkdir();
		File classLocations = new File(System.getProperty("user.home") + "/.LearnIt");

		JFrame frame = new JFrame("A Simple GUI");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLocation(430, 100);

		JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.add(panel);

		String[] choices = { "Import", "Export", "Delete", "Add" };

		final JComboBox<String> cb = new JComboBox<String>(choices);
		cb.setMaximumSize(cb.getPreferredSize());
		cb.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(cb);
		
		JMenu menu = new JMenu();
		Container pane = frame.getContentPane();
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.LEADING, true);
		pane.setLayout(layout);
		
		JButton btn = new JButton("OK");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(cb.getSelectedItem());
				// TODO What happens when it runs
			}
		});
		btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btn);

		ArrayList<String> savedSession = new ArrayList<String>();
		
		JButton tempBtn;
		ModifiedMouseListener tempML;
		ModifiedActionListener tempAL;
		for (int i = 0; i < classLocations.list().length; i++) {
			tempBtn = new JButton(classLocations.list()[i]);
			tempAL = new ModifiedActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					savedSession.add(text);
					getButton().setContentAreaFilled(!getButton().isContentAreaFilled());
					getButton().setBackground(Color.BLUE);
					}
			};
			
			tempAL.setButton(tempBtn);
			tempAL.setText(classLocations.list()[i]);
			tempBtn.addActionListener(tempAL);
			tempBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
			tempBtn.setBorderPainted(false);
			tempBtn.setFocusPainted(false);
			tempBtn.setContentAreaFilled(false);
			pg.addComponent(panel).addComponent(tempBtn);
		}
		layout.setVerticalGroup(pg);

	    JLabel label = new JLabel("Label");
	    JButton b2 = new JButton("Second Button");

	    layout.setHorizontalGroup(layout
	        .createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label)
	        .addComponent(b2));

	}

}
