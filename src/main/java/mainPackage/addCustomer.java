package mainPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class addCustomer extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel txtId = new JLabel("New label");

	Connection con;
	PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCustomer frame = new addCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public addCustomer() {
		setBounds(0, 0, 564, 422);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 528, 323);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 29, 99, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 64, 99, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("NIC number:");
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 98, 99, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Passport ID:");
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 129, 99, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address:");
		lblNewLabel_4.setForeground(Color.MAGENTA);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 162, 99, 14);
		panel.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setBounds(108, 28, 118, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 63, 118, 20);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(108, 97, 118, 20);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(108, 128, 118, 20);
		panel.add(textField_3);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(108, 154, 118, 44);
		panel.add(formattedTextField);

		JLabel lblNewLabel_5 = new JLabel("Date of Birth:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.MAGENTA);
		lblNewLabel_5.setBounds(236, 34, 84, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Gender:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setForeground(Color.MAGENTA);
		lblNewLabel_6.setBounds(236, 66, 63, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Contact:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setForeground(Color.MAGENTA);
		lblNewLabel_7.setBounds(236, 100, 63, 14);
		panel.add(lblNewLabel_7);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(334, 62, 47, 23);
		panel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Female");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(395, 62, 63, 23);
		panel.add(rdbtnNewRadioButton_1);

		textField_4 = new JTextField();
		textField_4.setBounds(334, 97, 124, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addingCustomerId();

			}
		});
		btnNewButton.setBounds(236, 121, 104, 35);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(345, 121, 113, 35);
		panel.add(btnNewButton_1);

		textField_5 = new JTextField();
		textField_5.setBounds(334, 32, 124, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Customer ID:");
		lblNewLabel_8.setForeground(Color.MAGENTA);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(236, 175, 86, 14);
		panel.add(lblNewLabel_8);

		txtId.setFont(new Font("Snap ITC", Font.BOLD, 16));
		txtId.setForeground(Color.BLUE);
		txtId.setBounds(334, 164, 124, 34);
		panel.add(txtId);

	}

	public void addingCustomerId() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select MAX(id) from customer");
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				txtId.setText ("CID001");
			}
			else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
				id++;
				txtId.setText("CID"+String.format("%03d",id));
			}
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
