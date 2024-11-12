package Visual;

// Librerías
import javax.swing.*;
import java.awt.*;    
import java.awt.event.*;  // Importar para ActionListener

public class Mantenimiento extends JFrame {

    // Constructor de la ventana
    public Mantenimiento() {

        // Título de la ventana
        setTitle("Ventana de Mantenimiento");

        // Tamaño de la ventana
        setSize(500, 350);

        // Configuración para cerrar la ventana al presionar la 'X'
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Color de fondo de la ventana
        getContentPane().setBackground(new Color(46, 64, 83)); 

        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("¿Qué desea Administrar?");

        // Establece la posición (x, y) y tamaño (ancho, alto)
        tituloLabel1.setBounds(100, 50, 400, 40); 
        // Color de texto en blanco
        tituloLabel1.setForeground(Color.WHITE); 
        // Fuente estilizada y tamaño
        tituloLabel1.setFont(new Font("Rockwell", Font.BOLD, 25));  
        

        // Añadir los JLabel a la ventana
        add(tituloLabel1);
        

        // Crear botón A1 y definir su posición y tamaño
        JButton botonA1 = new JButton("Administrar Organizadores");
        botonA1.setBounds(100, 100, 300, 50); 
        add(botonA1); 

        // Crear botón A2 y definir su posición y tamaño
        JButton botonA2 = new JButton("Administrar Eventos");
        botonA2.setBounds(100, 170, 300, 50); 
        add(botonA2); 
        
        ImageIcon userIcon = new ImageIcon("Imagenes/LOGOUT.png"); // Ruta de la imagen
        Image scaledUserImage = userIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon = new ImageIcon(scaledUserImage); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel = new JLabel(scaledUserIcon); // Usar el ImageIcon escalado en el JLabel
        userIconLabel.setBounds(180, 240, 30, 30); // Posición y tamaño de la imagen
        add(userIconLabel);

        JButton Logout = new JButton("     LOGOUT");
        Logout.setBorder(new RoundedBorder(10));
        Logout.setForeground(Color.black);
        Logout.setFocusPainted(false);
        Logout.setBounds(180, 240, 100, 30); 
        Logout.setContentAreaFilled(false); // Hace el área de contenido transparente
        Logout.setForeground(Color.BLACK);
        add(Logout); 

        

        // Acción para el botón de "Administrar Organizadores"
        botonA1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de AdOrganizadores
                AdOrganizadores ventanaOrganizadores = new AdOrganizadores();
                ventanaOrganizadores.setVisible(true);
                // Cerrar la ventana actual (Mantenimiento)
                dispose();
            }
        });

        // Acción para el botón de "Administrar Eventos"
        botonA2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de AdEventos
                AdEventos ventanaEventos = new AdEventos();
                ventanaEventos.setVisible(true);
                // Cerrar la ventana actual (Mantenimiento)
                dispose();
            }
        });

         // Acción para el botón de "LOGOUT"
         Logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de AdEventos
                Login ventanaLogin = new Login();
                ventanaLogin.setVisible(true);
                // Cerrar la ventana actual (Mantenimiento)
                dispose();
            }
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

}


