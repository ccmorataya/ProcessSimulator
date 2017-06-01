/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author ba
 */
public class ProcessSimulator {
	static ArrayList<String> processArray = new ArrayList<>();
	static ArrayList<String> burstTimeArray = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	GridLayout gdLayout = new GridLayout(4, 3, 10, 10);
	    
	String[] algorithmsString = { "RR", "FCFS", "SJF"};
	JComboBox algorithmsList = new JComboBox(algorithmsString);
	
	JTextField txtProcess = new JTextField("Proceso:");
	JTextField txtBurstTime = new JTextField("Burst Time:");
	JButton btnAddProcess = new JButton("Agregar");
	
	JTextField txtQuantum = new JTextField("Quantum:");
	JButton btnProcess = new JButton("Procesar");
	
	JFrame frame = new JFrame("My frame");
	frame.setDefaultCloseOperation(3);
	frame.setLayout(gdLayout);
	frame.add(new JLabel(""));
	frame.add(algorithmsList);
	frame.add(new JLabel(""));
	frame.add(txtProcess);
	frame.add(txtBurstTime);
	frame.add(btnAddProcess);
	frame.add(new JLabel(""));
	frame.add(txtQuantum);
	frame.add(new JLabel(""));
	frame.add(new JLabel(""));
	frame.add(btnProcess);
	frame.setSize(800, 600);
	frame.setVisible(true);
	
	btnAddProcess.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			processArray.add(txtProcess.getText());
			burstTimeArray.add(txtBurstTime.getText());
			txtProcess.setText("");
			txtBurstTime.setText("");
			System.out.println(processArray + " --- " + burstTimeArray);
		}
	});
    }
    
}
