package com.administracion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;

public class PanelControl extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private final JPanel panel = new JPanel();
	public static String userOnBoard;

	/**
	 * Create the frame.
	 */
	public PanelControl() {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("Panel de administracion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 750);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton cargarJsoButton = new JButton("Cargar Json");
		menuBar.add(cargarJsoButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		Component horizontalStrut = Box.createHorizontalStrut(450);
		panel_1.add(horizontalStrut);

		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox);

		Component horizontalStrut_1 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_1);

		JLabel ciudadOrignJLabel = new JLabel("Ciudad origen");
		panel_1.add(ciudadOrignJLabel);

		JCheckBox bilbaoBox = new JCheckBox("Bilbao");
		panel_1.add(bilbaoBox);

		JCheckBox madridBox = new JCheckBox("Madrid");
		panel_1.add(madridBox);

		JCheckBox barcelonaBox = new JCheckBox("Barcelona");
		panel_1.add(barcelonaBox);

		JCheckBox valenciaBox = new JCheckBox("Valencia");
		panel_1.add(valenciaBox);

		ButtonGroup origen = new ButtonGroup();
		origen.add(bilbaoBox);
		bilbaoBox.setActionCommand("bilbao");
		origen.add(madridBox);
		madridBox.setActionCommand("madrid");
		origen.add(barcelonaBox);
		barcelonaBox.setActionCommand("barcelona");
		origen.add(valenciaBox);
		valenciaBox.setActionCommand("valencia");

		Component horizontalStrut_5 = Box.createHorizontalStrut(800);
		horizontalStrut_5.setBackground(Color.LIGHT_GRAY);
		horizontalStrut_5.setForeground(Color.LIGHT_GRAY);
		panel_1.add(horizontalStrut_5);

		JLabel destionoLabel = new JLabel("Ciudad destino");
		panel_1.add(destionoLabel);

		JCheckBox bilbaoBox2 = new JCheckBox("Bilbao");
		panel_1.add(bilbaoBox2);

		JCheckBox madridBox2 = new JCheckBox("Madrid");
		panel_1.add(madridBox2);

		JCheckBox barcelonaBox2 = new JCheckBox("Barcelona");
		panel_1.add(barcelonaBox2);

		JCheckBox valenciaBox2 = new JCheckBox("Valencia");
		panel_1.add(valenciaBox2);
		ButtonGroup destino = new ButtonGroup();
		destino.add(bilbaoBox2);
		bilbaoBox2.setActionCommand("bilbao");
		destino.add(madridBox2);
		madridBox2.setActionCommand("madrid");
		destino.add(barcelonaBox2);
		barcelonaBox2.setActionCommand("barcelona");
		destino.add(valenciaBox2);
		valenciaBox2.setActionCommand("valencia");

		Component horizontalStrut_2 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_3);

		JLabel lblNewLabel_4 = new JLabel("Hora salida");
		panel_1.add(lblNewLabel_4);

		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_4);

		JLabel lblNewLabel_5 = new JLabel("Hora llegada");
		panel_1.add(lblNewLabel_5);

		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		Component horizontalStrut_6 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_6);

		JLabel lblNewLabel_7 = new JLabel(" Descripcion*");
		panel_1.add(lblNewLabel_7);

		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		Component horizontalStrut_7 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_7);

		JButton añadirButton = new JButton("Añadir");
		panel_1.add(añadirButton);

		Component horizontalStrut_8 = Box.createHorizontalStrut(100);
		panel_1.add(horizontalStrut_8);

		JButton eliminarButton = new JButton("Eliminar");
		panel_1.add(eliminarButton);

		JButton f5Button = new JButton("Reload");
		panel_1.add(f5Button);

		Component horizontalStrut_9 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_9);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(105);
		flowLayout.setHgap(150);
		panel.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel.setForeground(Color.BLACK);
		panel_1.add(panel);

		JComboBox<String> dronCombo = new JComboBox<String>();
		panel.add(dronCombo);

		cargarJsoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable() {
					@Override
					public void run() {
						ArrayList<HashMap<String, String>> a = new BackEndAdmin().cargarDatosAutomatico();
						for (int i = 0; i < a.size(); i++) {
							HashMap<String, String> ii = a.get(i);
							new BackEndAdmin().guardarBD(ii);
						}
						BackEndAdmin bd = new BackEndAdmin();
						ArrayList<HashMap<String, String>> aa = bd.leerBD();
						System.out.println(aa.toString());
						dronCombo.removeAllItems();
						for (int i = 0; i < aa.size(); i++) {
							HashMap<String, String> m = aa.get(i);
							dronCombo.addItem("dron " + m.get("id") + " " + m.get("cargaDescripcion"));
						}
					}
				}.run();
				;
			}
		});
		añadirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable() {
					@Override
					public void run() {
						HashMap<String, String> m = new HashMap<String, String>();
						m.put("idUsuario", userOnBoard);
						m.put("coordenadasX", "0");
						m.put("coordenadasY", "0");
						CoordenadasDB c = new CoordenadasDB();
						for (int i = 0; i < c.leerBD().size(); i++) {
							HashMap<String, String> hashMapCoordenadas = c.leerBD().get(i);
							String ciudad1 = hashMapCoordenadas.get("ciudad");
							String ciudad2 = origen.getSelection().toString();
							// hashMapCoordenadas.get("ciudadSalida").equals(hashMapJson.get("ciudad"))
							if (ciudad1.equals(ciudad2)) {
								String coordenadaX, coordenadaY;
								coordenadaX = hashMapCoordenadas.get("coordenadasX");
								coordenadaY = hashMapCoordenadas.get("coordenadasY");
								m.remove("coordenadasX");
								m.remove("coordenadasY");
								m.put("coordenadasX", coordenadaX);
								m.put("coordenadasY", coordenadaY);
							}
						}
						m.put("ciudadSalida", origen.getSelection().getActionCommand());
						m.put("ciudadLlegada", destino.getSelection().getActionCommand());
						m.put("horaSalida", textField_2.getText());
						m.put("horaLlegada", textField_3.getText());
						m.put("cargaDescripcion", textField_4.getText());
						new BackEndAdmin().guardarBD(m);
						BackEndAdmin bd = new BackEndAdmin();
						ArrayList<HashMap<String, String>> a = bd.leerBD();
						dronCombo.removeAllItems();
						for (int i = 0; i < a.size(); i++) {
							HashMap<String, String> mm = a.get(i);
							dronCombo.addItem("dron " + mm.get("id") + " " + mm.get("cargaDescripcion"));
						}
						
					}
				}.run();
				;
			}
		});
		eliminarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable() {
					@Override
					public void run() {
						String elegido = String.valueOf(dronCombo.getSelectedItem());
						String[] slice = elegido.split(" ");
						System.out.println( slice[1]);
						new BackEndAdmin().eliminarDatos(Integer.valueOf(slice[1]));
						BackEndAdmin bd = new BackEndAdmin();
						ArrayList<HashMap<String, String>> aa = bd.leerBD();
						System.out.println(aa.toString());
						dronCombo.removeAllItems();
						for (int i = 0; i < aa.size(); i++) {
							HashMap<String, String> m = aa.get(i);
							dronCombo.addItem("dron " + m.get("id") + " " + m.get("cargaDescripcion"));
						}
					}
				}.run();
				;
			}
		});
		f5Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable() {
					@Override
					public void run() {
						BackEndAdmin bd = new BackEndAdmin();
						ArrayList<HashMap<String, String>> aa = bd.leerBD();
						System.out.println(aa.toString());
						dronCombo.removeAllItems();
						for (int i = 0; i < aa.size(); i++) {
							HashMap<String, String> m = aa.get(i);
							dronCombo.addItem("dron " + m.get("id") + " " + m.get("cargaDescripcion"));
						}
					}
				}.run();
				;
			}
		});
/*
		new Thread() {
			@Override
			public void run() {
				BackEndAdmin bd = new BackEndAdmin();
				ArrayList<HashMap<String, String>> a = bd.leerBD();
				dronCombo.removeAllItems();
				for (int i = 0; i < a.size(); i++) {
					HashMap<String, String> m = a.get(i);
					dronCombo.addItem("dron " + m.get("id"))
				}
			}
		};
*/
	}

}