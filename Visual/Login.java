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
        getContentPane().setBackground(new Color(23, 92, 101)); 

        // Crear el primer JLabel para la primera línea de texto
        JLabel tituloLabel1 = new JLabel("Login");
        tituloLabel1.setBounds(115, 30, 400, 40); 
        tituloLabel1.setForeground(Color.WHITE); 
        tituloLabel1.setFont(new Font("Helvetica", Font.BOLD, 20)); 
        add(tituloLabel1);

        // Etiqueta y campo para idUsuario
        JLabel userIdLabel = new JLabel("ID de Usuario:");
        userIdLabel.setBounds(40, 90, 100, 30);
        userIdLabel.setForeground(Color.WHITE);
        add(userIdLabel);

        idtJTextField = new JTextField();
        idtJTextField.setBounds(150, 90, 100, 30);
        add(idtJTextField);

        // Etiqueta y campo para contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(40, 130, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        contrasenatexField = new JPasswordField();
        contrasenatexField.setBounds(150, 130, 100, 30);
        add(contrasenatexField);

        // Crear botón "Ingresar" y definir su posición y tamaño
        JButton botonA1 = new JButton("Ingresar");
        botonA1.setBounds(100, 200, 100, 50); 
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

