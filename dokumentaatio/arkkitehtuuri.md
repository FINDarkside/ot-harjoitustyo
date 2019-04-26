# Arkkitehtuuri

## Rakenne

Ohjelma jakautuu kahteen pakettiin, `tetris.domain` ja `tetris.ui`. Tarkoitus on vielä toteuttaa kolmas paketti, `dao` joka vastaa huipputuloksien ja tallennettujen pelien lukemisesta ja tallentamisesta.

#### Luokkakaavio

<img src="kuvat/luokkakaavio.png">

## Käyttöliittymä

Käyttöliittymään kuuluu tällä hetkellä kaksi näkymää:
* Valikko
* Pelinäkymä

Tarkoitus on vielä lisätä seuraavat näkymät:

* Huipputulokset
* Tallennetut (keskeneräiset) pelit

Näkymät on eriytetty pelin logiikasta. Näkymät on määritelty fxml tiedostoina sen sijaan että näkymät luotaisiin koodissa.

## Sovelluslogiikka

Pelin tärkein luokka on [Game](../../../tree/master/Tetris/src/main/java/tetris/domain/Game.java) joka vastaa koko pelilogiikasta. Luokan julkiset metodit ovat update metodin lisäksi lähinnä gettereitä ja settereitä. `void update(float dt)` on ainoa julkinen metodi joka muuttaa pelin tilaa.

#### Pelin alustus - Sekvenssikaavio
<img src="kuvat/sekvenssikaavio1.png">


## Puuttuvat ominaisuudet ja muut heikkoudet

* Tällä hetkellä pelin loputtua ainoa tapa aloittaa uusi, on käynnistää ohjelma uudestaan
* Tällä hetkellä aktiivisen tetrominon pyörittäminen voi johtaa siihen että tetromino on osittain pelialueen ulkopuolella
* Huipputuloksia ei ole vielä toteutettu
* Mahdollisuutta tallentaa ja jatkaa peliä ei ole vielä toteutettu
