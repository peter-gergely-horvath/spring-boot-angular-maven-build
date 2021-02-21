package com.frakton.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Memo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memo memo = (Memo) o;
        return Objects.equals(id, memo.id) &&
                Objects.equals(text, memo.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
