package com.smilecare.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.smilecare.exception.InvalidInputException;
import com.smilecare.model.Appointment;
import com.smilecare.model.ServiceType;

public class SmileCareWindow extends JFrame{

	private ArrayList<Appointment> appointmentsList = new ArrayList<>();
	
	private JTextField txtName, txtDate;
	private JComboBox<ServiceType> comboService;
	private JTextArea txtInvoice;
	
	public SmileCareWindow() {
		// TODO Auto-generated constructor stub
		setTitle("SmileCare Dental Dashboard");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));
		
		JPanel leftPanel = new JPanel(new GridLayout(4, 2));
		leftPanel.add(new JLabel("Patient Name:"));
		txtName = new JTextField();
		leftPanel.add(txtName);
		
		leftPanel.add(new JLabel("Date (YYYY-MM-DD:)"));
		txtDate = new JTextField();
		leftPanel.add(txtDate);
		
		
		leftPanel.add(new JLabel("Service:"));
		comboService = new JComboBox<>(ServiceType.values());
		leftPanel.add(comboService);
		
		JButton btnBook = new JButton("Book Appointment");
		btnBook.addActionListener(e -> {
			try {
				saveAppointment();
			} catch (InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		leftPanel.add(new JLabel());
		leftPanel.add(btnBook);
		
		txtInvoice = new JTextArea("Invoices will appear here...");
		txtInvoice.setEditable(false);
		
		add(leftPanel);
		add(new JScrollPane(txtInvoice));
		
	}
	// Logic to save booking
	private void saveAppointment() throws InvalidInputException {
		// TODO Auto-generated method stub
		try {
		String name = txtName.getText();
		String date = txtDate.getText();
		ServiceType service = (ServiceType) comboService.getSelectedItem();
		
		// Validate using our custom exception
		if(name.isEmpty() || date.isEmpty()) {
			throw new InvalidInputException("Name and Date cannot be Empty !");
		}
		
		//create the object and add to our list
		Appointment newAppt = new Appointment(name, date, service);
		appointmentsList.add(newAppt);
		
		//show success and print a simple receipt
		JOptionPane.showMessageDialog(this, "Booked");
		txtInvoice.setText("Receipt:\n" + name + " - " + service.getName() + "\nTotal: $" + service.getBaseCost());
		
		//clear the boxes for the next patient
		txtName.setText("");
		txtDate.setText("");
		
		} catch (InvalidInputException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}