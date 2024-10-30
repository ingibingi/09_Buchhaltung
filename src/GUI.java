import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame frame;

    //Historie
    JPanel pnlHistorie;
    JLabel lblKontostand;
    JComboBox cbFilterKategorie;
    JTextField txtDateVon;
    JTextField txtDateBis;
    JTextArea txtHistorie;
    JLabel lblEinnahmen;
    JLabel lblAusgaben;
    JLabel lblSaldo;

    //Details
    JPanel pnlDetails;
    JButton btnNeu;
    JComboBox cbKategorie;
    JTextField txtDatum;
    JTextArea txtZusatzinfo;
    JTextField txtBetrag;
    JButton btnSpeichern;

    public GUI(){
        frame = new JFrame("Haushaltsrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        pnlHistorie = new JPanel();
        pnlHistorie.setLayout(new BoxLayout(pnlHistorie,BoxLayout.Y_AXIS));
        lblKontostand = new JLabel(0 + "€");

        cbFilterKategorie = new JComboBox(Kategorie.ListeKategorien.toArray());
        txtDateVon = new JTextField("2024-01-01");
        txtDateBis = new JTextField("2024-12-31");
        txtHistorie = new JTextArea("Histirieneintrag 1\nHistorieneintrag 2");
        lblEinnahmen = new JLabel(0+" €");
        lblAusgaben = new JLabel(0+" €");
        lblSaldo = new JLabel(0+" €");

        pnlDetails = new JPanel();
        pnlDetails.setLayout(new BoxLayout(pnlDetails,BoxLayout.Y_AXIS));
        btnNeu = new JButton("Neuer Eintrag");
        cbKategorie = new JComboBox(Kategorie.ListeKategorien.toArray());
        txtDatum = new JTextField("2024-10-29");
        txtZusatzinfo = new JTextArea("Zusatzinfos\nzum Beleg");
        txtBetrag = new JTextField(0+" €");
        btnSpeichern = new JButton("Speichern");

        frame.add(pnlHistorie);
        pnlHistorie.add(lblKontostand);
        pnlHistorie.add(cbFilterKategorie);
        pnlHistorie.add(txtDateVon);
        pnlHistorie.add(txtDateBis);
        pnlHistorie.add(txtHistorie);
        pnlHistorie.add(lblEinnahmen);
        pnlHistorie.add(lblAusgaben);
        pnlHistorie.add(lblSaldo);

        frame.add(pnlDetails);
        pnlDetails.add(btnNeu);
        pnlDetails.add(cbKategorie);
        pnlDetails.add(txtDatum);
        pnlDetails.add(txtZusatzinfo);
        pnlDetails.add(txtBetrag);
        pnlDetails.add(btnSpeichern);

        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnNeu){
            //TODO: Methode für neuen Eintrag
        }
    }
}
