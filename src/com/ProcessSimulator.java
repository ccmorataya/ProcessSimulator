/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.placeholder.PlaceHolder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

/** vaComplete-Imports-Add)*
 * @author ccmorataya
 */
public class ProcessSimulator {
    private static HashMap<String, Integer> hmProcess = new HashMap<>();
    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

		GridLayout gdLayout = new GridLayout(4, 3, 10, 10);

		String[] algorithmsString = {"SJF", "PBS", "RR"};
		JComboBox algorithmsList = new JComboBox(algorithmsString);

		JTextField txtProcess = new JTextField("");
		JTextField txtBurstTime = new JTextField("");
		JButton btnAddProcess = new JButton("Agregar");

		JTextField txtQuantum = new JTextField("");
		JButton btnProcess = new JButton("Procesar");

		JFrame frame = new JFrame("Process simulator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(gdLayout);
		frame.add(new JLabel(""));
		frame.add(algorithmsList);
		frame.add(new JLabel(""));
		frame.add(txtProcess);
		frame.add(txtBurstTime);
		frame.add(btnAddProcess);
		frame.add(new JLabel(""));
		frame.add(txtQuantum);
		txtQuantum.setVisible(false);
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(btnProcess);
		frame.setSize(800, 600);
		frame.setVisible(true);

		new PlaceHolder(txtProcess, "Proceso:");
		new PlaceHolder(txtBurstTime, "Ráfaga:");
		new PlaceHolder(txtQuantum, "Quantum:");

		algorithmsList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (algorithmsList.getSelectedItem() != "RR")
					txtQuantum.setVisible(false);
				else
					txtQuantum.setVisible(true);
			}
		});

		btnAddProcess.addActionListener((ActionEvent ev) -> {
			if (!"".equals(txtProcess.getText()) && !"".equals(txtBurstTime.getText())) {
				try {
				    algorithmsList.setEnabled(false);
					hmProcess.put(txtProcess.getText(), Integer.parseInt(txtBurstTime.getText()));
					txtProcess.setText("");
					txtBurstTime.setText("");
					hmProcess.forEach((k, v) -> System.out.println("Item: " + k + "\tValue:" + v));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Verifica que los datos del proceso a agregar esten completos");
				}
			} else
				JOptionPane.showMessageDialog(null, "Verifica que los datos del proceso a agregar esten completos");
		});

		btnProcess.addActionListener((ActionEvent ev) -> {
			System.out.printf("Event: %s", ev);
			algorithmsList.setEnabled(true);
		});
	}

}
