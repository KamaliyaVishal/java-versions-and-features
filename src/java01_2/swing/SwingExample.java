package java1_2.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java 1.2 Swing GUI Framework Example Demonstrates Swing components
 */
public class SwingExample extends JFrame implements ActionListener
{
	private JButton button;
	private JLabel label;
	private JTextField textField;
	private int clickCount = 0;

	public SwingExample()
	{
		setTitle("Java 1.2 Swing Example");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		// Label
		label = new JLabel("Welcome to Swing");
		add(label);

		// TextField
		textField = new JTextField(20);
		add(textField);

		// Button
		button = new JButton("Click Me");
		button.addActionListener(this);
		add(button);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == button)
		{
			clickCount++;
			label.setText("Button clicked " + clickCount + " times");
			System.out.println("Button clicked! Text: " + textField.getText());
		}
	}

	public static void main(String[] args)
	{
		System.out.println("=== Java 1.2 Swing GUI Framework ===\n");
		System.out.println("Swing provides:");
		System.out.println("- Lightweight components");
		System.out.println("- Platform-independent look and feel");
		System.out.println("- Rich component set");
		System.out.println("- Better than AWT");
		System.out.println("- MVC architecture");
		System.out.println("\nCreating Swing window...");
		SwingUtilities.invokeLater(() -> new SwingExample());
	}
}

