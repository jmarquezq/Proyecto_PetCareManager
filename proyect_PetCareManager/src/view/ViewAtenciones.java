package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AtencionController;
import model.Cita;
import model.Mascota;

public class ViewAtenciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscarCita;
    private JTextField txtMascotaNombre, txtFecha;
    private JTextField txtPeso, txtTemperatura, txtDiagnostico, txtTratamiento, txtObservacion;
    
    private AtencionController controller;
    private String idMascotaCargada = "";
    private String idCitaCargada = "";

    public ViewAtenciones() {
        controller = new AtencionController();

        setTitle("Registrar Atención Médica - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 550);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Registrar Atención Médica");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(180, 15, 250, 25);
        contentPane.add(lblTitulo);

        // ================= PASO 1: BUSCAR CITA =================
        JLabel lblBuscar = new JLabel("ID Cita (Ej. CT-0001):");
        lblBuscar.setBounds(20, 60, 150, 25);
        contentPane.add(lblBuscar);

        txtBuscarCita = new JTextField();
        txtBuscarCita.setBounds(160, 60, 120, 25);
        contentPane.add(txtBuscarCita);

        JButton btnBuscar = new JButton("Buscar Turno");
        btnBuscar.setBounds(290, 60, 120, 25);
        contentPane.add(btnBuscar);

        // ================= DATOS EXTRAÍDOS (SOLO LECTURA) =================
        JLabel lblMascota = new JLabel("Paciente:");
        lblMascota.setBounds(20, 110, 80, 25);
        contentPane.add(lblMascota);

        txtMascotaNombre = new JTextField();
        txtMascotaNombre.setBounds(100, 110, 150, 25);
        txtMascotaNombre.setEditable(false);
        txtMascotaNombre.setBackground(Color.LIGHT_GRAY);
        contentPane.add(txtMascotaNombre);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(280, 110, 50, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(330, 110, 100, 25);
        txtFecha.setEditable(false);
        txtFecha.setBackground(Color.LIGHT_GRAY);
        contentPane.add(txtFecha);

        // ================= SIGNOS VITALES =================
        JLabel lblSignos = new JLabel("Signos Vitales:");
        lblSignos.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSignos.setBounds(20, 160, 150, 20);
        contentPane.add(lblSignos);

        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setBounds(20, 190, 70, 25);
        contentPane.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(100, 190, 80, 25);
        contentPane.add(txtPeso);

        JLabel lblTemp = new JLabel("Temp. (°C):");
        lblTemp.setBounds(200, 190, 80, 25);
        contentPane.add(lblTemp);

        txtTemperatura = new JTextField();
        txtTemperatura.setBounds(280, 190, 80, 25);
        contentPane.add(txtTemperatura);

        // ================= DATOS CLÍNICOS =================
        JLabel lblClinicos = new JLabel("Datos Clínicos:");
        lblClinicos.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblClinicos.setBounds(20, 240, 150, 20);
        contentPane.add(lblClinicos);

        JLabel lblDiag = new JLabel("Diagnóstico:");
        lblDiag.setBounds(20, 270, 80, 25);
        contentPane.add(lblDiag);

        txtDiagnostico = new JTextField();
        txtDiagnostico.setBounds(100, 270, 380, 25);
        contentPane.add(txtDiagnostico);

        JLabel lblTrat = new JLabel("Tratamiento:");
        lblTrat.setBounds(20, 310, 80, 25);
        contentPane.add(lblTrat);

        txtTratamiento = new JTextField();
        txtTratamiento.setBounds(100, 310, 380, 25);
        contentPane.add(txtTratamiento);

        JLabel lblObs = new JLabel("Observación:");
        lblObs.setBounds(20, 350, 80, 25);
        contentPane.add(lblObs);

        txtObservacion = new JTextField();
        txtObservacion.setBounds(100, 350, 380, 25);
        contentPane.add(txtObservacion);

        // ================= BOTÓN DE REGISTRO =================
        JButton btnRegistrar = new JButton("Guardar Atención Médica");
        btnRegistrar.setBounds(150, 420, 220, 35);
        contentPane.add(btnRegistrar);

        // ================= EVENTOS =================

        // Buscar Cita
        btnBuscar.addActionListener(e -> {
            String terminoCita = txtBuscarCita.getText().trim().toUpperCase();
            if (terminoCita.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID de la Cita.");
                return;
            }

            boolean citaEncontrada = false;
            try {
                // Leer archivo de citas
                List<String[]> citas = new util.GenericDAO<Cita>("src/doc/citas.txt").cargarTodo();
                for (String[] c : citas) {
                    if (c[0].equals(terminoCita)) {
                        idCitaCargada = c[0];
                        idMascotaCargada = c[1];
                        txtFecha.setText(c[2]);
                        citaEncontrada = true;
                        break;
                    }
                }

                // Si encuentra la cita, buscar el nombre de la mascota para mostrarlo
                if (citaEncontrada) {
                    List<String[]> mascotas = new util.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
                    for (String[] m : mascotas) {
                        if (m[0].equals(idMascotaCargada)) {
                            txtMascotaNombre.setText(m[1]); // Mostramos el nombre de la mascota
                            break;
                        }
                    }
                }

            } catch (Exception ex) {
                System.err.println("Error de lectura: " + ex.getMessage());
            }

            if (!citaEncontrada) {
                JOptionPane.showMessageDialog(this, "No existe un turno agendado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarTodo();
            }
        });

        // Registrar Atención
        btnRegistrar.addActionListener(e -> {
            if (idCitaCargada.isEmpty() || idMascotaCargada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe buscar un turno válido primero.");
                return;
            }

            String peso = txtPeso.getText().trim();
            String temp = txtTemperatura.getText().trim();
            String diag = txtDiagnostico.getText().trim();
            String trat = txtTratamiento.getText().trim();
            String obs = txtObservacion.getText().trim();
            String fecha = txtFecha.getText();

            // Validar campos vacíos
            if (peso.isEmpty() || temp.isEmpty() || diag.isEmpty() || trat.isEmpty() || obs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos médicos son obligatorios.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar Regex
            String validacion = controller.validarFormatos(peso, temp, diag, trat, obs);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar
            if (controller.registrarAtencion(idCitaCargada, idMascotaCargada, fecha, peso, temp, diag, trat, obs)) {
                JOptionPane.showMessageDialog(this, "Atención médica guardada con éxito.");
                limpiarTodo();
            }
        });

        setLocationRelativeTo(null);
    }

    private void limpiarTodo() {
        txtBuscarCita.setText("");
        idCitaCargada = "";
        idMascotaCargada = "";
        txtMascotaNombre.setText("");
        txtFecha.setText("");
        txtPeso.setText("");
        txtTemperatura.setText("");
        txtDiagnostico.setText("");
        txtTratamiento.setText("");
        txtObservacion.setText("");
    }
}
