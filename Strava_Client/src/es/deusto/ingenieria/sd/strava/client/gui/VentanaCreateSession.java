
package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.controller.SessionController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Date;
import javax.swing.JComboBox;
import java.awt.Font;


public class VentanaCreateSession extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitle;
	private JTextField textFieldDistance;
	private JTextField textFieldStartTime;
	private JTextField textFieldDuration;
	private SessionController sessionController;
	private JComboBox<String> comboBoxSportType;
	
	

	public VentanaCreateSession(SessionController sessionC) {
		sessionController = sessionC;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(125, 213, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setBounds(46, 52, 150, 13);
		contentPane.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(46, 76, 150, 19);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblDistance = new JLabel("Distance(km): ");
		lblDistance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDistance.setBounds(46, 114, 150, 13);
		contentPane.add(lblDistance);
		
		textFieldDistance = new JTextField();
		textFieldDistance.setColumns(10);
		textFieldDistance.setBounds(46, 138, 150, 19);
		contentPane.add(textFieldDistance);
		
		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStartDate.setBounds(375, 127, 150, 13);
		contentPane.add(lblStartDate);
		
		JLabel lblStartTime = new JLabel("Start Time: ");
		lblStartTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStartTime.setBounds(375, 52, 150, 13);
		contentPane.add(lblStartTime);
		
		textFieldStartTime = new JTextField();
		textFieldStartTime.setColumns(10);
		textFieldStartTime.setBounds(375, 76, 150, 19);
		contentPane.add(textFieldStartTime);
		
		JLabel lblDuration = new JLabel("Duration: ");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuration.setBounds(46, 168, 150, 13);
		contentPane.add(lblDuration);
		
		JLabel lblSportType = new JLabel("Sport Type: ");
		lblSportType.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSportType.setBounds(46, 229, 150, 13);
        contentPane.add(lblSportType);
        
        comboBoxSportType = new JComboBox<String>(new String[]{"Running", "Cycling"});
        comboBoxSportType.setBounds(46, 253, 150, 21);
        contentPane.add(comboBoxSportType);
		
		textFieldDuration = new JTextField();
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(46, 192, 150, 19);
		contentPane.add(textFieldDuration);
		
		JCalendar startDate = new JCalendar();
		startDate.setWeekOfYearVisible(false);
		startDate.setBounds(375, 150, 205, 138);
		contentPane.add(startDate);
		
		startDate.setMinSelectableDate(new Date(System.currentTimeMillis()));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(335, 314, 150, 21);
		contentPane.add(btnCreate);
		
		JLabel lblTitl = new JLabel("CREATE NEW SESSION");
		lblTitl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitl.setBounds(202, 11, 222, 40);
		contentPane.add(lblTitl);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(152, 313, 122, 23);
		contentPane.add(btnReturn);
		
		btnCreate.addActionListener(e->{
			sessionController.createSession(textFieldTitle.getText(), 
					(String) comboBoxSportType.getSelectedItem(), 
					Double.parseDouble(textFieldDistance.getText()), 
					startDate.getDate(), textFieldStartTime.getText(),
					Double.parseDouble(textFieldDuration.getText()));
			
			JOptionPane.showMessageDialog(VentanaCreateSession.this, "Session created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			LoginController logC = new LoginController(sessionController.getServiceLocator());
			VentanaMenu vM = new VentanaMenu(logC);
			vM.setVisible(true);
			dispose();
			
		});
		
		 btnReturn.addActionListener(e->{
				LoginController logC = new LoginController(sessionC.getServiceLocator());
				VentanaMenu vM = new VentanaMenu(logC);
	        	vM.setVisible(true);
	        	dispose();
				
			});
		
	}
}

