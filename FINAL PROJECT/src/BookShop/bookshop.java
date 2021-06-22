package BookShop;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class bookshop extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookshop frame = new bookshop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     Connection conn = null;
     private JTextField textFieldBname;
     private JTextField textFieldEdition;
     private JTextField textFieldPrice;
     private JTextField textFieldID;
     private JTextField textFieldsearch;
     private JTable table;
     private JTable table_1;
	/**
	 * Create the frame.
	 */
	public bookshop() {
		conn = dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(43, 45, 320, 195);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 60, 95, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 103, 95, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 145, 95, 28);
		panel.add(lblNewLabel_1_2);
		
		textFieldBname = new JTextField();
		textFieldBname.setColumns(10);
		textFieldBname.setBounds(115, 66, 184, 20);
		panel.add(textFieldBname);
		
		textFieldEdition = new JTextField();
		textFieldEdition.setColumns(10);
		textFieldEdition.setBounds(115, 109, 184, 20);
		panel.add(textFieldEdition);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(115, 151, 184, 20);
		panel.add(textFieldPrice);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(115, 26, 184, 20);
		panel.add(textFieldID);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 20, 73, 28);
		panel.add(lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Books values(?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldBname.getText());
					pst.setString(3, textFieldEdition.getText());
					pst.setString(4, textFieldPrice.getText());
					pst.execute();
					 JOptionPane.showMessageDialog(null, "Book uploaded successful");
					pst.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(43, 262, 89, 50);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(btnNewButton);
		
		JButton btnUp = new JButton("Update");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "update Books set BookID ='"+textFieldID.getText() +"', Name='"+textFieldBname.getText()+"', Edition='"+textFieldEdition.getText()+"', Price ='"+textFieldPrice.getText()+"' where BookID ='"+textFieldID.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.execute();
					 JOptionPane.showMessageDialog(null, "Book Updated successful");
					pst.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnUp.setBounds(142, 262, 89, 50);
		btnUp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(btnUp);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldID.setText("");
				textFieldBname.setText("");
				textFieldEdition.setText("");
				textFieldPrice.setText("");
				textFieldBname.requestFocus();
			}
			
		});
		btnNewButton_1_1.setBounds(241, 262, 89, 50);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(26, 354, 333, 52);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 11, 82, 28);
		panel_1.add(lblNewLabel_1_1_1);
		
		textFieldsearch = new JTextField();
		textFieldsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
                    try {
					String query = "Select * from Books Where Name=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldsearch.getText());
					ResultSet rs = pst.executeQuery();
					table_1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
					while(rs.next()) {	
					}
					pst.close();					
				}catch(Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		textFieldsearch.setColumns(10);
		textFieldsearch.setBounds(102, 13, 219, 28);
		panel_1.add(textFieldsearch);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from Books ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(744, 45, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane table1 = new JScrollPane();
		table1.setBounds(373, 44, 362, 259);
		contentPane.add(table1);
		
		table = new JTable();
		table1.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 322, 363, 120);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("MAXWELL BOOKSHOP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(305, 11, 223, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Logout");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?") == 0) {
					home ad = new home();
					ad.setVisible(true);
					bookshop.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_2.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblNewLabel_2.setForeground(Color.BLUE);
			}
		});
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(794, 0, 61, 23);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Delete from Books where BookID ='"+textFieldID.getText() +"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.execute();
					 JOptionPane.showConfirmDialog(null, "Are you sure you want to the Book deleted?");
					pst.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setBounds(744, 91, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
