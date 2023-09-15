package mainPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TicketReport extends JInternalFrame {
	private JTable table;

	Connection con;
	PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketReport frame = new TicketReport();
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
	public TicketReport() {
		setBounds(0, 0, 900, 700);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 864, 526);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(363, 482, 132, 33);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 63, 790, 295);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Ticket ID", "Flight ID", "Customer ID", "Class", "Price", "Seats", "Date" }));
		
		loadData();

	}

	public void loadData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "");
			
			ps = con.prepareStatement("select * from ticket");
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
					vec.add(rs.getString("flightid"));
					vec.add(rs.getString("custid"));
					vec.add(rs.getString("class"));
					vec.add(rs.getString("price"));
					vec.add(rs.getString("seats"));
					vec.add(rs.getString("date"));
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
}
