import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JLabel Title;
    private JButton sairButton;
    private JButton jogoFacilButton;
    private JButton jogoMedioButton;
    private JButton jogoDificilButton;
    private TabelaRecordes recordesFacil;
    private TabelaRecordes recordesMedio;
    private TabelaRecordes recordesDificil;

    public MinesFinder(String title) {
        super(title);

        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        sairButton.addActionListener(this::sairButtonActionPerformed);
        jogoFacilButton.addActionListener(this::jogoFacilButtonActionPerformed);
        jogoMedioButton.addActionListener(this::jogoMedioButtonActionPerformed);
        jogoDificilButton.addActionListener(this::jogoDificilButtonActionPerformed);
    }
    private void jogoFacilButtonActionPerformed(ActionEvent e){
        new JanelaDeJogo(new CampoMinado(9,9,1),recordesFacil);
        setVisible(false);
    }

    private void jogoMedioButtonActionPerformed(ActionEvent e){
        new JanelaDeJogo(new CampoMinado(16,16,40),recordesMedio);
        setVisible(false);
    }

    private void jogoDificilButtonActionPerformed(ActionEvent e){
        new JanelaDeJogo(new CampoMinado(16,30,90),recordesDificil);
        setVisible(false);
    }

    private void sairButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args){
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
