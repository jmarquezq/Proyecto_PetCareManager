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

public class ViewMascota extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtEspecie;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private MascotaController controller;

    public ViewMascota() {
        controller = new MascotaController();

        setTitle("Registro de Mascotas - PetCare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // TÍTULO
        JLabel lblTitulo = new JLabel("ADMINISTRACIÓN DE MASCOTAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(180, 15, 250, 25);
        contentPane.add(lblTitulo);

        // FORMULARIO: NOMBRE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 60, 80, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 150, 25);
        contentPane.add(txtNombre);

        // FORMULARIO: EDAD
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(40, 100, 80, 25);
        contentPane.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(120, 100, 150, 25);
        contentPane.add(txtEdad);

        // FORMULARIO: ESPECIE
        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(40, 140, 80, 25);
        contentPane.add(lblEspecie);

        txtEspecie = new JTextField();
        txtEspecie.setBounds(120, 140, 150, 25);
        contentPane.add(txtEspecie);

        // BOTÓN REGISTRAR / GUARDAR
        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setBounds(120, 190, 150, 30);
        contentPane.add(btnGuardar);

        // TABLA PARA MOSTRAR LOS DATOS
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(300, 60, 260, 300);
        contentPane.add(scrollPane);

        tablaMascotas = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nombre", "Edad", "Especie" }
        );
        tablaMascotas.setModel(modeloTabla);
        scrollPane.setViewportView(tablaMascotas);

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