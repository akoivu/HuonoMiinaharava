# Aihe:
Miinaharava. Toteutetaan perinteinen miinaharavapeli. Tavoitteena saada ainakin peruspeli toimimaan moitteettomasti, luoda hirveän ruma graafinen käyttöliittymä sekä erikokoiset pelilaudat. Jos homma on toimintakuntoisena liian aikaisin, lisään high score -ominaisuuden ja jotain muuta kivaa.

# Rakennekuvaus:
Sovellus rakentuu sovelluslogiikasta, sekä käyttöliittymästä. Logiikkaa pyörittävät luokat Ruutu, eli miinakentän yksittäinen ruutualkio, sekä Ruudukko, joka koostuu useasta Ruutu-oliosta. Ruudun ja Ruudukon eri tilat on toteutettu enumina: Ruutu voi olla, joko TYHJA tai MIINA siitä riippuen onko se miina vai ei, ja Ruudukko voi olla joko tilassa PELITILA, HAVIOTILA tai VOITTOTILA riippuen siitä missä vaiheessa peli on.

Käyttöliittymä koostuu luokasta Applikaatio, joka käynnistää sovelluksen, sekä viidestä eri näkymäluokasta, joita en kokenut tarpeelliseksi esittää luokkakaaviossa. Käyttöliittymä aluksi pyytää käyttäjältä miinakentän leveyden, korkeuden sekä halutun miinamäärän ja sen jälkeen luo näiden tietojen perusteella Ruudukon. Tämän Ruudukon perusteella käyttöliittymä päivittää näkymää jokaisen klikkauksen jälkeen.

# Luokkakaavio:
![Määrittelyvaiheen luokkakaavio](/dokumentaatio/kuvat/LuokkakaavioUPDATED.png)

# Sekvenssikaaviot:
## Miinattoman ruudun klikkaus
![Käyttäjä klikkaa ruutua, jossa ei ole miinaa](/dokumentaatio/kuvat/klikataanMiinatontaUPDATE.png)
## Ruudun liputus
![Liputus](/dokumentaatio/kuvat/liputetaan.png)
