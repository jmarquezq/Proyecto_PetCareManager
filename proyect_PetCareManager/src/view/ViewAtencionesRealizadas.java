package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Atencion;
import model.Mascota;

public class ViewAtencionesRealizadas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JLabel lblNombreMascota, lblEspecie, lblDueno;
    private JTextArea txtAreaAtenciones;

    public ViewAtencionesRealizadas() {
        setTitle("Atenciones Médicas Realizadas - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 550);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Revisar Atenciones Anteriores");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(130, 15, 250, 25);
        contentPane.add(lblTitulo);

        // ================= BUSCADOR =================
        JLabel lblBuscar = new JLabel("Buscar Mascota (ID):");
        lblBuscar.setBounds(20, 60, 150, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(160, 60, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(290, 60, 90, 25);
        contentPane.add(btnBuscar);

        // ================= CABECERA DE DATOS =================
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

        // ================= TEXTAREA DEL HISTORIAL CLÍNICO =================
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 440, 310);
        contentPane.add(scrollPane);

        txtAreaAtenciones = new JTextArea();
        txtAreaAtenciones.setEditable(false);
        txtAreaAtenciones.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaAtenciones.setBackground(new Color(250, 250, 250));
        scrollPane.setViewportView(txtAreaAtenciones);

        // ================= EVENTOS =================
        btnBuscar.addActionListener(e -> buscarAtenciones());
        
        setLocationRelativeTo(null);
    }

    private void buscarAtenciones() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID de la mascota (Ej. M001).");
            return;
        }

        String idMascotaEncontrada = "";
        
        // 1. Buscar los datos principales de la mascota
        try {
            List<String[]> mascotas = new util.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
            for (String[] m : mascotas) {
                if (m[0].equalsIgnoreCase(termino) || m[1].equalsIgnoreCase(termino)) {
                    idMascotaEncontrada = m[0];
                    lblNombreMascota.setText(m[1]); // Nombre
                    lblEspecie.setText(m[2]);       // Especie
                    
                    // Extraer solo el nombre del propietario
                    String dueñoFull = m[6];
                    if(dueñoFull.contains("-")) {
                        lblDueno.setText("Dueño: " + dueñoFull.split("-")[1].trim());
                    } else {
                        lblDueno.setText("Dueño: " + dueñoFull);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer archivo de mascotas.");
        }

        if (idMascotaEncontrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mascota no encontrada en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            lblNombreMascota.setText("-");
            lblEspecie.setText("-");
            lblDueno.setText("Dueño: -");
            txtAreaAtenciones.setText("");
            return;
        }

        // 2. Buscar e imprimir las atenciones médicas realizadas
        StringBuilder sb = new StringBuilder();
        boolean tieneAtenciones = false;

        try {
            List<String[]> atenciones = new util.GenericDAO<Atencion>("src/doc/atenciones.txt").cargarTodo();
            for (String[] a : atenciones) {
                // a[2] corresponde a idMascota según el modelo Atencion.java
                if (a.length == 9 && a[2].equalsIgnoreCase(idMascotaEncontrada)) {
                    
                    sb.append("--------------------------------\n");
                    sb.append(a[0]).append("\n"); // ID Atención
                    sb.append("Fecha: ").append(a[3]).append("\n\n"); // Fecha
                    
                    sb.append("Diagnóstico:\n");
                    sb.append(a[6]).append("\n\n"); // Diagnóstico
                    
                    sb.append("Tratamiento:\n");
                    sb.append(a[7]).append("\n"); // Tratamiento
                    
                    tieneAtenciones = true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer archivo de atenciones.");
        }

        if (tieneAtenciones) {
            sb.append("--------------------------------\n");
        } else {
            sb.append("--------------------------------\n");
            sb.append("No hay atenciones médicas registradas para este paciente.\n");
            sb.append("--------------------------------\n");
        }
        
        txtAreaAtenciones.setText(sb.toString());
        // Forzar el scroll al inicio para que se lea desde el primer registro
        txtAreaAtenciones.setCaretPosition(0);
    }
}
