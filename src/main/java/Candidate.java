/**
 * Created by admin on 24.04.2018.
 */
public class Candidate {
    public String name;
    public String persent;
    public String ftlValue;
    public Candidate(String name, String persent) {
        this.name = name;
        this.persent = persent;
    }
    public void createFtlValue(){
        ftlValue=name+"          "+persent+"%";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {
        return (int)Float.parseFloat(persent);
    }

    public void setPersent(String persent) {
        this.persent = persent;
    }

    public String getFtlValue() {
        return ftlValue;
    }

    @Override
    public String toString() {
        return name+"   "+persent;
    }
}
