
package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaBienvenida extends JFrame {
	private JPanel contentPane;
	
	public VentanaBienvenida(LoginController controller) {
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelMensajeBienv = new JLabel("WELCOME TO STRAVA");
		LabelMensajeBienv.setForeground(new Color(255, 128, 64));
		LabelMensajeBienv.setFont(new Font("Tahoma", Font.BOLD, 23));
		LabelMensajeBienv.setBounds(149, 76, 259, 70);
		contentPane.add(LabelMensajeBienv);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegister vr = new VentanaRegister(controller);
	        	setVisible(false);
	        	vr.setVisible(true);
			}
		});
		btnRegister.setBounds(335, 259, 109, 37);
		contentPane.add(btnRegister);
		
		JButton btnLog = new JButton("LOG IN");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLog vl = new VentanaLog(controller);
	        	setVisible(false);
	        	vl.setVisible(true);
			}
		});
		btnLog.setBounds(134, 259, 109, 37);
		contentPane.add(btnLog);
		
//		JLabel lblImage = new JLabel("");
//		lblImage.setIcon(
//				new ImageIcon(VentanaBienvenida.class.getResource("./images/Strava.png")));
//		lblImage.setBounds(86, 94, 291, 122);
//		contentPane.add(lblImage);
//	}
	
}
}
