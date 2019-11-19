package com.AdministracionDrones;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panel_control extends javax.swing.JFrame {

	private static final long serialVersionUID = -6832354065185389329L;

	private JLabel labelDesc;
	private JTextField textoDesc;
	private JLabel labelAnyo;
	private JTextField textoAnyo;
	private JLabel labelCiudad;
	private JRadioButton radioCiudadBil;
	private JRadioButton radioCiudadMad;
	private JRadioButton radioCiudadBcn;
	private JRadioButton radioCiudadVal;
	private JRadioButton radioCiudadSev;
	private JLabel labelCoordX;
	private JTextField textoCoordX;
	private JLabel labelCoordY;
	private JTextField textoCoordY;
	private JLabel labelDron;
	private JComboBox comboDron;
	private JButton botonAnyadir;
	
	
	public panel_control() {
		initComponents();
	}

	private void initComponents() {

		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(8,2));
		
		labelDesc = new JLabel("DESCRIPCIÓN");
		textoDesc = new JTextField(20);
		labelAnyo = new JLabel("AÑO:");
		textoAnyo = new JTextField(20);
		labelCiudad = new JLabel("CIUDAD:");
		JPanel panelCiudad = new JPanel();
		radioCiudadBil = new JRadioButton("Bilbao");
		radioCiudadMad = new JRadioButton("Madrid");
		radioCiudadBcn = new JRadioButton("Barcelona");
		radioCiudadVal = new JRadioButton("Valencia");
		radioCiudadSev = new JRadioButton("Sevilla");
		ButtonGroup radioCiudad = new ButtonGroup(); // Para que cuando pulse 1, se despulsen los demas
		radioCiudad.add(radioCiudadBil);
		radioCiudad.add(radioCiudadMad);
		radioCiudad.add(radioCiudadBcn);
		radioCiudad.add(radioCiudadVal);
		radioCiudad.add(radioCiudadSev);
		labelCoordX = new JLabel("COORDENADA X");
		textoCoordX = new JTextField(20);
		labelCoordY = new JLabel("COORDENADA Y");
		textoCoordY = new JTextField(20);
		//LabelGenero y comboGenero missing
		botonAnyadir = new JButton ("AÑADIR : ");
		
		//Panel estético
		panelCiudad.add(radioCiudadBil);
		panelCiudad.add(radioCiudadMad);
		panelCiudad.add(radioCiudadBcn);
		panelCiudad.add(radioCiudadVal);
		panelCiudad.add(radioCiudadSev);
		
		
		cp.add(labelDesc);
		cp.add(textoDesc);
		cp.add(labelAnyo);
		cp.add(textoAnyo);
		cp.add(labelCiudad);
		cp.add(panelCiudad);
		cp.add(botonAnyadir);
		


		/*jPanel1 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(102, 102, 102));
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de control",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial Narrow", 0, 24), new java.awt.Color(0, 153, 204))); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 388, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 262, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		*/
		});
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Panel");
		this.setSize(700, 300);	// o this.pack();
		this.setVisible(true);
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(panel_control.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(panel_control.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(panel_control.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(panel_control.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new panel_control().setVisible(true);
			}
		});
	}

	private javax.swing.JPanel jPanel1;
}