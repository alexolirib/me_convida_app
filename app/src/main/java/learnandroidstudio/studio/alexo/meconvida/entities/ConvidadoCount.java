package learnandroidstudio.studio.alexo.meconvida.entities;

public class ConvidadoCount {

    private int presentCount;
    private int absentCount;
    private int allCont;

    public ConvidadoCount() {
        this.presentCount = 0;
        this.absentCount = 0;
        this.allCont = 0;
    }

    public ConvidadoCount(int presentCount, int absentCount, int allCont) {
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.allCont = allCont;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getAllCont() {
        return allCont;
    }

    public void setAllCont(int allCont) {
        this.allCont = allCont;
    }
}
