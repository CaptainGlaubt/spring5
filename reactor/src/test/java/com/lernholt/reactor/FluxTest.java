package com.lernholt.reactor;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

class FluxTest {
    @Test
    public void example_just() {
        Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public void example_from_array() {
        String[] fruits = new String[] { "Apple", "Orange", "Grape", "Banana", "Strawberry" };
        Flux<String> fruitFlux = Flux.fromArray(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public void example_from_iterable() {
        List<String> fruits = Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry");
        Flux<String> fruitFlux = Flux.fromIterable(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public void example_from_stream() {
        Stream<String> fruits = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
        Flux<String> fruitFlux = Flux.fromStream(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public void example_range() {
        Flux<Integer> rangeFlux = Flux.range(1, 5);
        StepVerifier.create(rangeFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    public void example_interval() {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
        StepVerifier.create(intervalFlux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

    @Test
    public void example_merge() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa").delayElements(Duration.ofMillis(500));
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));
        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);
        StepVerifier.create(mergedFlux)
                .expectNext("Garfield")
                .expectNext("Lasagna")
                .expectNext("Kojak")
                .expectNext("Lollipops")
                .expectNext("Barbossa")
                .expectNext("Apples")
                .verifyComplete();
    }

    @Test
    public void example_zip() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");
        Flux<Tuple2<String, String>> zippedFlux = Flux.zip(characterFlux, foodFlux);
        StepVerifier.create(zippedFlux)
                .expectNextMatches(t -> t.getT1().equals("Garfield") && t.getT2().equals("Lasagna"))
                .expectNextMatches(t -> t.getT1().equals("Kojak") && t.getT2().equals("Lollipops"))
                .expectNextMatches(t -> t.getT1().equals("Barbossa") && t.getT2().equals("Apples"))
                .verifyComplete();
    }

    @Test
    public void example_zip_custom() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");
        Flux<String> zippedFlux = Flux.zip(characterFlux, foodFlux, (c, f) -> String.format("%s eats %s", c, f));
        StepVerifier.create(zippedFlux)
                .expectNext("Garfield eats Lasagna")
                .expectNext("Kojak eats Lollipops")
                .expectNext("Barbossa eats Apples")
                .verifyComplete();
    }

    @Test
    public void example_first_with_signal() {
        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth").delaySubscription(Duration.ofMillis(100));
        Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel", "antilope");
        Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);
        StepVerifier.create(firstFlux)
                .expectNext("hare")
                .expectNext("cheetah")
                .expectNext("squirrel")
                .expectNext("antilope")
                .verifyComplete();
    }

    @Test
    public void example_skip_emissions_exact() {
        Flux<String> skipFlux = Flux
                .just("one", "two", "skip a few", "ninetynine", "one hundred", "one hundred and one")
                .skip(3);
        StepVerifier.create(skipFlux)
                .expectNext("ninetynine")
                .expectNext("one hundred")
                .expectNext("one hundred and one")
                .verifyComplete();
    }

    @Test
    public void example_skip_emissions_until() {
        Flux<String> skipFlux = Flux
                .just("one", "two", "skip a few", "ninetynine", "one hundred", "one hundred and one")
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(4));
        StepVerifier.create(skipFlux)
                .expectNext("ninetynine")
                .expectNext("one hundred")
                .expectNext("one hundred and one")
                .verifyComplete();
    }

    @Test
    public void example_take_emissions_exact() {
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grant Teton")
                .take(4);
        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone")
                .expectNext("Yosemite")
                .expectNext("Grand Canyon")
                .expectNext("Zion")
                .verifyComplete();
    }

    @Test
    public void example_take_emissions_until() {
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grant Teton")
                .delayElements(Duration.ofSeconds(1))
                .take(Duration.ofSeconds(5));
        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone")
                .expectNext("Yosemite")
                .expectNext("Grand Canyon")
                .expectNext("Zion")
                .verifyComplete();
    }

    @Test
    public void example_filter() {
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grant Teton")
                .filter(np -> !np.contains(" "));
        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone")
                .expectNext("Yosemite")
                .expectNext("Zion")
                .verifyComplete();
    }

    @Test
    public void example_distinct() {
        Flux<String> animalFlux = Flux.just("dog", "cat", "bird", "dog", "bird", "anteater").distinct();
        StepVerifier.create(animalFlux)
                .expectNext("dog")
                .expectNext("cat")
                .expectNext("bird")
                .expectNext("anteater")
                .verifyComplete();
    }

    @Test
    public void example_map_sync() {
        Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr").map(player -> {
            String[] tokens = player.split(" ");
            return new Player(tokens[0], tokens[1]);
        });
        StepVerifier.create(playerFlux)
                .expectNext(new Player("Michael", "Jordan"))
                .expectNext(new Player("Scottie", "Pippen"))
                .expectNext(new Player("Steve", "Kerr"))
                .verifyComplete();
    }

    @Test
    public void example_map_async() {
        Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
                .flatMap(n -> Mono.just(n).map(player -> {
                    String[] tokens = player.split(" ");
                    return new Player(tokens[0], tokens[1]);
                }).subscribeOn(Schedulers.parallel()));
        List<Player> playerList = Arrays.asList(new Player("Michael", "Jordan"), new Player("Scottie", "Pippen"),
                new Player("Steve", "Kerr"));
        StepVerifier.create(playerFlux)
                .expectNextMatches(p -> playerList.contains(p))
                .expectNextMatches(p -> playerList.contains(p))
                .expectNextMatches(p -> playerList.contains(p))
                .verifyComplete();
    }

    @Test
    public void example_buffer_sync() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");
        Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);
        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana"))
                .expectNext(Arrays.asList("kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    public void example_buffer_async() {
        Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
                .buffer(3)
                .flatMap(x -> Flux.fromIterable(x).map(y -> y.toUpperCase()).subscribeOn(Schedulers.parallel()).log())
                .subscribe();
    }

    @Test
    public void example_collect_list() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");
        Mono<List<String>> fruitListMono = fruitFlux.collectList();
        StepVerifier.create(fruitListMono)
                .expectNext(Arrays.asList("apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    public void example_collect_map() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");
        Mono<Map<Character, String>> animalMapMono = animalFlux.collectMap(a -> a.charAt(0));
        StepVerifier.create(animalMapMono).expectNextMatches(map -> {
            return map.size() == 3 && map.get('a').equals("aardvark") && map.get('e').equals("eagle")
                    && map.get('k').equals("kangaroo");
        }).verifyComplete();
    }

    @Test
    public void example_all() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");
        Mono<Boolean> hasAMono = animalFlux.all(a -> a.contains("a"));
        StepVerifier.create(hasAMono).expectNext(true).verifyComplete();
    }

    @Test
    public void example_any() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");
        Mono<Boolean> hasAMono = animalFlux.any(a -> a.contains("t"));
        StepVerifier.create(hasAMono).expectNext(true).verifyComplete();
        Mono<Boolean> hasZMono = animalFlux.any(a -> a.contains("z"));
        StepVerifier.create(hasZMono).expectNext(false).verifyComplete();
    }

    @Data
    @RequiredArgsConstructor
    private static class Player {
        private final String firstName;
        private final String lastName;
    }

}
