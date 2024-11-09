package Visual;
// aca es la ventana de Login

//Librerias

import javax.swing.*; 
public class Login extends JFrame {

    // Constructor de la ventana
    public Login() {

        // Título de la ventana
        setTitle("Login");

        // Tamaño de la ventana
        setSize(500, 500);

        // Configuración para cerrar la ventana al presionar la 'X'
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        //agregar imagen
        JLabel fondo = new JLabel(new ImageIcon("imagen.jpg")); 

        // Ajusta el tamaño y posición para cubrir toda la ventana
        fondo.setBounds(0, 0, 500, 500); 

        // Agrega la imagen a la ventana
        add(fondo); 

        // Crear un botón
        JButton botonLogin = new JButton("Iniciar Sesion");

        //posicion y tamaño del boton
        botonLogin.setBounds(200, 400, 100, 40);

        // Agrega el botón encima del fondo de imagen
        fondo.add(botonLogin); 

        // Muestra la ventana centrada en la pantalla
        setLocationRelativeTo(null);
    }
}
