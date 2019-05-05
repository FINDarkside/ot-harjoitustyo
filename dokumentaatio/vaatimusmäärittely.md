# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on [tetris](https://en.wikipedia.org/wiki/Tetris#Gameplay) peli. Pelissä on siis idea muodostaa horisontaalisia rivejä palikoilla joita tippuu pelialueen yläreunasta.
Kun rivi on valmis, sen rivin palikat poistuvat. Peli loppuu kun joku palikka ei enää mahdu pelialueelle. En lähde tässä ruotimaan tarkemmin tetriksen perusideaa, yllä on linkki wikipedia artikkeliin. Toteutettava peli ei välttämättä vastaa alkuperäistä tetristä kaikilta osin, mutta tästä lisää myöhemmin toiminnallisuuksissa.

## Toiminnallisuudet

* Perus tetris toiminnallisuus
  * Palikoita tippuu yksi kerrallaan
  * Valmis horisontaalinen rivi poistaa sen rivin palikat
  * Palikoita voi pyörittää
  * Palikat tippuvat tiettyä vauhtia alaspäin
  * Palikan voi tiputtaa ilman odotusta
* Rivien poistamisesta saa pisteitä
* Leaderboards toiminnallisuus. Eli lista korkeimmista pisteistä ja nimimerkeistä
* Pelin voi keskeyttää ja jatkaa myöhemmin
* Pelin voi tallentaa ja vanhaa peliä voi jatkaa myöhemmin (vaikka ohjelma olisi suljettu välillä)

## Käyttöliittymään näkymät:

* Päänäkymä - Sisältää painikkeet pelin aloitukseen, scoreboardille ja mahdollisesti muihin asioihin riippuen toteutetuista ominaisuuksista
* Pelinäkymä - Sisältää itse pelin
* Huipputulos näkymä - Tulokset järjestetty pisteitten mukaan laskevasti
* Tallennettujen pelien selaus näkymä - Näkymässä voi ladata ja poistaa tallennettuja pelejä
* Näkymä uuden huipputuloksen lisäämiselle

## Jatkokehitysideoita

* Erilaisia asetuksia, esim. suurempi pelikenttä
* Responsiivisempi käyttöliittymä
* Kauniimpi käyttöliittymä
* Paremmat kontrollit, jotta voisi esim. pitää vasenta nuolta pohjassa eikä pitäisi naputella
