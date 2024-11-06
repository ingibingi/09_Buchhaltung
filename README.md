---
Autor: Ingo Schlapschy
Ort: LBS Eibiswald
Gruppe: 24/25 LG1 2aAPC INFv JRZ
Datum: 2024-10-01
AbgabeErledigt: false
---
# Angabe
- [Link zur Angabe](https://www.eduvidual.at/mod/assign/view.php?id=6290148)
```
    1. Lernziele
    • Erstelle eine einfache Haushaltskosten App (Einnahmen-/Ausgabenrechner) mit Datenbank
    • Klassenstruktur planen
    • Umsetzung in JAVA

    2. Aufgabenstellung
    • Wie sieht die Datenbankstruktur aus?
    • Wie wird die Oberfläche gestaltet?
    • Welche Klassen werden benötigt?
    • Welche Methoden werden in der Klasse benötigt?
    • ERROR Handling: Unbedingt auf mögliche Eingabefehler reagieren!
    
    4. Ergebnis – Lösung
    Funktionsbeschreibung: 
        ◦ Eingaben von Daten 
            ▪ Erstelle verschiedene Kategorien von Kosten, zB. Gehalt, Geschenke als Einnahmen oder Nahrungsmittel, Betriebskosten, Miete, … als Ausgaben
        ◦ Ausgabe der Daten in gewünschter Form (Einnahmen-/Ausgabenrechnung)
        ◦ Auswahl des Zeitraums für Anzeige
        ◦ Datenspeicherung in Datenbank
        ◦ Sicherheit: Datensätze dürfen nur am selben Tag verändert werden!
```

## Ziele
- Bewegungen erfassen
  - Einnahmen/Ausgaben
  - Kategorie
  - Datum
  - Betrag
  - Zusatzinfo
- GUI
  - Historie
    - Summe Einnahmen
    - Summe Ausgaben
    - Filter
      - von - bis
  - neue Einlage
  - neue Herausnahme
## ToDo
- [X] Datenbank
  - [X] Struktur definieren
  - [X] Datenbank erstellen
  - [X] Mit Testdaten befüllen
  - [X] Datenbank einbinden
    - [X] Projekt SQL-fähig machen
    - [X] Einfache SQL-Abfrage in Main-Methode funktioniert
- [ ] GUI
  - [X] Aufbau überlegen
  - [X] GUI bauen
  - [ ] Elemente implementieren
- [ ] Grundfunktionalität
  - [X] Grundklassen erstellen
  - [X] GUI starten
  - [X] Datenbank einlesen/ausgeben
  - [ ] Eingabe neue Buchung
  - [X] Historie anzeigen
    - [ ] Filter nach Datum
  - [X] Berechnung Summen
  - [ ] Error-Handling
  - [ ] Bearbeitung von Eingaben
    - [ ] Löschen
    - [ ] Bearbeiten
    - [ ] Nur innerhalb von 24h
- [ ] Erweiterte Funktionalität
  - [ ] Ideen sammeln und priorisieren
  - [ ] Person
# Protokoll
## Datenbank planen
### Aufbau
- t_Kategorie
  - ID (int)
  - Bezeichnung (varChar(30))
  - Kurzbezeichnung (varChar(6))
  - istEingang (boolean)
- t_Buchung
  - ID (int)
  - ZuletztVeraendert (timestamp)
  - Kategorie (FK(t_Kategorie))
  - Datum (date)
  - Zusatzinfo (text)
  - Betrag (double)
### Ueberlegungen
- Zusätzliches Feld `ZuletztVeraendert` 
  - soll Ausbessern von Fehlern innerhalb eines Zeitfensters ermöglichen
  - soll Buchungsdatum unabhängig vom Rechnungsdatum machen.
## Datenbank erstellen
- MariaDB über Apache2-Webserver (via XAMPP)
- Erstellen mit phpMyAdmin
  - Fremdschlüssel-Constraint nicht vergessen!
[haushaltsbuchung_leer.sql](..%2FextraFiles%2Fhaushaltsbuchung_leer.sql)
## Testdaten erstellen
[haushaltsbuchung_testvorlage.sql](..%2FextraFiles%2Fhaushaltsbuchung_testvorlage.sql)
## Datenbank einbinden
### Projekt SQL-fähig machen
- Plugin: Database Navigator aktivieren
  ![PluginDatabaseNavigator.png](PluginDatabasNavigator.png)
- MySQL Connector/J einbinden
  - Wird benötigt damit das Java Programm die Datenbank verwenden kann
  - Platform independent - Version
  - [mysql-connector-j-9.1.0.jar](lib%2Fmysql-connector-j-9.1.0.jar)
  - ![img.png](img.png)
  - ![img_1.png](img_1.png)
### Einfache SQL-Abfrage implementieren
```java
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuchung","root","");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_Buchung");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+"\t"
                                +resultSet.getString(4)+"\t"
                                +resultSet.getString(6)+"\t"
                                +resultSet.getString(5));
        }
    }
}
```
![img_2.png](img_2.png)
## GUI planen
- Meine bisherigen GUI-Form-Erfahrungen sind nicht sehr glücklich verlaufen
- Deshalb: Umsetzung ohne GUI-Form, direkt in Code bauen.
- Erster Entwurf ![FirstDesign.jpg](FirstDesign.jpg)
- Erster Prototyp![img_3.png](img_3.png)
  - Flow-Layout für 2 Spalten
  - Darin 2 Panele mit Box-Layout
## Klassen planen
- Main
  - Stellt Verbindung mit Datenbank her
  - Lädt Resultsets 
    - für Buchungen
    - für Kategorien
  - Erstellt String[] Kategorien
  - Erstellt String[] Buchungen
  - Startet GUI
  - Füllt Liste mit Einträgen entsprechend der Datenbank
    - D. H. Vollständige Liste ist während der
- GUI
  - Stellt Benutzerinteraktionen zur Verfügung
  - Methoden zur Änderung der Elemente der Liste
    - Hinzufügen
    - Ändern (später)
- Kategorie
  - Enthält Kategoriedetails
- Buchung
  - Einzelne Buchung mit sämtlichen Infos aus Datenbank
## Klasse Kategorie
```java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kategorie {
    static ArrayList<Kategorie> listeKategorien = new ArrayList<>();
    int id;
    String bezeichnung;
    String kurzbezeichnung;
    Boolean istEingang;

    public Kategorie(ResultSet SingleResultSet) throws SQLException {
            this.id = SingleResultSet.getInt(1);
            this.bezeichnung = SingleResultSet.getString(2);
            this.kurzbezeichnung = SingleResultSet.getString(3);
            this.istEingang = SingleResultSet.getBoolean(4);
            listeKategorien.add(this);
    }

    public Kategorie(String Bezeichnung, String Kurzbezeichnung, Boolean istEingang){
        this.id = listeKategorien.size()+1;
        this.bezeichnung =Bezeichnung;
        this.kurzbezeichnung =Kurzbezeichnung;
        this.istEingang=istEingang;
        listeKategorien.add(this);
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }
}

```
## Klasse Buchung
- unsicher, wie mit Datum am besten umgegangen werden soll.
- Aktuelle lösung: 1. Vorschlag von IntelliJ übernehmen
```java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Buchung {
  static ArrayList<Buchung> listeBuchungen = new ArrayList<>();
  int id;
  LocalDateTime zuletztVeraendert;
  Kategorie kategorie;
  LocalDate datum;
  String zusatzinfo;
  double betrag;

  public Buchung(ResultSet SingleResultSet) throws SQLException {
    this.id = SingleResultSet.getInt(1);
    this.zuletztVeraendert = LocalDateTime.parse(SingleResultSet.getString(2));
    int kategorieID = SingleResultSet.getInt(3);
    this.kategorie = Kategorie.listeKategorien.get(kategorieID);
    this.datum = LocalDate.parse(SingleResultSet.getString(4));
    this.zusatzinfo = SingleResultSet.getString(5);
    this.betrag = SingleResultSet.getDouble(6);
    listeBuchungen.add(this);
  }

  public Buchung(Kategorie kategorie, LocalDate datum, String zusatzinfo, double betrag){
    this.id = listeBuchungen.size()+1;
    this.zuletztVeraendert = LocalDateTime.now();
    this.kategorie = kategorie;
    this.datum = datum;
    this.zusatzinfo = zusatzinfo;
    this.betrag = betrag;
    listeBuchungen.add(this);
  }

  @Override
  public String toString() {
    return this.kategorie.istEingang.toString()+"\t"
            +this.datum.toString()+"\t"
            +this.kategorie.kurzbezeichnung.toString()+"\t"
            +this.betrag+" €";
  }

}

```

### Problem mit Datum und Kategorieindex und Buchungsindex beheben
- war bisher problematisch weil index nicht übereinstimmt mit länge der Liste
  - neue Methode zum auslesen der Kategorie über die id
```java
  public static Kategorie findKategorieById(int id){
  return listeKategorien.stream().filter(kategorie -> id== kategorie.id).findFirst().orElse(null);
}

```

```java
public Buchung(ResultSet SingleResultSet) throws SQLException {
        //...
        int kategorieID = SingleResultSet.getInt(3);
        this.kategorie = Kategorie.findKategorieById(kategorieID);
         //...
        listeBuchungen.add(this);
    }
```

## GUI erweitern um Buchungen, Summen Einnahmen u. Ausgaben
```java
public static String getUebersichtBuchungen(){
        uebersichtBuchungen = "";
        for (Buchung i : listeBuchungen) {
            uebersichtBuchungen = uebersichtBuchungen + i.toString() + "\n";
        }
        return uebersichtBuchungen;
    }

    public static double getEinnahmen() {
        double sumEinnahmen = 0;
        for (Buchung i : listeBuchungen) {
            if (i.kategorie.istEingang) {
                sumEinnahmen += i.betrag;
            }
        }
        return sumEinnahmen;
    }

    public static double getAusgaben() {
        double sumAusgaben = 0;
        for (Buchung i : listeBuchungen) {
            if (!i.kategorie.istEingang) {
                sumAusgaben += i.betrag;
            }
        }
        return sumAusgaben;
    }

    public static double getSaldo(){
        double sumSaldo = 0;
        for (Buchung i: listeBuchungen){
            if (i.kategorie.istEingang){
                sumSaldo += i.betrag;
            } else {
                sumSaldo -= i.betrag;
            }
        }
        return sumSaldo;
    }
```
```java
public GUI(){
        //...
        lblEinnahmen = new JLabel("Einnahmen:\t"+Buchung.getEinnahmen()+" €");
        lblAusgaben = new JLabel("Ausgaben:\t"+Buchung.getAusgaben()+" €");
        lblSaldo = new JLabel("Saldo:\t"+Buchung.getSaldo()+" €");
        //...
        txtHistorie = new JTextArea(Buchung.uebersichtBuchungen);
        //...
```

## Zweifel an Projektstruktur
- Ist es sinnvoll dass ich die Klasse Kategorie und Buchung habe?
- Wozu brauche ich Kategorie- und Buchungs-Objekte?
  - Ich mache alles unnötigerweise doppelt.
- Alternativ könnte ich:
  - Ich kann in Main eine ArrayList<String> erstellen in der die Kategorien gespeichert sind
  - Die Ausgaben mach ich auch direkt über resultSets.
  - Das Eingeben von neuen Buchungen wäre so auch einfach er umsetzbar
- Bevor ich jetzt alles was ich hab überstürzt über den Haufen schmeiße
  - mache ich jetzt zumindes noch die eingabe von neuen Buchungen,
  - damit ich etwas funktionsfähiges zum abgeben habe.

## Neue Buchung speichern
```java
public void actionPerformed(ActionEvent e) {
        //...
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
```

```java
    public static void insertBuchung(Buchung buchung) throws SQLException {
        String myQuery = "INSERT INTO t_Buchung " +
            "(`ID`, `ZuletztVeraendert`, `Kategorie`, `Datum`, `Zusatzinfo`, `Betrag`) " +
            "VALUES (";
            //ID
                if(buchung.id>0){
                    myQuery = myQuery.concat("'"+buchung.id+"',");
                }else {
                    myQuery = myQuery.concat("NULL,");
                }
            //ZuletztVeraendert
                 myQuery = myQuery.concat("current_timestamp(),");
            //Kategorie
                myQuery = myQuery.concat(buchung.kategorie.id+",");
            //Datum
                if(buchung.datum.equals(null)){
                    myQuery = myQuery.concat("CURDATE(),");
                } else {
                    myQuery = myQuery.concat("'"+buchung.datum + "',");
                }
            //Zusatzinfo
                    myQuery = myQuery.concat("'"+buchung.zusatzinfo+"',");
            //Betrag
                myQuery = myQuery.concat("'"+buchung.betrag+"'");
                myQuery = myQuery.concat(");");

            System.out.println("myQuery: "+myQuery);

            Statement statementBuchungen = connection.createStatement();
            statementBuchungen.executeUpdate(myQuery);
    }
```


