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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.VacunaController;
import model.Mascota;
import model.Vacuna;

public class ViewVacunas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JLabel lblValorNombre, lblValorEspecie, lblValorEdad;
    
    private JTextField txtNombreVacuna, txtFecha, txtObservacion;
    private JTable tablaVacunas;
    private DefaultTableModel modeloTabla;
    
    private VacunaController controller;
    private String idMascotaSeleccionada = "";

    public ViewVacunas() {
        controller = new VacunaController();

        setTitle("Actualización de Vacunas - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 550);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Actualizar Vacunas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(250, 10, 200, 25);
        contentPane.add(lblTitulo);

        // ================= PASO 1: BUSCAR MASCOTA =================
        JLabel lblBuscar = new JLabel("Buscar Mascota (ID o Nombre):");
        lblBuscar.setBounds(20, 50, 180, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(200, 50, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(330, 50, 80, 25);
        contentPane.add(btnBuscar);

        // ================= MOSTRAR DATOS DE MASCOTA =================
        JPanel panelDatos = new JPanel();
        panelDatos.setBounds(20, 90, 590, 40);
        panelDatos.setBackground(new Color(240, 248, 255)); // Color de fondo suave
        panelDatos.setLayout(null);
        contentPane.add(panelDatos);

        lblValorNombre = new JLabel("-");
        lblValorNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorNombre.setBounds(10, 10, 150, 20);
        panelDatos.add(lblValorNombre);

        lblValorEspecie = new JLabel("-");
        lblValorEspecie.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorEspecie.setBounds(200, 10, 150, 20);
        panelDatos.add(lblValorEspecie);

        lblValorEdad = new JLabel("-");
        lblValorEdad.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblValorEdad.setBounds(400, 10, 150, 20);
        panelDatos.add(lblValorEdad);

        // ================= TABLA DE VACUNAS EXISTENTES =================
        JLabel lblSubtituloTabla = new JLabel("Vacunas Aplicadas:");
        lblSubtituloTabla.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSubtituloTabla.setBounds(20, 150, 200, 20);
        contentPane.add(lblSubtituloTabla);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 590, 150);
        contentPane.add(scrollPane);

        tablaVacunas = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Vacuna", "Fecha" } // Solo mostramos estos dos datos como pediste
        );
        tablaVacunas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaVacunas);

        // ================= FORMULARIO PARA AGREGAR VACUNA =================
        JLabel lblSubtituloForm = new JLabel("Registrar Nueva Vacuna:");
        lblSubtituloForm.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSubtituloForm.setBounds(20, 350, 200, 20);
        contentPane.add(lblSubtituloForm);

        JLabel lblVacuna = new JLabel("Nombre Vacuna:");
        lblVacuna.setBounds(20, 380, 100, 25);
        contentPane.add(lblVacuna);

        txtNombreVacuna = new JTextField();
        txtNombreVacuna.setBounds(120, 380, 150, 25);
        contentPane.add(txtNombreVacuna);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(290, 380, 50, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(340, 380, 100, 25);
        txtFecha.setToolTipText("DD/MM/AAAA");
        contentPane.add(txtFecha);

        JLabel lblObs = new JLabel("Observación:");
        lblObs.setBounds(20, 420, 100, 25);
        contentPane.add(lblObs);

        txtObservacion = new JTextField();
        txtObservacion.setBounds(120, 420, 320, 25);
        contentPane.add(txtObservacion);

        JButton btnAgregar = new JButton("Agregar Vacuna");
        btnAgregar.setBounds(460, 380, 150, 65); // Botón grande
        contentPane.add(btnAgregar);

        // ================= EVENTOS =================

        // Buscar mascota
        btnBuscar.addActionListener(e -> {
            String termino = txtBuscar.getText().trim();
            if (termino.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID o Nombre para buscar.");
                return;
            }

            boolean encontrada = false;
            try {
                List<String[]> mascotas = new util.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
                for (String[] m : mascotas) {
                    if (m[0].equalsIgnoreCase(termino) || m[1].equalsIgnoreCase(termino)) {
                        idMascotaSeleccionada = m[0];
                        lblValorNombre.setText(m[1]); // Nombre
                        lblValorEspecie.setText(m[2]); // Especie
                        lblValorEdad.setText(m[4] + " años"); // Edad
                        
                        encontrada = true;
                        cargarTablaVacunas(); // Carga las vacunas de esta mascota
                        break;
                    }
                }
            } catch (Exception ex) {
                System.err.println("Error al leer mascotas: " + ex.getMessage());
            }

            if (!encontrada) {
                JOptionPane.showMessageDialog(this, "Mascota no encontrada.");
                idMascotaSeleccionada = "";
                lblValorNombre.setText("-");
                lblValorEspecie.setText("-");
                lblValorEdad.setText("-");
                modeloTabla.setRowCount(0); // Vaciar tabla
            }
        });

        // Agregar Vacuna
        btnAgregar.addActionListener(e -> {
            if (idMascotaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe buscar y seleccionar una mascota.");
                return;
            }

            String nombreVac = txtNombreVacuna.getText().trim();
            String fecha = txtFecha.getText().trim();
            String obs = txtObservacion.getText().trim();

            if (nombreVac.isEmpty() || fecha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre de la vacuna y la fecha son obligatorios.");
                return;
            }

            // Validar Regex
            String validacion = controller.validarFormatos(nombreVac, fecha, obs);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar
            if (controller.registrarVacuna(idMascotaSeleccionada, nombreVac, fecha, obs)) {
                JOptionPane.showMessageDialog(this, "Vacuna registrada con éxito.");
                txtNombreVacuna.setText("");
                txtFecha.setText("");
                txtObservacion.setText("");
                cargarTablaVacunas(); // Recargar la tabla automáticamente
            }
        });

        setLocationRelativeTo(null);
    }

    // Método para filtrar las vacunas por la mascota actual
    private void cargarTablaVacunas() {
        modeloTabla.setRowCount(0); // Limpiar
        if (idMascotaSeleccionada.isEmpty()) return;

        try {
            List<String[]> vacunas = new util.GenericDAO<Vacuna>("src/doc/vacunas.txt").cargarTodo();
            if (vacunas != null) {
                for (String[] v : vacunas) {
                    // v[1] es idMascota en el modelo Vacuna.java
                    if (v.length == 5 && v[1].equals(idMascotaSeleccionada)) {
                        // Agregamos solo Nombre Vacuna (v[2]) y Fecha (v[3]) a la tabla
                        modeloTabla.addRow(new Object[]{ v[2], v[3] });
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar vacunas: " + e.getMessage());
        }
    }
}
