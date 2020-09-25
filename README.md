# Kata notes

### Werkwijze / work log
1. Ik lees Requirements.txt door en werp een eerste blik op de code. Ik run de code een keer en bekijk de output.
2. Voor ik iets van code wijzig ga ik tests schrijven met als basis de documentatie, zodat mijn changes geen impact hebben op de werking. De niet-werkende test doe ik weg. Ik schrijf per type item een simpele test die het verwachte gedrag controleert, alsook de edge cases. Ik schrijf ook nog een extra test die de `TestFixture` data gebruikt en de output verifiëert met de oorspronkelijke output.
3. Low hanging fruit fixen, refactoring met hulp van IDE:
    1. For-loop met index naar `forEach` loop, veel leesbaarder
    2. Extract duplicate code --> ik heb hieruit een `increaseValue()` / `decreaseValue()` met range check gedestilleerd, alsook `decreaseSellIn()`
    3. If-statements inverten waar nuttig voor duidelijkere logica
    4. Nested if-statements omdraaien in cases waar ik "opmerk" dat daardoor de logica duidelijker leesbaar wordt
    5. Constants aanmaken voor magic numbers en item types. --> In `companion object` van `Item` class, mag dat? 😬
    6. `TestFixture` wat meer kotlin-minded maken: `.isEmpty` ipv `.length == 0`, `until` ipv range check ...
4. Logica herwerken met use case pattern: ik zoek uit wat het gedrag is voor elk soort item, ipv 1 for loop met een hoop if-checks. Dat gaat wel vlot nu alles al wat leesbaarder is. For now zet ik het gedrag voor elk item in een aparte method. Elke methode annoteer ik met een comment met het gewenste gedrag voor elk item.
5. Conjured item case implementeren, dat is een mooi moment hiervoor nu alle items apart staan. Met bijbehorende test, uiteraard.
6. Nu wil ik graag elke variatie in gedrag voor een item in een aparte class steken. Ik kies voor een `ItemUpdater`-interface die ik voor elk item type implementeer. Ik maak een `ItemUpdaterProvider` die voor een item de juiste updater voorziet.
7. De `ItemUpdaterProvider` herwerk ik zodat er een map bestaat van item type naar de juiste `ItemUpdater` instance, dit bespaart het aanmaken van een hoop objects in de for loop (1 updater per item type, ipv 1 updater per item).
8. Nog veel kleine wijzigingen die onder "general opkuiswerk" vallen, bv. `ItemUpdater` had eerst 2 methods (`updateSellIn` en `updateQuality`) die in de for-loop werden opgeroepen. Nu is dat 1 method (`updateItem()`) geworden omdat het onderscheid niet uitmaakt voor de gebruiker van `ItemUpdater`. Comments wat consistenter gemaakt, ...

### Hiermee is het volgende bereikt
- Tests zorgen ervoor dat regressie op het beschreven gedrag niet meer kan plaatsvinden
- Het gedrag van elk type item zit apart en de implementatie van elk item is makkelijk leesbaar
- Een nieuw item ondersteunen is makkelijk en vereist enkel het implementeren van de nieuwe logica (al dan niet gebaseerd op die van een "gewoon" item)
- Elke klasse heeft nu een duidelijke functie en doet niet meer dan nodig
- Voldoet aan SOLID principes
- De code is gedocumenteerd waar nodig

### Bedenkingen
- Ik heb erover nagedacht om subclasses te maken van de `Item`-class per item type, aangezien `Item` een `open class` is. Maar ik vond het niet heel duidelijk uit de opdracht of dat nu mocht of niet. Gezien de tekst ook vermeldt dat de `items`-array ook niet zomaar aangepast mag worden heb ik het niet gedaan.
- `Item` had ik graag aangepast zodat er een item type o.i.d. bestaat, ipv deze exacte match op item name. Ik had ook graag van `Item` een `data class` gemaakt, dan krijg je een nuttige `toString()`-functie, `equals` en `hashcode` gratis. Maar dat zijn beperkingen van de opdracht.
- De package structuur van mijn uiteindelijke oplossing vind ik maar matig. Normaliter zou ik het `Item`-model in een apart model package bewaren, maar in zo'n simpel appje als dit vind ik het een beetje overkill, dan krijg je 1 class per package en dat is eerder vervelend dan nuttig. In een echt project zou ik er meer aandacht aan besteden.
- Ik heb erover nagedacht om het makkelijker te maken om nieuwe item types te ondersteunen. Momenteel moet je een nieuwe `ItemUpdater` voorzien en daar manueel een nieuwe instance van aanmaken in `ItemUpdaterProvider`. In een echt project met een uitgebreide dependency injection setup (bv. dagger met multibindings) moet het wel mogelijk zijn om een flexibeler systeem op te zetten voor nieuwe items. Dat kan nu ook maar dat is veel meer werk voor eigenlijk weinig returns. Zeker gezien de eenvoudigheid van de oorspronkelijke applicatie vermoed ik dat dat het doel een beetje voorbij schiet. Ik heb het vooral simpel proberen te houden.