package com.example.cinema.service;

import com.example.cinema.entity.Hall;
import com.example.cinema.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall getHallById(Long id) {
        Optional<Hall> hall = hallRepository.findById(id);
        return hall.orElse(null);
    }

    public Hall updateHall(Long id, Hall updatedHall) {
        return hallRepository.findById(id)
                .map(hall -> {
                    hall.setName(updatedHall.getName());
                    hall.setCapacity(updatedHall.getCapacity());
                    return hallRepository.save(hall);
                })
                .orElse(null);
    }

    public boolean deleteHall(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
