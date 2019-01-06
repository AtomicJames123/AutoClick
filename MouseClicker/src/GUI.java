import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GUI {

	private JFrame frmMouseclicker;
	static boolean Start = false;
	int count = 0;
	Random random = new Random();
	double low = 2.0;
	double high = 3.0;
	double result = 0.0;
	Thread t;
	Robot robot;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMouseclicker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMouseclicker = new JFrame();
		frmMouseclicker.setTitle("AutoClick");
		frmMouseclicker.setResizable(false);
		frmMouseclicker.setBounds(100, 100, 235, 164);
		frmMouseclicker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMouseclicker.getContentPane().setLayout(null);

		
		try {
			robot = new Robot();
		}
		
		catch(Exception e) {
			
		}
		
		JButton btnNewButton = new JButton("Start (F1)");
		AbstractAction buttonAction = new AbstractAction("Start (F1)") {
		//btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (textField.getText().equals("") && textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmMouseclicker, "Minimum value will be set to default value of 2 seconds and Maximum value will be set default value of 3 seconds.");
					low = 2.0;
					high = 3.0;
					Start = true;
				}
				
				else if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmMouseclicker, "Minimum value will be set to default value of 2 seconds.");
					low = 2.0;
					Start = true;
				}
				
				else if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(frmMouseclicker, "Maximum value will be set to default value of 3 seconds.");
					high = 3.0;
					Start = true;
				}
				
				else if (low > high) {
					JOptionPane.showMessageDialog(frmMouseclicker, "Minimum value cannot be greater then the Maximum value.");
					textField.setText("");
					textField_1.setText("");
					Start = false;
				}
				
				else {
					Start = true;
				}
				
				t = new Thread() {
					public void run() {
						while (Start) {
							result = low + (high - low) * random.nextDouble();
							result = result * 1000.0;
							robot.mousePress(InputEvent.BUTTON1_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_MASK);
							robot.delay((int) result);
						}
					}
				};
				t.start();
			}
		};
		
		btnNewButton.setAction(buttonAction);
		
		btnNewButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Start (F1)");
		btnNewButton.getActionMap().put("Start (F1)", buttonAction);
		
		btnNewButton.setBounds(10, 90, 90, 23);
		frmMouseclicker.getContentPane().add(btnNewButton);
		
		
		JButton btnStop = new JButton("Stop (F2)");
		
		//btnStop.addActionListener(new ActionListener() {
		buttonAction = new AbstractAction("Stop (F2)") {
			public void actionPerformed(ActionEvent e) {
				Start = false;
			}
		};
		 
		btnStop.setAction(buttonAction);
		 
		btnStop.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		        KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "Stop (F2)");
		 
		btnStop.getActionMap().put("Stop (F2)", buttonAction);
		
		btnStop.setBounds(117, 90, 90, 23);
		frmMouseclicker.getContentPane().add(btnStop);
		
		JLabel lblMinimumInterval = new JLabel("Min Interval (Seconds)");
		lblMinimumInterval.setBounds(10, 24, 137, 14);
		frmMouseclicker.getContentPane().add(lblMinimumInterval);
		
		textField = new JTextField();
		textField.setBounds(157, 21, 21, 20);
		frmMouseclicker.getContentPane().add(textField);
		textField.setColumns(1);
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
	                low = Double.parseDouble(textField.getText());
	            } 
                catch (Exception e) {
	            	if (textField.getText().trim().equals("")) {
	                }
	            	
	                else {
	                	JOptionPane.showMessageDialog(frmMouseclicker, "Only Numbers Allowed");
	                	textField.setText("");
	                }
                }
			}
		});
		
		JLabel lblMaximumInterval = new JLabel("Max Interval (Seconds)");
		lblMaximumInterval.setBounds(10, 49, 137, 14);
		frmMouseclicker.getContentPane().add(lblMaximumInterval);
		
		textField_1 = new JTextField();
		textField_1.setColumns(1);
		textField_1.setBounds(157, 46, 21, 20);
		frmMouseclicker.getContentPane().add(textField_1);
		
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
	                high = Double.parseDouble(textField_1.getText());
	            } 
				
				catch (Exception e) {
	                
	            	if (textField_1.getText().trim().equals("")) {
	                }
	                else {
	                	JOptionPane.showMessageDialog(frmMouseclicker, "Only Numbers Allowed");
	                	textField_1.setText("");
	                }
	            }
			}
		});
		
	}
}
 