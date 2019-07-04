package pfr.centr.report.models;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TypeReport implements Cloneable {
    private int id;
    private Date daterep;
    private int rayon;
    private int type;
    private int operation;
    private int iduser;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public TypeReport() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDaterep() {
        return daterep;
    }

    public void setDaterep(Date daterep) {
        this.daterep = daterep;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
        return true;
        if(!(obj instanceof TypeReport))
            return false;
        TypeReport typeReport = (TypeReport)obj;
        return id == typeReport.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id)+String.valueOf(this.daterep.getTime());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
