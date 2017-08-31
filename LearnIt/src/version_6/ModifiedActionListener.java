package version_6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ModifiedActionListener implements ActionListener {

	
	String text;
	public void setText(String s)
	{
		text = s;
	}
	
	public String getText()
	{
		return text;
	}
	
	JButton btn;
	public void setButton(JButton button)
	{
		 btn = button;
	}
	
	public JButton getButton()
	{
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Should be overriden whenever used
	}
	
}
