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
import controller.AtencionController;

public class ViewAtencionesRealizadas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JLabel lblNombreMascota, lblEspecie, lblDueno;
    private JTextArea txtAreaAtenciones;
    private JButton btn_Regresar;
    
    private AtencionController controller;

    public ViewAtencionesRealizadas() {

        controller = new AtencionController();

        setTitle("Atenciones Médicas Realizadas - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 550);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Revisar Atenciones Anteriores");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(130, 15, 250, 25);
        contentPane.add(lblTitulo);

        JLabel lblBuscar = new JLabel("Buscar Mascota (ID):");
        lblBuscar.setBounds(20, 60, 150, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(160, 60, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBuscar.setBackground(SystemColor.activeCaption);
        btnBuscar.setBounds(290, 60, 90, 25);
        contentPane.add(btnBuscar);

        lblNombreMascota = new JLabel("-");
        lblNombreMascota.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNombreMascota.setBounds(20, 100, 200, 25);
        contentPane.add(lblNombreMascota);

        lblEspecie = new JLabel("-");
        lblEspecie.setBounds(20, 125, 150, 20);
        contentPane.add(lblEspecie);

        lblDueno = new JLabel("Dueño: -");
        lblDueno.setBounds(20, 145, 300, 20);
        contentPane.add(lblDueno);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 440, 310);
        contentPane.add(scrollPane);

        txtAreaAtenciones = new JTextArea();
        txtAreaAtenciones.setEditable(false);
        txtAreaAtenciones.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaAtenciones.setBackground(new Color(250, 250, 250));
        scrollPane.setViewportView(txtAreaAtenciones);
        
        btn_Regresar = new JButton("Regresar");
        btn_Regresar.setBackground(SystemColor.activeCaption); 
        btn_Regresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btn_Regresar.setBounds(392, 60, 84, 25);
        contentPane.add(btn_Regresar);
    
        btn_Regresar.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });

        btnBuscar.addActionListener(e -> buscarAtenciones());
        
        setLocationRelativeTo(null);
    }

    private void buscarAtenciones() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID de la mascota (Ej. M001).");
            return;
        }

        String[] mascota = controller.buscarMascotaCompleta(termino);

        if (mascota == null) {
            JOptionPane.showMessageDialog(this, "Mascota no encontrada en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            lblNombreMascota.setText("-");
            lblEspecie.setText("-");
            lblDueno.setText("Dueño: -");
            txtAreaAtenciones.setText("");
            return;
        }

        String idMascotaEncontrada = mascota[0];
        lblNombreMascota.setText(mascota[1]); 
        lblEspecie.setText(mascota[2]);       
        
        String duenoFull = mascota[6];
        if (duenoFull.contains("-")) {
            lblDueno.setText("Dueño: " + duenoFull.split("-")[1].trim());
        } else {
            lblDueno.setText("Dueño: " + duenoFull);
        }

        String historialTexto = controller.obtenerHistorialClinico(idMascotaEncontrada);
        
        txtAreaAtenciones.setText(historialTexto);
        txtAreaAtenciones.setCaretPosition(0); 
    }
}
