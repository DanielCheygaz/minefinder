import java.util.Random;

public class CampoMinado {
    private boolean[][] minas;
    public static final int VAZIO = 0;
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;
    private int[][] estado;
    private int nrLinhas;
    private int nrColunas;
    private int nrMinas;
    private boolean primeiraJogada;

    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;
        this.minas = new boolean[nrLinhas][nrColunas]; // Valores começam a false
        this.estado = new int[nrLinhas][nrColunas]; // Valores começam a 0
        this.primeiraJogada = true;

        for(var x = 0; x < nrLinhas; ++x){
            for(var y = 0; y < nrColunas; ++y){
                estado[x][y] = TAPADO;
            }
        }
    }

    public void revelarQuadricula(int x, int y){
        if(estado[x][y] < TAPADO){
            return;
        }

        if(primeiraJogada){
            primeiraJogada = false;
            colocarMinas(x,y);
        }
    }

    private void colocarMinas(int exceptoX, int exceptoY){
        var aleatorio = new Random();
        var x = 0;
        var y = 0;

        for(var i = 0; i < nrMinas; i++){
            do{
                x = aleatorio.nextInt(nrColunas);
                y = aleatorio.nextInt(nrLinhas);
            }while(minas[x][y] || (x == exceptoX && y == exceptoY));

            minas[x][y] = true;
        }
    }

    public boolean hasMina(int x, int y) { 
        return minas[x][y];
    }

    public int getEstado(int x, int y) {
        return estado[x][y];
    }

    public int getNrLinhas() {
        return nrLinhas;
    }

    public int getNrColunas() {
        return nrColunas;
    }
}
