package BookShop;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class home {

	private JFrame home;
	private JTextField textusername;
	private JLabel lblusername;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
					window.home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     Connection conn = null;
     private JLabel lblNewLabel_1;
	/**
	 * Create the application.
	 */
	public home() {
		initialize();
		conn = dbconnection.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		home = new JFrame();
		home.getContentPane().setBackground(new Color(128, 128, 128));
		home.setBounds(100, 100, 805, 457);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.getContentPane().setLayout(null);
		textusername = new JTextField();
		textusername.setBounds(308, 121, 182, 30);
		home.getContentPane().add(textusername);
		textusername.setColumns(10);
		
		lblusername = new JLabel("Username:");
		lblusername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblusername.setBounds(203, 120, 80, 30);
		home.getContentPane().add(lblusername);
		
		lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(203, 185, 80, 20);
		home.getContentPane().add(lblNewLabel);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                      try {
					
					String query = "Select * from Admin where Username = ? and Password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textusername.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()) {
						count =count + 1;
					}
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						
					    dispose();
						bookshop ad = new bookshop();
						ad.setVisible(true);
						}
					else {
						JOptionPane.showMessageDialog(null, "Username and Password is incorrect");
					}
					
				}catch(Exception e1){
					
				}
			}
		});
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBackground(new Color(0, 139, 139));
		btnlogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnlogin.setBounds(321, 237, 166, 30);
		home.getContentPane().add(btnlogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(308, 176, 182, 30);
		home.getContentPane().add(passwordField);
		
		lblNewLabel_1 = new JLabel("");
		//Image img = new ImageIcon(this.getClass().getResource("/BookShop/a3.png")).getImage();
		//lblNewLabel_1.setIcon(new ImageIcon(img));

		lblNewLabel_1.setBounds(366, 44, 89, 66);
		home.getContentPane().add(lblNewLabel_1);
	}

	protected void dispose() {
		
		
	}

	public void setVisible(boolean b) {
	
		
	}

}
