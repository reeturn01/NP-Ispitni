package classes;

import exceptions.DeadlineNotValidException;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class TaskManager {

    public static final Comparator<Task> SORTED_BY_PRIORITY_TASK_COMPARATOR = comparing(Task::getPriority, comparing(optional -> optional.orElse(null), nullsLast(naturalOrder())));
    public static final Comparator<Task> SORTED_BY_CURRENT_TIME_DIFFERENCE_TASK_COMPARATOR = comparing(Task::timeDifferenceBetweenDeadlineAndCurrentTime, comparing(optional -> optional.orElse(null), nullsLast(naturalOrder())));
    //DateTime Format 2020-07-11T23:59:59.000
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_TASK = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void readTasks(InputStream in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()){
            String []lineParts = scanner.nextLine().split(",");
            String category = lineParts[0];
            String name = lineParts[1];
            String description = lineParts[2];

            try{
                Task newTask = createTaskFactory(category, name, description, Arrays.copyOfRange(lineParts, 3, lineParts.length));
                tasks.add(newTask);
            } catch (DeadlineNotValidException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private Task createTaskFactory(String category, String name, String description, String[] optionalFields) throws DeadlineNotValidException {
        LocalDateTime deadline = null;
        Integer priority = null;

        for (String optionalField : optionalFields) {
            Optional<LocalDateTime> dateTimeOptional = parseLocalDateTime(optionalField);
            if (dateTimeOptional.isPresent()) {
                deadline = dateTimeOptional.get();
            } else {
                priority = Integer.parseInt(optionalField);
            }
        }
        return new Task(category, name, description, deadline, priority);
    }

    private Optional<LocalDateTime> parseLocalDateTime(String localDateTimeToParse) {
        try{
            return Optional.of(LocalDateTime.parse(localDateTimeToParse, DATE_TIME_FORMATTER_FOR_TASK));

        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    public void printTasks(PrintStream os, boolean includePriority, boolean includeCategory) {
        if (includeCategory){
            printTasksByCategory(os);
            if (includePriority){
                printTasksSortedByPriority(os);
            }else{
                printTasksSortedByCurrentTimeDifference(os);
            }
        } else {
            printAllTasks(os);
        }
    }

    private void printTasksSortedByCurrentTimeDifference(PrintStream os) {
        tasks.stream()
                .sorted(SORTED_BY_CURRENT_TIME_DIFFERENCE_TASK_COMPARATOR)
                .forEach(os::println);
    }

    private void printTasksSortedByPriority(PrintStream os) {
        tasks.stream()
                .sorted(SORTED_BY_PRIORITY_TASK_COMPARATOR)
                .forEach(os::println);
    }

    private void printAllTasks(PrintStream os) {
        tasks.forEach(os::println);
    }

    private void printTasksByCategory(PrintStream os) {
        TreeMap<String, List<Task>> tasksByCategory = tasks.stream()
                .collect(groupingBy(Task::getCategory, TreeMap::new, toList()));

        tasksByCategory.forEach(
                (key, listOfTasks) -> {
                    os.println(key);
                    listOfTasks.forEach(os::println);
                }
        );
    }
}
