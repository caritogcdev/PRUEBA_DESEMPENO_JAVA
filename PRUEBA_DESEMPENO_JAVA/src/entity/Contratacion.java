package entity;

public class Contratacion {
    private int id;
    private String fechaAplicacion;
    private String estado;
    private double salario;
    private String created_at;

    //Llaves foráneas
    private int vacanteId;
    private int coderId;

    //Inyectar un objeto dentro de otra clase (Inyección de dependencias)
    private Vacante objVacante;
    private Coder objCoder;

    public Contratacion() {
    }

    public Contratacion(String fechaAplicacion, String estado, double salario, int vacanteId, int coderId, Vacante objVacante, Coder objCoder) {
        this.fechaAplicacion = fechaAplicacion;
        this.estado = estado;
        this.salario = salario;
        this.vacanteId = vacanteId;
        this.coderId = coderId;
        this.objVacante = objVacante;
        this.objCoder = objCoder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getVacanteId() {
        return vacanteId;
    }

    public void setVacanteId(int vacanteId) {
        this.vacanteId = vacanteId;
    }

    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return "Contratacion:" +
                "fechaAplicacion:'" + fechaAplicacion + '\'' +
                ", estado:'" + estado + '\'' +
                ", salario:" + salario +
                ", created_at:'" + created_at + '\'' +
                ", objVacante:" + objVacante +
                ", objCoder:" + objCoder;
    }
}
