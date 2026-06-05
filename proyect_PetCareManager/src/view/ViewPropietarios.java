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
import controller.PropietarioController;

public class ViewPropietarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCedula, txtNombre, txtTelefono, txtDireccion, txtCorreo;
    private JTable tablaPropietarios;
    private DefaultTableModel modeloTabla;
    private PropietarioController controller;

    public ViewPropietarios() {
        controller = new PropietarioController();

        setTitle("Registro de Propietarios - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setBounds(100, 100, 750, 420);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Registro de Dueños / Clientes");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(250, 15, 250, 25);
        contentPane.add(lblTitulo);

        crearLabel(10, 65, "Cédula:");
        txtCedula = crearTextField(100, 65);
        
        crearLabel(10, 105, "Nombre:");
        txtNombre = crearTextField(100, 105);
        
        crearLabel(10, 145, "Teléfono:");
        txtTelefono = crearTextField(100, 145);
        
        crearLabel(10, 185, "Dirección:");
        txtDireccion = crearTextField(100, 185);
        
        crearLabel(10, 225, "Correo:");
        txtCorreo = crearTextField(100, 225);

        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardar.setBackground(SystemColor.activeCaption);
        btnGuardar.setBounds(10, 280, 110, 25);
        contentPane.add(btnGuardar);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnRegresar.setBackground(SystemColor.activeCaption);
        btnRegresar.setBounds(140, 280, 110, 25);
        contentPane.add(btnRegresar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(280, 65, 430, 280);
        contentPane.add(scrollPane);

        tablaPropietarios = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Cédula", "Nombre", "Teléfono", "Dirección", "Correo" }
        );
        tablaPropietarios.setModel(modeloTabla);
        scrollPane.setViewportView(tablaPropietarios);

        btnGuardar.addActionListener(e -> {
            String cedula = txtCedula.getText().trim();
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String correo = txtCorreo.getText().trim();

            String validacion = controller.validarFormatos(cedula, nombre, telefono, direccion, correo);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            if (controller.registrarPropietario(cedula, nombre, telefono, direccion, correo)) {
                JOptionPane.showMessageDialog(this, "Propietario registrado con éxito.");
                txtCedula.setText(""); txtNombre.setText(""); txtTelefono.setText("");
                txtDireccion.setText(""); txtCorreo.setText("");
                llenarTabla();
            }
        });

        btnRegresar.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });

        llenarTabla();
        setLocationRelativeTo(null);
    }

    private void crearLabel(int x, int y, String texto) {
        JLabel label = new JLabel(texto);
        label.setBounds(x, y, 80, 25);
        contentPane.add(label);
    }

    private JTextField crearTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 25);
        contentPane.add(textField);
        return textField;
    }

    private void llenarTabla() {
        modeloTabla.setRowCount(0);
        List<String[]> lista = controller.obtenerTodosLosPropietarios();
        if (lista != null) {
            for (String[] r : lista) {
                if (r.length == 5) {
                    modeloTabla.addRow(r);
                }
            }
        }
    }
}