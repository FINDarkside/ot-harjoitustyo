# Arkkitehtuuri

## Rakenne

Ohjelma jakautuu kolmeen tasoon, `tetris.domain`, `tetris.dao` ja `tetris.ui`. `tetris.domain` vastaa pelin logiikasta. `tetris.dao` vastaa pelien ja huipputuloksien lukemisesta ja tallentamisesta. `tetris.domain` vastaa pelin käyttöliittymästä.

#### Luokkakaavio

<img src="kuvat/luokkakaavio.png">

## Käyttöliittymä

Käyttöliittymään kuuluu 5 eri näkymää:
* Päävalikko
* Pelinäkymä
* "Game over" näkymä
* Huipputulos näkymä
* Tallennettujen pelien näkymä

Näkymät on eriytetty pelin logiikasta. Näkymät on suurimmaksi osaksi toteutettu fxml merkintäkielellä sen sijaan että näkymät luotaisiin koodissa. Näkymien logiikasta vastaavat kontrollerit jotka on nimetty näkymien mukaan. Esim `GameView` näkymän kontrolleri on `GameViewController`.

## Sovelluslogiikka

Pelin tärkein luokka on [Game](../../../tree/master/Tetris/src/main/java/tetris/domain/Game.java) joka vastaa koko pelilogiikasta. Luokan julkiset metodit ovat update metodin lisäksi lähinnä gettereitä ja settereitä. `void update(float dt)` on ainoa julkinen metodi joka muuttaa pelin tilaa. 

#### Pelin alustus - Sekvenssikaavio
<img src="kuvat/sekvenssikaavio1.png">

## Tiedon tallennus

`tetris.dao` paketin `DbGameSaveDao` ja `DbHighscoreDao` vastaa pysyvien tietojen lukemisesta ja tallentamisesta.

Peli käyttää SQLite tietokantaa pelien ja huipputuloksien tallentamiseen. SQLite tietokanta sijoitetaan aina samaan kansioon, kuin missä ajettava jar tiedosto sijaitsee. Mikäli tietokantaa ei löydy, ohjelma luo sellaisen.

Testaukseen käytetään SQLiten im-memory moodia.

