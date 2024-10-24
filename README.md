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
- [ ] Datenbank
  - [X] Struktur definieren
  - [X] Datenbank erstellen
  - [X] Mit Testdaten befüllen
- [ ] GUI
  - [ ] Aufbau überlegen
  - [ ] GUI bauen
- [ ] Grundfunktionalität
  - [ ] Grundklassen erstellen
  - [ ] GUI starten
  - [ ] Datenbank einlesen/ausgeben
  - [ ] Eingabe neue Buchung
  - [ ] Historie anzeigen
    - [ ] Filter nach Datum
  - [ ] Berechnung Summen
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
- t_Kategorie
  - ID (int)
  - Bezeichnung (varChar(30))
  - Kurzbezeichnung (varChar(6))
  - istEingang (boolean)
- t_Buchung
  - ID (int)
  - ZuletztVerändert (timestamp)
  - Kategorie (FK(t_Kategorie))
  - Datum (date)
  - Zusatzinfo (text)
  - Betrag (double)
## Datenbank erstellen
- MariaDB über Apache2-Webserver (via XAMPP)
- Erstellen mit phpMyAdmin
  - Fremdschlüssel-Constraint nicht vergessen!
[haushaltsbuchung_leer.sql](..%2FextraFiles%2Fhaushaltsbuchung_leer.sql)
## Testdaten erstellen
[haushaltsbuchung_testvorlage.sql](..%2FextraFiles%2Fhaushaltsbuchung_testvorlage.sql)

