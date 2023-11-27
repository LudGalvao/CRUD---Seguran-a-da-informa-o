package entidades;

public class ValoresReferencia {
    Integer id;
    String descricao;
    Integer limiteInferior;
    Integer limiteSuperior;

    String unidade;

    public ValoresReferencia() {
    }

    public ValoresReferencia(String descricao, Integer limiteInferior, Integer limiteSuperior, String unidade) {
        this.descricao = descricao;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.unidade = unidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
