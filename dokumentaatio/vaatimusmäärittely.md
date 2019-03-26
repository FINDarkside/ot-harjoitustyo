# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on [tetris](https://en.wikipedia.org/wiki/Tetris#Gameplay) peli. Pelissä on siis idea muodostaa horisontaalisia rivejä palikoilla joita tippuu pelialueen yläreunasta.
Kun rivi on valmis, sen rivin palikat poistuvat. Peli loppuu kun joku palikka ei enää mahdu pelialueelle. En lähde tässä ruotimaan tarkemmin tetriksen perusideaa, yllä on linkki wikipedia artikkeliin. Toteutettava peli ei välttämättä vastaa alkuperäistä tetristä kaikilta osin, mutta tästä lisää myöhemmin toiminnallisuuksissa.

## Suunnitellut toiminnallisuudet

* Perus tetris toiminnallisuus
  * Palikoita tippuu yksi kerrallaan
  * Valmis horisontaalinen rivi poistaa sen rivin palikat
  * Palikoita voi pyörittää
  * Palikat tippuvat tiettyä vauhtia alaspäin
  * Palikoiden tippumista voi nopeuttaa
* Rivien poistamisesta saa pisteitä
* Leaderboards toiminnallisuus. Eli lista korkeimmista pisteistä ja nimimerkeistä

Mahdollisia toiminnallisuuksia mikäli aikaa riittää:

* Mahdollisuus tallentaa ja ladata keskeneräisiä pelejä
* Ei rumat grafiikat
* Erilaisia säätöjä ja asetuksia peliin. Mahdollisesti esimerkiksi käyttäjän luomia palikoita (esim 1x4 palikka) tai jonkuntapaisia erikoispalikoita.

Käyttöliittymään mahdollisia näkymiä:

* Päänäkymä - Sisältää painikkeet pelin aloitukseen, scoreboardille ja mahdollisesti muihin asioihin riippuen toteutetuista ominaisuuksista
* Pelinäkymä - Sisältää itse pelin
* Scoreboard - Sisältää huipputulokset
* Asetukset - Näkymä asetusten muuttamiselle mikäli ominaisuus toteutetaan
