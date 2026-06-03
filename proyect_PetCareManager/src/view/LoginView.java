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
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginView extends JFrame {

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
                    LoginView frame = new LoginView();
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
    public LoginView() {

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 429, 240);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.inactiveCaption);
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblUsuario.setBounds(10, 50, 80, 25);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 51, 146, 25);
        contentPane.add(txtUsuario);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblClave.setBounds(10, 90, 118, 25);
        contentPane.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(100, 91, 146, 25);
        contentPane.add(txtClave);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnIngresar.setBackground(new Color(0, 128, 0));
        btnIngresar.setBounds(83, 141, 120, 30);
        contentPane.add(btnIngresar);
        
        JLabel lblImg = new JLabel("");
        lblImg.setIcon(new ImageIcon(LoginView.class.getResource("/recursos/veterinaria (2).png")));
        lblImg.setBounds(252, 0, 170, 194);
        contentPane.add(lblImg);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblLogin.setBounds(92, 10, 62, 25);
        contentPane.add(lblLogin);

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