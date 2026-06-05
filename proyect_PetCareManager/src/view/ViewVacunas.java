package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
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
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Actualizar Vacunas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(250, 10, 200, 25);
        contentPane.add(lblTitulo);

        JLabel lblBuscar = new JLabel("Buscar Mascota (ID o Nombre):");
        lblBuscar.setBounds(20, 50, 180, 25);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(200, 50, 120, 25);
        contentPane.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscar.setBackground(SystemColor.activeCaption);
        btnBuscar.setBounds(330, 50, 80, 25);
        contentPane.add(btnBuscar);

        JPanel panelDatos = new JPanel();
        panelDatos.setBounds(20, 90, 590, 40);
        panelDatos.setBackground(new Color(240, 248, 255));
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
            new String[] { "Vacuna", "Fecha" }
        );
        tablaVacunas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaVacunas);

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
        contentPane.add(txtFecha);

        JLabel lblObs = new JLabel("Observación:");
        lblObs.setBounds(20, 420, 100, 25);
        contentPane.add(lblObs);

        txtObservacion = new JTextField();
        txtObservacion.setBounds(120, 420, 320, 25);
        contentPane.add(txtObservacion);

        JButton btnAgregar = new JButton("Agregar Vacuna");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBackground(SystemColor.activeCaption);
        btnAgregar.setBounds(460, 380, 150, 30);
        contentPane.add(btnAgregar);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnRegresar.setBackground(SystemColor.activeCaption);
        btnRegresar.setBounds(460, 415, 150, 30);
        contentPane.add(btnRegresar);

        btnBuscar.addActionListener(e -> {
            String termino = txtBuscar.getText().trim();
            if (termino.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID o Nombre para buscar.");
                return;
            }

            String[] m = controller.buscarMascota(termino);
            if (m != null) {
                idMascotaSeleccionada = m[0];
                lblValorNombre.setText(m[1]);
                lblValorEspecie.setText(m[2]);
                lblValorEdad.setText(m[4] + " años");
                cargarTablaVacunas();
            } else {
                JOptionPane.showMessageDialog(this, "Mascota no encontrada.");
                idMascotaSeleccionada = "";
                lblValorNombre.setText("-");
                lblValorEspecie.setText("-");
                lblValorEdad.setText("-");
                modeloTabla.setRowCount(0);
            }
        });

        btnAgregar.addActionListener(e -> {
            if (idMascotaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe buscar y seleccionar una mascota.");
                return;
            }

            String nombreVac = txtNombreVacuna.getText().trim();
            String fecha = txtFecha.getText().trim();
            String obs = txtObservacion.getText().trim();

            String validacion = controller.validarFormatos(nombreVac, fecha, obs);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (controller.registrarVacuna(idMascotaSeleccionada, nombreVac, fecha, obs)) {
                JOptionPane.showMessageDialog(this, "Vacuna registrada con éxito.");
                txtNombreVacuna.setText("");
                txtFecha.setText("");
                txtObservacion.setText("");
                cargarTablaVacunas();
            }
        });

        btnRegresar.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
    }

    private void cargarTablaVacunas() {
        modeloTabla.setRowCount(0);
        if (idMascotaSeleccionada.isEmpty()) return;

        List<String[]> vacunas = controller.obtenerTodasLasVacunas();
        if (vacunas != null) {
            for (String[] v : vacunas) {
                if (v.length == 5 && v[1].equals(idMascotaSeleccionada)) {
                    modeloTabla.addRow(new Object[]{ v[2], v[3] });
                }
            }
        }
    }
}
