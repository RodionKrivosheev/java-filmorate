package ru.yandex.practicum.filmorate.model;


import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
public class Film {
    @NonNull
    private int id;
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @NotBlank
    @Size(max = 200, message = "Максимальная длина описания — 200 символов.")
    private String description;
    @NonNull
    @NotNull
    private LocalDate releaseDate;
    @NonNull
    @NotNull
    @Positive
    private int duration;

    private Set<Integer> likes = new HashSet<>();

    private int likesCounter;

    public void addLike(Integer id) {
        likes.add(id);
        likesCounter++;
    }

    public void deleteLike(Integer id) {
        likes.remove(id);
        likesCounter--;
    }
}





