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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private final JPanel panel = new JPanel();

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

		JLabel lblNewLabel = new JLabel("Descripcion");
		panel_1.add(lblNewLabel);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(450);
		panel_1.add(horizontalStrut);

		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox);

		JLabel lblNewLabel_1 = new JLabel("A\u00F1o");
		panel_1.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_1);

		JLabel lblNewLabel_2 = new JLabel("Ciudad origen");
		panel_1.add(lblNewLabel_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Bilbao");
		panel_1.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Madrid");
		panel_1.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Barcelona");
		panel_1.add(chckbxNewCheckBox_2);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Valencia");
		panel_1.add(chckbxNewCheckBox_3);

		Component horizontalStrut_5 = Box.createHorizontalStrut(800);
		horizontalStrut_5.setBackground(Color.LIGHT_GRAY);
		horizontalStrut_5.setForeground(Color.LIGHT_GRAY);
		panel_1.add(horizontalStrut_5);

		JLabel lblNewLabel_6 = new JLabel("Ciudad destino");
		panel_1.add(lblNewLabel_6);

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Bilbao");
		panel_1.add(chckbxNewCheckBox_4);

		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Madrid");
		panel_1.add(chckbxNewCheckBox_5);

		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Barcelona");
		panel_1.add(chckbxNewCheckBox_6);

		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Valencia");
		panel_1.add(chckbxNewCheckBox_7);

		Component horizontalStrut_2 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_2);

		JLabel lblNewLabel_3 = new JLabel("Dron");
		panel_1.add(lblNewLabel_3);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Dron 1");
		comboBox.addItem("Dron 2");
		comboBox.addItem("Dron 3");
		comboBox.addItem("Dron 4");
		comboBox.addItem("Dron 5");
		comboBox.addItem("Dron 6");

		panel_1.add(comboBox);

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

		JButton btnNewButton_2 = new JButton("Eliminar");
		panel_1.add(btnNewButton_2);

		Component horizontalStrut_9 = Box.createHorizontalStrut(800);
		panel_1.add(horizontalStrut_9);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(105);
		flowLayout.setHgap(150);
		panel.setBorder(new LineBorder(Color.BLACK, 2, true));
		panel.setForeground(Color.BLACK);
		panel_1.add(panel);

		JList list = new JList();
		list.setVisibleRowCount(10);
		panel.add(list);

		cargarJsoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable(){
					@Override
					public void run() {
						ArrayList<HashMap<String, String>> a = new BackEndAdmin().cargarDatosAutomatico();
						for (int i = 0; i < a.size(); i++) {
							HashMap<String, String> ii = a.get(i);
							new BackEndAdmin().guardarBD(ii);
						}
						
						
					}
				}.run();;
			}		
		});
		añadirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Runnable(){
					@Override
					public void run() {
						
					}
				}.run();;
			}		
		});
	}

}