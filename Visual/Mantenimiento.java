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
        setSize(500, 330);

        // Configuración para cerrar la ventana al presionar la 'X'
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Color de fondo de la ventana
        getContentPane().setBackground(new Color(46, 64, 83)); 


        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("¿Qué desea Administrar?");

        // Establece la posición (x, y) y tamaño (ancho, alto)
        tituloLabel1.setBounds(95, 50, 400, 40); 
        // Color de texto en blanco
        tituloLabel1.setForeground(Color.WHITE); 
        // Fuente estilizada y tamaño
        tituloLabel1.setFont(new Font("Rockwell", Font.BOLD, 25));  
        

        // Añadir los JLabel a la ventana
        add(tituloLabel1);
        
        ImageIcon userIcon3 = new ImageIcon("Imagenes/organizador.png"); // Ruta de la imagen
        Image scaledUserImage3 = userIcon3.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon3 = new ImageIcon(scaledUserImage3); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel3 = new JLabel(scaledUserIcon3); // Usar el ImageIcon escalado en el JLabel
        userIconLabel3.setBounds(145, 106, 40, 40); // Posición y tamaño de la imagen
        add(userIconLabel3);

        // Crear botón A1 y definir su posición y tamaño
        JButton botonA1 = new JButton("          Administrar Organizadores");
        botonA1.setBorder(new RoundedBorder(10));
        botonA1.setForeground(Color.white);
        botonA1.setFocusPainted(false);
        botonA1.setContentAreaFilled(false);
        botonA1.setBounds(140, 100, 235, 50); 
        add(botonA1); 

        ImageIcon userIcon2 = new ImageIcon("Imagenes/evento.png"); // Ruta de la imagen
        Image scaledUserImage2 = userIcon2.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon2 = new ImageIcon(scaledUserImage2); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel2 = new JLabel(scaledUserIcon2); // Usar el ImageIcon escalado en el JLabel
        userIconLabel2.setBounds(145, 176, 40, 40); // Posición y tamaño de la imagen
        add(userIconLabel2);
       
        // Crear botón A2 y definir su posición y tamaño
        JButton botonA2 = new JButton("       Administrar Eventos");
        botonA2.setBorder(new RoundedBorder(10));
        botonA2.setForeground(Color.white);
        botonA2.setFocusPainted(false);
        botonA2.setContentAreaFilled(false);
        botonA2.setBounds(140, 170, 235, 50); 
        add(botonA2); 
        
        ImageIcon userIcon = new ImageIcon("Imagenes/LOGOUT.png"); // Ruta de la imagen
        Image scaledUserImage = userIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon = new ImageIcon(scaledUserImage); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel = new JLabel(scaledUserIcon); // Usar el ImageIcon escalado en el JLabel
        userIconLabel.setBounds(200, 240, 30, 30); // Posición y tamaño de la imagen
        add(userIconLabel);

        JButton Logout = new JButton("     LOGOUT");
        Logout.setBorder(new RoundedBorder(10));
        Logout.setForeground(Color.white);
        Logout.setFocusPainted(false);
        Logout.setContentAreaFilled(false);
        Logout.setBounds(200, 240, 100, 30);  
        add(Logout); 

        ImageIcon userIcon1 = new ImageIcon("Imagenes/fondo.png"); // Ruta de la imagen
        Image scaledUserImage1 = userIcon1.getImage().getScaledInstance(500, 330, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon1 = new ImageIcon(scaledUserImage1); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel1 = new JLabel(scaledUserIcon1); // Usar el ImageIcon escalado en el JLabel
        userIconLabel1.setSize(500, 330); // Posición y tamaño de la imagen
        add(userIconLabel1);

        

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


