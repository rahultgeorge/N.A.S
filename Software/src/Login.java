import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnClear;
    private GridBagConstraints cs;
    private JPanel outerPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					   window.setSize(300,100);
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
		frame.setSize(600, 170);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		outerPanel = new JPanel(new GridLayout(2,1));
		inputPanel = new JPanel(new GridLayout(2,3));
		buttonPanel = new JPanel(new GridBagLayout());
		outerPanel.add(inputPanel);
		outerPanel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
		outerPanel.setBorder(new TitledBorder("LOGIN"));;
		
		cs = new GridBagConstraints();		
		lbUsername = new JLabel("Username : ");
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 0;
		inputPanel.add(lbUsername, cs);
		
		cs = new GridBagConstraints();		
		tfUsername = new JTextField();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 1;
		cs.gridy = 0;
		inputPanel.add(tfUsername, cs);
		
		cs = new GridBagConstraints();		
		lbPassword = new JLabel("Password : ");
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 1;
		inputPanel.add(lbPassword, cs);
		
		cs = new GridBagConstraints();		
		pfPassword = new JPasswordField();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 1;
		cs.gridy = 1;
		inputPanel.add(pfPassword, cs);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                LoginButton();
            }
        });
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 0;
		cs.insets = new Insets(0,0,0,10);
		buttonPanel.add(btnLogin, cs);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {			 
            public void actionPerformed(ActionEvent e) {
                tfUsername.setText("");
                pfPassword.setText("");
            }
        });
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 1;
		cs.gridy = 0;	
		cs.insets = new Insets(0,10,0,0);	
		buttonPanel.add(btnClear, cs);
		
		frame.setContentPane(outerPanel);		
	}
	
	private void LoginButton(){
		if(tfUsername.getText().isEmpty()||pfPassword.getText().isEmpty()){
			JOptionPane.showMessageDialog(Login.this, "Missing Username or(and) Password");
			return;
		}
		if(!(tfUsername.getText().equals("admin"))||!(pfPassword.getText().equals("admin"))){
			JOptionPane.showMessageDialog(Login.this, "Wrong Username or(and) Password");
			return;
		}		
		Backup.main(null);
		frame.setVisible(false);
	}
}
