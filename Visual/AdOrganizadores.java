package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Conexion.ConexionBD;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdOrganizadores extends JFrame {
    private JTextField txtCedulaJuridica, txtNombre;
    private JTextArea txtAreaResultado;
    private ConexionBD conector = new ConexionBD();
    private JTable table;
    private DefaultTableModel tableModel;

    public AdOrganizadores() {
        setTitle("Administrar Organizadores");
        setSize(600, 460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Usamos null para manejar las coordenadas manualmente

         // Color de fondo de la ventana
         getContentPane().setBackground(new Color(46, 64, 83)); 

        // Definir los campos de texto
        txtCedulaJuridica = new JTextField(10);
        txtNombre = new JTextField(10);
        txtAreaResultado = new JTextArea(15, 30);
        txtAreaResultado.setEditable(false); // Solo lectura

        // Botones
        JButton btnInsertar = new JButton("Insertar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnVolver = new JButton("Volver");

        // Ubicación y tamaño de los componentes
        JLabel lblCedulaJuridica = new JLabel("Cédula Jurídica");
        lblCedulaJuridica.setBounds(350, 20, 120, 25);
        lblCedulaJuridica.setForeground(Color.WHITE);
        txtCedulaJuridica.setBounds(320, 60, 150, 25);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(160, 20, 120, 25);
        lblNombre.setForeground(Color.WHITE);
        txtNombre.setBounds(110, 60, 150, 25);

      /*   txtAreaResultado.setBounds(20, 100, 530, 200); // El área de texto para mostrar resultados */
        btnInsertar.setBounds(20, 320, 120, 30);
        btnActualizar.setBounds(150, 320, 120, 30);
        btnEliminar.setBounds(280, 320, 120, 30);
        btnMostrar.setBounds(410, 320, 120, 30);
        btnVolver.setBounds(230, 360, 120, 30);

        String[] columnNames = { "Cédula Jurídica", "Nombre" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(90, 100, 400, 200); // Ajusta el tamaño y posición del JScrollPane
        add(scrollPane);

        // Añadir los componentes a la ventana
        add(lblCedulaJuridica);
        add(txtCedulaJuridica);
        add(lblNombre);
        add(txtNombre);
        add(txtAreaResultado);
        add(btnInsertar);
        add(btnActualizar);
        add(btnEliminar);
        add(btnMostrar);
        add(btnVolver);

         ImageIcon userIcon1 = new ImageIcon("Imagenes/fondo.png"); // Ruta de la imagen
        Image scaledUserImage1 = userIcon1.getImage().getScaledInstance(600, 460, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon1 = new ImageIcon(scaledUserImage1); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel1 = new JLabel(scaledUserIcon1); // Usar el ImageIcon escalado en el JLabel
        userIconLabel1.setSize(600, 460); // Posición y tamaño de la imagen
        add(userIconLabel1);

       // Acción de Insertar
btnInsertar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String cedulaJuridica = txtCedulaJuridica.getText();
        String nombre = txtNombre.getText();
        Connection conexion = null;
        PreparedStatement preparar = null;

        try {
            conexion = conector.getConexion();
            if (conexion == null) {
                txtAreaResultado.setText("Error en la conexión a la base de datos.");
                return;
            }

            conexion.setAutoCommit(true);
            String Sentencia = "{CALL insertarOrganizador(?, ?)}";
            preparar = conexion.prepareStatement(Sentencia);
            preparar.setString(1, cedulaJuridica);
            preparar.setString(2, nombre);

            int filasInsertadas = preparar.executeUpdate();
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "El organizador se ha insertado con éxito.");
                btnMostrar.doClick(); // Actualizar la tabla después de insertar
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el organizador.");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar el organizador: " + e1.getMessage());
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
                if (preparar != null)
                    preparar.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
});

// Acción de Actualizar
btnActualizar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String cedulaJuridica = txtCedulaJuridica.getText();
        String nombre = txtNombre.getText();
        Connection conexion = null;
        PreparedStatement preparar = null;

        try {
            conexion = conector.getConexion();
            if (conexion == null) {
                txtAreaResultado.setText("Error en la conexión a la base de datos.");
                return;
            }

            String Sentencia = "{CALL actualizarOrganizador(?, ?)}"; // Llamada al procedimiento almacenado
            preparar = conexion.prepareStatement(Sentencia);
            preparar.setString(1, cedulaJuridica);
            preparar.setString(2, nombre);

            int filasActualizadas = preparar.executeUpdate();
            if (filasActualizadas > 0) {
                txtAreaResultado.setText("Organizador actualizado con Cédula Jurídica: " + cedulaJuridica);
                btnMostrar.doClick(); // Actualizar la tabla después de actualizar
            } else {
                txtAreaResultado.setText("No se encontró un organizador con la Cédula Jurídica: " + cedulaJuridica);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el organizador: " + e1.getMessage());
        } finally {
            try {
                if (preparar != null)
                    preparar.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
});

btnMostrar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        Connection conexion = null;
        PreparedStatement preparar = null;
        ResultSet resultados = null;

        try {
            conexion = conector.getConexion();
            String Sentencia = "{CALL mostrarOrganizadores()}";
            preparar = conexion.prepareStatement(Sentencia);
            resultados = preparar.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            tableModel.setRowCount(0);

            // Recorrer los resultados y agregar cada fila al modelo de tabla
            while (resultados.next()) {
                String cedulaJuridica = resultados.getString("CedulaJuridica");
                String nombre = resultados.getString("Nombre");

                // Agregar la fila al modelo de la tabla
                tableModel.addRow(new Object[]{cedulaJuridica, nombre});
            }

            // Verificar si no se encontraron datos y mostrar un mensaje en la tabla
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron organizadores.");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar los organizadores: " + e1.getMessage());
        } finally {
            try {
                if (resultados != null)
                    resultados.close();
                if (preparar != null)
                    preparar.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
});

// Acción de Eliminar
btnEliminar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String cedulaJuridica = txtCedulaJuridica.getText();
        Connection conexion = null;
        PreparedStatement preparar = null;

        try {
            conexion = conector.getConexion();
            if (conexion == null) {
                txtAreaResultado.setText("Error en la conexión a la base de datos.");
                return;
            }

            String Sentencia = "{CALL eliminaOrganizador(?)}"; // Llamada al procedimiento almacenado
            preparar = conexion.prepareStatement(Sentencia);
            preparar.setString(1, cedulaJuridica);

            int filasEliminadas = preparar.executeUpdate();
            if (filasEliminadas > 0) {
                txtAreaResultado.setText("Organizador eliminado con Cédula Jurídica: " + cedulaJuridica);
                btnMostrar.doClick(); // Actualizar la tabla después de eliminar
            } else {
                txtAreaResultado.setText("No se encontró un organizador con la Cédula Jurídica: " + cedulaJuridica);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el organizador: " + e1.getMessage());
        } finally {
            try {
                if (preparar != null)
                    preparar.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
});


        // Acción de Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mantenimiento ventanaMantenimiento = new Mantenimiento();
                ventanaMantenimiento.setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });

        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true); // Asegurarse de que la ventana sea visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdOrganizadores().setVisible(true);
        });
    }
}
