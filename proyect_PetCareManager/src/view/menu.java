package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        JMenuBar menuBar = new JMenuBar();
	        menuBar.setBounds(22, 23, 442, 58);
	        contentPane.add(menuBar);
	        
	        JMenu mn_agen = new JMenu("Agendar");
	        menuBar.add(mn_agen);
	        
	        JMenu mn_cita = new JMenu("Citas");
	        menuBar.add(mn_cita);
	        
	        JMenu mn_history = new JMenu("Historial");
	        menuBar.add(mn_history);
	        
	        JMenu mn_vacunas = new JMenu("Actualizar Vacunas");
	        menuBar.add(mn_vacunas);
	        
	        JMenu mn_revisar = new JMenu("Revisar Anteriores Atenciones");
	        menuBar.add(mn_revisar);

	}
}
