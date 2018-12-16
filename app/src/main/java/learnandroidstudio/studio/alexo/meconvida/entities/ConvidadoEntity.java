package learnandroidstudio.studio.alexo.meconvida.entities;

public class ConvidadoEntity {
    private int id;
    private String name;
    private String document;
    private int condirmed;

    public ConvidadoEntity() {
    }

    public ConvidadoEntity(int id, String name, String document, int condirmed) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.condirmed = condirmed;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCondirmed() {
        return condirmed;
    }

    public void setCondirmed(int condirmed) {
        this.condirmed = condirmed;
    }
}
