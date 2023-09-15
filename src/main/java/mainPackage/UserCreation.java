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

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class UserCreation extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_5;

	Connection con;
	PreparedStatement ps;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCreation frame = new UserCreation();
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
	public UserCreation() {
		setBounds(0, 0, 800, 600);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 402, 431);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(27, 57, 98, 32);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(27, 116, 98, 32);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("User name:");
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(27, 175, 98, 32);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(27, 235, 98, 32);
		panel.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(165, 57, 130, 32);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(165, 118, 130, 32);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(165, 177, 130, 32);
		panel.add(textField_2);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateUserId();
				fetchTheData();
			}
		});
		btnNewButton.setBounds(57, 316, 114, 47);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(230, 316, 114, 47);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setForeground(Color.MAGENTA);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 11, 46, 35);
		panel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setForeground(Color.BLUE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(57, 11, 88, 35);
		panel.add(lblNewLabel_5);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 235, 130, 32);
		panel.add(passwordField);

	}

	public void fetchTheData() {
		String id = lblNewLabel_5.getText();
		String firstName = textField.getText();
		String lastName = textField_1.getText();
		String userName = textField_2.getText();
		String password = passwordField.getText();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			ps = con.prepareStatement("insert into user (id,firstname,lastname,username,password)values(?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, userName);
			ps.setString(5, password);
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "User created! ");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void generateUserId() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select MAX(id) from user");
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				lblNewLabel_5.setText("UID001");
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(3, rs.getString("MAX(id)").length()));
				id++;
				lblNewLabel_5.setText("FID" + String.format("%03d", id));
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
