package com.aoc;

import com.aoc.input.Day;
import com.aoc.input.Input;
import com.aoc.input.StringInput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BenchmarkInputReader {
    public static Input<String> read(final Day day) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("/day" + day.getValue() + "/input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read input for Day " + day.getValue(), e);
        }
        return new StringInput(lines);
    }
}
