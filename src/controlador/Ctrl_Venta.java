package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.CabeceraVenta;

public class Ctrl_Venta {
    public boolean guardarCabeceraVenta(CabeceraVenta cabecera) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "INSERT INTO tb_cabecera_venta (idCabeceraventa, idCliente, valorPagar, fechaVenta, estado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement consulta = cn.prepareStatement(sql);

            consulta.setInt(1, cabecera.getIdCabeceraventa());
            consulta.setInt(2, cabecera.getIdCliente());
            consulta.setDouble(3, cabecera.getValorPagar());

            // Formatear la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(new Date());
            consulta.setString(4, fechaFormateada);
            
            consulta.setInt(5, cabecera.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }

    public static void main(String[] args) {
        Ctrl_Venta ctrl = new Ctrl_Venta();
        
        // Crear una instancia de CabeceraVenta
        CabeceraVenta cabecera = new CabeceraVenta();
        cabecera.setIdCabeceraventa(0); // Se asume auto-incremento en la base de datos
        cabecera.setIdCliente(1); // ID del cliente
        cabecera.setValorPagar(150.00); // Valor a pagar
        cabecera.setFechaVenta(""); // Se establecerá la fecha actual automáticamente
        cabecera.setEstado(1); // Estado de la venta

        boolean guardado = ctrl.guardarCabeceraVenta(cabecera);
        System.out.println("Cabecera de venta guardada: " + guardado);
    }
}
