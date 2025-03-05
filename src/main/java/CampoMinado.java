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

    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;
        this.minas = new boolean[nrLinhas][nrColunas]; // Valores começam a false
        this.estado = new int[nrLinhas][nrColunas]; // Valores começam a 0
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public int getEstado(int x, int y) {
        return estado[x][y];
    }
}
