# Ohjelmistotekniikka 2019 - Tetris

Tetris klooni.

Kontrolleina toimivat nuolinäppäimet. Vasen ja oikea nuoli siirtää tippuvaa palikkaa, ylöspäin nuoli kääntää palikkaa ja alaspäin nuoli tiputtaa palikan pohjalle asti.

## Releaset

[Viikko 5](https://github.com/FINDarkside/ot-harjoitustyo/releases/tag/viikko5)

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
