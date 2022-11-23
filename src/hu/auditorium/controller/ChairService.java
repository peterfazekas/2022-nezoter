package hu.auditorium.controller;

import hu.auditorium.model.domain.Chair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChairService {

    private final List<Chair> chairs;

    public ChairService(List<Chair> chairs) {
        this.chairs = chairs;
    }

}
