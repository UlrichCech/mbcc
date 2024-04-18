# Mercedes-Benz Car-Configurator (Demo)

Dieses Projekt ist ein einfaches Beispiel-/Übungsprojekt. Es ist in GitHub unter der URL "https://github.com/ulrichcech/mbcc" zu finden.   


## Installation

Das Sourcecode-Projekt kann über folgenden Link geclont werden: https://github.com/ulrichcech/mbcc.git

Nachdem das Projekt ausgecheckt wurde, sind zum lokalen Start des Backends in der IDE (IntelliJ) noch folgende Schritte auszuführen:
1. Download von Payara 6 (https://nexus.payara.fish/repository/payara-community/fish/payara/distributions/payara/6.2024.4/payara-6.2024.4.zip)
2. Entpacken des ZIP-Archives
3. In IntelliJ eine entsprechende RUN-Configuration einrichten ("Glassfish Server Local")
   1. Im "Server"-Tab ist bei Application-Server der gerade heruntergeladene Payara 6 einzurichten 
   2. als Domain ist "domain1" erforderlich
   3. Im "Deployment"-Tab sollte das Artifakt "mb-car-configurator-server:war exploded" ausgewählt werden
4. Es ist die Datenbank lokal zu starten; dazu gibt es im Verzeichnis "```deployment/execution/dev```" die Datei ```docker-compose.yml```
   1. in der ```docker-compose.yml``` die Konfiguration für "mbcc-db" ausführen (hiermit wird eine lokale PostgreSQL-Datenbank innerhalb eines Docker-Containers gestartet, wobei der Port 5432 exponiert wird)
5. Schließlich kann nun die Backend-Server-Anwendung (Run-Configuration) gestartet werden


Außerdem ist die Anwendung als Docker-Container verfügbar. Dazu findet sich im Sourcecode-Repository im Verzeichnis "/deployment/execution/test" eine weitere ```docker-compose.yml``` Datei
1. Die ```docker-compose.yml``` Datei enthält die Konfiguration für die Datenbank UND die Anwendung
2. Bei Authentifizierungs-Problemen kann man sich in die GitHub-Container-Registry folgendermaßen einloggen: ```echo <classic-token> | docker login ghcr.io -u ulrichcech --password-stdin``` 


## Swagger-Dokumentation

Die Anwendung stellt ein simples REST-Interface zur Verfügung. Bei laufender Anwendung kann diese im Browser folgendermaßen aufgerufen werden:
```http://localhost:8080/api/openapi-ui/index.html```

Zudem lässt sich die OpenAPI im reinen JSON-Format ausgeben:  
http://localhost:8080/openapi?format=json

## Health-Check

Unter folgenden URLs können health-check Statusinformationen abgerufen werden:
- http://localhost:8080/health/ready (dieser Check läuft bis in die Datenbank)
- http://localhost:8080/health/live  (dieser Check )


```bash
git clone git@github.com:ulrichcech/mbcc.git
cd mbcc
