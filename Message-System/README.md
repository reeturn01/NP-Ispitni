# Message System (60)

Да се имплементира систем за чување на пораки според определени теми (topics). За секоја порака во системот се чува:
- временски печат (timestamp), објект од класата **_LocalDateTime_**
- содржина на пораката **_String_**
- на која партиција треба да биде зачувана пораката (**_Integer_** којшто може да биде и null).
- клуч на поракта (**_String_**)

Системот за чување на овие пораки функционира на следниов начин:
- Пораките се чуваат во рамки на еден **_Broker_**. Во брокерот може да има повеќе теми (**_topics_**).
- Во секоја тема (**_Topic_**) има одреден број на партиции каде што се чуваат пораки. Пораките се чуваат сортирани според датумот на креирање. На секоја од партициите има исто ограничување за тоа кои пораки може да се чуваат и тоа:
  - може да се чуваат максимум **_limit_** пораки. Доколку пристигне порака кога капацитетот е исполнет, се бриши најстарата порака, а се додава новата.
  - не може да се чуваат пораки постари од **_startDate_**.
- На секој **_Topic_** може да му биде зголемен бројот на партиции, но не смее да биде намален!

За да се имплементира системот потребно е да ги напишете следните класи со соодветни функционалности:
- **_Message_**
  - да се чуваат во неа променливите специфицирани погоре.
  - да се имплементира toString() методот со default имплементацијата (внимавајте на имињата на променливите), но да се печатат само содржината на пораката и нејзиниот timestamp (видете во тест примерите).
- **_Topic_** - класа за чување на пораки по теми. За секој **_Topic_** се чува името на темата, како и колекција од сите пораки распределени по партиции. Оваа класа треба да ги има следните методи:
  - **_Topic(String topicName, int partitionsCount)_** конструктор
  - **_void addMessage (Message message) throws PartitionDoesNotExistException_** - метод за додавање на нова порака во оваа тема. Додавањето се прави во соодветна партиција која се чува како информација во пораката **_message_**. Доколку таму не е специфицирана, треба да се искористи методот **_assignPartition_** од класата **_PartitionAssigner_** која што е дадена во почетниот код. Доколку не постои партицијата која е наведена во пораката да се фрли исклучок од тип **_PartitionDoesNotExistException_**.
  - **_void changeNumberOfPartitions (int newPartitionsNumber) throws UnsupportedOperationException_** - метод за промена на бројот на партиции. Доколку се проба да се намали бројот на партиции да се фрли исклучок како во потписот на функцијата.
  - **_String toString()_** - метод којшто ќе дава toString репрезентација на topic-oт така што за него ќе се испечати неговото име i бројот на партиции во формат **_Topic: %10s Partitions: %5d\n_**, а потоа во нови редови сите пораки содржините на партициите. Партициите да се сортирани според нивниот реден број.
- **_MessageBroker_** - класа за чување на повеќе теми (објекти од класата Topics). Дополнително во оваа класа се чуваат и стартниот датум, како и максималниот капацитет на секоја партиција за сите теми во овој брокер. Да се имплементираат следните методи:
  - **_MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic)_**- конструктор
  - **_void addTopic (String topic, int partitionsCount)_** - метод за додавање на нов topic со одреден број на партиции во него. не смее да се додаде topic со исто име.
  - **_void addMessage (String topic, Message message)_** - метод за додавање на порака на определен topic.
  - **_void changeTopicSettings (String topic, int partitionsCount)_** - метод за промена на бројот на партиции на определен topic
  - **_String toString()_** - метод којшто ќе дава String нотација на брокерот. Најпрво ќе се испечати колку topic-и има, а потоа за сите topic-и ќе се даде нивната toString репрезентација. Topic-ите да се сортирани според нивното име.

## Sample input
```
2018-01-15T00:00
5
1
topic1;6
50
topic1;2018-02-05T08:48;Message from the system with id15273;3;DHCFB
topic1;2018-05-04T06:17;Message from the system with id13928;5;ICJDB
topic1;2018-02-22T01:32;Message from the system with id19340;5;AEDJB
topic1;2018-05-04T08:41;Message from the system with id19594;4;EJFJB
topic1;2018-03-23T20:36;Message from the system with id11642;6;CEGBB
topic1;2018-03-13T16:55;Message from the system with id16574;EHFGB
topic1;2018-07-13T17:48;Message from the system with id19979;JHJJB
topic1;2018-05-21T15:10;Message from the system with id10204;6;EACAB
topic1;2018-03-13T19:21;Message from the system with id18685;FIGIB
topic1;2018-07-12T14:01;Message from the system with id16388;6;IIDGB
topic1;2018-02-04T05:49;Message from the system with id10372;CHDAB
topic1;2018-06-12T21:30;Message from the system with id11313;DBDBB
topic1;2018-04-14T08:46;Message from the system with id16962;CGJGB
topic1;2018-02-18T02:33;Message from the system with id16683;DIGGB
topic1;2018-05-28T01:04;Message from the system with id15794;6;EJHFB
topic1;2018-03-14T22:54;Message from the system with id11035;5;FDABB
topic1;2018-02-19T01:31;Message from the system with id19586;4;GIFJB
topic1;2018-06-22T00:44;Message from the system with id16194;EJBGB
topic1;2018-02-05T11:09;Message from the system with id17755;FFHHB
topic1;2018-04-07T22:35;Message from the system with id16936;GDJGB
topic1;2018-03-18T16:41;Message from the system with id12335;FDDCB
topic1;2018-06-02T01:13;Message from the system with id12574;4;EHFCB
topic1;2018-01-18T14:25;Message from the system with id19447;7;HEEJB
topic1;2018-05-17T08:42;Message from the system with id13969;6;JGJDB
topic1;2018-07-07T04:06;Message from the system with id19472;CHEJB
topic1;2018-03-02T16:07;Message from the system with id15653;DFGFB
topic1;2018-07-02T00:31;Message from the system with id18377;HHDIB
topic1;2018-01-01T11:32;Message from the system with id12798;IJHCB
topic1;2018-06-19T11:58;Message from the system with id10926;5;GCJAB
topic1;2018-04-22T05:02;Message from the system with id14512;CBFEB
topic1;2018-02-26T13:13;Message from the system with id14148;IEBEB
topic1;2018-02-08T12:11;Message from the system with id13456;GFEDB
topic1;2018-06-05T23:54;Message from the system with id18641;BEGIB
topic1;2018-06-15T02:06;Message from the system with id12436;GDECB
topic1;2018-01-07T11:52;Message from the system with id19366;5;GGDJB
topic1;2018-05-23T08:40;Message from the system with id11249;JECBB
topic1;2018-04-15T15:19;Message from the system with id11158;IFBBB
topic1;2018-02-06T08:12;Message from the system with id19260;4;AGCJB
topic1;2018-03-14T22:36;Message from the system with id11194;5;EJBBB
topic1;2018-06-07T22:59;Message from the system with id12290;4;AJCCB
topic1;2018-05-16T00:13;Message from the system with id16060;AGAGB
topic1;2018-05-03T04:29;Message from the system with id16956;4;GFJGB
topic1;2018-07-05T08:58;Message from the system with id17945;FEJHB
topic1;2018-03-30T01:12;Message from the system with id18640;3;AEGIB
topic1;2018-07-11T07:12;Message from the system with id12458;IFECB
topic1;2018-04-23T18:51;Message from the system with id14499;JJEEB
topic1;2018-03-07T14:59;Message from the system with id15083;6;DIAFB
topic1;2018-07-08T22:54;Message from the system with id13643;3;DEGDB
topic1;2018-02-11T18:50;Message from the system with id17812;CBIHB
topic1;2018-05-22T16:50;Message from the system with id16013;3;DBAGB
1
topic1;9
50
topic1;2018-06-30T07:35;Message from the system with id12451;7;BFECB
topic1;2018-02-28T18:50;Message from the system with id15661;BGGFB
topic1;2018-03-19T05:57;Message from the system with id13344;7;EEDDB
topic1;2018-07-11T09:08;Message from the system with id11508;6;IAFBB
topic1;2018-02-02T23:40;Message from the system with id16418;IBEGB
topic1;2018-05-21T11:59;Message from the system with id14593;DJFEB
topic1;2018-05-27T03:47;Message from the system with id14331;BDDEB
topic1;2018-03-27T21:28;Message from the system with id15150;AFBFB
topic1;2018-06-04T20:14;Message from the system with id18428;4;ICEIB
topic1;2018-04-10T17:10;Message from the system with id14469;JGEEB
topic1;2018-04-13T05:25;Message from the system with id10008;IAAAB
topic1;2018-03-25T02:35;Message from the system with id19519;JBFJB
topic1;2018-02-01T00:53;Message from the system with id19183;3;DIBJB
topic1;2018-06-10T12:06;Message from the system with id12479;5;JHECB
topic1;2018-01-07T03:27;Message from the system with id19079;JHAJB
topic1;2018-05-21T00:36;Message from the system with id13464;7;EGEDB
topic1;2018-05-05T16:48;Message from the system with id13629;6;JCGDB
topic1;2018-02-19T01:07;Message from the system with id12086;GIACB
topic1;2018-06-30T06:03;Message from the system with id17509;JAFHB
topic1;2018-07-08T01:59;Message from the system with id18600;AAGIB
topic1;2018-05-14T07:36;Message from the system with id12325;6;FCDCB
topic1;2018-02-06T04:03;Message from the system with id18570;AHFIB
topic1;2018-03-19T15:35;Message from the system with id16472;4;CHEGB
topic1;2018-02-09T08:36;Message from the system with id13062;CGADB
topic1;2018-06-05T01:40;Message from the system with id18527;HCFIB
topic1;2018-03-02T23:05;Message from the system with id15781;BIHFB
topic1;2018-05-01T15:29;Message from the system with id17784;4;EIHHB
topic1;2018-04-22T01:16;Message from the system with id13219;JBCDB
topic1;2018-05-28T01:18;Message from the system with id10640;5;AEGAB
topic1;2018-04-30T22:14;Message from the system with id18893;3;DJIIB
topic1;2018-01-29T19:07;Message from the system with id13328;ICDDB
topic1;2018-04-16T13:06;Message from the system with id16397;3;HJDGB
topic1;2018-01-12T05:46;Message from the system with id13911;BBJDB
topic1;2018-03-29T15:13;Message from the system with id11666;5;GGGBB
topic1;2018-02-22T12:12;Message from the system with id11513;DBFBB
topic1;2018-05-18T19:10;Message from the system with id17907;HAJHB
topic1;2018-01-06T17:19;Message from the system with id17596;GJFHB
topic1;2018-06-07T03:45;Message from the system with id12042;CEACB
topic1;2018-03-01T21:48;Message from the system with id11427;HCEBB
topic1;2018-06-30T17:07;Message from the system with id12370;3;AHDCB
topic1;2018-05-05T03:51;Message from the system with id13800;AAIDB
topic1;2018-07-09T15:49;Message from the system with id14293;DJCEB
topic1;2018-05-08T09:47;Message from the system with id10597;6;HJFAB
topic1;2018-04-21T05:16;Message from the system with id18395;5;FJDIB
topic1;2018-04-24T11:49;Message from the system with id18034;6;EDAIB
topic1;2018-06-08T09:20;Message from the system with id18185;6;FIBIB
topic1;2018-05-30T21:40;Message from the system with id19407;HAEJB
topic1;2018-07-11T16:02;Message from the system with id12313;7;DBDCB
topic1;2018-04-10T21:56;Message from the system with id11034;EDABB
topic1;2018-01-29T04:05;Message from the system with id19917;HBJJB
```

## Sample output
```
===ADDING MESSAGES TO TOPICS===
The topic topic1 does not have a partition with number 7
===BROKER STATE AFTER ADDITION OF MESSAGES===
Broker with  1 topics:
Topic:     topic1 Partitions:     6
1 : Count of messages:     4
Messages:
Message{timestamp=2018-03-13T16:55, message='Message from the system with id16574'}
Message{timestamp=2018-05-23T08:40, message='Message from the system with id11249'}
Message{timestamp=2018-07-07T04:06, message='Message from the system with id19472'}
Message{timestamp=2018-07-13T17:48, message='Message from the system with id19979'}
2 : Count of messages:     3
Messages:
Message{timestamp=2018-02-18T02:33, message='Message from the system with id16683'}
Message{timestamp=2018-02-26T13:13, message='Message from the system with id14148'}
Message{timestamp=2018-04-14T08:46, message='Message from the system with id16962'}
3 : Count of messages:     5
Messages:
Message{timestamp=2018-04-07T22:35, message='Message from the system with id16936'}
Message{timestamp=2018-04-22T05:02, message='Message from the system with id14512'}
Message{timestamp=2018-05-16T00:13, message='Message from the system with id16060'}
Message{timestamp=2018-05-22T16:50, message='Message from the system with id16013'}
Message{timestamp=2018-07-08T22:54, message='Message from the system with id13643'}
4 : Count of messages:     5
Messages:
Message{timestamp=2018-06-05T23:54, message='Message from the system with id18641'}
Message{timestamp=2018-06-07T22:59, message='Message from the system with id12290'}
Message{timestamp=2018-07-02T00:31, message='Message from the system with id18377'}
Message{timestamp=2018-07-05T08:58, message='Message from the system with id17945'}
Message{timestamp=2018-07-11T07:12, message='Message from the system with id12458'}
5 : Count of messages:     5
Messages:
Message{timestamp=2018-04-23T18:51, message='Message from the system with id14499'}
Message{timestamp=2018-05-04T06:17, message='Message from the system with id13928'}
Message{timestamp=2018-06-12T21:30, message='Message from the system with id11313'}
Message{timestamp=2018-06-19T11:58, message='Message from the system with id10926'}
Message{timestamp=2018-06-22T00:44, message='Message from the system with id16194'}
6 : Count of messages:     5
Messages:
Message{timestamp=2018-03-07T14:59, message='Message from the system with id15083'}
Message{timestamp=2018-05-21T15:10, message='Message from the system with id10204'}
Message{timestamp=2018-05-28T01:04, message='Message from the system with id15794'}
Message{timestamp=2018-06-15T02:06, message='Message from the system with id12436'}
Message{timestamp=2018-07-12T14:01, message='Message from the system with id16388'}
===CHANGE OF TOPICS CONFIGURATION===
===ADDING NEW MESSAGES TO TOPICS===
===BROKER STATE AFTER CONFIGURATION CHANGE===
Broker with  1 topics:
Topic:     topic1 Partitions:     9
1 : Count of messages:     4
Messages:
Message{timestamp=2018-03-13T16:55, message='Message from the system with id16574'}
Message{timestamp=2018-05-23T08:40, message='Message from the system with id11249'}
Message{timestamp=2018-07-07T04:06, message='Message from the system with id19472'}
Message{timestamp=2018-07-13T17:48, message='Message from the system with id19979'}
2 : Count of messages:     5
Messages:
Message{timestamp=2018-01-29T04:05, message='Message from the system with id19917'}
Message{timestamp=2018-05-18T19:10, message='Message from the system with id17907'}
Message{timestamp=2018-05-30T21:40, message='Message from the system with id19407'}
Message{timestamp=2018-06-07T03:45, message='Message from the system with id12042'}
Message{timestamp=2018-07-08T01:59, message='Message from the system with id18600'}
3 : Count of messages:     5
Messages:
Message{timestamp=2018-04-30T22:14, message='Message from the system with id18893'}
Message{timestamp=2018-05-16T00:13, message='Message from the system with id16060'}
Message{timestamp=2018-05-22T16:50, message='Message from the system with id16013'}
Message{timestamp=2018-06-30T17:07, message='Message from the system with id12370'}
Message{timestamp=2018-07-08T22:54, message='Message from the system with id13643'}
4 : Count of messages:     5
Messages:
Message{timestamp=2018-01-29T19:07, message='Message from the system with id13328'}
Message{timestamp=2018-06-07T22:59, message='Message from the system with id12290'}
Message{timestamp=2018-07-02T00:31, message='Message from the system with id18377'}
Message{timestamp=2018-07-05T08:58, message='Message from the system with id17945'}
Message{timestamp=2018-07-11T07:12, message='Message from the system with id12458'}
5 : Count of messages:     5
Messages:
Message{timestamp=2018-04-10T21:56, message='Message from the system with id11034'}
Message{timestamp=2018-06-10T12:06, message='Message from the system with id12479'}
Message{timestamp=2018-06-12T21:30, message='Message from the system with id11313'}
Message{timestamp=2018-06-19T11:58, message='Message from the system with id10926'}
Message{timestamp=2018-06-22T00:44, message='Message from the system with id16194'}
6 : Count of messages:     5
Messages:
Message{timestamp=2018-06-08T09:20, message='Message from the system with id18185'}
Message{timestamp=2018-06-15T02:06, message='Message from the system with id12436'}
Message{timestamp=2018-07-09T15:49, message='Message from the system with id14293'}
Message{timestamp=2018-07-11T09:08, message='Message from the system with id11508'}
Message{timestamp=2018-07-12T14:01, message='Message from the system with id16388'}
7 : Count of messages:     5
Messages:
Message{timestamp=2018-03-19T05:57, message='Message from the system with id13344'}
Message{timestamp=2018-05-21T00:36, message='Message from the system with id13464'}
Message{timestamp=2018-06-05T01:40, message='Message from the system with id18527'}
Message{timestamp=2018-06-30T07:35, message='Message from the system with id12451'}
Message{timestamp=2018-07-11T16:02, message='Message from the system with id12313'}
8 : Count of messages:     3
Messages:
Message{timestamp=2018-02-09T08:36, message='Message from the system with id13062'}
Message{timestamp=2018-03-01T21:48, message='Message from the system with id11427'}
Message{timestamp=2018-05-27T03:47, message='Message from the system with id14331'}
9 : Count of messages:     5
Messages:
Message{timestamp=2018-03-02T23:05, message='Message from the system with id15781'}
Message{timestamp=2018-03-25T02:35, message='Message from the system with id19519'}
Message{timestamp=2018-04-22T01:16, message='Message from the system with id13219'}
Message{timestamp=2018-05-21T11:59, message='Message from the system with id14593'}
Message{timestamp=2018-06-30T06:03, message='Message from the system with id17509'}
```