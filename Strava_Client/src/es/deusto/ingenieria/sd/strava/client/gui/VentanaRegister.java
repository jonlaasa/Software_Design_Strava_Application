
package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class VentanaRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldWeight;
	private JTextField textFieldHeight;
	private JTextField textFieldMaximumHeartRate;
	private JTextField textFieldHeartRateAtRest;
	private JTextField textPassword;
	private LoginController controller;
	private JComboBox<String> comboBoxProvider;


	public VentanaRegister(LoginController controller) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 439);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBienvenida vb = new VentanaBienvenida(controller);
				setVisible(false);
	        	vb.setVisible(true);
			}
		});
		btnBack.setBounds(181, 353, 85, 21);
		contentPane.add(btnBack);
		
		
		JCalendar birthdate = new JCalendar();
		birthdate.setWeekOfYearVisible(false);
		birthdate.setBounds(306, 196, 183, 126);
		contentPane.add(birthdate);
		
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passHash = org.apache.commons.codec.digest.DigestUtils.sha1Hex(textPassword.getText());
				String email = textFieldEmail.getText();
				String name = textFieldName.getText();
				Date birth = birthdate.getDate();
				double weight =  Double.parseDouble(textFieldWeight.getText());
				double height =  Double.parseDouble(textFieldHeight.getText());
				double maxR =  Double.parseDouble(textFieldMaximumHeartRate.getText());
				double hR =  Double.parseDouble(textFieldHeartRateAtRest.getText());
				String provider = (String) comboBoxProvider.getSelectedItem();
			
				controller.register(name, passHash,email,birth,weight,height,maxR,hR,provider);
				//MESSAGE
				
				VentanaBienvenida vb = new VentanaBienvenida(controller);
				setVisible(false);
				vb.setVisible(true);	
			}
		});
		btnContinue.setBounds(312, 353, 97, 21);
		contentPane.add(btnContinue);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(82, 90, 183, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(82, 147, 183, 19);
		contentPane.add(textFieldEmail);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setColumns(10);
		textFieldWeight.setBounds(82, 303, 183, 19);
		contentPane.add(textFieldWeight);
		
		textFieldHeight = new JTextField();
		textFieldHeight.setColumns(10);
		textFieldHeight.setBounds(82, 249, 183, 19);
		contentPane.add(textFieldHeight);
		
		textFieldMaximumHeartRate = new JTextField();
		textFieldMaximumHeartRate.setColumns(10);
		textFieldMaximumHeartRate.setBounds(306, 90, 183, 19);
		contentPane.add(textFieldMaximumHeartRate);
		
		textFieldHeartRateAtRest = new JTextField();
		textFieldHeartRateAtRest.setColumns(10);
		textFieldHeartRateAtRest.setBounds(306, 147, 183, 19);
		contentPane.add(textFieldHeartRateAtRest);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(82, 202, 183, 19);
		contentPane.add(textPassword);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(82, 66, 183, 13);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(83, 120, 183, 13);
		contentPane.add(lblEmail);
		
		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setBounds(306, 177, 183, 13);
		contentPane.add(lblBirthdate);
		
		JLabel lblWeight = new JLabel("Weight(kg):");
		lblWeight.setBounds(83, 279, 183, 13);
		contentPane.add(lblWeight);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(83, 177, 183, 13);
		contentPane.add(lblPassword);
		
		JLabel lblHeartRateAtRest = new JLabel("Heart Rate At Rest:");
		lblHeartRateAtRest.setBounds(306, 123, 183, 13);
		contentPane.add(lblHeartRateAtRest);
		
		JLabel lblMaximumHeartRate = new JLabel("Maximum Heart Rate:");
		lblMaximumHeartRate.setBounds(306, 66, 183, 13);
		contentPane.add(lblMaximumHeartRate);
		
		
		//PROVIDER
		
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setBounds(182, 11, 183, 13);
		contentPane.add(lblProvider);
		
		comboBoxProvider = new JComboBox<String>(new String[]{"GOOGLE", "FACEBOOK"});
	    comboBoxProvider.setBounds(183, 34, 193, 21);
	    contentPane.add(comboBoxProvider);
		
		//////
		
		JLabel lblHeight = new JLabel("Height (cm):");
		lblHeight.setBounds(82, 232, 183, 13);
		contentPane.add(lblHeight);
		
		
	}
}
