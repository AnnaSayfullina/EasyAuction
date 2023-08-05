package com.example.course_easyauction.service;

import com.example.course_easyauction.dto.Bidder;


public interface BidService {
    Bidder getFirstBidder(int id);

    Bidder getMostFrequentBidder(int id);

    void createBid(int id, String bidderName);
}
