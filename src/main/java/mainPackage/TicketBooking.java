package mainPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class TicketBooking extends JInternalFrame {
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_14_1;
	private JLabel lblNewLabel_14_2;
	private JLabel lblNewLabel_14_4;
	private JLabel lblNewLabel_4;

	Connection con;
	PreparedStatement ps;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_18;
	private JLabel lblNewLabel_16;
	private JSpinner spinner;
	private JComboBox comboBox_2;

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
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillTable();
			}
		});
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

		lblNewLabel_4 = new JLabel("");
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

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		panel_3.setBounds(10, 374, 574, 319);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Flight number:");
		lblNewLabel_8.setForeground(Color.MAGENTA);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(10, 11, 112, 31);
		panel_3.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Flight name:");
		lblNewLabel_9.setForeground(Color.MAGENTA);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(10, 53, 112, 35);
		panel_3.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Depart time");
		lblNewLabel_10.setForeground(Color.MAGENTA);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(10, 99, 112, 36);
		panel_3.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Class:");
		lblNewLabel_11.setForeground(Color.MAGENTA);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_11.setBounds(10, 146, 112, 30);
		panel_3.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Price:");
		lblNewLabel_12.setForeground(Color.MAGENTA);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_12.setBounds(10, 187, 112, 31);
		panel_3.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Seats:");
		lblNewLabel_13.setForeground(Color.MAGENTA);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13.setBounds(10, 229, 112, 31);
		panel_3.add(lblNewLabel_13);

		lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setForeground(Color.BLUE);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14.setBounds(146, 11, 174, 31);
		panel_3.add(lblNewLabel_14);

		lblNewLabel_14_1 = new JLabel("");
		lblNewLabel_14_1.setForeground(Color.BLUE);
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14_1.setBounds(146, 53, 174, 31);
		panel_3.add(lblNewLabel_14_1);

		lblNewLabel_14_2 = new JLabel("");
		lblNewLabel_14_2.setForeground(Color.BLUE);
		lblNewLabel_14_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14_2.setBounds(146, 99, 174, 31);
		panel_3.add(lblNewLabel_14_2);

		lblNewLabel_14_4 = new JLabel("");
		lblNewLabel_14_4.setForeground(Color.BLUE);
		lblNewLabel_14_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14_4.setBounds(146, 187, 174, 31);
		panel_3.add(lblNewLabel_14_4);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "Economy", "Business", "First" }));
		comboBox_2.setBounds(146, 152, 112, 22);
		panel_3.add(comboBox_2);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calculation();
			}
		});
		spinner.setBounds(146, 236, 59, 24);
		panel_3.add(spinner);

		JLabel lblNewLabel_15 = new JLabel("Date:");
		lblNewLabel_15.setForeground(Color.MAGENTA);
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_15.setBounds(309, 11, 78, 31);
		panel_3.add(lblNewLabel_15);

		lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setForeground(Color.BLUE);
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_16.setBounds(381, 11, 92, 31);
		panel_3.add(lblNewLabel_16);

		JButton btnNewButton_2 = new JButton("Book");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateFlightId();
				bookButton();
			}
		});
		btnNewButton_2.setBounds(298, 229, 104, 41);
		panel_3.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Cancel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(414, 229, 104, 41);
		panel_3.add(btnNewButton_2_1);

		JLabel lblNewLabel_17 = new JLabel("Overall price:");
		lblNewLabel_17.setForeground(Color.MAGENTA);
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_17.setBounds(309, 91, 112, 39);
		panel_3.add(lblNewLabel_17);

		lblNewLabel_18 = new JLabel("0");
		lblNewLabel_18.setForeground(Color.BLUE);
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_18.setBounds(362, 140, 134, 47);
		panel_3.add(lblNewLabel_18);

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

	public void fillTable() {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		int selectedRow = table.getSelectedRow();
		lblNewLabel_14.setText(df.getValueAt(selectedRow, 0).toString());
		lblNewLabel_14_1.setText(df.getValueAt(selectedRow, 1).toString());
		lblNewLabel_14_2.setText(df.getValueAt(selectedRow, 6).toString());
		lblNewLabel_14_4.setText(df.getValueAt(selectedRow, 7).toString());
		lblNewLabel_16.setText(df.getValueAt(selectedRow, 4).toString());
	}

	public void generateFlightId() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select MAX(id) from ticket");
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				lblNewLabel_4.setText("TID001");
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(3, rs.getString("MAX(id)").length()));
				id++;
				lblNewLabel_4.setText("TID" + String.format("%03d", id));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void calculation() {
		int price = Integer.parseInt(lblNewLabel_14_4.getText());
		int quantity = Integer.parseInt(spinner.getValue().toString());
		int total = price * quantity;

		lblNewLabel_18.setText(String.valueOf(total));
	}

	public void bookButton() {
		String ticketId = lblNewLabel_4.getText();
		String flightId = lblNewLabel_14.getText();
		String customerId = textField.getText();
		String avioClass = comboBox_2.getSelectedItem().toString().trim();
		String price = lblNewLabel_18.getText();
		String seats = spinner.getValue().toString();
		String date = lblNewLabel_16.getText();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			ps = con.prepareStatement(
					"insert into ticket (id,flightid,custid,class,price,seats,date)values(?,?,?,?,?,?,?)");
			ps.setString(1, ticketId);
			ps.setString(2, flightId);
			ps.setString(3, customerId);
			ps.setString(4, avioClass);
			ps.setString(5, price);
			ps.setString(6, seats);
			ps.setString(7, date);
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Ticked booked ! ");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
