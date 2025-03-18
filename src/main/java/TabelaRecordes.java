public class TabelaRecordes {
    private String nome;
    private Long tempo;

    public TabelaRecordes() {
        this.nome = "Anónimo";
        this.tempo = (long) 9999999;
    }

    public void setRecorde(String nome, Long duracaoJogo){
        if (duracaoJogo<this.tempo){
            this.nome = nome;
            this.tempo = duracaoJogo;
        }
    }

    public String getNome() {
        return nome;
    }

    public Long getTempo() {
        return tempo;
    }
}
