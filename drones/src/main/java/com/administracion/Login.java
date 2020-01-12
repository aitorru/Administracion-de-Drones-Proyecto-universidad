package com.administracion;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class Login extends javax.swing.JFrame {

	private static final long serialVersionUID = 8155885392826282088L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	int xx, yy;

	public Login() {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		try {
			initComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents() throws IOException {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 438);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 382, 431);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				yy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Login.this.setLocation(x - xx, y - yy);
			}
		});
		label.setBounds(-135, 0, 517, 269);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("paisaje.jpg");
		BufferedImage image = ImageIO.read(is);
		label.setIcon(new ImageIcon(image));
		panel.add(label);
		
		JLabel lblNewLabel_4 = new JLabel("Administracion de drones");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(63, 295, 260, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblnete = new JLabel("...A la vanguardia a través de la tecnología...");
		lblnete.setHorizontalAlignment(SwingConstants.CENTER);
		lblnete.setForeground(Color.WHITE);
		lblnete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblnete.setBounds(63, 337, 260, 16);
		panel.add(lblnete);
		
		Button button = new Button("Acceder");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(220, 20, 60));
		button.setBounds(413, 330, 300, 34);
		contentPane.add(button);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		textField = new JTextField();
		textField.setBounds(414, 47, 299, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USUARIO");
		lblNewLabel.setBounds(414, 29, 72, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("EMAIL");
		lblNewLabel_1.setBounds(413, 94, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(414, 117, 299, 34);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(413, 193, 300, 34);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASÑA");
		lblNewLabel_2.setBounds(413, 174, 90, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("REPETIR CONTRASEÑA");
		lblNewLabel_3.setBounds(413, 250, 145, 16);
		contentPane.add(lblNewLabel_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(414, 269, 299, 34);
		contentPane.add(passwordField_1);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				System.exit(0);
				
			}
		});
		lbl_close.setForeground(new Color(255, 0, 0));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_close.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_close.setBounds(684, 0, 56, 16);
		contentPane.add(lbl_close);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		String usuario = textField_1.getText();
		char[] paswdCH = passwordField.getPassword();
		String paswd = "";
		for (int i = 0; i < paswdCH.length; i++){
			paswd = paswd + paswdCH[i];
		}
		UserDB u = new UserDB();
		Crypto c = new Crypto();

		if (usuario.isEmpty() || paswd.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algun campo esta vacio");

		} else {
			ArrayList<HashMap<String, String>> listaUsuarios = u.leerBD();
			for (int i = 0; i < listaUsuarios.size(); i++) {
				HashMap<String, String> temp = listaUsuarios.get(i);
				if (temp.get("user").equals(usuario)) {
					//Creado Login con base de datos.
					if (temp.get("password").equals(c.StringToCrypto(paswd))) {
						// temp.get("idUsuario"); // Recogida de id de usuario para muestra especifia de drones
						PanelControl.userOnBoard = temp.get("idUsuario");
						JOptionPane.showMessageDialog(null, "Bienvenido");
						PanelControl pc = new PanelControl();
						pc.setVisible(true);
						this.dispose();
					} else {
						JOptionPane.showConfirmDialog(null, "Su usuario o contraseña es incorrecta");
					}
				}
			}
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
				try {
					Login frame = new Login();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}
