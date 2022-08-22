package classes;

import exceptions.DeadlineNotValidException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Task {
    private String category;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private Integer priority;

    public Task() {
    }

    public Task(String category, String name, String description, LocalDateTime deadline, Integer priority) throws DeadlineNotValidException {
        this.category = category;
        this.name = name;
        this.description = description;
        if (deadline != null && deadline.isBefore(LocalDateTime.now()))
            throw new DeadlineNotValidException(deadline);
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public Optional<LocalDateTime> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    public Optional<Integer> getPriority() {
        return Optional.ofNullable(priority);
    }
    public Optional<Long> timeDifferenceBetweenDeadlineAndCurrentTime(){
        if (getDeadline().isEmpty()){
            return Optional.empty();
        }

        return Optional.of(ChronoUnit.SECONDS.between(deadline, LocalDateTime.now()));
    }

    @Override
    public String toString() {
        return "Task{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                '}';
    }
}
