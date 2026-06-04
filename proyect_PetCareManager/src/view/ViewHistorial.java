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
import model.Cita;
import model.Mascota;

public class ViewHistorial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JLabel lblValorMascota, lblValorDueno, lblValorEspecie;
    private JTextArea txtAreaHistorial;

    public ViewHistorial() {
        setTitle("Historial Médico - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Consultar Historial Médico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(120, 15, 250, 25);
        contentPane.add(lblTitulo);

        // BUSCADOR
        JLabel lblBuscar = new JLabel("Buscar (ID o Nombre):");
        lblBuscar.setBounds(20, 60, 150, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(160, 60, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(290, 60, 90, 25);
        contentPane.add(btnBuscar);

        // DATOS DE LA MASCOTA
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

        // TEXTAREA DEL HISTORIAL
        JLabel lblHistorial = new JLabel("Historial:");
        lblHistorial.setBounds(20, 190, 80, 25);
        contentPane.add(lblHistorial);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 220, 390, 220);
        contentPane.add(scrollPane);

        txtAreaHistorial = new JTextArea();
        txtAreaHistorial.setEditable(false); // Solo lectura
        txtAreaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaHistorial.setBackground(new Color(245, 245, 245));
        scrollPane.setViewportView(txtAreaHistorial);

        // EVENTO BUSCAR
        btnBuscar.addActionListener(e -> buscarHistorial());
        
        setLocationRelativeTo(null);
    }

    private void buscarHistorial() {
        String termino = txtBuscar.getText().trim();
        if (termino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID o Nombre.");
            return;
        }

        String idMascotaEncontrada = "";
        
        // 1. Buscar los datos de la mascota
        try {
            List<String[]> mascotas = new util.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
            for (String[] m : mascotas) {
                if (m[0].equalsIgnoreCase(termino) || m[1].equalsIgnoreCase(termino)) {
                    idMascotaEncontrada = m[0];
                    lblValorMascota.setText(m[1]);
                    lblValorEspecie.setText(m[2]);
                    
                    // Formatear dueño
                    String dueñoFull = m[6];
                    if(dueñoFull.contains("-")) {
                        lblValorDueno.setText(dueñoFull.split("-")[1].trim());
                    } else {
                        lblValorDueno.setText(dueñoFull);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer mascotas.");
        }

        if (idMascotaEncontrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mascota no encontrada.");
            return;
        }

        // 2. Buscar e imprimir las citas de esa mascota
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        
        boolean tieneCitas = false;
        try {
            List<String[]> citas = new util.GenericDAO<Cita>("src/doc/citas.txt").cargarTodo();
            for (String[] c : citas) {
                // c[1] es el idMascota en el archivo de citas
                if (c.length == 5 && c[1].equals(idMascotaEncontrada)) {
                    // Formato: Fecha + Motivo
                    sb.append(c[2]).append(" ").append(c[4]).append("\n");
                    tieneCitas = true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer citas.");
        }

        if (!tieneCitas) {
            sb.append("Sin atenciones registradas.\n");
        }
        sb.append("--------------------------------\n");
        
        txtAreaHistorial.setText(sb.toString());
    }
}