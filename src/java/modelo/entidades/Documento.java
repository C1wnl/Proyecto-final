package modelo.entidades;

import java.sql.Date;

public class Documento {
    private int id;
    private String nombre;
    private int tipoId;
    private String version;
    private Date fechaSubida;
    private String ruta;

    public Documento() {}

    public Documento(String nombre, int tipoId, String version, Date fechaSubida, String ruta) {
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.version = version;
        this.fechaSubida = fechaSubida;
        this.ruta = ruta;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getTipoId() { return tipoId; }
    public void setTipoId(int tipoId) { this.tipoId = tipoId; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public Date getFechaSubida() { return fechaSubida; }
    public void setFechaSubida(Date fechaSubida) { this.fechaSubida = fechaSubida; }

    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }
}
