import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JanelaDeJogo extends JFrame {
    private JPanel painelJogo;
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;
    private TabelaRecordes recordes;


    public JanelaDeJogo(CampoMinado campoMinado, TabelaRecordes tabela) {
        this.campoMinado = campoMinado;
        this.recordes = tabela;

        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();

        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];

        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));

        MouseListener mouseListener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    return;
                }
                var botao = (BotaoCampoMinado) e.getSource();
                var x = botao.getLinha();
                var y = botao.getColuna();

                var estadoQuadricula = campoMinado.getEstadoQuadricula(x, y);
                if (estadoQuadricula == CampoMinado.TAPADO) {
                    campoMinado.marcarComoTendoMina(x, y);
                } else if (estadoQuadricula == CampoMinado.MARCADO) {
                    campoMinado.marcarComoSuspeita(x, y);
                } else if (estadoQuadricula == CampoMinado.DUVIDA) {
                    campoMinado.desmarcarQuadricula(x, y);
                }
                actualizarEstadoBotoes();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        var keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                var botao = (BotaoCampoMinado) e.getSource();
                var x = botao.getLinha(); // ou var linha = botao.getLinha();
                var y = botao.getColuna(); // ou var coluna = botao.getColuna();
                /*
                NOTA: Usámos as variáveis com nomes x, y para ser mais resumido no PDF, mas esta não é uma boa
                prática. Prefira os nomes linha, coluna sempre que puder, para não confundir e introduzir
                possíveis bugs. Isto porque na mente humana utilizamos muito o x na matemática para o eixo
                horizontal e o y para o eixo vertical. Visto que em programação não existe essa associação,
                sendo até o contrário (como é o presente caso abaixo), torna-se fácil cometer erros.
                Já os nomes linha, coluna nunca confundem o programador e sabemos perfeitamente em que situação
                incrementar cada variável respetivamente.
                Por exemplo: Ao subir com o teclado (VK_UP) o x decrementa um valor. Isto é estranho porque
                visualizamos o x no eixo horizontal a andar para a esquerda (sendo isso o VK_LEFT). Mas se o
                código estiver escrito da seguinte forma:
                 case KeyEvent.VK_UP -> botoes[--linha < 0 ? nrLinhas - 1 : linha][coluna].requestFocus();
                Agora o código é mais intuitivo e menos propenso a erros.
                */
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> botoes[--x < 0 ? nrLinhas - 1 :
                            x][y].requestFocus();
                    case KeyEvent.VK_DOWN -> botoes[(x + 1) %
                            nrLinhas][y].requestFocus();
                    case KeyEvent.VK_LEFT -> botoes[x][--y < 0 ? nrColunas - 1 :
                            y].requestFocus();
                    case KeyEvent.VK_RIGHT -> botoes[x][(y + 1) %
                            nrColunas].requestFocus();
                    case KeyEvent.VK_M -> {
                        switch (campoMinado.getEstadoQuadricula(x, y)) {
                            case CampoMinado.TAPADO -> campoMinado.marcarComoTendoMina(x, y);
                            case CampoMinado.MARCADO -> campoMinado.marcarComoSuspeita(x, y);
                            case CampoMinado.DUVIDA -> campoMinado.desmarcarQuadricula(x, y);
                        }
                        actualizarEstadoBotoes();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        };


        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {

                botoes[linha][coluna] = new BotaoCampoMinado(linha, coluna);
                botoes[linha][coluna].addActionListener(this::btnCampoMinadoActionPerformed);
                botoes[linha][coluna].addMouseListener(mouseListener);
                botoes[linha][coluna].addKeyListener(keyListener);
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

    public void btnCampoMinadoActionPerformed(ActionEvent e) {
        var botao = (BotaoCampoMinado) e.getSource();

        var x = botao.getLinha();
        var y = botao.getColuna();

        campoMinado.revelarQuadricula(x,y);
        actualizarEstadoBotoes();

        if (campoMinado.isJogoTerminado()) {
            if (campoMinado.isJogadorDerrotado()) {
                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina",
                        "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em "+
                                (campoMinado.getDuracaoJogo()/1000)+" segundos",
                        "Vitória", JOptionPane.INFORMATION_MESSAGE);

                boolean novoRecorde=campoMinado.getDuracaoJogo()<recordes.getTempo();

                if (novoRecorde) {
                    String nome=JOptionPane.showInputDialog("Introduza o seu nome");
                    recordes.setRecorde(nome, campoMinado.getDuracaoJogo());
                }

            }
            setVisible(false);
        }
    }

    public void actualizarEstadoBotoes(){
        for (int x = 0; x < campoMinado.getNrLinhas(); x++) {
            for (int y = 0; y < campoMinado.getNrColunas(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }
}
