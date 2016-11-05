import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Backup {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JPanel outerPanel;
	private JPanel filePanel;
	private JPanel buttonPanel;
	private JLabel label;
	private JTextField tfFile;
	private JButton browse;
	private GridBagConstraints cs;
	private JButton backUp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Backup window = new Backup();
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
	public Backup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 370);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		menuBar = new JMenuBar();		
		menu = new JMenu("Options");
		menuBar.add(menu);
		menuItem = new JMenu("Back Up");
		menu.add(menuItem);
		menuItem = new JMenu("Retreive");
		menu.add(menuItem);
		menuItem = new JMenu("Log Out");
		menu.add(menuItem);
		menuItem = new JMenu("Exit");
		menu.add(menuItem);
		
		frame.setJMenuBar(menuBar);
		
		outerPanel = new JPanel(new GridBagLayout());
		filePanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel(new GridBagLayout());
		frame.setContentPane(outerPanel);

		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 0;
		outerPanel.add(filePanel, cs);
		
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 1;

		cs.insets = new Insets(40,0,0,0);
		outerPanel.add(buttonPanel, cs);
		
		label = new JLabel("Select file : ");
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 0;
		cs.gridy = 0;
		filePanel.add(label, cs);
		
		tfFile = new JTextField(20);
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 1;
		cs.gridy = 0;
		filePanel.add(tfFile, cs);
		
		browse = new JButton("Browse");
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = 2;
		cs.insets = new Insets(0,10,0,0);
		cs.gridy = 0;
		filePanel.add(browse, cs);	
		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setAcceptAllFileFilterUsed(false);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		          tfFile.setText(fileChooser.getSelectedFile().toString());
		        }
			}			
		});	
		
		backUp = new JButton("Back Up");
		backUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(tfFile.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame, "Please select a file");
				}
				else{
					BackUp(tfFile.getText());
				}
			}			
		});
		
		buttonPanel.add(backUp);
	}

	public void BackUp(String file){
		Client.c = 1;
		Client.fileString = file;
		try {
			Client.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
