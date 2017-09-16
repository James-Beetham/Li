package version_6;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

//w w w .java  2s. c  om
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI2 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("GroupLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		new File(System.getProperty("user.home") + "/.LearnIt").mkdir();
		File classLocations = new File(System.getProperty("user.home") + "/.LearnIt");

		GroupLayout groupLayout = new GroupLayout(contentPane);

		contentPane.setLayout(groupLayout);

		JLabel label = new JLabel("Label");
		JButton b2 = new JButton("Second Button");

		JPanel pane = new JPanel();
		
		JButton tempBtn;
		ModifiedMouseListener tempML;
		ModifiedActionListener tempAL;
		ParallelGroup pgFileList = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		SequentialGroup sgFileList = groupLayout.createSequentialGroup();
	
		int i = 0;
		for (; i < classLocations.list().length; i++) {
			tempBtn = new JButton(classLocations.list()[i]);
			tempAL = new ModifiedActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					getButton().setContentAreaFilled(!getButton().isContentAreaFilled());
					getButton().setBackground(Color.BLUE);
				}
			};

			tempAL.setButton(tempBtn);
			tempAL.setText(classLocations.list()[i]);
			tempBtn.addActionListener(tempAL);
			tempBtn.setBorderPainted(false);
			tempBtn.setFocusPainted(false);
			tempBtn.setContentAreaFilled(false);
			tempBtn.setSize(new Dimension(100, 50));
			pane.add(tempBtn);
		}
		
		pane.setPreferredSize(new Dimension(80, i * 28));
		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(100, 100));
		pgFileList.addComponent(scrollPane, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		sgFileList.addComponent(scrollPane);
		
		JLabel lab = new JLabel("1");
		JLabel lab2 = new JLabel("2");

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGroup(pgFileList)
				.addComponent(lab2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(lab, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(sgFileList).addComponent(lab2).addComponent(lab));

		frame.pack();
		frame.setVisible(true);
	}
}