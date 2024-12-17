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
