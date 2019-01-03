import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	int low = 2000;
	int high = 3000;
	int result = 0;
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
		
		JButton btnNewButton = new JButton("Start");
		//AbstractAction buttonpressed = new AbstractAction() {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start = true;
				//System.out.println("Clicking has started with a value of: " + Start);
				//MouseClickThread thread = new MouseClickThread();
				//thread.start();
				t = new Thread() {
					public void run() {
						while (Start) {
							result = random.nextInt(high - low) + low;
							robot.mousePress(InputEvent.BUTTON1_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_MASK);
							robot.delay(result);
							System.out.println(result);
							//System.out.println(Start);
							//count++;
						}
					}
				};
				t.start();
			}
		});
		
		//btnNewButton.addActionListener(buttonpressed);
		btnNewButton.setBounds(10, 90, 90, 23);
		frmMouseclicker.getContentPane().add(btnNewButton);
		
		//btnNewButton.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
        //put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1,0), "F1_pressed");

		//btnNewButton.getActionMap().put("F1_pressed", buttonpressed);
		
		JButton btnStop = new JButton("Stop");
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start = false;
				//System.out.println(Start);
				t.stop();
				//System.out.println("Clicking has terminated with a value of: " + Start);
			}
		});
		
		btnStop.setBounds(117, 90, 90, 23);
		frmMouseclicker.getContentPane().add(btnStop);
		
		JLabel lblMinimumInterval = new JLabel("Min Interval (Seconds)");
		lblMinimumInterval.setBounds(10, 24, 137, 14);
		frmMouseclicker.getContentPane().add(lblMinimumInterval);
		
		//amountFormat = NumberFormat.getNumberInstance();
		textField = new JTextField();
		textField.setBounds(157, 21, 21, 20);
		frmMouseclicker.getContentPane().add(textField);
		textField.setColumns(1);
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
	                low = Integer.parseInt(textField.getText()) * 1000;
	            } 
				
				catch (Exception e) {
	                
	            	if (textField.getText().trim().equals("")) {
	                	//JOptionPane.showMessageDialog(frmMouseclicker, "Blank");
	                }
	                else {
	                	JOptionPane.showMessageDialog(frmMouseclicker, "Only Numbers Allowed");
	                	textField.setText("");
	                }
	                //textField.setText("");
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
	                high = Integer.parseInt(textField_1.getText()) * 1000;
	            } 
				
				catch (Exception e) {
	                
	            	if (textField_1.getText().trim().equals("")) {
	                	//JOptionPane.showMessageDialog(frmMouseclicker, "Blank");
	                }
	                else {
	                	JOptionPane.showMessageDialog(frmMouseclicker, "Only Numbers Allowed");
	                	textField_1.setText("");
	                }
	                //textField.setText("");
	            }
			}
		});
		
	}
}
 