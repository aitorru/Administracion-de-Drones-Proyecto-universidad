package com.administracion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import mdlaf.MaterialLookAndFeel;

public class PanelControl extends javax.swing.JFrame {

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
	private DefaultListModel modelo;
	private JList lista;
	private JButton botonEliminar;

	
	public PanelControl() {
		try {
			UIManager.setLookAndFeel (new MaterialLookAndFeel ());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}
		initComponents();
	}

	private void initComponents() {

		Container cp = this.getContentPane();
		// cp.setLayout(new GridLayout(8,2)); 
		cp.setLayout(new BorderLayout());
		
		JMenuBar barra = new JMenuBar();
		this.setJMenuBar(barra);
		
		JMenu menuProyecto = new JMenu("Proyecto");
		JMenu menuAyuda = new JMenu("Ayuda");
		
		barra.add(menuProyecto);
		barra.add(menuAyuda);
		
		JMenuItem itemDron = new JMenuItem("Drones");
		
		menuProyecto.add(itemDron);
		
		/*
		itemDron.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
			
		});
		
		*/
		
		JMenuItem itemAyuda = new JMenuItem("Acerca de...");
		menuAyuda.add(itemAyuda);
		
		itemAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				JOptionPane.showMessageDialog(null, "Este programa fue creado por Aitor Ruiz, Sergio Salgado y Jon Ibarreche");		
			}
			
		});
		
		
		labelDesc = new JLabel("DESCRIPCI�N");
		textoDesc = new JTextField(20);
		labelAnyo = new JLabel("A�O:");
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
		labelDron = new JLabel("DRONE");
		comboDron = new JComboBox();
		comboDron.addItem("Dron 1");
		comboDron.addItem("Dron 2");
		comboDron.addItem("Dron 3");
		comboDron.addItem("Dron 4");
		comboDron.addItem("Dron 5");
		botonAnyadir = new JButton ("A�ADIR : ");
		botonEliminar = new JButton ("Eliminar: ");
		modelo = new DefaultListModel();
		lista = new JList(modelo);
		
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(8,2));
		
		JScrollPane abajo = new JScrollPane(lista);
		
		//Panel est�tico
		panelCiudad.add(radioCiudadBil);
		panelCiudad.add(radioCiudadMad);
		panelCiudad.add(radioCiudadBcn);
		panelCiudad.add(radioCiudadVal);
		panelCiudad.add(radioCiudadSev);
		
		
		arriba.add(labelDesc);
		arriba.add(textoDesc);
		arriba.add(labelAnyo);
		arriba.add(textoAnyo);
		arriba.add(labelCiudad);
		arriba.add(panelCiudad);
		arriba.add(labelDron);
		arriba.add(comboDron);
		arriba.add(labelCoordX);
		arriba.add(textoCoordX);
		arriba.add(labelCoordY);
		arriba.add(textoCoordY);
		arriba.add(botonAnyadir);
		arriba.add(botonEliminar);
		
		cp.add(arriba, BorderLayout.NORTH);
		cp.add(abajo,BorderLayout.CENTER);

		//Coger los parametros de la bd
		/*
		botonAnyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Drone nuevo = new Drone();
				
				nuevo.setlabelAnyo(textoAnyo.getText());
				nuevo.setlabelCoordX(textoCoordX.getText());
				nuevo.setlabelCoordY(textoCoordY.getText());
				
				modelo.addElement(nuevo);

			}
		});
		
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] seleccionados = lista.getSelectedValues();
				
				for (Object object : seleccionados) {
					modelo.removeElement(object);
				}

			}
		});
		*/
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Panel de Control");
		this.setSize(700, 400);	// o this.pack();
		this.setVisible(true);
		
	}

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
		

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(PanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PanelControl().setVisible(true);
			}
		});
	}

	private javax.swing.JPanel jPanel1;
}