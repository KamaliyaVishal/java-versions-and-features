package java1.applets;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Java 1.0 Applet Example Demonstrates basic applet functionality
 * NOTE: Applets are deprecated in modern Java. This is for historical reference.
 * 
 * To run as applet, create HTML file:
 * <applet code="AppletExample.class" width="300" height="200"></applet>
 */
public class AppletExample extends Applet
{
	private String message = "Hello from Java 1.0 Applet!";

	public void init()
	{
		System.out.println("Applet initialized");
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
	}

	public void start()
	{
		System.out.println("Applet started");
	}

	public void paint(Graphics g)
	{
		g.drawString(message, 50, 50);
		g.drawRect(30, 30, 200, 100);
		g.drawOval(100, 70, 50, 50);
	}

	public void stop()
	{
		System.out.println("Applet stopped");
	}

	public void destroy()
	{
		System.out.println("Applet destroyed");
	}

	// Can also run as standalone application
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.0 Applet Example ===\n");
		System.out.println("Applets allow Java programs to run in web browsers.");
		System.out.println("Lifecycle methods:");
		System.out.println("- init(): Initialization");
		System.out.println("- start(): Applet starts");
		System.out.println("- paint(Graphics g): Drawing");
		System.out.println("- stop(): Applet stops");
		System.out.println("- destroy(): Cleanup");
		System.out.println("\nNote: Applets are deprecated in modern Java.");
		System.out.println("This example is for historical reference only.");
	}
}

