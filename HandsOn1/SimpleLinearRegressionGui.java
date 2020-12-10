
package seminario.HandsOn1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleLinearRegressionGui extends JFrame{
    private SimpleLinearRegression myAgent;
	
	private JTextField numberField;
	
	SimpleLinearRegressionGui(SimpleLinearRegression a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));
		p.add(new JLabel("Enter a number:"));
		numberField = new JTextField(15);
		p.add(numberField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String number = numberField.getText().trim();
					String prediction = myAgent.getPrediction(Integer.parseInt(number));
					numberField.setText("");
					JOptionPane.showMessageDialog(SimpleLinearRegressionGui.this, prediction, "Prediction sucess", JOptionPane.INFORMATION_MESSAGE); 
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(SimpleLinearRegressionGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}
}
