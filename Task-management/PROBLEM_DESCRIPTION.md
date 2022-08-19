# Tasks management (40)
Да се напише класа `TaskManager` што ќе служи за менаџирање на задачи (tasks) на даден корисник. За класата да се имплементираат методите:
- `readTasks (InputStream inputStream)` - метод за вчитување на задачите на корисникот при што секоја задача е во следниот формат: `[категорија][име_на_задача],[oпис],[рок_за_задачата],[приоритет]`. Рокот за задачата и приоритетот се опционални полиња.
  - Не смее да се дозволи дадена задача да има рок којшто е веќе поминат. Во ваков случај да се фрли исклучот од тип `DeadlineNotValidException`. Да се фати исклучокот на соодветно место, така што нема да се попречи вчитувањето на останатите задачи!!!
- `void printTasks(OutputStream os, boolean includePriority, boolean includeCategory)` - метод за печатење на задачите.
  - Доколку `includeCategory` e `true` да се испечатат задачите групирани според категории, во спротивно се печатат сите внесени задачи
  - Доколку `includePriority` e `true` да се испечатат задачите сортирани според приоритетот (при што 1 е највисок приоритет), a немаат приоритет или имаат ист приоритет се сортираат растечки според временското растојание помеѓу рокот и моменталниот датум, односно задачите со рок најблизок до денешниот датум се печатат први.
  - Доколку `includePriority` e `false` се печатат во растечки редослед според временското растојание помеѓу рокот и моменталниот датум.
  - При печатењето на задачите се користи default опцијата за `toString` (доколку работите вo IntelliJ), со тоа што треба да внимавате на името на променливите.

**Бонус:** Користење на шаблони за дизајн на софтвер за репрезентација на задачите и за нивното креирање.

## Sample Input
```
School,NP,lab 1 po NP,2020-06-23T23:59:59.000,1
School,NP,lab 2 po NP,2020-06-27T23:59:59.000
School,NP,lab 3 po NP,2020-07-04T23:59:59.000,2
School,NP,lab 4 po NP,2020-07-11T23:59:59.000
School,NP,prepare for June exam :)
School,NP,solve all exercises,3
```

## Sample Output
```
Tasks reading
By categories with priority
SCHOOL
Task{name='NP', description='lab 1 po NP', deadline=2020-06-23T23:59:59, priority=1}
Task{name='NP', description='lab 3 po NP', deadline=2020-07-04T23:59:59, priority=2}
Task{name='NP', description='solve all exercises', priority=3}
Task{name='NP', description='lab 2 po NP', deadline=2020-06-27T23:59:59}
Task{name='NP', description='lab 4 po NP', deadline=2020-07-11T23:59:59}
Task{name='NP', description='prepare for June exam :)'}
-------------------------
By categories without priority
SCHOOL
Task{name='NP', description='lab 1 po NP', deadline=2020-06-23T23:59:59, priority=1}
Task{name='NP', description='lab 2 po NP', deadline=2020-06-27T23:59:59}
Task{name='NP', description='lab 3 po NP', deadline=2020-07-04T23:59:59, priority=2}
Task{name='NP', description='lab 4 po NP', deadline=2020-07-11T23:59:59}
Task{name='NP', description='prepare for June exam :)'}
Task{name='NP', description='solve all exercises', priority=3}
-------------------------
All tasks without priority
Task{name='NP', description='lab 1 po NP', deadline=2020-06-23T23:59:59, priority=1}
Task{name='NP', description='lab 2 po NP', deadline=2020-06-27T23:59:59}
Task{name='NP', description='lab 3 po NP', deadline=2020-07-04T23:59:59, priority=2}
Task{name='NP', description='lab 4 po NP', deadline=2020-07-11T23:59:59}
Task{name='NP', description='prepare for June exam :)'}
Task{name='NP', description='solve all exercises', priority=3}
-------------------------
All tasks with priority
Task{name='NP', description='lab 1 po NP', deadline=2020-06-23T23:59:59, priority=1}
Task{name='NP', description='lab 3 po NP', deadline=2020-07-04T23:59:59, priority=2}
Task{name='NP', description='solve all exercises', priority=3}
Task{name='NP', description='lab 2 po NP', deadline=2020-06-27T23:59:59}
Task{name='NP', description='lab 4 po NP', deadline=2020-07-11T23:59:59}
Task{name='NP', description='prepare for June exam :)'}
-------------------------
```