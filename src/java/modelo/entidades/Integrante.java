package modelo.entidades;

public class Integrante {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private int cargoId;
    private String cargoNombre;
    private int jefeId;
    private String jefeNombre;

    public Integrante() {}

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getCargoId() { return cargoId; }
    public void setCargoId(int cargoId) { this.cargoId = cargoId; }

    public String getCargoNombre() { return cargoNombre; }
    public void setCargoNombre(String cargoNombre) { this.cargoNombre = cargoNombre; }

    public int getJefeId() { return jefeId; }
    public void setJefeId(int jefeId) { this.jefeId = jefeId; }

    public String getJefeNombre() { return jefeNombre; }
    public void setJefeNombre(String jefeNombre) { this.jefeNombre = jefeNombre; }
}

