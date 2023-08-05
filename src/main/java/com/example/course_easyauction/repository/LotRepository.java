package com.example.course_easyauction.repository;

import com.example.course_easyauction.model.Lot;
import com.example.course_easyauction.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer>, PagingAndSortingRepository<Lot, Integer> {

    Optional<Lot> findLotById(int id);

    Page<Lot> findAllByStatus(Status status, Pageable pageable);


}
