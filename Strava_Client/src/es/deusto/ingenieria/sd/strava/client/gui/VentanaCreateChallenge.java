
package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VentanaCreateChallenge extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldName;
    private JTextField textDistance;
    private JComboBox<String> comboBoxSportType;
    private JTextField textFieldGoal;
    private ChallengeController challengeController;

    public VentanaCreateChallenge(ChallengeController challengeC) throws ParseException {
    	challengeController= challengeC;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 705, 504);
        setLocationRelativeTo(null);        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(125, 213, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblName = new JLabel("Name: ");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblName.setBounds(72, 68, 150, 13);
        contentPane.add(lblName);
        
        textFieldName = new JTextField();
        textFieldName.setBounds(72, 92, 150, 19);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);
        
        JLabel lblEndDate = new JLabel("End Date: ");
        lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEndDate.setBounds(384, 232, 150, 13);
        contentPane.add(lblEndDate);
        
        JLabel lblStartDate = new JLabel("Start Date: ");
        lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblStartDate.setBounds(384, 68, 150, 13);
        contentPane.add(lblStartDate);
        
        JLabel lblTargetDistance = new JLabel("Target Distance: ");
        lblTargetDistance.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTargetDistance.setBounds(72, 140, 150, 13);
        contentPane.add(lblTargetDistance);
        
        textDistance = new JTextField();
        textDistance.setColumns(10);
        textDistance.setBounds(72, 164, 150, 19);
        contentPane.add(textDistance);
        
        comboBoxSportType = new JComboBox<String>(new String[]{"Running", "Cycling","Both"});
        comboBoxSportType.setBounds(72, 299, 150, 21);
        contentPane.add(comboBoxSportType);
        
        JButton btnCreate = new JButton("Create"); 
        btnCreate.setBounds(384, 406, 150, 21);
        contentPane.add(btnCreate);
        
        JButton btnReturn = new JButton("Return");
        btnReturn.setVerticalAlignment(SwingConstants.BOTTOM);
        btnReturn.setBounds(188, 404, 118, 23);
        contentPane.add(btnReturn);
        
        textFieldGoal = new JTextField();
        textFieldGoal.setBounds(72, 228, 150, 20);
        contentPane.add(textFieldGoal);
        textFieldGoal.setColumns(10);
        
        JLabel lblGoal = new JLabel("Goal:");
        lblGoal.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGoal.setBounds(72, 207, 46, 14);
        contentPane.add(lblGoal);
        
        JLabel lblSportType = new JLabel("Sport Type:");
        lblSportType.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSportType.setBounds(72, 275, 75, 14);
        contentPane.add(lblSportType);
        
        JLabel lblCreateChallenge = new JLabel("CREATE NEW CHALLENGE");
        lblCreateChallenge.setForeground(new Color(255, 128, 64));
        lblCreateChallenge.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCreateChallenge.setBounds(230, 11, 277, 46);
        contentPane.add(lblCreateChallenge);
        
        
        JCalendar startDate = new JCalendar();
		startDate.setWeekOfYearVisible(false);
		startDate.setBounds(384, 92, 203, 129);
		contentPane.add(startDate);
		
		
        
		JCalendar endDate = new JCalendar();
		endDate.setWeekOfYearVisible(false);
		endDate.setBounds(384, 256, 203, 129);
		contentPane.add(endDate);
		
		endDate.setMinSelectableDate(new Date(System.currentTimeMillis()));
        
		
		
		
		
        
		
		
        btnCreate.addActionListener(e->{
			challengeController.setUpChallenge(textFieldName.getText(), endDate.getDate(), Double.parseDouble(textDistance.getText()), Double.parseDouble(textFieldGoal.getText()), startDate.getDate(), (String) comboBoxSportType.getSelectedItem());
			JOptionPane.showMessageDialog(VentanaCreateChallenge.this, "Challenge created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);	
			//CREATE NEW LOGIN CONTROLLER AND ADD TOKEN
			
			LoginController logC = new LoginController(challengeController.getServiceLocator());
            VentanaMenu vM = new VentanaMenu(logC);
			vM.setVisible(true);
			dispose();
			
		});
        
        btnReturn.addActionListener(e->{
			LoginController logC = new LoginController(challengeController.getServiceLocator());
			VentanaMenu vM = new VentanaMenu(logC);
        	vM.setVisible(true);
        	dispose();
			
		});
    }
    
}
