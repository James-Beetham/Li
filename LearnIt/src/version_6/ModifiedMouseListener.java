package version_6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ModifiedMouseListener implements ActionListener{
	
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
		// TODO Auto-generated method stub
		
	}

}
