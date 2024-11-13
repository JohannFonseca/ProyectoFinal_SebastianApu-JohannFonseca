package Visual;

import javax.swing.*;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Conexion.ConexionBD;
import javax.swing.table.DefaultTableModel;

public class AdEventos extends JFrame {
    private JTextField txtIdEvento, txtCedulaJuridica, txtUbicacion, txtCapacidad, txtTitulo;
    private JTextArea txtAreaResultado;
    private JTable tablaEventos;
    private DefaultTableModel tableModel;
    private ConexionBD conector= new ConexionBD();

    public AdEventos() {
        setTitle("Administrar Eventos");
        setSize(750, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Usamos null para manejar las coordenadas manualmente
        getContentPane().setBackground(new Color(46, 64, 83)); 

        // Definir los campos de texto
        txtIdEvento = new JTextField(10);
        txtCedulaJuridica = new JTextField(10);
        txtUbicacion = new JTextField(10);
        txtCapacidad = new JTextField(10);
        txtTitulo = new JTextField(10);
        txtAreaResultado = new JTextArea(15, 30);
        txtAreaResultado.setEditable(false); // Solo lectura

        // Botones
        JButton btnInsertar = new JButton("Insertar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnVolver = new JButton("Volver");

        // Ubicación y tamaño de los componentes (definir coordenadas X, Y, ancho, alto)

        tableModel = new DefaultTableModel(new String[]{"ID Evento", "Cédula Jurídica", "Ubicación", "Capacidad", "Título"}, 0);
        tablaEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBounds(5, 100, 730, 200);

        JLabel lblIdEvento = new JLabel("ID Evento");
        lblIdEvento.setBounds(45, 20, 100, 25);
        PlaceholderTextField txtIdEvento = new PlaceholderTextField("Autonumerico");
        txtIdEvento.setBounds(20, 60, 120, 25);
        lblIdEvento.setForeground(Color.WHITE); 
      

        JLabel lblCedulaJuridica = new JLabel("Cédula Jurídica");
        lblCedulaJuridica.setBounds(170, 20, 100, 25);
        txtCedulaJuridica.setBounds(160, 60, 120, 25);
        lblCedulaJuridica.setForeground(Color.WHITE); 

        JLabel lblUbicacion = new JLabel("Ubicación");
        lblUbicacion.setBounds(330, 20, 100, 25);
        txtUbicacion.setBounds(300, 60, 120, 25);
        lblUbicacion.setForeground(Color.WHITE); 

        JLabel lblCapacidad = new JLabel("Capacidad");
        lblCapacidad.setBounds(470, 20, 100, 25);
        txtCapacidad.setBounds(440, 60, 120, 25);
        lblCapacidad.setForeground(Color.WHITE); 

        JLabel lblTitulo = new JLabel("Título");
        lblTitulo.setBounds(620, 20, 100, 25);
        txtTitulo.setBounds(580, 60, 120, 25);
        lblTitulo.setForeground(Color.WHITE); 

      /*   txtAreaResultado.setBounds(20, 220, 530, 200); // El área de texto para mostrar resultados */
        btnInsertar.setBounds(60, 330, 120, 30);
        btnActualizar.setBounds(220, 330, 120, 30);
        btnEliminar.setBounds(380, 330, 120, 30);
        btnMostrar.setBounds(540, 330, 120, 30);
        btnVolver.setBounds(300, 400, 120, 30);

        // Añadir los componentes a la ventana
        add(lblIdEvento);
        add(txtIdEvento);
        add(lblCedulaJuridica);
        add(txtCedulaJuridica);
        add(lblUbicacion);
        add(txtUbicacion);
        add(lblCapacidad);
        add(txtCapacidad);
        add(lblTitulo);
        add(txtTitulo);
        add(scrollPane); // Añadir el scroll con la tabla
        add(btnInsertar);
        add(btnActualizar);
        add(btnEliminar);
        add(btnMostrar);
        add(btnVolver);
        

        ImageIcon userIcon1 = new ImageIcon("Imagenes/fondo.png"); // Ruta de la imagen
        Image scaledUserImage1 = userIcon1.getImage().getScaledInstance(750, 480, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledUserIcon1 = new ImageIcon(scaledUserImage1); // Crear un nuevo ImageIcon con la imagen escalada
        JLabel userIconLabel1 = new JLabel(scaledUserIcon1); // Usar el ImageIcon escalado en el JLabel
        userIconLabel1.setSize(750, 480); // Posición y tamaño de la imagen
        add(userIconLabel1);

        // Acción de Insertar
        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedulaJuridica = txtCedulaJuridica.getText();
                String ubicacion = txtUbicacion.getText();
                String capacidad = txtCapacidad.getText();
                String titulo = txtTitulo.getText();
                Connection conexion = null;
                PreparedStatement preparar = null;
        
                try {
                    conexion = conector.getConexion();
                    conexion.setAutoCommit(true);
                    String Sentencia = "{CALL insertEvento(?, ?, ?, ?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, cedulaJuridica);
                    preparar.setString(2, ubicacion);
                    preparar.setString(3, capacidad);
                    preparar.setString(4, titulo);
        
                    int filasInsertadas = preparar.executeUpdate();
                    if (filasInsertadas > 0) {
                        JOptionPane.showMessageDialog(null, "El evento se ha insertado con éxito.");
                        btnMostrar.doClick(); // Actualizar la tabla después de insertar
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el evento.");
                    }
        
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar el evento: " + e1.getMessage());
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
        
                // Mostrar resultado en el área de texto
                txtAreaResultado.setText("Evento insertado: " + titulo);
            }
        });

//Actualizar

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEvento = txtIdEvento.getText();
                String cedulaJuridica = txtCedulaJuridica.getText();
                String ubicacion = txtUbicacion.getText();
                String capacidad = txtCapacidad.getText();
                String titulo = txtTitulo.getText();
                Connection conexion = null;
                PreparedStatement preparar = null;
        
                try {
                    conexion = conector.getConexion();
                    if (conexion == null) {
                        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos.");
                        return;
                    }
        
                    // Usar el SP que actualiza solo los campos con valores
                    String sentencia = "{CALL actualizarEvento(?, ?, ?, ?, ?)}";
                    preparar = conexion.prepareStatement(sentencia);
                    preparar.setString(1, idEvento);
                    preparar.setString(2, cedulaJuridica);
                    preparar.setString(3, ubicacion);
                    preparar.setString(4, capacidad);
                    preparar.setString(5, titulo);
        
                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "El evento se ha actualizado con éxito en la base de datos.");
                        btnMostrar.doClick(); // Actualizar la tabla
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra evento con ese ID.");
                    }
        
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el evento: " + e1.getMessage());
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
        




        // Acción de Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEvento = txtIdEvento.getText();
                Connection conexion = null;
                PreparedStatement preparar = null;
        
                try {
                    conexion = conector.getConexion();
                    if (conexion == null) {
                        txtAreaResultado.setText("Error en la conexión a la base de datos.");
                        return;
                    }
        
                    String Sentencia = "{CALL eliminarEvento(?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setInt(1, Integer.parseInt(idEvento));
        
                    int filasEliminadas = preparar.executeUpdate();
                    if (filasEliminadas > 0) {
                        txtAreaResultado.setText("Evento eliminado con éxito. ID: " + idEvento);
                        btnMostrar.doClick(); // Actualizar la tabla después de eliminar
                    } else {
                        txtAreaResultado.setText("No se encontró ningún evento con ID: " + idEvento);
                    }
        
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    txtAreaResultado.setText("Error al eliminar el evento: " + e1.getMessage());
                } catch (NumberFormatException ex) {
                    txtAreaResultado.setText("ID de evento inválido.");
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

        // Acción de Mostrar
       btnMostrar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        Connection conexion = null;
        PreparedStatement preparar = null;
        ResultSet resultados = null;

        try {
            conexion = conector.getConexion();
            String Sentencia = "{CALL mostrar_eventos()}";
            preparar = conexion.prepareStatement(Sentencia);
            resultados = preparar.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            tableModel.setRowCount(0);

            // Recorrer los resultados y agregar cada fila al modelo de tabla
            while (resultados.next()) {
                int idEvento = resultados.getInt("idEvento");
                String cedulaJuridica = resultados.getString("Cedula_Juridica");
                String ubicacion = resultados.getString("Ubicacion");
                String capacidad = resultados.getString("Capacidad");
                String titulo = resultados.getString("Titulo");

                // Agregar la fila al modelo de la tabla
                tableModel.addRow(new Object[]{idEvento, cedulaJuridica, ubicacion, capacidad, titulo});
            }

            // Verificar si no se encontraron datos y mostrar un mensaje
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron eventos.");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar los eventos: " + e1.getMessage());
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
            new AdEventos();
        });
    }

  
}

