import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JLabel Title;
    private JButton jogoFacilButton;
    private JButton sairButton;
    private JButton jogoMedioButton;
    private JButton jogoDificilButton;

    public MinesFinder(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        sairButton.addActionListener(this::sairButtonActionPerformed);
        jogoFacilButton.addActionListener(this::jogoFacilButtonActionPerformed);
    }
    private void jogoFacilButtonActionPerformed(ActionEvent e){
        new JanelaDeJogo(new CampoMinado(9,9,10));
        setVisible(false);
    }

    private void sairButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args){
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
