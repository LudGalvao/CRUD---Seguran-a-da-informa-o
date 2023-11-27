package entidades;

public class Senha {

    private Integer id;
    private String key;

    public Senha(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
