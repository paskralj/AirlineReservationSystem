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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class TicketBooking extends JInternalFrame {
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	Connection con;
	PreparedStatement ps;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketBooking frame = new TicketBooking();
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
	public TicketBooking() {
		setBounds(0, 0, 1200, 800);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 369, 139);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select country");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 0, 106, 17);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Departure");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 28, 73, 28);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Destination");
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(167, 28, 83, 28);
		panel.add(lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Croatia", "South Korea", "Australia", "Germany",
				"Turkey", "Panama", "Peru", "Colombia" }));
		comboBox.setBounds(10, 67, 123, 22);
		panel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Croatia", "South Korea", "Australia", "Germany",
				"Turkey", "Panama", "Peru", "Colombia" }));
		comboBox_1.setBounds(167, 67, 123, 22);
		panel.add(comboBox_1);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatingFlightTable();
			}
		});
		btnNewButton.setBounds(107, 100, 89, 23);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 574, 181);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "FlightID", "FlightName", "Departure",
				"Destination", "Date", "ArrivalTime", "DepartureTime", "Price" }));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(403, 11, 177, 139);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Ticket number:");
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(31, 11, 109, 36);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Ticket NO");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(41, 58, 99, 48);
		panel_1.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(620, 11, 395, 139);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Customer ID:");
		lblNewLabel_5.setForeground(Color.MAGENTA);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 11, 103, 25);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("First name:");
		lblNewLabel_6.setForeground(Color.MAGENTA);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 47, 92, 29);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Last name:");
		lblNewLabel_7.setForeground(Color.MAGENTA);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(10, 87, 92, 25);
		panel_2.add(lblNewLabel_7);

		textField = new JTextField();
		textField.setBounds(123, 15, 103, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getUsersFnameLname();
			}
		});
		btnNewButton_1.setBounds(248, 14, 89, 23);
		panel_2.add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.setBounds(123, 53, 137, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(123, 91, 137, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

	}

	public void generatingFlightTable() {
		String depature = comboBox.getSelectedItem().toString().trim();
		String destination = comboBox_1.getSelectedItem().toString().trim();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			ps = con.prepareStatement("select * from flight where departure = ? and destination = ?");
			ps.setString(1, depature);
			ps.setString(2, destination);
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount;
			columnCount = rsmd.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {
				Vector vec = new Vector();
				for (int i = 1; i < columnCount; i++) {
					vec.add(rs.getString("id"));
					vec.add(rs.getString("flightname"));
					vec.add(rs.getString("departure"));
					vec.add(rs.getString("destination"));
					vec.add(rs.getString("date"));
					vec.add(rs.getString("arrivaltime"));
					vec.add(rs.getString("departuretime"));
					vec.add(rs.getString("price"));
				}
				df.addRow(vec);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getUsersFnameLname() {
		String id = textField.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			ps = con.prepareStatement("select * from customer where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next() == false) {
				JOptionPane.showMessageDialog(this, "record not found");
			} else {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				textField_1.setText(firstName);
				textField_2.setText(lastName);
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
