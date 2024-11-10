package Visual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.ConexionBD;

public class AdEventos extends JFrame {
    private JTextField txtIdEvento, txtCedulaJuridica, txtUbicacion, txtCapacidad, txtTitulo;
    private JTextArea txtAreaResultado;
    private ConexionBD conector= new ConexionBD();

    public AdEventos() {
        setTitle("Administrar Eventos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Usamos null para manejar las coordenadas manualmente

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
        JLabel lblIdEvento = new JLabel("ID Evento:");
        lblIdEvento.setBounds(20, 20, 100, 25);
        txtIdEvento.setBounds(130, 20, 150, 25);

        JLabel lblCedulaJuridica = new JLabel("Cédula Jurídica:");
        lblCedulaJuridica.setBounds(20, 60, 100, 25);
        txtCedulaJuridica.setBounds(130, 60, 150, 25);

        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(20, 100, 100, 25);
        txtUbicacion.setBounds(130, 100, 150, 25);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(20, 140, 100, 25);
        txtCapacidad.setBounds(130, 140, 150, 25);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 180, 100, 25);
        txtTitulo.setBounds(130, 180, 150, 25);

        txtAreaResultado.setBounds(20, 220, 530, 200); // El área de texto para mostrar resultados
        btnInsertar.setBounds(20, 440, 120, 30);
        btnActualizar.setBounds(150, 440, 120, 30);
        btnEliminar.setBounds(280, 440, 120, 30);
        btnMostrar.setBounds(410, 440, 120, 30);
        btnVolver.setBounds(230, 480, 120, 30);

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

        // Acción de Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEvento = txtIdEvento.getText();
                String cedulaJuridica = txtCedulaJuridica.getText();
                String ubicacion = txtUbicacion.getText();
                String capacidad = txtCapacidad.getText();
                String titulo = txtTitulo.getText();
                Connection conexion = null;;
                PreparedStatement preparar = null;
                try {
                  conexion = conector.getConexion();
                            
                    conexion.setAutoCommit(true);
                    String Sentencia = "{CALL actualizarEvento(?,?,?,?,?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, idEvento);
                    preparar.setString(2,cedulaJuridica);
                    preparar.setString(3,ubicacion);
                    preparar.setString(4,capacidad);
                    preparar.setString(5,titulo); //cambiar
                    


                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se ha actualizado con exito del banco");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra persona con ese Id");
                    }

                } catch (SQLException e1) {

                    e1.printStackTrace();
                } finally {
                    {
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

            StringBuilder resultadoTexto = new StringBuilder();
            while (resultados.next()) {
                int idEvento = resultados.getInt("idEvento");
                String cedulaJuridica = resultados.getString("Cedula_Juridica");
                String ubicacion = resultados.getString("Ubicacion");
                String capacidad = resultados.getString("Capacidad");
                String titulo = resultados.getString("Titulo");

                resultadoTexto.append("ID Evento: ").append(idEvento)
                              .append(", Cédula Jurídica: ").append(cedulaJuridica)
                              .append(", Ubicación: ").append(ubicacion)
                              .append(", Capacidad: ").append(capacidad)
                              .append(", Título: ").append(titulo)
                              .append("\n");
            }

            if (resultadoTexto.length() == 0) {
                txtAreaResultado.setText("No se encontraron eventos.");
            } else {
                txtAreaResultado.setText(resultadoTexto.toString());
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
