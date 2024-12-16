package modelo;

public class CabeceraVenta {

    // Atributos
    private int idCabeceraventa;
    private int idCliente;
    private double ValorPagar; // Usa totalVenta en lugar de valorPagar
    private String fechaVenta;
    private int estado;

    // Constructor
    public CabeceraVenta(){
        this.idCabeceraventa = 0;
        this.idCliente = 0;
        this.ValorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }

    // Constructor sobrecargado
    public CabeceraVenta(int idCabeceraventa, int idCliente, double totalVenta, String fechaVenta, int estado) {
        this.idCabeceraventa = idCabeceraventa;
        this.idCliente = idCliente;
        this.ValorPagar = totalVenta;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdCabeceraventa() {
        return idCabeceraventa;
    }

    public void setIdCabeceraventa(int idCabeceraventa) {
        this.idCabeceraventa = idCabeceraventa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return ValorPagar;
    }

    public void setValorPagar(double totalVenta) {
        this.ValorPagar = totalVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    // toString
    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idCabeceraventa + ", idCliente=" + idCliente + ", ValorPagar=" + ValorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }
}
