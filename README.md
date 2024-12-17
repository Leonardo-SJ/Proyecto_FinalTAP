# Documentacion Concesionaria Biker
## Funcionalidad del proyecto
### Login
![image](https://github.com/user-attachments/assets/863207c1-2919-4a3a-9103-a5731e34cdb4)

¿Que hace el inicio de sesion?
Este nos permite acceder como administrador o como usuario, le tedremos que pasar el nombre de usuario y contreseña correctos
Si este es correcto nos enseñara un mensaje, Bienvenido * usuario n*, dependiendo del usuario nos dira si es empleado o administrador y nos dara la interfaz correspondiente


![image](https://github.com/user-attachments/assets/238c1811-8abe-46fe-bb54-9fb38c132f8a)


![image](https://github.com/user-attachments/assets/8f514fa8-d0c1-4f91-b3e2-c6bf03a85998)

*
![image](https://github.com/user-attachments/assets/e3e64184-903e-4d2d-9193-98e06a8d6905)


![image](https://github.com/user-attachments/assets/f5371e77-4555-41e5-9489-dd0eaef702eb)


Si el usuario o contraseñas son incorrectas nos mostrara el mensaje correspondiente

![image](https://github.com/user-attachments/assets/78f81ac6-faba-416b-822b-7e597bc5d0ee)


## Administrador

El administrador cuenta con cinco diferentes botones:
* Empleado: nos permite agregar nuevos emplaedos y gestionar los que ya estan daso de alta en la base de datos.
![image](https://github.com/user-attachments/assets/c2dc1c30-ceb0-4ce1-8f97-8559610bb65e)
![image](https://github.com/user-attachments/assets/bd7ad2ea-6867-4002-bc76-008f9a1ba035)

* Inventario el cual nos permite agregar productos al inventario, gestionarlo o agregar cantidades del producto a la base de datos corresponidente en la base de datos de almacen.
![image](https://github.com/user-attachments/assets/f4184279-7498-434b-b93a-776da5757162)
![image](https://github.com/user-attachments/assets/fb1aa7d5-4bbc-4c7c-b81e-8797d1e7acb9)


* Ventas el cual nos permite visualizar las ventas que se han realizado.
* Categoria: nos permite gestionar la categorias de las motos.


## Empleado

![image](https://github.com/user-attachments/assets/3ee2a5d4-f662-4768-8b6d-7e8b13f8068e)

El empleado cuenta con dos diferentes botones:
* Inventario el cual nos permite a nosotros poder checar la cantidad de motos actualmente disponibles
* Factura el cual nos permite quenerar un venta y enviar la factura por correo

## Clases de control
### Clase Conexion
#### Codigo
```java
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Equipo15
 */
public class Conexion {

    // Conexión local con SSL deshabilitado
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bd_sistema_concesionario?useSSL=false", "root", "25112004"
            );
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexión local: " + e);
        }
        return null;
    }
}
```
#### Importaciones
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
```
• Connection: Representa la conexión con la base de datos.

• DriverManager: Gestiona los controladores JDBC y facilita la obtención de conexiones 
con la base de datos.
• SQLException: Maneja las excepciones relacionadas con operaciones de bases de datos.
#### Definición del método
```java
   public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bd_sistema_concesionario?useSSL=false", "root", "25112004"
            );
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexión local: " + e);
        }
        return null;
    }
```
• El método conectar()es static, lo que significa que se puede llamar sin necesidad de 
crear una instancia de la clase Conexion.

• Tipo de retorno : Devuelve un objeto de tipo Connectionsi la conexión es exitosa; de lo 
contrario, devuelve null
#### Configuración de la conexión
```java
 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_concesionario?useSSL=false", "root", "25112004");
```
DriverManager.getConnection(): Método estático que establece la conexión con 
la base de datos.

• Cadena de conexión :

o jdbc:mysql://: Especifica el protocolo JDBC para una base de datos 
MySQL.

o localhost: Indica que la base de datos está alojada en la misma máquina 
(servidor local).

o bd_sistema_concesionario: Nombre de la base de datos.

o useSSL=false: Desactiva el uso de SSL (Secure Sockets Layer) para 
conexiones locales. Esto es común en entornos de desarrollo.

• "root": Usuario de la base de datos.
• "angelsebas2004": Contraseña del usuario root .

#### Excepciones
```java
catch (SQLException e) {
            System.out.println("Error en la conexión local: " + e);
        }
```
* Si hay algún problema al establecer la conexión, como un error en la cadena de conexión, 
usuario o contraseña incorrectos, o la base de datos no está disponible, se lanza una 
SQLException.
* El mensaje de error se imprime en la consola.
  
#### Return
* Si la conexión se establece correctamente, el objeto Connection( cn) se devuelve al 
llamador.
* Si ocurre un error, el método devuelve null.
### Clase Ctrl_Categoria
```java
package controlador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Categoria;

/**
 *
 * @author Equipo15
 */
public class Ctrl_Categoria {

    /**
     * **************************************************
     * metodo para guardar una nueva categoria
     * **************************************************
     */
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = controlador.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cartegoria: " + e);
        }

        return respuesta;
    }

    /**
     * ********************************************************************
     * metodo para consultar si la categoria registrado ya existe
     * ********************************************************************
     */
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar cartegoria: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para actualizar una nueva categoria
     * **************************************************
     */
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = controlador.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria ='" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cartegoria: " + e);
        }

        return respuesta;
    }
    
    
    /**
     * **************************************************
     * metodo para eliminar una nueva categoria
     * **************************************************
     */
    public boolean eliminar(int idColumna) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_categoria where idColumna ='" + idColumna + "'");
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cartegoria: " + e);
        }

        return respuesta;
    }
}

```
Importaciones
```java 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Categoria;
```
* Connection: Representa la conexión con la base de datos.
* PreparedStatement: Objeto para ejecutar consultas SQL parametrizadas.
* ResultSet: Almacena resultados de consultas SQL SELECT.
* SQLException: Maneja errores relacionados con la base de datos.
* Statement: Ejecuta consultas SQL no parametrizadas.
* Categoria: Clase modelo que almacena los atributos de una categoría.

Método guardar
```java
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = controlador.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cartegoria: " + e);
        }

        return respuesta;
    }
```
Descripción
1. Objetivo : Inserta una nueva categoría en la tabla tb_categoria.
2. Parámetros : Recibe un objeto Categoria, que contiene la descripción y estado de la 
categoría.
3. Lógica :
o Se conecta a la base de datos usando Conexion.conectar().
o Utilice un PreparedStatementpara la consulta parametrizada INSERT.
o setIntysetString : Asigna valores a los parámetros de la consulta.
▪ 1: Valor 0(se supone que el ID es autoincremental).
▪ 2: Descripción de la categoría.
▪ 3: Estado de la categoría.
o executeUpdate: Ejecuta la consulta y devuelve el número de filas afectadas.
4. Cierre : Cierra la conexión al final.

Método existe Categoría
```java
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar cartegoria: " + e);
        }
        return respuesta;
    }
```

Descripción
1. Objetivo : Comprueba si una categoría ya existe en la base de datos.
2. Parámetros : Recibe un Stringcon el nombre de la categoría.
3. Lógica :
o Se conecta a la base de datos.
o Se ejecuta una consulta SQL SELECTmediante un Statement.
o Si el ResultSetdevuelve alguna fila, se establece respuesta = true(categoría 
existente)

Método actualizar
```java
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = controlador.Conexion.conectar();
        try {
 
            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria ='" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cartegoria: " + e);
        }

        return respuesta;
    }
```
Descripción
1. Objetivo : Actualiza la descripción de una categoría existente.
2. Parámetros :
o objeto: Contiene la nueva descripción.
o idCategoria: ID de la categoría que se desea actualizar.
3. Lógica :
o Utilice uno PreparedStatementpara la consulta UPDATE.
o setStringasigna la nueva descripción.
o La condición where idCategoria = ? asegura que solo se actualizará el 
registro correcto.

Método eliminar

```java
    public boolean eliminar(int idColumna) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_categoria where idColumna ='" + idColumna + "'");
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cartegoria: " + e);
        }

        return respuesta;
    }
```
Descripción
1. Objetivo : Eliminar una categoría de la tabla tb_categoria.
2. Parámetros : Recibe el idColumnaque identifica la fila a eliminar.
3. Lógica :
o Utilice uno PreparedStatementcon una consulta DELETE.
o La condición where idColumna = ?identifica qué fila eliminar.
o Error : El método se ejecuta executeUpdatedos veces. Solo una llamada es 
necesaria.

### Clase Ctrl_Cliente
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;

/**
 *
 * @author Equipo15
 */
public class Ctrl_Cliente {

    /**
     * **************************************************
     * metodo para guardar un nuevo clientw
     * **************************************************
     */
   public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getCedula());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        return respuesta;
   }
    
    /**
     * ********************************************************************
     * metodo para consultar si el producto ya esta registrado en la BBDD
     * ********************************************************************
     */
    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        String sql = "select cedula from tb_cliente where cedula = '" + cedula + "';";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para actualizar un cliente
     * **************************************************
     */
   

    public boolean actualizar(Cliente cliente, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_cliente SET nombre = ?, apellido = ?, cedula = ?, telefono = ?, direccion = ? WHERE idCliente = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getCedula());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getDireccion());
            pst.setInt(6, idCliente);
            
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    
}


    /**
     * **************************************************
     * metodo para eliminar un cliente
     * **************************************************
     */
       public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_cliente where idCliente ='" + idCliente + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }



}
```
Importaciones
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;
```
Estas importaciones son necesarias para trabajar con JDBC (Java Database Connectivity) y realizar 
operaciones con la base de datos. También se importan clases para mostrar cuadros de diálogo y 
las clases Cliente y Empleado.
```java
   public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getCedula());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        return respuesta;
   }
```
Descripción :
1. Objetivo : Insertar un nuevo cliente en la tabla tb_cliente.
2. Parámetros :
o Recibe un objeto Clienteque contenga datos como nombre, apellido, 
cedula, etc.
3. Lógica :
o Se conecta a la base de datos.
o Prepare una consulta SQL parametrizada para insertar un registro.
o setIntysetString : Asigna valores a los campos en el orden indicado.
o Ejecuta la consulta con executeUpdate(), que devuelve el número de filas 
afectadas.
o Si es mayor que 0, la operación fue exitosa.

Método existe Cliente
```java
    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        String sql = "select cedula from tb_cliente where cedula = '" + cedula + "';";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Verificar si un cliente con una cédula específica ya está registrado.
2. Parámetros :
o Recibe un Stringque contiene la cédula del cliente.
3. Lógica :
o Construya una consulta SQL para buscar la cédula en la tabla tb_cliente.
o Si ResultSetdevuelve algún registro, se establece respuesta = true.

Método actualizar
```java
    public boolean actualizar(Cliente cliente, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_cliente SET nombre = ?, apellido = ?, cedula = ?, telefono = ?, direccion = ? WHERE idCliente = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getCedula());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getDireccion());
            pst.setInt(6, idCliente);
            
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    
}
```
Descripción :
1. Objetivo : Actualizar la información de un cliente existente.
2. Parámetros :
o Cliente cliente: Contiene los nuevos valores para el cliente.
o int idCliente: El ID del cliente a actualizar.
3. Lógica :
o Se prepara una consulta UPDATE con parámetros ?.
o Asigna los valores actualizados usando setStringy setInt.
o Ejecuta la consulta con executeUpdate().
o Si la cantidad de filas afectadas es mayor que 0, la operación fue exitosa.

Método eliminar
```java
       public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_cliente where idCliente ='" + idCliente + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Eliminar un cliente de la tabla tb_cliente.
2. Parámetros :
o int idCliente: El ID del cliente que se desea eliminar.
3. Lógica :
o Construya una consulta SQL DELETEpara eliminar un registro basado en el ID .
o Error : Se llama executeUpdate()dos veces. Esto no es necesario y es 
incorrecto.
4. Problema de seguridad : La concatenación del parámetro idClienteen la consulta 
introduce la inyección SQL .

### Clase Ctrl_Producto
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.AdministrarInventarioAdministrador;

/**
 *
 * @author Edison Zambrano - © Programador Fantasma
 */
public class Ctrl_Producto {
     /**
     * **************************************************
     * metodo para guardar un nuevo producto
     * **************************************************
     */
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIva());
            consulta.setInt(7, objeto.getIdCategoria());
           

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }

        return respuesta;
    }

    /**
     * ********************************************************************
     * metodo para consultar si el producto ya esta registrado en la BBDD
     * ********************************************************************
     */
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para actualizar un producto
     * **************************************************
     */

    public boolean actualizar(Producto producto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_producto SET nombre = ?, cantidad = ?, precio = ?, descripcion = ?, porcentajeIva = ?, idCategoria = ? WHERE idProducto = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getCantidad());
            pst.setDouble(3, producto.getPrecio());
            pst.setString(4, producto.getDescripcion());
            pst.setInt(5, producto.getPorcentajeIva());
            pst.setInt(6, producto.getIdCategoria());
            pst.setInt(7, idProducto);

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }


    
    
    /**
     * **************************************************
     * metodo para eliminar un producto
     * **************************************************
     */
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_producto where idProducto ='" + idProducto + "'");
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }
    
    /**
     * **************************************************
     * metodo para actualizar stock un producto
     * **************************************************
     */
    
     public boolean actualizarStock(Producto object, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto ='" + idProducto + "'");
            consulta.setInt(1, object.getCantidad());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto: " + e);
        }
        return respuesta;
    }
     
}
```


Importaciones
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.AdministrarInventarioAdministrador;
```
* Connection: Representa la conexión con la base de datos
* PreparedStatement: Permite ejecutar consultas SQL parametrizadas (seguras y 
eficientes).
* ResultSet: Contiene los resultados devueltos por una consulta SQL.
* SQLException: Captura de errores relacionados
* Statement: Ejecuta consultas SQL sin parámetros (menos seguro).
* Producto: Clase modelo que representa un producto con sus atr
* DefaultTableModel: Se usa para manejar datos en tabla
* AdministrarInventarioAdministrador: Posiblemente una interfaz grafica

  Metodo guardar el producto
```java
      public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIva());
            consulta.setInt(7, objeto.getIdCategoria());
           

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }

        return respuesta;
    }
```
  Descripción :
1. Objetivo : Insertar un nuevo producto en la tabla tb_producto.
2. Parámetros : Un objeto Productoque contiene los datos del producto (nomb
3. Lógica :
o Se utiliza una consulta SQL parametrizada para insertar valores.
o Se ejecuta la consulta y se verifica si executeUpdate()devuelve

Método existeProducto
```java
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Verificar si un producto ya existe en la base de datos.
2. Parámetros : El nombre del producto como String.
3. Lógica :
o Se ejecuta una consulta usando SQL Statement(lo cual no es seguro por la 
posibilidad de inyección SQL ).
o Si ResultSetdevuelve algún registro, se establece la variable respuestacomo 
true.

Método actualizar
```java
    public boolean actualizar(Producto producto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_producto SET nombre = ?, cantidad = ?, precio = ?, descripcion = ?, porcentajeIva = ?, idCategoria = ? WHERE idProducto = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getCantidad());
            pst.setDouble(3, producto.getPrecio());
            pst.setString(4, producto.getDescripcion());
            pst.setInt(5, producto.getPorcentajeIva());
            pst.setInt(6, producto.getIdCategoria());
            pst.setInt(7, idProducto);

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Actualizar los datos de un producto específico.
2. Parámetros :
o Producto objeto: Contiene los nuevos datos del producto.
o int idProducto: Identificador del producto a actualizar.
3. Lógica :
o Se utiliza una consulta UPDATEparametrizada.
o Se asignan los nuevos valores con setString, setInt, etc.

Método eliminar
```java
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_producto where idProducto ='" + idProducto + "'");
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Eliminar un producto de la base de datos.
2. Parámetros : int idProducto, el ID del producto a eliminar

Método actualizarStock
```java
     public boolean actualizarStock(Producto object, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto ='" + idProducto + "'");
            consulta.setInt(1, object.getCantidad());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Actualizar únicamente la cantidad (stock) de un producto.
2. Parámetros :
o Producto object: Contiene el nuevo valor de la cantidad.
o int idProducto: Identificador del producto.
### Clase Ctrl_RegistrarVenta
```java
import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

/**
 * @author edison
 */
public class Ctrl_RegistrarVenta {
    
    //ultimo id de la cabecera
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;
    /**
     * **************************************************
     * metodo para guardar la cabecera de venta
     * **************************************************
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cabecera_venta values(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0);//id
            consulta.setInt(2, objeto.getIdCliente());
            consulta.setDouble(3, objeto.getValorPagar());
            consulta.setString(4, objeto.getFechaVenta());
            consulta.setInt(5, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            
            ResultSet rs = consulta.getGeneratedKeys();
            while(rs.next()){
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para guardar el detalle de venta
     * **************************************************
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubTotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getIva());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }
    
    
         /**
     * **************************************************
     * metodo para actualizar un producto
     * **************************************************
     */
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_cabecera_venta set idCliente = ?, estado = ? "
                            + "where idCabeceraVenta ='" + idCabeceraVenta + "'");
            consulta.setInt(1, objeto.getIdCliente());
            consulta.setInt(2, objeto.getEstado());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        }
        return respuesta;
    }


    public void llenarTablaVentas() {
        Connection cn = Conexion.conectar();
        String sql = "SELECT cv.idCabeceraventa, cv.idCliente, cv.ValorPagar, cv.fechaVenta, cv.estado, c.nombre, c.apellido " +
                     "FROM tb_cabecera_venta cv " +
                     "INNER JOIN tb_cliente c ON cv.idCliente = c.idCliente";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Procesar los resultados
                int idCabeceraventa = rs.getInt("idCabeceraventa");
                int idCliente = rs.getInt("idCliente");
                double ValorPagar = rs.getDouble("ValorPagar");
                String fechaVenta = rs.getString("fechaVenta");
                int estado = rs.getInt("estado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                // Aquí puedes agregar código para llenar la tabla en la interfaz de usuario
                // ...
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de ventas: " + e);
        }
    }

    public static void main(String[] args) {
        Ctrl_RegistrarVenta ctrl = new Ctrl_RegistrarVenta();
        ctrl.llenarTablaVentas();
    }
}
```
```java

import controlador.Conexion; // Clase que gestiona la conexión a la base de datos.
import java.sql.Connection; // Representa la conexión con la base de datos.
import java.sql.PreparedStatement; // Permite consultas SQL parametrizadas.
import java.sql.ResultSet; // Contiene los resultados de una consulta SQL.
import java.sql.SQLException; // Captura errores relacionados con la base de datos.
import java.sql.Statement; // Ejecuta consultas SQL sin parámetros.
import modelo.CabeceraVenta; // Clase modelo que representa la cabecera de una venta.
import modelo.DetalleVenta; // Clase modelo que representa el detalle de una venta.
```

Método guardar
Descripción :
1. Objetivo : Guardar un registro en la tabla tb_cabecera_venta(cabecera de una venta).
2. Lógica :
o Se inserta un nuevo registro y se devuelve el ID autogenerado usando 
Statement.RETURN_GENERATED_KEYS.
o El ID generado se almacena idCabeceraRegistradapara poder asociar los 
detalles de la venta.

Método guardarDetalle
```java
public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubTotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getIva());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Guardar los detalles de la venta (productos comprados) en la tabla 
tb_detalle_venta.
2. Lógica :
o Se enlaza cada detalle de venta con la cabecera utilizando el atributo 
idCabeceraRegistrada.
o Se almacenan valores como ID del producto, cantidad, precio unitario, subtotal, 
descuento, IVA y total.

Método actualizar
```java
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_cabecera_venta set idCliente = ?, estado = ? "
                            + "where idCabeceraVenta ='" + idCabeceraVenta + "'");
            consulta.setInt(1, objeto.getIdCliente());
            consulta.setInt(2, objeto.getEstado());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        }
        return respuesta;
    }
```
Descripción :
1. Objetivo : Actualizar la cabecera de una venta existente.
2. Lógica :
o Se actualizarán valores como el ID del cliente y el estado de la venta.
o El ID de la cabecera se utiliza para identificar el registro y modificarlo.

Método llenarTablaVentas
```java
    public void llenarTablaVentas() {
        Connection cn = Conexion.conectar();
        String sql = "SELECT cv.idCabeceraventa, cv.idCliente, cv.ValorPagar, cv.fechaVenta, cv.estado, c.nombre, c.apellido " +
                     "FROM tb_cabecera_venta cv " +
                     "INNER JOIN tb_cliente c ON cv.idCliente = c.idCliente";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Procesar los resultados
                int idCabeceraventa = rs.getInt("idCabeceraventa");
                int idCliente = rs.getInt("idCliente");
                double ValorPagar = rs.getDouble("ValorPagar");
                String fechaVenta = rs.getString("fechaVenta");
                int estado = rs.getInt("estado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                // Aquí puedes agregar código para llenar la tabla en la interfaz de usuario
                // ...
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de ventas: " + e);
        }
    }
```
Descripción :
1. Objetivo : Obtener los datos de las ventas (cabecera y cliente) y llenar una tabla en la 
interfaz gráfica.
2. Lógica :
o Realice una consulta SQL con un INNER JOIN para combinar información de 
tb_cabecera_ventay tb_cliente.
o Itera sobre los resultados para procesar los datos.

### Clase Reportes
```java
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edison Zambrano
 */
public class Reportes {

    /* ********************************************************************
    * metodo para crear reportes de los clientes registrados en el sistema
    *********************************************************************** */
    public void ReportesClientes() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select idCliente, concat(nombre, ' ', apellido) as nombres, cedula, telefono, direccion from tb_cliente");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
    /* ********************************************************************
    * metodo para crear reportes de los productos registrados en el sistema
    *********************************************************************** */
    public void ReportesProductos() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Productos.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Productos \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("Por. Iva");
            tabla.addCell("Categoria");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, "
                                + "p.porcentajeIva, c.descripcion as categoria, p.estado "
                                + "from tb_producto as p, tb_categoria as c "
                                + "where p.idCategoria = c.idCategoria;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
        /* ********************************************************************
    * metodo para crear reportes de los categorias registrados en el sistema
    *********************************************************************** */
    public void ReportesCategorias() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Categorias.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Categorias \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from tb_categoria");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
    
        /* ********************************************************************
    * metodo para crear reportes de las ventas registrados en el sistema
    *********************************************************************** */
    public void ReportesVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Ventas.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 9, 4, 5, 3};

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Cliente");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha Venta");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                                + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                                + "from tb_cabecera_venta as cv, tb_cliente as c "
                                + "where cv.idCliente = c.idCliente;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));

                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }

}
```
Importaciones
```java
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
```
1. Librerías de iText : Para trabajar con documentos PDF, tablas, imágenes, fuentes y colores.
2. Librerías estándar : Para manejo de excepciones, entradas/salidas de archivos, y ventanas 
emergentes.
3. Librerías de JDBC : Para la conexión y consultas a la base de datos.

   Método ReportesClientes
```java
public void ReportesClientes() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select idCliente, concat(nombre, ' ', apellido) as nombres, cedula, telefono, direccion from tb_cliente");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
```
Propósito : Genera un reporte PDF con la lista de clientes registrados en la base de datos.
• Pasos principales :
1. Preparar el documento :
▪ Crear un objeto Document.
▪ Definir la ruta del archivo PDF como el escritorio del usuario.
▪ Agregue una imagen ( header) como encabezado al documento.
▪ Agregue un párrafo que sirve como título del informe.
2. Crear la tabla :
▪ Se usa una tabla ( PdfPTable) con 5 columnas: Código, Nombres, Cédula, 
Teléfono y Dirección.
▪ Realice una consulta SQL para recuperar los datos desde la tabla 
tb_cliente.
▪ Agrega los datos de la base de datos a las celdas de la tabla.
3. Finalizar el documento :
▪ Agregar la tabla al PDF.
▪ Cerrar el documento.
▪ Mostrar un mensaje de confirmación al usuario.

Método ReportesProductos
```java
    public void ReportesProductos() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Productos.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Productos \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("Por. Iva");
            tabla.addCell("Categoria");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, "
                                + "p.porcentajeIva, c.descripcion as categoria, p.estado "
                                + "from tb_producto as p, tb_categoria as c "
                                + "where p.idCategoria = c.idCategoria;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
```
Propósito : Genera un reporte PDF con los productos registrados.
• Diferencias con el método anterior :
o La tabla tiene 7 columnas y anchos personalizados.
o Se consulta la tabla tb_productoy se unen datos de tb_categoriapara 
mostrar la categoría de cada producto.
o Las columnas incluyen: Código, Nombre, Cantidad, Precio, Descripción, Porcentaje 
IVA y Categoría.

Método ReportesCategorias
```java
    public void ReportesCategorias() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Categorias.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Categorias \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from tb_categoria");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
```
Propósito : Genera un reporte PDF de categorías de productos.
• Diferencias :
o La tabla tiene 3 columnas: Código, Descripción y Estado.
o Se consulta la tabla tb_categoria.

Método ReportesVentas
```java
    public void ReportesVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Ventas.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 9, 4, 5, 3};

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Cliente");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha Venta");
            tabla.addCell("Estado");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                                + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                                + "from tb_cabecera_venta as cv, tb_cliente as c "
                                + "where cv.idCliente = c.idCliente;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));

                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);
        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
        }
    }
```
Propósito : Genera un reporte PDF con las ventas registradas.
• Diferencias :
o La tabla tiene 5 columnas: Código, Cliente, Total a Pagar, Fecha Venta y Estado.
o Se consulta la tabla tb_cabecera_ventay se unen datos de tb_cliente.


### Clase VentaPDF
```java
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Conexion;
import controlador.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.CrearFactura;

/**
 *
 * @author Equipo15
 */
public class VentaPDF {

    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    //metodo para obtener datos del cliente
    public void DatosCliente(int idCliente) {
    Connection cn = Conexion.conectar();
    String sql = "SELECT * FROM tb_cliente WHERE idCliente = '" + idCliente + "'";
    Statement st;
    try {
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
            cedulaCliente = rs.getString("cedula");
            telefonoCliente = rs.getString("telefono");
            direccionCliente = rs.getString("direccion");
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener datos del cliente: " + e);
    }
}

// Método para generar la factura de venta
public String getNombreArchivoPDFVenta() {
    return nombreArchivoPDFVenta;
}
    //metodo para generar la factura de venta
    public void generarFacturaPDF() {
        
        try {

            //cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            //cambiar el formato de la fecha de / a _
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }

            nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";
//String rutaPDF = "C:\\Users\\Angel\\Downloads\\proyecto\\SistemaDeventas1\\src\\pdf\\" + nombreArchivoPDFVenta;
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/emblema.jpeg");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE); //agregar nueva linea
            fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);//quitar el borde de la tabla
            //tamaño de las celdas
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar celdas
            Encabezado.addCell(img);

            
            String nombre = "Concecionaria Biker";
            String razon = "Biker: Más que motos, ofrecemos el sueño de libertad y la emoción del viento en tu rostro.";
            String telefono = "9711497982";
            String direccion = "Calzada Francisco I. MAdero,Oaxaca,México";
            String ruc = "1234567890";
      

            Encabezado.addCell("");//celda vacia
            Encabezado.addCell("RUC: " + ruc + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nESLOGAN: " + razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            //CUERPO
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);//nueva linea
            cliente.add("Datos del cliente: " + "\n\n");
            doc.add(cliente);

            //DATOS DEL CLIENTE
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);//quitar bordes
            //tamaño de las celdas
            float[] ColumnaCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(ColumnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("Cedula/RUC: "));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre: "));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono: "));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion: "));
            //quitar bordes 
            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            //agg celda a la tabla
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(cedulaCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            //agregar al documento
            doc.add(tablaCliente);
            
            //ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);
            
            //AGREGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de celdas
             float[] ColumnaProducto = new float[]{15f, 50f, 15f, 20f};
             tablaProducto.setWidths(ColumnaProducto);
             tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
             PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: "));
             PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: "));
             PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: "));
             PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: "));
             //quitar bordes
             producto1.setBorder(0);
             producto2.setBorder(0);
             producto3.setBorder(0);
             producto4.setBorder(0);
             //agregar color al encabezadi del producto
             producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //agg celda a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);
            
            for(int i = 0; i < CrearFactura.jTable_productos.getRowCount(); i++){
                String producto = CrearFactura.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = CrearFactura.jTable_productos.getValueAt(i, 2).toString();
                String precio = CrearFactura.jTable_productos.getValueAt(i, 3).toString();
                String total = CrearFactura.jTable_productos.getValueAt(i, 7).toString();
                
                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }
            
            //agregar al documento
            doc.add(tablaProducto);
            
            //Total pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + CrearFactura.txt_total_pagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
           
            //Mensaje
           Paragraph mensaje = new Paragraph();
           mensaje.add(Chunk.NEWLINE);
           mensaje.add("¡Gracias por su compra,Vuelva pronto!");
           mensaje.setAlignment(Element.ALIGN_CENTER);
           doc.add(mensaje);
           
           //cerrar el documento y el archivo
           doc.close();
           archivo.close();
           
           //abrir el documento al ser generado automaticamente
            Desktop.getDesktop().open(file);
            
            
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }

}
```

```java
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Conexion;
import controlador.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.CrearFactura;
```
iTextPDF : Biblioteca para crear documentos PDF.
• com.itextpdf.text: Contiene las clases principales para crear y manipular texto, 
tablas, imágenes y más.
• java.awt.Desktop: Para abrir el archivo PDF automáticamente después de generarlo.

Método DatosCliente
```java
    public void DatosCliente(int idCliente) {
    Connection cn = Conexion.conectar();
    String sql = "SELECT * FROM tb_cliente WHERE idCliente = '" + idCliente + "'";
    Statement st;
    try {
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
            cedulaCliente = rs.getString("cedula");
            telefonoCliente = rs.getString("telefono");
            direccionCliente = rs.getString("direccion");
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener datos del cliente: " + e);
    }
}

```
Este método extrae los datos de un cliente específico desde la base de datos utilizando un 
idCliente.
* Se conecta a la base de datos usando la clase Conexion.
* Ejecute una consulta SQL para obtener los datos del cliente.
* Asigna los valores al cliente ( nombreCliente, cedulaCliente, etc.).

Método generarFacturaPDF
```java
    public void generarFacturaPDF() {
        
        try {

            //cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            //cambiar el formato de la fecha de / a _
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }

            nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";
//String rutaPDF = "C:\\Users\\Angel\\Downloads\\proyecto\\SistemaDeventas1\\src\\pdf\\" + nombreArchivoPDFVenta;
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/emblema.jpeg");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE); //agregar nueva linea
            fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);//quitar el borde de la tabla
            //tamaño de las celdas
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar celdas
            Encabezado.addCell(img);

            
            String nombre = "Concecionaria Biker";
            String razon = "Biker: Más que motos, ofrecemos el sueño de libertad y la emoción del viento en tu rostro.";
            String telefono = "9711497982";
            String direccion = "Calzada Francisco I. MAdero,Oaxaca,México";
            String ruc = "1234567890";
      

            Encabezado.addCell("");//celda vacia
            Encabezado.addCell("RUC: " + ruc + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nESLOGAN: " + razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            //CUERPO
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);//nueva linea
            cliente.add("Datos del cliente: " + "\n\n");
            doc.add(cliente);

            //DATOS DEL CLIENTE
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);//quitar bordes
            //tamaño de las celdas
            float[] ColumnaCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(ColumnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("Cedula/RUC: "));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre: "));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono: "));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion: "));
            //quitar bordes 
            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            //agg celda a la tabla
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(cedulaCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            //agregar al documento
            doc.add(tablaCliente);
            
            //ESPACIO EN BLANCO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);
            
            //AGREGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de celdas
             float[] ColumnaProducto = new float[]{15f, 50f, 15f, 20f};
             tablaProducto.setWidths(ColumnaProducto);
             tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
             PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: "));
             PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: "));
             PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: "));
             PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: "));
             //quitar bordes
             producto1.setBorder(0);
             producto2.setBorder(0);
             producto3.setBorder(0);
             producto4.setBorder(0);
             //agregar color al encabezadi del producto
             producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //agg celda a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);
            
            for(int i = 0; i < CrearFactura.jTable_productos.getRowCount(); i++){
                String producto = CrearFactura.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = CrearFactura.jTable_productos.getValueAt(i, 2).toString();
                String precio = CrearFactura.jTable_productos.getValueAt(i, 3).toString();
                String total = CrearFactura.jTable_productos.getValueAt(i, 7).toString();
                
                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }
            
            //agregar al documento
            doc.add(tablaProducto);
            
            //Total pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + CrearFactura.txt_total_pagar.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
           
            //Mensaje
           Paragraph mensaje = new Paragraph();
           mensaje.add(Chunk.NEWLINE);
           mensaje.add("¡Gracias por su compra,Vuelva pronto!");
           mensaje.setAlignment(Element.ALIGN_CENTER);
           doc.add(mensaje);
           
           //cerrar el documento y el archivo
           doc.close();
           archivo.close();
           
           //abrir el documento al ser generado automaticamente
            Desktop.getDesktop().open(file);
            
            
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }
```
Genera el archivo PDF con la factura de venta. Este es el núcleo del código.
a) Configuración inicial
* Obtiene la fecha actual y la formatea.
* Define el nombre del archivo PDF basado en el nombre del cliente y la fecha actual.
* Crea un archivo en la ruta src/pdf/.
b) Encabezado
* Incluye un logotipo ( emblema.jpeg).
* Agrega datos de la empresa, como:
o Nombre
o RUC (número de identificación fiscal)
o Dirección
o Razón social
* Muestra la fecha de la factura.
c) Datos del cliente
Crea una tabla que contiene la información básica del cliente, como:
* Cédula/RUC
* Nombre
* Teléfono
* Dirección
d) Productos
Crea una tabla para listar los productos adquiridos. Incluye:
* Cantidad
* Descripción
* Precio unitario
* Precio total
Los datos se obtienen del componente jTable_productos(probablemente una tabla en la 
interfaz gráfica de la clase CrearFactura).
e) Total a pagar
Muestra el total a pagar extraído del campo de texto CrearFactura.txt_total_pagar.
f) Mensaje de agradecimiento
Incluye un mensaje de agradecimiento al cliente por su compra.
g) Generación y apertura del PDF
• Cierra el archivo y el documento.
• Abre automáticamente el PDF generado en el visor predeterminado.
```java
```

```java
```
