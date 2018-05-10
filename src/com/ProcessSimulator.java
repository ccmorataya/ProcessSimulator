/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.placeholder.PlaceHolder;

import java.awt.*;
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

		String[] algorithmsString = {"SJF", "PBS", "RR"};
		JComboBox algorithmsList = new JComboBox(algorithmsString);

		JTextField txtQuantum = new JTextField("");
		JButton btnSetQuantum = new JButton("Asignar Quantum");

		JTextField txtProcess = new JTextField("");
		JTextField txtBurstTime = new JTextField("");
		JTextField txtArrivalTime = new JTextField("");
		JButton btnAddProcess = new JButton("Agregar");

		JButton btnProcess = new JButton("Procesar");
		JButton btnReset = new JButton("Resetear");

		JFrame frame = new JFrame("Process simulator");
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		leftPanel.setLayout(new GridBagLayout());

		JTextArea txtArea = new JTextArea("", 6, 20);
		rightPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		// CM: leftPanel
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1.0;
		leftPanel.add(algorithmsList, constraints);
		constraints.weighty = 0.0;

		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(txtQuantum, constraints);

		constraints.gridy = 1;
		constraints.gridx = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(btnSetQuantum, constraints);

		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(txtProcess, constraints);

		constraints.gridy = 2;
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(txtBurstTime, constraints);

		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(txtArrivalTime, constraints);

		constraints.gridy = 2;
		constraints.gridx = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(btnAddProcess, constraints);

		constraints.gridy = 3;
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(btnProcess, constraints);

		constraints.gridy = 3;
		constraints.gridx = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(btnReset, constraints);
		// CM: End of leftPanel

		// CM: rightPanel
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		rightPanel.add(new JScrollPane(txtArea), constraints);
		// CM: End of rightPanel

		// CM: JFrame
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 1.0;
        frame.add(leftPanel, constraints);
        constraints.weighty = 0.0;

		constraints.gridy = 0;
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1.0;
		frame.add(rightPanel, constraints);
		constraints.weighty = 0.0;
		// CM: End of JFrame

		txtQuantum.setEnabled(false);
		btnSetQuantum.setEnabled(false);
		frame.setSize(800, 200);
		frame.setVisible(true);
		txtArea.setVisible(true);

		new PlaceHolder(txtProcess, "Proceso:");
		new PlaceHolder(txtBurstTime, "Ráfaga:");
		new PlaceHolder(txtQuantum, "Quantum:");
		new PlaceHolder(txtArrivalTime, "Tiempo de llegada:");

		algorithmsList.addActionListener(e -> {
			if (algorithmsList.getSelectedItem() != "RR") {
				txtQuantum.setEnabled(false);
				btnSetQuantum.setEnabled(false);
			}
			else{
				txtQuantum.setEnabled(true);
				btnSetQuantum.setEnabled(true);
			}
		});

		btnSetQuantum.addActionListener( (ActionEvent ev) -> {
			System.out.printf("Set quantum\n");
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
					txtArea.setText(null);
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
			if (hmProcess.size() > 0)
				hmProcess.forEach((k, v) -> //System.out.println("Item: " + k + "\tValue:" + v);
						// TODO-CM:: replace txtArea with jTable
					txtArea.append(k + " " + v + "\n")
				);
			else
				JOptionPane.showMessageDialog(null, "No hay datos para procesar");

			algorithmsList.setEnabled(true);
		});

		btnReset.addActionListener( (ActionEvent ev) -> {
		    hmProcess = new HashMap<>();
			System.out.printf("Reset all\n");
		});
	}

}
