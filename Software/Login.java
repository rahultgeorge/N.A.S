
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Login {

	private JFrame frame;
	private GridBagConstraints a_1, a_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowWeights = new double[] { 0.0, 0.0 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		JPanel panel = new JPanel(gbl_panel);
		panel.setBorder(new TitledBorder("Login"));
		JTextField username = new JTextField();
		username.setToolTipText("Enter username");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField pass = new JTextField("");
		pass.setToolTipText("Enter password");
		pass.setHorizontalAlignment(SwingConstants.CENTER);
		JButton log = new JButton("Login");

		GridBagConstraints a = new GridBagConstraints();
		a.gridwidth = 0;
		a.fill = GridBagConstraints.HORIZONTAL;
		a.weighty = 0.1;
		a.gridx = 0;
		a.anchor = GridBagConstraints.PAGE_START;
		a.gridy = 0;
		panel.add(username, a);
		a_1 = new GridBagConstraints();
		a_1.weighty = 0.1;
		a_1.anchor = GridBagConstraints.BASELINE;
		a_1.gridx = 0;
		a_1.gridy = 1;
		a_1.fill = GridBagConstraints.HORIZONTAL;
		panel.add(pass, a_1);
		a_2 = new GridBagConstraints();
		a_2.weighty = 0.1;
		a_2.gridx = 0;
		a_2.gridy = 2;
		a_2.anchor = GridBagConstraints.BASELINE;
		a_2.fill = GridBagConstraints.HORIZONTAL;
		panel.add(log, a_2);
		frame.getContentPane().add(panel);
		frame.setResizable(false);
        frame.setTitle("Upyourcloud");
	}
}
