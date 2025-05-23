package Vista;

import Controlador.Ctrl_Cliente;
import Modelo.Cliente;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz interna para la gestión de clientes.
 * Permite visualizar, actualizar y eliminar clientes registrados.
 * 
 * @author Sebas
 */
public class InterGestionarCliente extends javax.swing.JInternalFrame {

    private int idCliente = 0; // ID del cliente seleccionado para actualizar/eliminar

    public InterGestionarCliente() {
        initComponents();
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Clientes");

        // Cargar los datos de los clientes en la tabla al iniciar
        this.CargarTablaClientes();

        // Establecer imagen de fondo escalada al tamaño de la ventana
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();

        // Configurar el evento para que al seleccionar una fila, cargue los datos en los campos
        jTable_clientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable_clientes.getSelectedRow() != -1) {
                int filaSeleccionada = jTable_clientes.getSelectedRow();
                idCliente = (int) jTable_clientes.getValueAt(filaSeleccionada, 0);
                CargarDatosClienteSeleccionado(idCliente);
            }
        });
    }

    /**
     * Limpia los campos de texto del formulario para ingresar o modificar datos de cliente.
     */
    private void Limpiar() {
        txt_nombre.setText("");
        txt_telefono.setText("");
        txt_apellido.setText("");
        txt_direccion.setText("");
        txt_cedula.setText("");
        idCliente = 0; // Reiniciar ID seleccionado
        jTable_clientes.clearSelection(); // Deseleccionar tabla
    }

    /**
     * Carga todos los clientes registrados y muestra sus datos en la tabla de clientes.
     */
    private void CargarTablaClientes() {
        Ctrl_Cliente ctrlCliente = new Ctrl_Cliente();
        List<Cliente> listaClientes = ctrlCliente.listarClientes();

        // Modelo para la tabla: columnas id, nombre, apellido, cedula, telefono, direccion
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Apellido", "Cédula", "Teléfono", "Dirección"});

        if (listaClientes != null) {
            for (Cliente c : listaClientes) {
                Object[] fila = new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getApellido(),
                    c.getCedula(),
                    c.getTelefono(),
                    c.getDireccion()
                };
                modelo.addRow(fila);
            }
        }

        jTable_clientes.setModel(modelo);
    }

    /**
     * Carga los datos de un cliente específico en los campos del formulario para edición.
     * @param idCliente ID único del cliente a cargar.
     */
    private void CargarDatosClienteSeleccionado(int idCliente) {
        Ctrl_Cliente ctrlCliente = new Ctrl_Cliente();
        Cliente cliente = ctrlCliente.obtenerClientePorId(idCliente);

        if (cliente != null) {
            txt_nombre.setText(cliente.getNombre());
            txt_apellido.setText(cliente.getApellido());
            txt_cedula.setText(cliente.getCedula());
            txt_telefono.setText(cliente.getTelefono());
            txt_direccion.setText(cliente.getDireccion());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el cliente seleccionado.");
            Limpiar();
        }
    }

    /**
     * Evento que se ejecuta al presionar el botón "Actualizar".
     * Valida que los campos no estén vacíos, crea un objeto Cliente con los datos ingresados
     * y llama al controlador para actualizar la información en la base de datos o modelo.
     * Luego actualiza la tabla y limpia los campos del formulario.
     */
    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed

        // Validar campos obligatorios
        if (txt_nombre.getText().trim().isEmpty() || txt_apellido.getText().trim().isEmpty()
                || txt_cedula.getText().trim().isEmpty() || txt_telefono.getText().trim().isEmpty()
                || txt_direccion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Completa todos los campos!");
            return;
        }

        if (idCliente == 0) {
            JOptionPane.showMessageDialog(null, "¡Selecciona un cliente para actualizar!");
            return;
        }

        Cliente cliente = new Cliente();
        Ctrl_Cliente controlCliente = new Ctrl_Cliente();

        // Asignar valores a objeto cliente
        cliente.setNombre(txt_nombre.getText().trim());
        cliente.setApellido(txt_apellido.getText().trim());
        cliente.setCedula(txt_cedula.getText().trim());
        cliente.setTelefono(txt_telefono.getText().trim());
        cliente.setDireccion(txt_direccion.getText().trim());

        // Actualizar cliente en la fuente de datos
        boolean actualizado = controlCliente.actualizar(cliente, idCliente);

        if (actualizado) {
            JOptionPane.showMessageDialog(null, "¡Datos del cliente actualizados!");
            CargarTablaClientes();  // Refrescar tabla
            Limpiar();              // Limpiar formulario
        } else {
            JOptionPane.showMessageDialog(null, "¡Error al actualizar!");
        }
    }//GEN-LAST:event_jButton_actualizarActionPerformed

    /**
     * Evento que se ejecuta al presionar el botón "Eliminar".
     * Verifica que se haya seleccionado un cliente, y si es así,
     * llama al controlador para eliminar el cliente seleccionado.
     * Actualiza la tabla y limpia el formulario tras la operación.
     */
    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed

        Ctrl_Cliente controlCliente = new Ctrl_Cliente();

        if (idCliente == 0) {
            JOptionPane.showMessageDialog(null, "¡Seleccione un cliente para eliminar!");
            return;
        }

        // Confirmar eliminación
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar al cliente seleccionado?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = controlCliente.eliminar(idCliente);

            if (eliminado) {
                JOptionPane.showMessageDialog(null, "¡Cliente eliminado correctamente!");
                CargarTablaClientes();
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error al eliminar cliente!");
            }
        }
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_clientes;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
