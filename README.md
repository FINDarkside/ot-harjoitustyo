# Ohjelmistotekniikka 2019 - harjoitustyö

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