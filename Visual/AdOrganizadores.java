package Visual;

import javax.swing.*;

import Conexion.ConexionBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.ConexionBD;


public class AdOrganizadores extends JFrame {
    private JTextField txtCedulaJuridica, txtNombre;
    private JTextArea txtAreaResultado;
    private ConexionBD conector = new ConexionBD();

    public AdOrganizadores() {
        setTitle("Administrar Organizadores");
        setSize(600, 460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Usamos null para manejar las coordenadas manualmente

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
        JLabel lblCedulaJuridica = new JLabel("Cédula Jurídica:");
        lblCedulaJuridica.setBounds(20, 20, 120, 25);
        txtCedulaJuridica.setBounds(150, 20, 150, 25);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        txtNombre.setBounds(150, 60, 150, 25);

        txtAreaResultado.setBounds(20, 100, 530, 200); // El área de texto para mostrar resultados
        btnInsertar.setBounds(20, 320, 120, 30);
        btnActualizar.setBounds(150, 320, 120, 30);
        btnEliminar.setBounds(280, 320, 120, 30);
        btnMostrar.setBounds(410, 320, 120, 30);
        btnVolver.setBounds(230, 360, 120, 30);

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

        // Acción de Mostrar
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

                    StringBuilder resultadoTexto = new StringBuilder();
                    while (resultados.next()) {
                        String cedulaJuridica = resultados.getString("CedulaJuridica");
                        String nombre = resultados.getString("Nombre");

                        resultadoTexto.append("Cédula Jurídica: ").append(cedulaJuridica)
                                      .append(", Nombre: ").append(nombre)
                                      .append("\n");
                    }

                    if (resultadoTexto.length() == 0) {
                        txtAreaResultado.setText("No se encontraron organizadores.");
                    } else {
                        txtAreaResultado.setText(resultadoTexto.toString());
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

