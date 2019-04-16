# Ohjelmistotekniikka 2019 - Tetris

Tetris klooni.

## Dokumentaatio

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmäärittely.md)

[Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

**Käynnistys**

```
mvn compile exec:java
```

**Testien ajo**

```
mvn test
```

**Testikattavuusraportti**

```
mvn test jacoco:report
```

**Checkstyle**

```
mvn jxr:jxr checkstyle:checkstyle
```

**Jar tiedoston generointi**
```
mvn package
```