package modelo;

/**
 *
 * @author ediso
 */
public class Cliente {

    //Atributos
    private int idCliente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String telefono;
    

    //Constructor
    public Cliente() {
        this.idCliente = 0;
        this.nombre = "";
        this.apellido = "";
        this.cedula = "";
        this.direccion = "";
        this.telefono = "";
    }

    //Constructor sobrecargado
    public Cliente(int idCliente, String nombre, String apellido, String cedula,String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
         this.direccion = direccion; 
        this.telefono = telefono;
            }

    //metodos set and get
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

  
}
