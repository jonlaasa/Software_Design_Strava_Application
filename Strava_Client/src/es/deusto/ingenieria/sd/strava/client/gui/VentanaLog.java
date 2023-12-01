package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaLog extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textFieldPassword;
	private LoginController controller;
	private JComboBox<String> comboBoxProvider;

	public VentanaLog(LoginController controller) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 422);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblFtPerfil = new JLabel("");
//		lblFtPerfil.setIcon(new ImageIcon(VentanaLog.class.getResource("/images/FtPerfilV.png")));
//		lblFtPerfil.setBounds(140, 10, 174, 115);
//		contentPane.add(lblFtPerfil);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(227, 140, 148, 13);
		contentPane.add(lblEmail);
		
		JLabel provider = new JLabel("Provider: ");
		

		comboBoxProvider = new JComboBox<String>(new String[]{"GOOGLE", "FACEBOOK"});
	    comboBoxProvider.setBounds(227, 96, 176, 19);
	    contentPane.add(comboBoxProvider);
		
		
		
		textEmail = new JTextField();
		textEmail.setBounds(227, 164, 183, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(227, 222, 183, 19);
		contentPane.add(textFieldPassword);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBienvenida vv = new VentanaBienvenida(controller);
				setVisible(false);
	        	vv.setVisible(true);
			}
		});
		btnBack.setBounds(184, 297, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//COMPROBAMOS LOGIN
				//hash
				
				String passHash = org.apache.commons.codec.digest.DigestUtils.sha1Hex(textFieldPassword.getText());
				String email = textEmail.getText();
				String provid = (String) comboBoxProvider.getSelectedItem();
				if(controller.login(email, passHash,provid)==true) {
					VentanaMenu vM = new VentanaMenu(controller);
					setVisible(false);
		        	vM.setVisible(true);	
				}
				else {
					
					//WE DISPLAY A MESSAGE
					JOptionPane.showMessageDialog(VentanaLog.this, "LOG IN NOT SUCCESFUL", "Try", JOptionPane.ERROR_MESSAGE);
					
					
					
				}
				
				
			}
		});
		btnLogin.setBounds(374, 297, 101, 21);
		contentPane.add(btnLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(227, 197, 120, 14);
		contentPane.add(lblPassword);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(227, 73, 46, 14);
		contentPane.add(lblProvider);
		
		
		btnLogin.addActionListener(e->{
			
		});
	}
}