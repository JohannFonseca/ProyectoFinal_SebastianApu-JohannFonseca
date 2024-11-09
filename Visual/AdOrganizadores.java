package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdOrganizadores extends JFrame {
    private JTextField txtCedulaJuridica, txtNombre;
    private JTextArea txtAreaResultado;

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

        // Ubicación y tamaño de los componentes (definir coordenadas X, Y, ancho, alto)
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

                // Aquí iría la lógica para insertar el organizador
                txtAreaResultado.setText("Organizador insertado: " + nombre);
            }
        });

        // Acción de Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedulaJuridica = txtCedulaJuridica.getText();
                String nombre = txtNombre.getText();

                // Aquí iría la lógica para actualizar el organizador
                txtAreaResultado.setText("Organizador actualizado: " + nombre);
            }
        });

        // Acción de Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedulaJuridica = txtCedulaJuridica.getText();

                // Aquí iría la lógica para eliminar el organizador
                txtAreaResultado.setText("Organizador eliminado con Cédula Jurídica: " + cedulaJuridica);
            }
        });

        // Acción de Mostrar
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para mostrar los organizadores
                txtAreaResultado.setText("Mostrando todos los organizadores.");
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

