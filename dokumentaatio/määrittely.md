# Reittinhaku algoritmien vertailu. 

A* on ehkä eniten käytetty reitinhaku algoritmi, mutta onko se aina paras? 
Työn tarkoitus on verrata A* algoritmia muihin  algoritmeihin erilaisissa kaksiulotteisissa ruudukkopohjaisissa kartoissa. 

Toteutan siis A* algoritmin ja  syvyyshaun ja Dijkstran algoritmin, sekä näitä varten tarvittavat tietorakenteet eli jonon, verkon, keon ja puun.

Aineistona käytän movinlabsin 2D karttoja (linkki tähän).Kartat ovat kaksiulottaisia ruudukoita, jotka  muunnan verkoiksi. 
Mahdollinen lisä jos aikaa jää olisi verkkojen opitimointi ennen hakualgoritmien käyttöä. Eli käytännössä niin, että jätetään vain reitit reunoille ja esteille eikä välissä olevia ruutuja/solmuja. Ja sen tutkimen miten paljon kukin algoritmi hyötää tälleisestä esiprosessoinnista. 

Ohjelma saa siis syötteeksi kartan tai karttoja ja niistä aloitus pisteen ja maalin kartalla. 
Tuloksena on kuinka kauan kullakin algoritmillä kestää löytää lyhyin reitti aloituspisteestä maaliin kullakin kartalla.

## Tavoitteena olevat aika ja tilavaativuudet
n = solmujen määrä
k = kaarien määrä 

### A*
Aikavaativuus
yläraja n + k log k
Tilavaativuus 
yläraja n

### Leveyshaku
Aikavaativuus
yläraja n + k
Tilavaativuus 
yläraja n

### Dijkstran algoritmi
Aikavaativuus
yläraja n + k log k
Tilavaativuus 
yläraja n


### Lähteet
https://en.wikipedia.org/wiki/Breadth-first_search
https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
https://en.wikipedia.org/wiki/A*_search_algorithm
https://www.movingai.com/benchmarks/grids.html