package view;

import java.awt.Font;
import java.io.IOException;
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

import controller.MascotaController;
import model.Mascota;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class ViewMascota extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private MascotaController controller;
    private JTextField textField;

    public ViewMascota() {
        controller = new MascotaController();

        setTitle("Registro de Mascotas - PetCare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 556, 394);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // TÍTULO
        JLabel lblTitulo = new JLabel("Registro de mascotas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(180, 15, 250, 25);
        contentPane.add(lblTitulo);

        // FORMULARIO: NOMBRE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 96, 80, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 96, 133, 25);
        contentPane.add(txtNombre);

        // FORMULARIO: EDAD
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(10, 193, 80, 25);
        contentPane.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(120, 193, 133, 25);
        contentPane.add(txtEdad);

        // FORMULARIO: ESPECIE
        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(10, 123, 80, 25);
        contentPane.add(lblEspecie);

        // BOTÓN REGISTRAR / GUARDAR
        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setBounds(10, 269, 80, 20);
        contentPane.add(btnGuardar);

        // TABLA PARA MOSTRAR LOS DATOS
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(279, 95, 260, 247);
        contentPane.add(scrollPane);

        tablaMascotas = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nombre", "Edad", "Especie" }
        );
        tablaMascotas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaMascotas);
        
        JLabel lblNewLabel = new JLabel("Datos de la mascota:");
        lblNewLabel.setBounds(49, 67, 112, 19);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Raza:");
        lblNewLabel_1.setBounds(10, 164, 44, 12);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Genero:");
        lblNewLabel_2.setBounds(10, 228, 44, 12);
        contentPane.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setBounds(120, 166, 133, 18);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Macho\r\n");
        rdbtnNewRadioButton.setBounds(120, 224, 55, 20);
        contentPane.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Hembra");
        rdbtnNewRadioButton_1.setBounds(180, 224, 102, 20);
        contentPane.add(rdbtnNewRadioButton_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(120, 128, 133, 20);
        contentPane.add(comboBox);
        
        JButton btnNewButton = new JButton("Modificar");
        btnNewButton.setBounds(91, 269, 84, 20);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Eliminar");
        btnNewButton_1.setBounds(185, 269, 84, 20);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Limpiar");
        btnNewButton_2.setBounds(91, 312, 84, 20);
        contentPane.add(btnNewButton_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mascotas registradas");
        lblNewLabel_3.setBounds(279, 67, 117, 19);
        contentPane.add(lblNewLabel_3);

        // ACCIÓN DEL BOTÓN GUARDAR
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String edad = txtEdad.getText();
            String especie = txtEspecie.getText();

            // Llamamos a las validaciones del controlador que hablamos antes
            if (nombre.trim().isEmpty() || edad.trim().isEmpty() || especie.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            if (!edad.trim().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La edad debe contener solo números enteros.");
                return;
            }

            // Si pasa, el controlador registra en el archivo .txt
            controller.registrarMascota(nombre, edad, especie);
            JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.");

            // Limpiamos los cuadros de texto
            txtNombre.setText("");
            txtEdad.setText("");
            txtEspecie.setText("");

            // Actualizamos la tabla visual
            llenarTabla();
        });

        // Cargamos los datos guardados en la tabla apenas se abre la pantalla
        llenarTabla();
        
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    // MÉTODO PARA LLENAR LA TABLA CON LOS DATOS DEL ARCHIVO TXT
    private void llenarTabla() {
        modeloTabla.setRowCount(0); // Vaciar tabla antes de recargar
        MascotaController control = new MascotaController();
        try {
            // Usamos el método cargarTodo() de tu controlador a través de su util.GenericDAO
            java.util.List<String[]> lista = new util.GenericDAO<Mascota>("src/doc/mascota.txt").cargarTodo();
            for (String[] registro : lista) {
                modeloTabla.addRow(registro);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la tabla: " + e.getMessage());
        }
    }
}