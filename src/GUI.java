import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI implements ActionListener {
    JFrame frame;

    //Historie
    JPanel pnlHistorie;
    JLabel lblKontostand;
    JComboBox cbFilterKategorie;
    JTextField txtDateVon;
    JTextField txtDateBis;
    JButton btnAktualisieren;
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
    JLabel lblEuroSymbol;
    JPanel pnlBetrag;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnNeu){
            txtBetrag.setText("");
            txtZusatzinfo.setText("");
            txtDatum.setText("");

        }
        if(e.getSource()==btnAktualisieren){
            LocalDate dtVon = LocalDate.parse(txtDateVon.getText());
            LocalDate dtBis = LocalDate.parse(txtDateBis.getText());
            Kategorie kategorie = (Kategorie) cbFilterKategorie.getSelectedItem();

            try {
                Main.loadBuchungenAuswahl(dtVon, dtBis, kategorie);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            txtHistorie.setText(Buchung.getUebersichtBuchungen());
        }

        if(e.getSource()==btnSpeichern){

            Kategorie kategorie = (Kategorie) cbKategorie.getSelectedItem();
            LocalDate datum = LocalDate.parse(txtDatum.getText());
            String zusatzinfo = txtZusatzinfo.getText();
            double betrag = Double.parseDouble(txtBetrag.getText());

            Buchung buchung = new Buchung(kategorie,datum,zusatzinfo,betrag);
            try {
                Main.insertBuchung(buchung);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    JButton btnSpeichern;

    public GUI(){
        frame = new JFrame("Haushaltsrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        pnlHistorie = new JPanel();
        pnlHistorie.setLayout(new BoxLayout(pnlHistorie,BoxLayout.Y_AXIS));
        lblKontostand = new JLabel(0 + "€");

        cbFilterKategorie = new JComboBox(Kategorie.listeKategorien.toArray());
        txtDateVon = new JTextField("2024-01-01");
            txtDateVon.setEditable(true);
        txtDateBis = new JTextField("2024-12-31");
            txtDateBis.setEditable(true);
        btnAktualisieren = new JButton("Aktualisieren");
        txtHistorie = new JTextArea(Buchung.getUebersichtBuchungen());
        lblEinnahmen = new JLabel("Einnahmen:\t"+Buchung.getEinnahmen()+" €");
        lblAusgaben = new JLabel("Ausgaben:\t"+Buchung.getAusgaben()+" €");
        lblSaldo = new JLabel("Saldo:\t"+Buchung.getSaldo()+" €");

        pnlDetails = new JPanel();
        pnlDetails.setLayout(new BoxLayout(pnlDetails,BoxLayout.Y_AXIS));
        btnNeu = new JButton("Neuer Eintrag");
        cbKategorie = new JComboBox(Kategorie.listeKategorien.toArray());
        txtDatum = new JTextField("2024-10-29");
        txtZusatzinfo = new JTextArea("Zusatzinfos\nzum Beleg");
        txtBetrag = new JTextField(10);
        lblEuroSymbol = new JLabel("€");
        btnSpeichern = new JButton("Speichern");

        pnlBetrag = new JPanel();
        pnlBetrag.add(txtBetrag);
        pnlBetrag.add(lblEuroSymbol);

        frame.add(pnlHistorie);
        pnlHistorie.add(lblKontostand);
        pnlHistorie.add(cbFilterKategorie);
        pnlHistorie.add(txtDateVon);
        pnlHistorie.add(txtDateBis);
        pnlHistorie.add(btnAktualisieren);
        pnlHistorie.add(txtHistorie);
        pnlHistorie.add(lblEinnahmen);
        pnlHistorie.add(lblAusgaben);
        pnlHistorie.add(lblSaldo);

        frame.add(pnlDetails);
        pnlDetails.add(btnNeu);
        pnlDetails.add(cbKategorie);
        pnlDetails.add(txtDatum);
        pnlDetails.add(txtZusatzinfo);
        pnlDetails.add(pnlBetrag);
        pnlDetails.add(btnSpeichern);

        btnAktualisieren.addActionListener(this);
        btnNeu.addActionListener(this);
        btnSpeichern.addActionListener(this);

        frame.setVisible(true);
        frame.pack();
    }
}
