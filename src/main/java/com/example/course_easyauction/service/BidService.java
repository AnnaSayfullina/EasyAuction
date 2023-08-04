package com.example.course_easyauction.service;

import com.example.course_easyauction.model.Bid;
import org.springframework.stereotype.Service;


public interface BidService {
    Bid getFirstBidder(int id);

    Bid getMostFrequentBidder(int id);

    void createBid(int id, String bidderName);
}
