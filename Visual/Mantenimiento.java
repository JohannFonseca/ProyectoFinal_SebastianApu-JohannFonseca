package Visual;

// Librerias
import javax.swing.*; 
import java.awt.*;    

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
        getContentPane().setBackground(new Color(23, 92, 101)); 

        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("Administración de");
        tituloLabel1.setBounds(130, 50, 400, 40); // Establece la posición (x, y) y tamaño (ancho, alto)
        tituloLabel1.setForeground(Color.WHITE); // Color de texto en blanco
        tituloLabel1.setFont(new Font("Helvetica", Font.BOLD, 25)); // Fuente estilizada y tamaño

        // Crear el segundo JLabel para la segunda línea de texto
        JLabel tituloLabel2 = new JLabel("Organizadores y Eventos");
        tituloLabel2.setBounds(100, 80, 400, 40); // Posiciona debajo del primer JLabel
        tituloLabel2.setForeground(Color.WHITE); // Color de texto en blanco
        tituloLabel2.setFont(new Font("Helvetica", Font.BOLD, 25)); // Fuente estilizada y tamaño

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

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

}

