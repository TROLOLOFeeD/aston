package ru.aston.hw1;

import java.util.Date;
import java.util.Objects;

public final class Movie {
    private final String name;
    private final Date date;

    public Movie(String name, Date date) {
        this.name = Objects.requireNonNull(name, "У фильма должно быть название");
        Objects.requireNonNull(date, "Дата не может быть пустой");
        this.date = new Date(date.getTime());
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie{" + "name=" + this.name + ", date=" + date + "}";
    }

    public static void main(String[] args) {
        Date date = new Date(1222222222222L);

        Movie mov1 = new Movie("Calaharro", date);

        System.out.println(mov1.getDate() == date);
        System.out.println(mov1.getDate().getTime() == date.getTime());

        date.setTime(1212121212121L);

        System.out.println(mov1.getDate().getTime() == date.getTime());

        date = mov1.getDate();
        date.setTime(1212121212121L);
        System.out.println(mov1.getDate().getTime() == date.getTime());
    }
}