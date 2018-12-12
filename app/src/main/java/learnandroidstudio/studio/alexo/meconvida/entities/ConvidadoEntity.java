package learnandroidstudio.studio.alexo.meconvida.entities;

public class ConvidadoEntity {
    private int id;
    private String name;
    private int condirmed;

    public ConvidadoEntity() {
    }

    public ConvidadoEntity(int id, String name, int condirmed) {
        this.id = id;
        this.name = name;
        this.condirmed = condirmed;
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
