import java.util.ArrayList;

public class TabelaRecordes {
    private String nome;
    private Long tempo;
    private ArrayList<TabelaRecordesListener> listeners;

    public TabelaRecordes() {
        this.nome = "Anónimo";
        this.tempo = (long) 9999999;
        listeners = new ArrayList<>();
    }

    public void setRecorde(String nome, Long duracaoJogo){
        if (duracaoJogo<this.tempo){
            this.nome = nome;
            this.tempo = duracaoJogo;
        }
    }

    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.remove(list);
    }

    public String getNome() {
        return nome;
    }

    public Long getTempo() {
        return tempo;
    }
}
