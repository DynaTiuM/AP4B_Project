package View;

import java.awt.EventQueue;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create a new View frame and make it visible
					View frame = new View();
					frame.setVisible(true);
					frame.showBordInPopUp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}