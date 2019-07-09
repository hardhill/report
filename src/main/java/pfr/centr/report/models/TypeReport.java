package pfr.centr.report.models;

public class TypeReport implements Cloneable {
    private Long id;
    private String typereport;

    public TypeReport() {
    }

    public TypeReport(Long id, String typereport) {
        this.id = id;
        this.typereport = typereport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypereport() {
        return typereport;
    }

    public void setTypereport(String typereport) {
        this.typereport = typereport;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
