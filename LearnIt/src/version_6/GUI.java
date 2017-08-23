package version_6;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {
	
	static boolean ClassButtonPressed = false;
	static boolean ClassCreatorButtonPressed = false;
	static String ClassToAdd = null;

	public static void main(String[] args) throws InterruptedException {

		new File(System.getProperty("user.home") + "/.LearnIt").mkdir();
		File classLocations = new File(System.getProperty("user.home") + "/.LearnIt");
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1000, 1000));
		String[] listOfClasses = classLocations.list();
		JButton createClassButton = new JButton("Create Class");
		createClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassCreatorButtonPressed = true;
			}
		});
		JButton chooseClassButton = new JButton("Choose Class");
		chooseClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassButtonPressed = true;
			}
		});
		frame.add(chooseClassButton);
		frame.add(createClassButton);
		chooseClassButton.setSize(new Dimension(100, 30));
		createClassButton.setSize(new Dimension(100, 30));
		frame.setVisible(true);
		while (true) {
			if (ClassButtonPressed) {
				JFrame classSelector = new JFrame();
				classSelector.setSize(new Dimension(1000, 1000));
				ArrayList<JButton> classButtons = new ArrayList<JButton>();
				System.out.println("pre-loop");
				for (int i = 0; i < listOfClasses.length; ++i) {
					classButtons.add(new JButton(listOfClasses[i]));
				}
				for (JButton button : classButtons) {
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ClassToAdd = listOfClasses[classButtons.indexOf(button)];
						}
					});

					classSelector.add(button);
				}

				System.out.println("post-loop");
				classSelector.setVisible(true);
				ClassButtonPressed = false;
			}
			else if(ClassCreatorButtonPressed)
			{
				JFrame classCreator = new JFrame();
				classCreator.setSize(500, 500);
				JButton submit = new JButton("Submit");
				JTextField className = new JTextField();
				submit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new File(System.getProperty("user.home") + "/.LearnIt/" + className.getText()).mkdir();
						String[] listOfClasses = classLocations.list();
						classCreator.dispatchEvent(new WindowEvent(classCreator, WindowEvent.WINDOW_CLOSING));
						
					}
				});
				submit.setSize(new Dimension(100, 30));
				className.setSize(new Dimension(100, 30));
				classCreator.add(submit);
				classCreator.add(className);
				classCreator.setVisible(true);
				ClassCreatorButtonPressed = false;
			}
			Thread.sleep(10);
		}

	}

}
