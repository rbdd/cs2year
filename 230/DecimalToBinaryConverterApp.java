import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * Simple application to convert a decimal number to binary.
 * 
 * The application allows the user to enter a value for N, and then calculates 
 * and displays the resulting binary number in a text area. For large values of N, the calculation may
 * take some time - and so the calculation is performed in a background
 * SwingWorker thread.
 * 
 * This is a minimal SwingWorker app and the SwingWorker isn't cancelable.
 *
 */
@SuppressWarnings("serial")
public class DecimalToBinaryConverterApp extends JPanel {

	private JButton _startBtn;        // Button to start the calculation process.
	private JTextArea _binaryValues;  // Component to display the result.
	
	public DecimalToBinaryConverterApp() {
		// Create the GUI components.
		JLabel lblN = new JLabel("Value N:");
		final JTextField tfN = new JTextField(20);
		
		_startBtn = new JButton("Compute");
		_binaryValues = new JTextArea();
		_binaryValues.setEditable(false);
		
		// Add an ActionListener to the start button. When clicked, the 
		// button's handler extracts the value for N entered by the user from 
		// the textfield and creates a new instance of ConversionWorker
		// (a SwingWorker) to convert to binary bits.
		_startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String strN = tfN.getText().trim();
				long n = 0;
				
				try {
					n = Long.parseLong(strN);
				} catch(NumberFormatException e) {
					System.out.println(e);
				}

				// Disable the Start button until the result of the calculation is known.
				_startBtn.setEnabled(false);
				
				_binaryValues.setText(null);
				
				// Create and execute a SwingWorker to perform the calculation in the background.
				SwingWorker<List<Integer>,Void> worker = new ConverterWorker(n);
				worker.execute();
				
			}
		});
		
		// Construct the GUI. 
		JPanel controlPanel = new JPanel();
		controlPanel.add(lblN);
		controlPanel.add(tfN);
		controlPanel.add(_startBtn);
		
		JScrollPane scrollPaneForOutput = new JScrollPane();
		scrollPaneForOutput.setViewportView(_binaryValues);
		
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.NORTH);
		add(scrollPaneForOutput, BorderLayout.CENTER);
		setPreferredSize(new Dimension(500,300));
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Convert Decimal N to Binary");

		// Create and set up the content pane.
		JComponent newContentPane = new DecimalToBinaryConverterApp();
		frame.add(newContentPane);

		// Display the window.
		frame.pack();
        frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/**
	 * An inner class of Subclass of SwingWorker to convert 
	 * decimal to binary and update the GUI to display the computed binary number in the
	 * format b b b, e.g. decimal number of 20 is shown in the text area as: 1  0  1  0  0
	 */
    private class ConverterWorker extends SwingWorker<List<Integer>, Void> {
        private long _n;
        public ConverterWorker(long valueOfN) {
            _n = valueOfN;
        }    
        @Override
        protected List<Integer> doInBackground() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            while (_n > 0) {
                result.add(0, (int)_n % 2);
                _n /= 2;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }		
        @Override
        protected void done() {
            try{
                List<Integer> result = get();
                String s = "";
                for (Integer i: result){s += i + "  ";}
                _binaryValues.setText(s.substring(0, s.length()-2));
            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}
