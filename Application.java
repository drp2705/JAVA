package com.smilecare.main;

import javax.swing.SwingUtilities;

import com.smilecare.gui.SmileCareWindow;

public class Application {
public static void main(String[] args) {
	// Safely start the Swing user Interface
	SwingUtilities.invokeLater(() -> {
		SmileCareWindow window = new SmileCareWindow();
		window.setVisible(true);
	});
}
}
