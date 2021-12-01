import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class TieDownCalculator implements ActionListener {
	private static String[] choices = { "INCHES","FEET"};
	private static int fontSize = 44;
	private static JComboBox<String> menu = new JComboBox<String>(choices);
	private static JTextField inputBox = new JTextField("");
	private static int numOfHoles;
	private static int target = 86;
	private static ArrayList<Float> distances = new ArrayList<Float>();
	
	public TieDownCalculator() {
		JFrame frame = new JFrame("Tie Down Calculator");

		JPanel panel = new JPanel();

		JLabel question = new JLabel("How long is the pad?");
		question.setVisible(true);
		question.setFont(new Font("Serif", Font.PLAIN, fontSize));
		inputBox.setFont(new Font("Serif", Font.PLAIN, fontSize));
		JButton calculate = new JButton("Calculate");
		calculate.addActionListener(this);
		menu.setVisible(true);
		panel.add(question);
		panel.add(inputBox);
		panel.add(menu);
		panel.add(calculate);
		
		panel.setLayout(new GridLayout(0,1));

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Tie Down Calculator");
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String selection = menu.getItemAt(menu.getSelectedIndex());
		String result;
		float input = Float.parseFloat(inputBox.getText());
		float newLength = input - 60;
		float distance;
		float greatest = 0;
		
		// convert FEET to INCHES
		if (selection.equals("FEET")) {
			input = input * 12;
		} 
		
		// compile all posible distances
		for (int i = 1; i < 20; i++) {
			distance = newLength / i;
			
			distances.add(distance);
		}
		
		Collections.sort(distances);
		
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i) < target) {
				greatest = distances.get(i);
			}
		}
		
		String answer = "Drill " + greatest + " inches apart.";
		
		JOptionPane.showMessageDialog(null, answer,"Result",1);
	}
	
	public static void main(String[] args) {
		new TieDownCalculator();
	}
}


