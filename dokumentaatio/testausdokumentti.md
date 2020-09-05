#Testausdokumentti

##Yksikkikö testaus
Logiikkalle ja tietorakenteille on kohtuullisen kattavat yksikkö testit.
Testi kattavuus oli:

##Muu testaus

Karttojen ja scenaarioiden lukua tiedostoista on testattus manuaalisesti käyttäen Moving Ai Lab:sin karttoja https://www.movingai.com/benchmarks/dao/index.html.
Koska scenaatiot sisältävät myös lyhyimän polun oikean piduuden niiden käyttö on samana toiminut testeinä algoritmeille ja tällä tavalla löytyikin pari bugia.

## Algoritmien vertailu

Vertailin algoritmeja seuraavilla kartoilla ja niihin liittyvillä scenaariolla: dragon age:origins pelistä brc200d, brc203d, den001d, den005d ja hrt201d. Oikean mailman kaupunkikartat: Berlin_0_256 ja Berlin_0512

Testeissä osoittautui että A* oli keskimäärin 1.32 kertaa nopeampi kuin djikstra, kun taas A* oli keskimäärin 1,68 kertaa nopeampi kui djikstra. Vaihtelu väli A*:rilla oli 0.88-1.78 ja JPS:llä 1.25-2.18. Erityisesti JPS:ltä oli voinut odattaa selkeästi parempia tuloksia. 
