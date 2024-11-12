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
        setSize(500, 500);

        // Configuración para cerrar la ventana al presionar la 'X'
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Color de fondo de la ventana
        getContentPane().setBackground(new Color(46, 64, 83)); 

        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("Administración de");

        // Establece la posición (x, y) y tamaño (ancho, alto)
        tituloLabel1.setBounds(130, 50, 400, 40); 
        // Color de texto en blanco
        tituloLabel1.setForeground(Color.WHITE); 
        // Fuente estilizada y tamaño
        tituloLabel1.setFont(new Font("Helvetica", Font.BOLD, 25)); 

        // Crear el segundo JLabel para la segunda línea de texto
        JLabel tituloLabel2 = new JLabel("Organizadores y Eventos");
        // Posiciona debajo del primer JLabel
        tituloLabel2.setBounds(100, 80, 400, 40); 
        // Color de texto en blanco
        tituloLabel2.setForeground(Color.WHITE); 
        // Fuente estilizada y tamaño
        tituloLabel2.setFont(new Font("Helvetica", Font.BOLD, 25)); 

        // Añadir los JLabel a la ventana
        add(tituloLabel1);
        add(tituloLabel2);

        // Crear botón A1 y definir su posición y tamaño
        JButton botonA1 = new JButton("Administrar Organizadores");
        botonA1.setBounds(100, 200, 300, 50); 
        add(botonA1); 

        // Crear botón A2 y definir su posición y tamaño
        JButton botonA2 = new JButton("Administrar Eventos");
        botonA2.setBounds(100, 270, 300, 50); 
        add(botonA2); 

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

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

}


