package main;

import java.awt.EventQueue;

import View.View;
import controller.Controller;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create a new View frame and make it visible
					View frame = new View(4);
					frame.setVisible(true);
					frame.showBordInPopUp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
