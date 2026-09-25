package java1.awt;

import java.awt.*;
import java.awt.event.*;

/**
 * Java 1.0 AWT (Abstract Window Toolkit) Example Demonstrates basic GUI components
 * NOTE: AWT is the original GUI framework in Java 1.0
 */
public class AWTExample extends Frame implements ActionListener
{
	private Button button;
	private Label label;
	private TextField textField;
	private int clickCount = 0;

	public AWTExample()
	{
		setTitle("Java 1.0 AWT Example");
		setSize(400, 300);
		setLayout(new FlowLayout());

		// Label
		label = new Label("Welcome to Java 1.0 AWT");
		add(label);

		// TextField
		textField = new TextField(20);
		add(textField);

		// Button
		button = new Button("Click Me");
		button.addActionListener(this);
		add(button);

		// Window listener for closing
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

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
		System.out.println("=== Java 1.0 AWT Example ===\n");
		System.out.println("AWT (Abstract Window Toolkit) provides:");
		System.out.println("- Basic GUI components (Button, Label, TextField)");
		System.out.println("- Layout managers (FlowLayout, BorderLayout, GridLayout)");
		System.out.println("- Event handling (ActionListener, WindowListener)");
		System.out.println("- Graphics capabilities");
		System.out.println("\nCreating AWT window...");
		new AWTExample();
	}
}

