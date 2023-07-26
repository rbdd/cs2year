import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/*
 * Simple application to calculate the factorial of a given number N.
 *
 * The application allows the user to enter a value for N, and then calculates
 * and displays the factorial. For large values of N, the calculation may
 * take some time - and so the calculation is performed in a background
 * SwingWorker thread.
 *
 * This is a minimal SwingWorker app. It doesn't report intermediate results
 * and the SwingWorker isn't cancelable.
 *
 *
 */
@SuppressWarnings("serial")
public class FactorialSwingApp extends JPanel {

	private JButton _startBtn;        // Button to start the calculation process.
	private JTextField _factorialResult;  // Component to display the result.

	public FactorialSwingApp() {
		// Create the GUI components.
		JLabel lblN = new JLabel("Value N:");
		final JTextField tfN = new JTextField(20);

		_startBtn = new JButton("Compute");
		_factorialResult = new JTextField();
		_factorialResult.setEditable(false);

		// Adds an ActionListener to the start button. When clicked, the
		// button's handler extracts the value for N entered by the user from
		// the textfield and creates a new instance of FactorialWorker
		// (a SwingWorker) to find N's factorial.
		_startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String strN = tfN.getText().trim();
				int n = 0;
				n = Integer.parseInt(strN);
				// Disable the Start button until the result of the calculation is known.
				_startBtn.setEnabled(false);

				_factorialResult.setText(null);

				// Create and execute a SwingWorker to perform the calculation in the background.
				SwingWorker<Long,Void> worker = new FactorialWorker(n);
				worker.execute();
				_startBtn.setEnabled(true);
			}
		});

		// Construct the GUI.
		JPanel controlPanel = new JPanel();
		controlPanel.add(lblN);
		controlPanel.add(tfN);
		controlPanel.add(_startBtn);

		JScrollPane scrollPaneForOutput = new JScrollPane();
		scrollPaneForOutput.setViewportView(_factorialResult);

		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.NORTH);
		add(scrollPaneForOutput, BorderLayout.CENTER);
		setPreferredSize(new Dimension(500,300));
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Prime Factorisation of N");

		// Create and set up the content pane.
		JComponent newContentPane = new FactorialSwingApp();
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
	 * Complete this class as per question requirements.
	 *
	 * Subclass of SwingWorker to calculate the factorial of a number.
	 *
	 * FactorialWorker overrides doInBackground() and done() to
	 * calculate the factorial and update the GUI to display
	 * the computed result, respectively.
	 *
	 */
	private class FactorialWorker extends SwingWorker<Long, Void> {
		private int _n;

		public FactorialWorker(int valueOfN) {
			_n = valueOfN;
		}

		/**
		 * Method that is executed by Swing using a background thread. This
		 * method does the work of calculating the factorial result.
		 */
		@Override
		protected Long doInBackground() {
			 //ADD CODE
             long t = 1;
             for (int i = _n; i > 0; i--){
                t *= i;
             }
             return t;
		}

		/**
		 * Method that Swing arranges to be executed on the Event Dispatch
		 * thread when the FactorialWorker object has completed its
		 * processing. This method updates the app's text-field component to
		 * display the computed factorial value
		 */
		@Override
		protected void done() {
			//ADD CODE
            try{long result = get();
                _factorialResult.setText(String.valueOf(result));
            }catch (Exception e){}
            
                
	}
}
}