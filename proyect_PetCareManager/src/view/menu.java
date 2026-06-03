package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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
		 JButton btnMascotas = new JButton("Registar");
		 btnMascotas.setBounds(10, 10, 73, 23);
	        JButton btnPropietarios = new JButton("Historial");
	        btnPropietarios.setBounds(93, 10, 71, 23);
	        JButton btnCitas = new JButton("Citas");
	        btnCitas.setBounds(174, 10, 57, 23);
	        JButton btnReportes = new JButton("Actualizar Vacunas");
	        btnReportes.setBounds(324, 10, 123, 23);
	        JButton btnSalir = new JButton("Agendar");
	        btnSalir.setBounds(235, 10, 73, 23);
	        contentPane.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("New label");
	        lblNewLabel.setBounds(23, 76, 424, 125);
	        contentPane.add(lblNewLabel);

	        contentPane.add(btnMascotas);
	        contentPane.add(btnPropietarios);
	        contentPane.add(btnCitas);
	        contentPane.add(btnReportes);
	        contentPane.add(btnSalir);

	        btnSalir.addActionListener(e -> System.exit(0));

	}

}
