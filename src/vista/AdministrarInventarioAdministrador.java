package vista;

import controlador.Conexion;
import controlador.Ctrl_Categoria;
import controlador.Ctrl_Producto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Producto;

/**
 *
 * @author ediso
 */
public class AdministrarInventarioAdministrador extends javax.swing.JInternalFrame {

    private int idProducto;
    int obtenerIdCategoriaCombo = 0;

    public AdministrarInventarioAdministrador() {
        initComponents();
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Productos");
        //Cargar tabla
        this.CargarTablaProductos();
        this.CargarComboCategoria();

        //insertar imagen en nuestro JLabel
        ImageIcon wallpaper = new ImageIcon("src/img/fondo4.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton_eliminar = new javax.swing.JButton();
        jButton_actualizar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_nombre = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jComboBox_iva = new javax.swing.JComboBox<>();
        jComboBox_categoria = new javax.swing.JComboBox<>();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Inventario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 730, 270));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_eliminar.setBackground(new java.awt.Color(255, 51, 51));
        jButton_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, -1));

        jButton_actualizar.setBackground(new java.awt.Color(51, 204, 0));
        jButton_actualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 130, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre:"));
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, -1));

        txt_cantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad Disponible:"));
        txt_cantidad.setEnabled(false);
        jPanel3.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 170, -1));

        txt_precio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_precio.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio:"));
        jPanel3.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 170, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("Color:"));
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 170, -1));

        jComboBox_iva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_iva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione iva:", "No grava iva", "12%", "14%" }));
        jPanel3.add(jComboBox_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 150, -1));

        jComboBox_categoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione categoria:", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 150, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 130));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        Ctrl_Producto controlProducto = new Ctrl_Producto();
        if (idProducto == 0) {
            JOptionPane.showMessageDialog(null, "¡Seleccione un Producto!");
        } else {
            if (!controlProducto.eliminar(idProducto)) {
                JOptionPane.showMessageDialog(null, "¡Producto Eliminado!");
                this.CargarTablaProductos();
                this.CargarComboCategoria();
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error al eliminar producto!");
            }
        }
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed

        Producto producto = new Producto();
        Ctrl_Producto controlProducto = new Ctrl_Producto();
        String iva = "";
        String categoria = "";
        iva = jComboBox_iva.getSelectedItem().toString().trim();
        categoria = jComboBox_categoria.getSelectedItem().toString().trim();

        //validar campos
        if (txt_nombre.getText().isEmpty() || txt_cantidad.getText().isEmpty() || txt_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        } else {
            if (iva.equalsIgnoreCase("Seleccione iva:")) {
                JOptionPane.showMessageDialog(null, "Seleccione iva.");
            } else {
                if (categoria.equalsIgnoreCase("Seleccione categoria:")) {
                    JOptionPane.showMessageDialog(null, "Seleccione categoria");
                } else {
                    try {
                        producto.setNombre(txt_nombre.getText().trim());
                        producto.setCantidad(Integer.parseInt(txt_cantidad.getText().trim()));
                        String precioTXT = "";
                        double Precio = 0.0;
                        precioTXT = txt_precio.getText().trim();
                        boolean aux = false;
                        /*
                        *Si el usuario ingresa , (coma) como punto decimal,
                        lo transformamos a punto (.)
                        */
                        for (int i = 0; i < precioTXT.length(); i++) {
                            if (precioTXT.charAt(i) == ',') {
                                String precioNuevo = precioTXT.replace(",", ".");
                                Precio = Double.parseDouble(precioNuevo);
                                aux = true;
                            }
                        }
                        //evaluar la condicion
                        if (aux == true) {
                            producto.setPrecio(Precio);
                        } else {
                            Precio = Double.parseDouble(precioTXT);
                            producto.setPrecio(Precio);
                        }

                        producto.setDescripcion(txt_descripcion.getText().trim());
                        //Porcentaje IVA
                        if (iva.equalsIgnoreCase("No grava iva")) {
                            producto.setPorcentajeIva(0);
                        } else if (iva.equalsIgnoreCase("12%")) {
                            producto.setPorcentajeIva(12);
                        } else if (iva.equalsIgnoreCase("14%")) {
                            producto.setPorcentajeIva(14);
                        }

                        //idcategoria - cargar metodo que obtiene el id de categoria
                        this.IdCategoria();
                        producto.setIdCategoria(obtenerIdCategoriaCombo);
                        producto.setIdCategoria(1);

                        if (controlProducto.actualizar(producto, idProducto)) {
                            JOptionPane.showMessageDialog(null, "Registro Actualizado");
                            this.CargarComboCategoria();
                            this.CargarTablaProductos();
                            this.jComboBox_iva.setSelectedItem("Seleccione iva:");
                            this.Limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Actualizar");
                        }
                    } catch (HeadlessException | NumberFormatException e) {
                        System.out.println("Error en: " + e);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_actualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JComboBox<String> jComboBox_categoria;
    private javax.swing.JComboBox<String> jComboBox_iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables

    /*
     * *****************************************************
     * metodo para limpiar
     * *****************************************************
     */
    private void Limpiar() {
        txt_nombre.setText("");
        txt_cantidad.setText("");
        txt_precio.setText("");
        txt_descripcion.setText("");
        jComboBox_iva.setSelectedItem("Seleccione iva:");
        jComboBox_categoria.setSelectedItem("Seleccione categoria:");
    }

    /*
     * *****************************************************
     * metodo para cargar las categorias en el JCombox
     * *****************************************************
     */
    private void CargarComboCategoria() {
        Connection cn = Conexion.conectar();
        String sql = "select * from tb_categoria";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_categoria.removeAllItems();
            jComboBox_categoria.addItem("Seleccione categoria:");
            while (rs.next()) {
                jComboBox_categoria.addItem(rs.getString("descripcion"));
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al cargar categorias!");
        }
    }

    /*
     * *****************************************************
     * metodo para mostrar todos los productos registrados
     * *****************************************************
     */
    String descripcionCategoria = "";
    double precio = 0.0;
    int porcentajeIva = 0;
    double IVA = 0;

    private void CargarTablaProductos() {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIva, c.descripcion " + "FROM tb_producto AS p " + "JOIN tb_categoria AS c ON p.idCategoria = c.idCategoria";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            AdministrarInventarioAdministrador.jTable_productos = new JTable(model);
            AdministrarInventarioAdministrador.jScrollPane1.setViewportView(AdministrarInventarioAdministrador.jTable_productos);

            model.addColumn("N°");//ID
            model.addColumn("nombre");
            model.addColumn("cantidad");
            model.addColumn("precio");
            model.addColumn("color");
            model.addColumn("Iva");
            model.addColumn("Categoria");
           

            while (rs.next()) {

                precio = rs.getDouble("precio");
                porcentajeIva = rs.getInt("porcentajeIva");

                Object fila[] = new Object[7];
                for (int i = 0; i < 7; i++) {

                    if (i == 5) {
                        this.calcularIva(precio, porcentajeIva);//metodo
                        fila[i] = IVA;
                        rs.getObject(i + 1);
                    } else {
                        fila[i] = rs.getObject(i + 1);
                    }
                }
                model.addRow(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla productos: " + e);
        }
        //evento para obtener campo al cual el usuario da click
        //y obtener la interfaz que mostrara la informacion general
        jTable_productos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_productos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idProducto = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosProductoSeleccionado(idProducto);//metodo
                }
            }
        });
    }

    /*
     * **************************************************
     * Metodo para calcular Iva
     * **************************************************
     */
    private double calcularIva(double precio, int iva) {
        int p_iva = iva;
        switch (p_iva) {
            case 0:
                IVA = 0.0;
                break;
            case 12:
                IVA = precio * 0.12;
                break;
            case 14:
                IVA = precio * 0.14;
                break;
            default:
                break;
        }
        //redondear decimales
        IVA = (double) Math.round(IVA * 100) / 100;
        return IVA;
    }

    /*
     * **************************************************
     * Metodo que envia datos seleccionados
     * **************************************************
     */
    private void EnviarDatosProductoSeleccionado(int idProducto) {
        try {
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "select * from tb_producto where idProducto = '" + idProducto + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt_nombre.setText(rs.getString("nombre"));
                txt_cantidad.setText(rs.getString("cantidad"));
                txt_precio.setText(rs.getString("precio"));
                txt_descripcion.setText(rs.getString("descripcion"));
                int iva = rs.getInt("porcentajeIva");
                switch (iva) {
                    case 0:
                        jComboBox_iva.setSelectedItem("No grava iva");
                        break;
                    case 12:
                        jComboBox_iva.setSelectedItem("12%");
                        break;
                    case 14:
                        jComboBox_iva.setSelectedItem("14%");
                        break;
                    default:
                        jComboBox_iva.setSelectedItem("Seleccione iva:");
                        break;
                }
                int idCate = rs.getInt("idCategoria");
                jComboBox_categoria.setSelectedItem(relacionarCategoria(idCate));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar producto: " + e);
        }
    }

    /*
     * **************************************************
     * Metodo para relacionar categorias
     * **************************************************
     */
    private String relacionarCategoria(int idCategoria) {

        String sql = "select descripcion from tb_categoria where idCategoria = '" + idCategoria + "'";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                descripcionCategoria = rs.getString("descripcion");
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("¡Error al obtener el id de la categoria!");
        }
        return descripcionCategoria;
    }

    /**
     *
     * Metodo para obtener id categoria
     */
    private int IdCategoria() {
        String sql = "select * from tb_categoria where descripcion = '" + this.jComboBox_categoria.getSelectedItem() + "'";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obtenerIdCategoriaCombo = rs.getInt("idCategoria");
            }
        } catch (SQLException e) {
            System.out.println("Error al obener id categoria");
        }
        return obtenerIdCategoriaCombo;
    }
}
