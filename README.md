# Ohjelmistotekniikka 2019 - harjoitustyö

## Dokumentaatio

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmäärittely.md)

## Komentorivitoiminnot

**Käynnistys**

```
mvn compile exec:java "-Dexec.mainClass=tetris.MainApp"
```

**Testien ajo**

```
mvn test
```

**Testikattavuusraportti**

```
mvn test jacoco:report
```
