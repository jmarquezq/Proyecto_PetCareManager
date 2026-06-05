package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.CitaController;

public class ViewCitas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTextField txtMascota, txtPropietario;
    private JTextField txtFecha, txtHora;
    private JComboBox<String> cbMotivo;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private CitaController controller;
    private String idMascotaSeleccionada = "";

    public ViewCitas() {
        controller = new CitaController();

        setTitle("Agenda Médica - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 780, 480);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Planificación de Turnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(280, 10, 250, 25);
        contentPane.add(lblTitulo);

        JLabel lblPaso1 = new JLabel("Paso 1: Buscar Mascota (ID o Nombre)");
        lblPaso1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPaso1.setBounds(10, 45, 250, 20);
        contentPane.add(lblPaso1);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(10, 70, 150, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(SystemColor.activeCaption);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnBuscar.setBounds(170, 70, 80, 25);
        contentPane.add(btnBuscar);

        JLabel lblMascota = new JLabel("Mascota:");
        lblMascota.setBounds(10, 110, 80, 25);
        contentPane.add(lblMascota);

        txtMascota = new JTextField();
        txtMascota.setBounds(100, 110, 150, 25);
        txtMascota.setEditable(false);
        txtMascota.setBackground(Color.LIGHT_GRAY);
        contentPane.add(txtMascota);

        JLabel lblPropietario = new JLabel("Dueño:");
        lblPropietario.setBounds(10, 140, 80, 25);
        contentPane.add(lblPropietario);

        txtPropietario = new JTextField();
        txtPropietario.setBounds(100, 140, 150, 25);
        txtPropietario.setEditable(false);
        txtPropietario.setBackground(Color.LIGHT_GRAY);
        contentPane.add(txtPropietario);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 190, 80, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(100, 190, 100, 25);
        contentPane.add(txtFecha);
        
        JLabel lblFmtFecha = new JLabel("(DD/MM/AAAA)");
        lblFmtFecha.setForeground(Color.GRAY);
        lblFmtFecha.setBounds(205, 190, 90, 25);
        contentPane.add(lblFmtFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(10, 220, 80, 25);
        contentPane.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(100, 220, 100, 25);
        contentPane.add(txtHora);

        JLabel lblFmtHora = new JLabel("(HH:MM)");
        lblFmtHora.setForeground(Color.GRAY);
        lblFmtHora.setBounds(205, 220, 60, 25);
        contentPane.add(lblFmtHora);

        JLabel lblMotivo = new JLabel("Motivo:");
        lblMotivo.setBounds(10, 250, 80, 25);
        contentPane.add(lblMotivo);

        cbMotivo = new JComboBox<>(new String[]{"Consulta General", "Vacuna", "Control", "Seguimiento"});
        cbMotivo.setBounds(100, 250, 150, 25);
        cbMotivo.setBackground(Color.WHITE);
        contentPane.add(cbMotivo);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnAgendar.setBackground(SystemColor.activeCaption);
        btnAgendar.setBounds(10, 300, 110, 25);
        contentPane.add(btnAgendar);

        JButton btn_Regresar = new JButton("Regresar");
        btn_Regresar.setBackground(SystemColor.activeCaption);
        btn_Regresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btn_Regresar.setBounds(140, 300, 110, 25);
        contentPane.add(btn_Regresar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(310, 70, 430, 255);
        contentPane.add(scrollPane);

        tablaCitas = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID Cita", "ID Mascota", "Fecha", "Hora", "Motivo" }
        );
        tablaCitas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaCitas);

        btnBuscar.addActionListener(e -> {
            String termino = txtBuscar.getText().trim();
            if (termino.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID o Nombre para buscar.");
                return;
            }
            
            String[] m = controller.buscarMascota(termino);
            if (m != null) {
                idMascotaSeleccionada = m[0];
                txtMascota.setText(m[1]);
                String duenoFull = m[6];
                if (duenoFull.contains("-")) {
                    txtPropietario.setText(duenoFull.split("-")[1].trim());
                } else {
                    txtPropietario.setText(duenoFull);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mascota no encontrada en el sistema.", "No existe", JOptionPane.ERROR_MESSAGE);
                idMascotaSeleccionada = "";
                txtMascota.setText("");
                txtPropietario.setText("");
            }
        });

        btnAgendar.addActionListener(e -> {
            if (idMascotaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar una mascota primero.");
                return;
            }

            String fecha = txtFecha.getText().trim();
            String hora = txtHora.getText().trim();
            String motivo = cbMotivo.getSelectedItem().toString();

            String validacion = controller.validarFormatos(fecha, hora);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (controller.agendarCita(idMascotaSeleccionada, fecha, hora, motivo)) {
                JOptionPane.showMessageDialog(this, "Cita agendada con éxito.");
                limpiarCampos();
                llenarTabla();
            }
        });

        btn_Regresar.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });

        llenarTabla();
        setLocationRelativeTo(null);
    }

    private void limpiarCampos() {
        txtBuscar.setText("");
        idMascotaSeleccionada = "";
        txtMascota.setText("");
        txtPropietario.setText("");
        txtFecha.setText("");
        txtHora.setText("");
    }

    private void llenarTabla() {
        modeloTabla.setRowCount(0);
        List<String[]> lista = controller.obtenerTodasLasCitas();
        if (lista != null) {
            for (String[] r : lista) {
                if (r.length == 5) {
                    modeloTabla.addRow(r);
                }
            }
        }
    }
}