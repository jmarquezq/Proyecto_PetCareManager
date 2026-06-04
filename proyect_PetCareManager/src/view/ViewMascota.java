package view;

import java.awt.Font;
import java.io.IOException;
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

import controller.MascotaController;
import model.Mascota;
import model.Propietario;

public class ViewMascota extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdMascota, txtNombre, txtEspecie, txtRaza, txtEdad;
    private JComboBox<String> cbSexo, cbPropietario;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private MascotaController controller;

    public ViewMascota() {
        controller = new MascotaController();

        setTitle("Registro de Mascotas - PetCare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ¡Cambio aplicado!
        setBounds(100, 100, 850, 420);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Registro de Mascotas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(350, 15, 250, 25);
        contentPane.add(lblTitulo);

        // FORMULARIO: 7 CAMPOS
        crearLabel(10, 60, "ID Mascota:");
        txtIdMascota = crearTextField(100, 60);

        crearLabel(10, 95, "Nombre:");
        txtNombre = crearTextField(100, 95);

        crearLabel(10, 130, "Especie:");
        txtEspecie = crearTextField(100, 130);

        crearLabel(10, 165, "Raza:");
        txtRaza = crearTextField(100, 165);

        crearLabel(10, 200, "Edad:");
        txtEdad = crearTextField(100, 200);

        crearLabel(10, 235, "Sexo:");
        cbSexo = new JComboBox<>(new String[]{"Macho", "Hembra"});
        cbSexo.setBounds(100, 235, 150, 25);
        contentPane.add(cbSexo);

        crearLabel(10, 270, "Propietario:");
        cbPropietario = new JComboBox<>();
        cbPropietario.setBounds(100, 270, 150, 25);
        contentPane.add(cbPropietario);
        cargarPropietarios(); // Llena el combo con el txt

        // BOTONES
        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setBounds(10, 320, 100, 25);
        contentPane.add(btnGuardar);

        // TABLA
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(280, 60, 530, 285);
        contentPane.add(scrollPane);

        tablaMascotas = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nombre", "Especie", "Raza", "Edad", "Sexo", "Propietario" }
        );
        tablaMascotas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaMascotas);

     // EVENTO DEL BOTÓN GUARDAR (REEMPLAZAR EN TU CÓDIGO)
        btnGuardar.addActionListener(e -> {
            String id = txtIdMascota.getText();
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            String raza = txtRaza.getText();
            String edad = txtEdad.getText();
            String sexo = cbSexo.getSelectedItem().toString();
            
            if (cbPropietario.getSelectedItem() == null || cbPropietario.getSelectedItem().toString().equals("Sin registrar")) {
                JOptionPane.showMessageDialog(this, "Debe registrar un Propietario en el menú principal primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String prop = cbPropietario.getSelectedItem().toString();

            // Validar campos vacíos
            if (id.isEmpty() || nombre.isEmpty() || especie.isEmpty() || raza.isEmpty() || edad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos de la mascota.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar Expresiones Regulares
            String validacion = controller.validarFormatos(id, nombre, especie, raza, edad);
            if (!validacion.equals("OK")) {
                JOptionPane.showMessageDialog(this, validacion, "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return; // Detiene el registro
            }

            // Si todo está correcto, se registra
            if (controller.registrarMascota(id, nombre, especie, raza, edad, sexo, prop)) {
                JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.");
                txtIdMascota.setText(""); txtNombre.setText(""); txtEspecie.setText("");
                txtRaza.setText(""); txtEdad.setText("");
                llenarTabla();
            }
        });

        llenarTabla();
        setLocationRelativeTo(null); 
    }

    // Funciones de utilidad para no repetir código en la vista
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

    // Método clave que lee el archivo de propietarios y llena la lista desplegable
    private void cargarPropietarios() {
        try {
            List<String[]> propietarios = new Libreria_generica.GenericDAO<Propietario>("src/doc/propietarios.txt").cargarTodo();
            if (propietarios != null && !propietarios.isEmpty()) {
                for (String[] prop : propietarios) {
                    if (prop.length >= 2) {
                        cbPropietario.addItem(prop[0] + " - " + prop[1]); // Cédula - Nombre
                    }
                }
            } else {
                cbPropietario.addItem("Sin registrar");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar dueños: " + e.getMessage());
        }
    }

    private void llenarTabla() {
        modeloTabla.setRowCount(0); 
        try {
            List<String[]> lista = new Libreria_generica.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
            if (lista != null) {
                for (String[] registro : lista) {
                    if(registro.length == 7) modeloTabla.addRow(registro);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar mascotas: " + e.getMessage());
        }
    }
}