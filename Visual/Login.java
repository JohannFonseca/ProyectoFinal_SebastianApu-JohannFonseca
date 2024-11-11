package Visual;

import javax.swing.*;
import java.awt.*;    
import java.awt.event.*;
import java.sql.*;  // Importar para manejar la conexión a MySQL
import Conexion.ConexionBD;  // Importar tu clase de conexión

public class Login extends JFrame {

    // Declaración de los campos de texto para idUsuario y Contraseña
    private JTextField idtJTextField;
    private JPasswordField contrasenatexField;

    // Instancia de la clase de conexión
    private ConexionBD conexionBD;

    // Constructor de la ventana
    public Login() {
        // Inicializar la instancia de ConexionBD
        conexionBD = new ConexionBD();

        // Título de la ventana
        setTitle("Login");

        // Tamaño de la ventana
        setSize(300, 300);

        // Configuración para cerrar la ventana al presionar la 'X'
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Color de fondo de la ventana
        getContentPane().setBackground(new Color(230, 214, 144)); 

        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("Ingresa tu Usuario");
        tituloLabel1.setBounds(60, 30, 400, 40); 
        tituloLabel1.setForeground(Color.BLACK); 
        tituloLabel1.setFont(new Font("Helvetica", Font.BOLD, 20)); 
        add(tituloLabel1);

        ImageIcon userIcon = new ImageIcon("Imagenes/id.png"); // Ruta de la imagen
        Image scaledUserImage = userIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon = new ImageIcon(scaledUserImage); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel = new JLabel(scaledUserIcon); // Usar el ImageIcon escalado en el JLabel
        userIconLabel.setBounds(30, 90, 30, 30); // Posición y tamaño de la imagen
        add(userIconLabel);


        idtJTextField = new JTextField();
        idtJTextField.setBounds(70, 90, 160, 30);
        add(idtJTextField);

        ImageIcon passwordIcon = new ImageIcon("Imagenes/contraseña.png"); // Ruta de la imagen
        Image scaledPasswordImage = passwordIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledPasswordIcon = new ImageIcon(scaledPasswordImage); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel passwordIconLabel = new JLabel(scaledPasswordIcon); // Usar el ImageIcon escalado en el JLabel
        passwordIconLabel.setBounds(30, 130, 30, 30); // Posición y tamaño de la imagen
        add(passwordIconLabel);
    

        contrasenatexField = new JPasswordField();
        contrasenatexField.setBounds(70, 130, 160, 30);
        add(contrasenatexField);

        // Crear botón "Ingresar" y definir su posición y tamaño
        JButton botonA1 = new JButton("Ingresar");
        botonA1.setBounds(90, 170, 100, 50);
        botonA1.setFont(new Font("Arial", Font.BOLD, 15));  
        botonA1.setContentAreaFilled(false); // Hace el área de contenido transparente
        botonA1.setBorderPainted(false); // Elimina el borde del botón
        botonA1.setForeground(Color.BLACK); // Cambia el color del texto para mejor visibilidad
        add(botonA1);
        

        // Acción para el botón de "Ingresar"
        botonA1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = idtJTextField.getText();
                String password = new String(contrasenatexField.getPassword());

                // Llamar a la función para verificar el usuario en la base de datos
                if (checkLogin(userId, password)) {
                    JOptionPane.showMessageDialog(Login.this, "Inicio de sesión exitoso");
                    Mantenimiento ventanaMantenimiento = new Mantenimiento();
                    ventanaMantenimiento.setVisible(true);
                    dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(Login.this, "ID de usuario o contraseña incorrectos");
                }
            }
        });

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    // Método para verificar el usuario en la base de datos
    private boolean checkLogin(String userId, String password) {
        boolean loginSuccessful = false;

        // Obtener la conexión a través de la clase ConexionBD
        try (Connection conn = conexionBD.getConexion()) {
            if (conn != null) {
                String query = "SELECT * FROM usuario WHERE idUsuario = ? AND Contrasena = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, userId);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                loginSuccessful = rs.next(); // Retorna true si encuentra el usuario
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
        return loginSuccessful;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
        });
    }
}

