package practice.lab4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Practice4 {

    public static class City {

        private final String name;
        private final String state;
        private final int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }

        public String toString() {
            return String.format("City{name='%s', state='%s', population=%d}", name, state,
                population);
        }

    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
            .map(l -> l.split(", "))
            .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {

        String path = "src/main/resources/lab4/cities.txt";

        Stream<City> cities = readCities(path);
        // Q1: count how many cities there are for each state
        Map<String, Long> cityCountPerState = cities.collect(
            Collectors.groupingBy(City::getState, Collectors.counting()));
        System.out.println("# of cities per state:\n" + cityCountPerState + '\n');

        cities = readCities(path);
        // Q2: count the total population for each state
        Map<String, Integer> statePopulation = cities.collect(
            Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println("population per state:\n" + statePopulation + '\n');

        cities = readCities(path);
        // Q3: for each state, get the set of cities with >500,000 population
        Map<String, Set<City>> largeCitiesByState = cities.collect(
            Collectors.groupingBy(City::getState,
                Collectors.filtering(city -> city.getPopulation() > 500000, Collectors.toSet())));
        System.out.println("cities with >500,000 population for each state:");
        largeCitiesByState.forEach((str, set) -> System.out.println(str + ": " + set));
    }
}
