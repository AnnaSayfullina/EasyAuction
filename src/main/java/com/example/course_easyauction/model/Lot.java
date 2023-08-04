package com.example.course_easyauction.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "lot")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Max(64)
    @Min(3)
    @Column(name = "title")
    private String title;

    @Max(4096)
    @Min(3)
    @Column(name = "description")
    private String description;

    @Min(1)
    @Column(name = "start_price")
    private int startPrice;

    @Min(1)
    @Column(name = "bid_price")
    private int bidPrice;

    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
//    @JoinColumn(name="id")
    List<Bid> bidList;


}
