package com.example.cinema.service;

import com.example.cinema.entity.Hall;
import java.util.List;

public interface HallService {
    Hall createHall(Hall hall);
    List<Hall> getAllHalls();
    Hall getHallById(Long id);
    Hall updateHall(Long id, Hall updatedHall);
    boolean deleteHall(Long id);
}
