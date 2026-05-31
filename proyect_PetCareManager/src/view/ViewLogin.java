package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.LoginController;

public class ViewLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtClave;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewLogin frame = new ViewLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewLogin() {

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 50, 80, 25);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 50, 180, 25);
        contentPane.add(txtUsuario);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(50, 90, 80, 25);
        contentPane.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(140, 90, 180, 25);
        contentPane.add(txtClave);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(140, 140, 120, 30);
        contentPane.add(btnIngresar);

        btnIngresar.addActionListener(e -> {

            String usuario = txtUsuario.getText();
            String clave = String.valueOf(txtClave.getPassword());

            LoginController controller = new LoginController();

            if (controller.validarLogin(usuario, clave)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Bienvenido al sistema");

//                new ViewMascotas().setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Usuario o contraseña incorrectos");
            }
        });

        setLocationRelativeTo(null);
    }
}