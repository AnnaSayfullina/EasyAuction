package com.example.course_easyauction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLot implements Serializable {

    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;

    @Override
    public String toString() {
        return "CreateLot{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", bidPrice=" + bidPrice +
                '}';
    }
}
