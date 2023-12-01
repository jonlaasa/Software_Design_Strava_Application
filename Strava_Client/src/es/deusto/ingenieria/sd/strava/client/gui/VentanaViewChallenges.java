
package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaViewChallenges extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnClose;

    public VentanaViewChallenges(List<ChallengeDTO> challenges) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 693, 444);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(125, 213, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Define the table model with column names
        String[] columnNames = { "Name", "Start Date", "End Date", "Distance", "Goal", "Sport Type" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate the table with challenge data
        for (ChallengeDTO challenge : challenges) {
            Object[] rowData = { challenge.getName(), challenge.getStartDate(), challenge.getEndDate(),
                    challenge.getDistance(), challenge.getGoal(), challenge.getSportType() };
            model.addRow(rowData);
        }

        // Create the table with the model
        table = new JTable(model);
        table.setBounds(10, 10, 560, 300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 644, 350);
        contentPane.add(scrollPane);
        
        btnClose = new JButton("Close");
        btnClose.setBounds(331, 371, 89, 23);
        contentPane.add(btnClose);
        
        btnClose.addActionListener(e->{
        	setVisible(false);
        });
    }
}
