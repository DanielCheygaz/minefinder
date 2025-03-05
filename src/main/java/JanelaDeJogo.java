import javax.swing.*;
import java.awt.*;

public class JanelaDeJogo extends JFrame {
    private JPanel painelJogo;
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;


    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;

        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();

        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];

        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));

        // Criar e adicionar os botões à janela
        for(int linha = 0; linha < nrLinhas; ++linha){
            for(int coluna = 0; coluna < nrColunas; ++coluna){
                botoes[linha][coluna] = new BotaoCampoMinado();
                painelJogo.add(botoes[linha][coluna]);
            }
        }

        Dimension dimensaoPainel = new Dimension(600,600);
        painelJogo.setMinimumSize(dimensaoPainel);
        painelJogo.setPreferredSize(dimensaoPainel);

        setContentPane(painelJogo);

        //Destrói esta janela, removendo-a completamente da memória
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();

        setVisible(true);
    }
}
