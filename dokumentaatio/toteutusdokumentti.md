#Toteutusdokumentti

##Ohjelman rakenne

Ohjelma on jaettu neljään pakkaukseen. Datastructures pitää sisällää ohjelman käyttämät tieto rakenteet. IO käsitelee ohjelman ulkoisia tiedostoja eli karttojen ja scenariooiden lukumesin ohjelmaan. testUI sisältää ohjelman käytttöliittymän. Logic:issa taas o kaikki ohjelman varsinainen toiminnalisuus eli reitinhakualgoritmit, niiden vertaulu ja apumetodeja sisältävä helpers luokka.

##Saavutetut aika ja tilavaativuudet

##Työn puuteet ja parannusehdotukset

A*:rin ja JPS:än tehokkuus suhteessa djikstraan ja erityisesti JPS:än tapauksessa toivutta selvästi pienemmäksi. Kokeilin hieman erilaista versiota JPS:stä, mutta en saanut sitä toimimaan ajoissa. Tämän hetkinen versio tutkii liian monia viereisiä ruutuja silloinkin kuin ne eivät ole pakotettuja naapureita. 

Käyttöliittymä on hyvin minimaalinen, on käytännössä helpompaa kovakoodata halutut testi tapaukset kun lisätä niitä käsin.
