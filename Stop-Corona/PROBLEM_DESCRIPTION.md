# Stop Corona (60)
За потребите на Министерството за здраство потребно е да се направи апликација која ќе ги менаџира корисниците на апликацијата и контактите со коишто биле во близина. Да се дефинира класа `StopCoronaApp` и за истата да се имплементираат:
- default constructor
- метод `void addUser(String name, String id)`- што ќе регистрира нов корисник на апликацијата, методот треба да фрли исклучок од тип `UserIdAlreadyExistsException` доколку веќе постои корисник со такво id.
- метод `void addLocations (String id, List<ILocation> iLocations)` - што за корисникот со ID исто како првиот аргумент, ќе ги регистрира сите негови детектирани локации. `ILocation` e интерфејс што ви е даден во почетниот код и обезбедува информации за должината и ширината на локацијата, како и времето кога е детектирана таа локација.
- метод `void detectNewCase (String id, LocalDateTime timestamp)` - што симулира пријавување на даден корисник дека е носител на вирусот. Првиот аргумент е неговото ID, а вториот е времето кога корисникот пријавил дека е носител.
- метод `void detectNewCase (String id, LocalDateTime timestamp)` - што симулира пријавување на даден корисник дека е носител на вирусот. Првиот аргумент е неговото ID, а вториот е времето кога корисникот пријавил дека е носител.
- метод `Map<User, Integer> getDirectContacts (User u)` - што ќе враќа мапа во која што клучеви ќе се сите блиски контакти на корисикот `u`, а соодветните вредности во мапата ќе се колку пати се оствариле блиски контакти со корисникот u.
- метод `Collection<User> getIndirectContacts (User u)` - што ќе враќа колекција од индиректните контакти на корисникот `u`. За индиректни контакти се сметаат блиските контакти на директните контакти на `u`, при што еден корисник не може да биде и директен и индиректен контакт на некој друг корисник.
- метод `void createReport ()`- што ќе креира и испечати извештај за МЗ во којшто за сите корисници-носители на вирусот ќе испечати информации во следниот формат.

```
[user_name] [user_id] [timestamp_detected]
Direct contacts:
[contact1_name] [contact1_first_five_letters_of_id] [number_of_detected_contacts1]
[contact2_name] [contact2_first_five_letters_of_id] [number_of_detected_contacts1]
...
[contactN_name] [contactN_first_five_letters_of_id] [number_of_detected_contactsN]
Count of direct contacts: [sum]
Indirect contacts:
[contact1_name] [contact1_first_five_letters_of_id] 
[contact2_name] [contact2_first_five_letters_of_id] 
...
[contactN_name] [contactN_first_five_letters_of_id]
Count of indirect contacts: [count]
```

- Дополнително на крајот на извештајот да се испечати просечниот број на директни и индиректни контакти на корисниците што се носители на Корона вирусот.

_**Напомена:**_
- Близок контакт се смета контактот на двајца корисници кога евклидовото растојание помеѓу некоја од нивните локациите е `<=2`, а временското растојание на соодветно измерените локации е помало од 5 минути.
- Носителите на вирусот да се сортирани според времето кога се детектирани дека се носители. Директните контакти на носителите да бидат сортирани според бројот на остварени блиски контакти во опаѓачки редослед. Индиректните контакти да се сортирани лексикографски според нивното име, а доколку е исто според ИД на корисникот.

## Sample input
```text
REG Sofija dd97e97f-246f-4e17-a828-1ccff0ed6be7
REG Viktorija 4c05f170-6b1b-4e87-b9f9-0506f5c9d88b
REG Viktorija a79a7f36-cd2b-42d9-aa14-76d08ddde2d9
REG Riste ce776a00-561f-492d-a147-29d822bfdda6
REG Jovana 2a63d9e7-a7c9-48c8-9784-a02956e65af3
REG Sofija 193cb87a-566a-44e7-b0d6-075d0596a35b
REG Viktorija a7e15ed2-0ff4-40e9-a042-3274e1025b53
REG Trajce d8ebec4d-1f8f-4eef-b67e-4bcfccda7cb4
REG Petar cee31d55-9ceb-4614-9eb2-9b2c8839abad
REG Stefan d78b27e0-cb8f-4175-8f9a-d169d8b7ff41
LOC dd97e97f-246f-4e17-a828-1ccff0ed6be7 2.02 5.62 2020-06-06T22:28:25.385 0.07 5.06 2020-06-06T22:19:25.396 
LOC 4c05f170-6b1b-4e87-b9f9-0506f5c9d88b 2.27 5.35 2020-06-06T22:17:25.397 1.25 5.93 2020-06-06T22:24:25.398 
LOC a79a7f36-cd2b-42d9-aa14-76d08ddde2d9 0.24 5.28 2020-06-06T22:23:25.398 2.98 5.38 2020-06-06T22:22:25.398 
LOC ce776a00-561f-492d-a147-29d822bfdda6 1.90 5.17 2020-06-06T22:17:25.399 0.83 5.24 2020-06-06T22:18:25.399 
LOC 2a63d9e7-a7c9-48c8-9784-a02956e65af3 4.94 5.47 2020-06-06T22:18:25.400 4.11 5.14 2020-06-06T22:21:25.400 
LOC 193cb87a-566a-44e7-b0d6-075d0596a35b 4.24 5.81 2020-06-06T22:24:25.401 1.50 5.81 2020-06-06T22:23:25.401 
LOC a7e15ed2-0ff4-40e9-a042-3274e1025b53 3.95 5.48 2020-06-06T22:29:25.401 4.28 5.57 2020-06-06T22:29:25.401 
LOC d8ebec4d-1f8f-4eef-b67e-4bcfccda7cb4 1.41 5.05 2020-06-06T22:20:25.402 0.74 5.68 2020-06-06T22:25:25.402 
LOC cee31d55-9ceb-4614-9eb2-9b2c8839abad 0.30 5.93 2020-06-06T22:17:25.402 0.21 5.45 2020-06-06T22:17:25.402 
LOC d78b27e0-cb8f-4175-8f9a-d169d8b7ff41 2.24 5.90 2020-06-06T22:23:25.403 2.87 5.02 2020-06-06T22:20:25.403 
DET 2a63d9e7-a7c9-48c8-9784-a02956e65af3 2020-06-07T22:19:25.403
DET a79a7f36-cd2b-42d9-aa14-76d08ddde2d9 2020-06-07T22:19:25.800
REP
```

## Sample output
```text
Jovana 2a63d9e7-a7c9-48c8-9784-a02956e65af3 2020-06-07T22:19:25.403
Direct contacts:
Viktorija a79a*** 2
Viktorija 4c05*** 1
Stefan d78b*** 1
Sofija 193c*** 1
Count of direct contacts: 5
Indirect contacts: 
Riste ce77***
Sofija dd97***
Trajce d8eb***
Viktorija a7e1***
Count of indirect contacts: 4
Viktorija a79a7f36-cd2b-42d9-aa14-76d08ddde2d9 2020-06-07T22:19:25.800
Direct contacts:
Sofija 193c*** 3
Trajce d8eb*** 3
Jovana 2a63*** 2
Viktorija 4c05*** 2
Stefan d78b*** 2
Riste ce77*** 2
Sofija dd97*** 2
Count of direct contacts: 16
Indirect contacts: 
Petar cee3***
Viktorija a7e1***
Count of indirect contacts: 2
Average direct contacts: 10.5000
Average indirect contacts: 3.0000
```