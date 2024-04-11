package entity;

public class Vacante {
    private int id;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;
    private String clan;
    private String created_at;


    private int empresaId; // Llave foránea

    // Inyectar un objeto dentro de otra clase (Inyección de dependencias)
    private Empresa objEmpresa;

    public Vacante() {
    }


    public Vacante(String titulo, String descripcion, String duracion, String estado, String tecnologia, String clan, int empresaId, Empresa objEmpresa) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
        this.clan = clan;
        this.empresaId = empresaId;
        this.objEmpresa = objEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    @Override
    public String toString() {
        return "Vacante:" +
                "titulo:'" + titulo + '\'' +
                ", descripcion:'" + descripcion + '\'' +
                ", duracion:'" + duracion + '\'' +
                ", estado:'" + estado + '\'' +
                ", tecnologia:'" + tecnologia + '\'' +
                ", clan:'" + clan + '\'' +
                ", objEmpresa:" + objEmpresa;
    }
}
