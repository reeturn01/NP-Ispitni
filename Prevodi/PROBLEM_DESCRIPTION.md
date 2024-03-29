# Преводи

Да се имплементира класа _**Subtitles**_ која ќе чита од влезен тек (стандарден влез, датотека, ...) превод во стандарден _**srt**_ формат. Секој еден елемент од преводот се состои од реден број, време на почеток на прикажување, време на крај на прикажување и текст и е во следниот формат (пример):

```
2
00:00:48,321 --> 00:00:50,837
Let's see a real bet.
```
Делот со текстот може да има повеќе редови. Сите елементи се разделени со еден нов ред.

Ваша задача е да ги имплементирате методите:

- _**Subtitles()**_ - default конструктор
- _**int loadSubtitles(InputStream inputStream)**_ - метод за читање на преводот (враќа резултат колку елементи се прочитани)
- _**void print()**_ - го печати вчитаниот превод во истиот формат како и при читањето.
- _**void shift(int ms)**_ - ги поместува времињата на сите елементи од преводот за бројот на милисекунди кои се проследува како аргумент (може да биде негативен, со што се поместуваат времињата наназад).

## Sample Input

```
1
00:00:43,700 --> 00:00:47,973
Come on ladies, we're pushing pennies
around like a lot of old 'tards here.

2
00:00:48,321 --> 00:00:50,837
Let's see a real bet.

3
00:01:10,889 --> 00:01:12,216
Twice.

4
00:01:12,394 --> 00:01:14,271
- That's better.
- Fold.

5
00:01:18,099 --> 00:01:20,056
All in.

6
00:01:23,230 --> 00:01:25,563
Good morning mate.
Delivery for you.
```

## Sample Output

```
+++++ ORIGINIAL SUBTITLES +++++
1
00:00:43,700 --> 00:00:47,973
Come on ladies, we're pushing pennies
around like a lot of old 'tards here.

2
00:00:48,321 --> 00:00:50,837
Let's see a real bet.

3
00:01:10,889 --> 00:01:12,216
Twice.

4
00:01:12,394 --> 00:01:14,271
- That's better.
- Fold.

5
00:01:18,099 --> 00:01:20,056
All in.

6
00:01:23,230 --> 00:01:25,563
Good morning mate.
Delivery for you.

SHIFT FOR 222 ms
+++++ SHIFTED SUBTITLES +++++
1
00:00:43,922 --> 00:00:48,195
Come on ladies, we're pushing pennies
around like a lot of old 'tards here.

2
00:00:48,543 --> 00:00:51,059
Let's see a real bet.

3
00:01:11,111 --> 00:01:12,438
Twice.

4
00:01:12,616 --> 00:01:14,493
- That's better.
- Fold.

5
00:01:18,321 --> 00:01:20,278
All in.

6
00:01:23,452 --> 00:01:25,785
Good morning mate.
Delivery for you.
```