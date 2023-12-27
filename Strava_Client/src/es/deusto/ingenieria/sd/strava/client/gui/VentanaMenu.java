
package es.deusto.ingenieria.sd.strava.client.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.controller.SessionController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	private LoginController lController;
	private SessionController sController;
	private ChallengeController cController;
	
	public VentanaMenu(LoginController loginController) {
		lController = loginController;
		sController = new SessionController(lController.getServiceLocator());
		cController = new ChallengeController(lController.getServiceLocator());
		//ADD TOKENS
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("MENU");
		menuBar.add(mnMenu);

		
		
		JMenuItem mntmLogout = new JMenuItem("LOG OUT");
		mnMenu.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateSession = new JButton("CREATE SESSION");
		btnCreateSession.setBounds(187, 165, 188, 21);
		btnCreateSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCreateSession vcs = new VentanaCreateSession(sController);
				setVisible(false);
	        	vcs.setVisible(true);
			}
		});
		contentPane.add(btnCreateSession);
		
		JButton btnCreateChallenge = new JButton("CREATE CHALLENGE");
		btnCreateChallenge.setBounds(187, 107, 188, 21);
		btnCreateChallenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCreateChallenge vcc;
				try {
					vcc = new VentanaCreateChallenge(cController);
					setVisible(false);
		        	vcc.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnCreateChallenge);
		
		JButton btnAddActive = new JButton("ADD ACTIVE CHALLENGE");
		btnAddActive.setBounds(187, 220, 188, 21);
		btnAddActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAddActive vaa;
				try {
					vaa = new VentanaAddActive(cController);
					setVisible(false);
		        	vaa.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnAddActive);
		mntmLogout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//CALL LOG OUT FUNCTION  
				lController.logout();
				//REMOVE THE TOKEN FROM THE CONTROLLER AND RETURN TO THE WELCOME WINDOW
				VentanaBienvenida vb = new VentanaBienvenida(loginController);
				setVisible(false);
	        	vb.setVisible(true);
			}
		});
		
//		JLabel lblImage = new JLabel("");
//		lblImage.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/Settings.png")));
//		lblImage.setBounds(152, 29, 135, 150);
//		contentPane.add(lblImage);
		
	}
}
