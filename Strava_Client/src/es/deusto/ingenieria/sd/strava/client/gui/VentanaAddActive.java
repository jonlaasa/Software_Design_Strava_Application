

package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Panel;
import java.awt.Font;
import java.awt.event.ActionListener;


public class VentanaAddActive extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSuscribe;
	 private ChallengeController challengeController;

	public VentanaAddActive(ChallengeController challengeC) throws ParseException {
		challengeController = challengeC;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 321);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Insert name of the challenge to subscribe:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(21, 137, 265, 13);
		contentPane.add(lblNewLabel);

		textFieldSuscribe = new JTextField();
		textFieldSuscribe.setBounds(21, 161, 244, 19);
		contentPane.add(textFieldSuscribe);
		textFieldSuscribe.setColumns(10);

		JButton btnDownloadActive = new JButton("SEE ACTIVE CHALLENGES");
		btnDownloadActive.setBounds(146, 71, 174, 21);
		contentPane.add(btnDownloadActive);

		JButton btnSubscribe = new JButton("SUBSCRIBE");
		btnSubscribe.setBounds(268, 237, 102, 21);
		contentPane.add(btnSubscribe);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(99, 236, 96, 23);
		contentPane.add(btnReturn);
		
		JLabel lblTitle = new JLabel("SUBSCRIBE TO A CHALLENGE");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(115, 11, 325, 49);
		contentPane.add(lblTitle);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//ADD THIS HARDCODED TO THE 
		
		btnSubscribe.addActionListener(e->{
			boolean value = challengeController.acceptChallenge(textFieldSuscribe.getText());
			//EXISTS?
			if(value ==true) {
				JOptionPane.showMessageDialog(VentanaAddActive.this, "Challenge subscribed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);	
				
				
			}else {
				
				JOptionPane.showMessageDialog(VentanaAddActive.this, "Challenge doesn´t exist !", "Error", JOptionPane.ERROR_MESSAGE);	
				
				
			}
			
			

			
		});
		
		btnDownloadActive.addActionListener(e->{
			
				List<ChallengeDTO> list;
				try {
					list = challengeC.getServiceLocator().getService().getActiveChallenges();
					VentanaViewChallenges vV= new VentanaViewChallenges(list);
					vV.setVisible(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
		});
		
		btnReturn.addActionListener(e->{
			LoginController logC = new LoginController(challengeController.getServiceLocator());
			VentanaMenu vM = new VentanaMenu(logC);
        	vM.setVisible(true);
        	dispose();
			
		});
	}
}

