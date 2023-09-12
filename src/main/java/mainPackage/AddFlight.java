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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFlight extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblNewLabel_8;
	Connection con;
	PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFlight frame = new AddFlight();
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
	public AddFlight() {
		setBounds(0, 0, 564, 422);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 528, 370);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Flight ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setBounds(10, 11, 83, 31);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Flight name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(10, 53, 95, 33);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Departure");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setBounds(10, 97, 83, 34);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Destination");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setBounds(10, 142, 83, 31);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Date:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.MAGENTA);
		lblNewLabel_4.setBounds(260, 21, 86, 21);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Arrival time:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setForeground(Color.MAGENTA);
		lblNewLabel_5.setBounds(260, 64, 113, 22);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Departure time:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setForeground(Color.MAGENTA);
		lblNewLabel_6.setBounds(260, 109, 113, 22);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Flight charges:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setForeground(Color.MAGENTA);
		lblNewLabel_7.setBounds(260, 152, 113, 21);
		panel.add(lblNewLabel_7);

		textField = new JTextField();
		textField.setBounds(111, 61, 139, 20);
		panel.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Croatia", "South Korea", "Australia", "Germany",
				"Turkey", "Panama", "Peru", "Colombia" }));
		comboBox.setBounds(111, 105, 139, 22);
		panel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Croatia", "South Korea", "Australia", "Germany",
				"Turkey", "Panama", "Peru", "Colombia" }));
		comboBox_1.setBounds(111, 148, 139, 22);
		panel.add(comboBox_1);

		textField_1 = new JTextField();
		textField_1.setBounds(372, 18, 123, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(372, 61, 123, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(372, 106, 123, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(372, 149, 123, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateFlightId();
				fetchingValues();
			}
		});
		btnNewButton.setBounds(96, 216, 123, 42);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(306, 216, 113, 42);
		panel.add(btnNewButton_1);

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setForeground(Color.BLUE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(114, 16, 95, 21);
		panel.add(lblNewLabel_8);

	}

	public void generateFlightId() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select MAX(id) from flight");
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				lblNewLabel_8.setText("FID001");
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(3, rs.getString("MAX(id)").length()));
				id++;
				lblNewLabel_8.setText("FID" + String.format("%03d", id));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fetchingValues() {
		String id = lblNewLabel_8.getText();
		String flightName = textField.getText();
		String departure = comboBox.getSelectedItem().toString().trim();
		String destination = comboBox_1.getSelectedItem().toString().trim();
		String date = textField_1.getText();
		String arrivalTime = textField_2.getText();
		String departureTime= textField_3.getText();
		String price = textField_4.getText();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			ps = con.prepareStatement(
					"insert into flight (id,flightname,departure,destination,date,arrivaltime,departuretime,price)values(?,?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, flightName);
			ps.setString(3, departure);
			ps.setString(4, destination);
			ps.setString(5, date);
			ps.setString(6, arrivalTime);
			ps.setString(7, departureTime);
			ps.setString(8, price);
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Flight registered! ");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
