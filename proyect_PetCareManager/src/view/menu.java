package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu frame = new menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public menu() {
        setTitle("PetCare - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnSistema = new JMenu("Sistema");
        menuBar.add(mnSistema);
        
        JMenuItem mntmPropietarios = new JMenuItem("Registrar Propietarios");
        mnSistema.add(mntmPropietarios);
        
        JMenuItem mntmMascotas = new JMenuItem("Registrar Mascotas");
        mnSistema.add(mntmMascotas);
        
        JMenuItem mntmCitas = new JMenuItem("Programar Citas");
        mnSistema.add(mntmCitas);
        
        JMenu mnConsultas = new JMenu("Consultas Médicas");
        menuBar.add(mnConsultas);
        
        JMenuItem mntmHistorial = new JMenuItem("Historial Médico (Citas)");
        mnConsultas.add(mntmHistorial);
        
        JMenuItem mntmVacunas = new JMenuItem("Vacunas");
        mnConsultas.add(mntmVacunas);

        // CORRECCIÓN: Separación de Registro y Revisión
        JMenuItem mntmRegistrarAtencion = new JMenuItem("Registrar Atención Médica");
        mnConsultas.add(mntmRegistrarAtencion);
        
        JMenuItem mntmAtenciones = new JMenuItem("Revisar Atenciones Realizadas");
        mnConsultas.add(mntmAtenciones);

        JMenu mnOpciones = new JMenu("Opciones");
        menuBar.add(mnOpciones);
        
        JMenuItem mntmSalir = new JMenuItem("Salir");
        mnOpciones.add(mntmSalir);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ACCIONES DE LOS MENÚS
        mntmPropietarios.addActionListener(e -> new ViewPropietarios().setVisible(true));
        mntmMascotas.addActionListener(e -> new ViewMascota().setVisible(true));
        mntmCitas.addActionListener(e -> new ViewCitas().setVisible(true));
        mntmHistorial.addActionListener(e -> new ViewHistorial().setVisible(true));
        mntmVacunas.addActionListener(e -> new ViewVacunas().setVisible(true));
        
        // Enlaces corregidos
        mntmRegistrarAtencion.addActionListener(e -> new ViewAtenciones().setVisible(true)); // Pantalla de ingreso
        mntmAtenciones.addActionListener(e -> new ViewAtencionesRealizadas().setVisible(true)); // Pantalla de revisión
        
        mntmSalir.addActionListener(e -> System.exit(0));
        
        setLocationRelativeTo(null); 
    }
}