# Ohjelmistotekniikka 2019 - Tetris

Tetris klooni.

Kontrolleina toimivat nuolinäppäimet. Vasen ja oikea nuoli siirtää tippuvaa palikkaa, ylöspäin nuoli kääntää palikkaa ja alaspäin nuoli tiputtaa palikan pohjalle asti.

## Releaset

[Viikko 5](https://github.com/FINDarkside/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/FINDarkside/ot-harjoitustyo/releases/tag/viikko6)

## Dokumentaatio

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmäärittely.md)

[Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

[Käyttöohjeet](dokumentaatio/käyttöohjeet.md)

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
Generoidun raportin sijainti: `target/site/jacoco/index.html`


**Checkstyle**

```
mvn jxr:jxr checkstyle:checkstyle
```
Generoidun raportin sijainti: `target/site/checkstyle.html`

**Jar tiedoston generointi**
```
mvn package
```

**JavaDoc**
```
mvn javadoc:javadoc
```
Generoidun dokumentaation sijainti: `target/site/apidocs/index.html`