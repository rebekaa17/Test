package com.example.cinema.service;

import com.example.cinema.entity.Hall;
import com.example.cinema.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getHallById(Long id) {
        Optional<Hall> hall = hallRepository.findById(id);
        return hall.orElse(null);
    }

    @Override
    public Hall updateHall(Long id, Hall updatedHall) {
        return hallRepository.findById(id)
                .map(hall -> {
                    hall.setName(updatedHall.getName());
                    hall.setCapacity(updatedHall.getCapacity());
                    return hallRepository.save(hall);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteHall(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
