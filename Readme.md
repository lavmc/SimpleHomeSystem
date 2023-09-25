# SimpleHomeSystem für Landania.net

Das SimpleHomeSystem ist ein modulares Minecraft-Plugin, entwickelt für das Landania.net Netzwerk. Es ermöglicht Spielern, ihre eigenen "Homes" zu setzen, zu löschen und sich zu ihnen zu teleportieren. Das Plugin verwendet MySQL zur Datenhaltung und ist in Java 17+ mit Paper 1.19.4 geschrieben.

## Module

Das System ist in drei Hauptmodule unterteilt:

- **home-system-common**: Enthält gemeinsame Klassen und Logiken, die in den anderen Modulen wiederverwendet werden können.
- **home-system-api**: Definiert Schnittstellen und erwartete Verhaltensweisen für das Home-System.
- **home-system-spigot**: Das eigentliche Spigot-Plugin mit Befehlen, Event-Listenern und spezifischer Implementierung.

## Konfiguration

- **config.json**: Hier werden die MySQL-Anmeldeinformationen und alle Systemnachrichten gespeichert. Dieses befindet sich im Ordner `configurations` im Hauptverzeichnis des Plugins.

## Befehle & Berechtigungen

1. **/sethome <Name>**
    - Beschreibung: Setzt ein neues Home für den Spieler.
    - Berechtigung: `simplehomesystem.sethome`

2. **/delhome <Name>**
    - Beschreibung: Löscht ein spezifisches Home des Spielers.
    - Berechtigung: `simplehomesystem.delhome`

3. **/home <Name>**
    - Beschreibung: Teleportiert den Spieler zu einem spezifischen Home.
    - Berechtigung: `simplehomesystem.home`

4. **/homes**
    - Beschreibung: Öffnet ein paginiertes GUI, in dem alle Homes des Spielers aufgelistet sind.
    - Berechtigung: `simplehomesystem.homes`

## GUI

- Das GUI ist paginiert und wird mit dem Befehl `/homes` geöffnet.
- Jedes Home wird als einzelner Eintrag im GUI dargestellt.
- Es gibt auch eine Option, um alle Homes des Spielers zu löschen.
- Mit den Pfeilen im GUI kann man durch die verschiedenen Seiten navigieren.

## Installation & Erstkonfiguration

1. Stellen Sie sicher, dass Sie Paper 1.19.4 und Java 17 oder höher installiert haben.
2. Legen Sie die Plugin-JAR-Datei in den `plugins`-Ordner Ihres Servers.
3. Starten Sie den Server neu oder laden Sie das Plugin.
4. Ein Platzhalter `config.json` wird im Ordner `configurations` im Plugin-Verzeichnis erstellt.
5. Bearbeiten Sie die `config.json` mit Ihren MySQL-Anmeldeinformationen und anderen Einstellungen.
6. Nach dem ersten Starten und Laden sollten Sie die DB-Verbindung im JSON ändern.
7. Starten Sie den Server erneut oder laden Sie das Plugin noch einmal.
8. Genießen Sie das SimpleHomeSystem!
