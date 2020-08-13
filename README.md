# ExamsApp Backend

Consider că este necesară o asemenea documentație, deoarece am recreat proiectul (versiunea anterioară avea multe 
quirk-uri și comportamente neașteptate și bizare).

În timp ce conceptul este același, am schimbat puțin structura:
- am trecut de la `application.properties` la `application.yml`, este mult mai ușor de citit si lucrat cu acesta;
- toate pachetele sunt la forma singulara;
- pachetul `models` a devenit `entity`
- pachetul `repositories` a devenit `repo`
- pachetul `controller` a devenit `rest`
- pachetul `exceptions` a fost scos

Aplicația este, acum, 100% RESTful, ceea ce înseamnă că se fac request-uri HTTP cu verbele potrivite. De exemplu, 
pentru listarea de examene, se procedeaza astfel:
```
REQUEST: 
GET /api/exam

RESPONSE:
200 application/json [  ]
```

De asemenea, am implementat filtrarea datelor in functie de specificatii folosind libraria 
[https://github.com/tkaczmarzyk/specification-arg-resolver](Specification Arg Resolver), care mapeaza parametrii
din query in mod dinamic pe query SQL. Asadar, un API call de forma `/api/exam/filter?yearOfStudy=3` va genera in spate
un cod SQL de forma `SELECT e.* FROM exam e WHERE e.year_of_study = 3`.

Metoda asta este mult mai eficienta fiindca reduce din timpul petrecut in filtrarea fiecarui camp in parte. Asadar, in 
loc sa avem, de exemplu, 20 de endpoint-uri diferite, care filtreaza cate un camp fiecare, avem un singur endpoint,
`/api/exam/filter`, care poate fi compus din mai multe campuri filtrabile.

## Structura
- `entity/` -> conține toate entitățile care vor fi ulterior transformate în tabele
- `repo/` -> prescurtare de la `repository`, conține toate clasele și metodele de interogare pe fiecare tabel în parte
- `rest/` -> conține controllerele ce servesc datele din baza de date
- `service/` -> este un layer intermediar între controller și repository, unde se pot aplica procesări ulterioare la nevoie
- `spec/` -> conține toate specificațiile pentru entități ca să beneficieze de sortare în stil OOP

## Development
Java: 11
IDE: IntelliJ Idea Ultimate

## Pornire
Deschide proiectul in IDE si porneste task-ul `:bootRun`, dupa care poti face API call-uri pe adresa 
`http://localhost:8080/api`