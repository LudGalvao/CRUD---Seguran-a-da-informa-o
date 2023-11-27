// Classe calcio
package entidades;

public class calcio {
    private Integer id;
    private String mgPerLiter;

    public calcio() {
    }

    public calcio(Integer id, String mgPerLiter) {
        this.id = id;
        this.mgPerLiter = mgPerLiter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMgPerLiter() {
        return mgPerLiter;
    }

    public void setMgPerLiter(String mgPerLiter) {
        this.mgPerLiter = mgPerLiter;
    }
}
