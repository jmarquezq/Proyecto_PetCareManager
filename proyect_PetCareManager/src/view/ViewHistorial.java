package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.CitaController;

public class ViewHistorial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JLabel lblValorMascota, lblValorDueno, lblValorEspecie;
    private JTextArea txtAreaHistorial;
    private CitaController controller;

    public ViewHistorial() {
        controller = new CitaController();

        setTitle("Historial Médico - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 458, 500);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Consultar Historial Médico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(120, 15, 250, 25);
        contentPane.add(lblTitulo);

        JLabel lblBuscar = new JLabel("Buscar (ID o Nombre):");
        lblBuscar.setBounds(20, 60, 150, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(135, 60, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBuscar.setBackground(SystemColor.activeCaption);
        btnBuscar.setBounds(265, 60, 74, 25);
        contentPane.add(btnBuscar);

        JLabel lblMascota = new JLabel("Mascota:");
        lblMascota.setBounds(20, 100, 60, 25);
        contentPane.add(lblMascota);
        
        lblValorMascota = new JLabel("-");
        lblValorMascota.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorMascota.setBounds(80, 100, 150, 25);
        contentPane.add(lblValorMascota);

        JLabel lblDueno = new JLabel("Dueño:");
        lblDueno.setBounds(20, 125, 60, 25);
        contentPane.add(lblDueno);
        
        lblValorDueno = new JLabel("-");
        lblValorDueno.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorDueno.setBounds(80, 125, 150, 25);
        contentPane.add(lblValorDueno);

        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(20, 150, 60, 25);
        contentPane.add(lblEspecie);
        
        lblValorEspecie = new JLabel("-");
        lblValorEspecie.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorEspecie.setBounds(80, 150, 150, 25);
        contentPane.add(lblValorEspecie);

        JLabel lblHistorial = new JLabel("Historial:");
        lblHistorial.setBounds(20, 190, 80, 25);
        contentPane.add(lblHistorial);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 220, 390, 220);
        contentPane.add(scrollPane);

        txtAreaHistorial = new JTextArea();
        txtAreaHistorial.setEditable(false);
        txtAreaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaHistorial.setBackground(new Color(245, 245, 245));
        scrollPane.setViewportView(txtAreaHistorial);
        
        JButton btn_Regresar = new JButton("Regresar");
        btn_Regresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btn_Regresar.setBackground(SystemColor.activeCaption);
        btn_Regresar.setBounds(349, 60, 87, 25);
        contentPane.add(btn_Regresar);

        btnBuscar.addActionListener(e -> buscarHistorial());
        
        btn_Regresar.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });
        
        setLocationRelativeTo(null);
    }

    private void buscarHistorial() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID o Nombre.");
            return;
        }

        String[] m = controller.buscarMascota(termino);

        if (m == null) {
            JOptionPane.showMessageDialog(this, "Mascota no encontrada.");
            lblValorMascota.setText("-");
            lblValorEspecie.setText("-");
            lblValorDueno.setText("-");
            txtAreaHistorial.setText("");
            return;
        }

        String idMascotaEncontrada = m[0];
        lblValorMascota.setText(m[1]);
        lblValorEspecie.setText(m[2]);
        
        String duenoFull = m[6];
        if (duenoFull.contains("-")) {
            lblValorDueno.setText(duenoFull.split("-")[1].trim());
        } else {
            lblValorDueno.setText(duenoFull);
        }

        String historialTexto = controller.obtenerHistorialTurnos(idMascotaEncontrada);
        txtAreaHistorial.setText(historialTexto);
    }
}