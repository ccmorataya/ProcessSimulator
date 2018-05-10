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
    private static HashMap<String, ArrayList<Integer>> hmProcess = new HashMap<>();
    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

		GridLayout gdLayout = new GridLayout(4, 4, 10, 10);

		String[] algorithmsString = {"SJF", "PBS", "RR"};
		JComboBox algorithmsList = new JComboBox(algorithmsString);

		JTextField txtProcess = new JTextField("");
		JTextField txtBurstTime = new JTextField("");
		JTextField txtArrivalTime = new JTextField("");
		JButton btnAddProcess = new JButton("Agregar");

		JTextField txtQuantum = new JTextField("");
		JButton btnProcess = new JButton("Procesar");

		JFrame frame = new JFrame("Process simulator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(gdLayout);
		frame.add(new JLabel(""));	// 0,0
		frame.add(algorithmsList);		// 0,1
		frame.add(new JLabel(""));	// 0,2
		frame.add(new JLabel(""));	// 0,3
		frame.add(txtProcess);			// 1,0
		frame.add(txtBurstTime);		// 1,1
		frame.add(txtArrivalTime);		// 1,2
		frame.add(btnAddProcess);		// 1,3
		frame.add(new JLabel(""));	// 2,0
		frame.add(txtQuantum);			// 2,1
		txtQuantum.setVisible(false);
		frame.add(new JLabel(""));	// 2,2
		frame.add(new JLabel(""));	// 2,3
		frame.add(new JLabel(""));	// 3,0
		frame.add(btnProcess);			// 3,1
		frame.setSize(800, 600);
		frame.setVisible(true);

		new PlaceHolder(txtProcess, "Proceso:");
		new PlaceHolder(txtBurstTime, "Ráfaga:");
		new PlaceHolder(txtQuantum, "Quantum:");
		new PlaceHolder(txtArrivalTime, "Tiempo de llegada:");

		algorithmsList.addActionListener(e -> {
			if (algorithmsList.getSelectedItem() != "RR")
				txtQuantum.setVisible(false);
			else
				txtQuantum.setVisible(true);
		});

		btnAddProcess.addActionListener((ActionEvent ev) -> {
			if (!"".equals(txtProcess.getText()) && !"".equals(txtBurstTime.getText()) && !"".equals(txtArrivalTime.getText())) {
				try {
				    algorithmsList.setEnabled(false);
				    ArrayList<Integer> temp = new ArrayList<>();
				    temp.add(Integer.parseInt(txtBurstTime.getText()));
					temp.add(Integer.parseInt(txtArrivalTime.getText()));
					hmProcess.put(txtProcess.getText(), temp);
					txtProcess.setText(null);
					txtBurstTime.setText(null);
					txtArrivalTime.setText(null);
					new PlaceHolder(txtProcess, "Proceso:");
					new PlaceHolder(txtBurstTime, "Ráfaga:");
					new PlaceHolder(txtQuantum, "Quantum:");
					new PlaceHolder(txtArrivalTime, "Tiempo de llegada:");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Verifica que los datos del proceso a agregar esten completos");
				}
			} else
				JOptionPane.showMessageDialog(null, "Verifica que los datos del proceso a agregar esten completos");
		});

		btnProcess.addActionListener((ActionEvent ev) -> {
			hmProcess.forEach((k, v) -> System.out.println("Item: " + k + "\tValue:" + v));
			algorithmsList.setEnabled(true);
		});
	}

}
