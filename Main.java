import java.util.*;

// Song Class
class Song {
    private String title;
    private String artist;
    private int bpm;

    public Song(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    // hashCode and equals for comparison in Set
    @Override
    public int hashCode() {
        return Objects.hash(title, artist, bpm);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Song song = (Song) obj;
        return Integer.compare(song.bpm, bpm) == 0 &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", bpm=" + bpm +
                '}';
    }

}

class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

// Generic
abstract class Animal {
    void eat() {
        System.out.println("animal eating ...");
    }
}
class Dog extends Animal {
    void bark() {
        System.out.println("dog barking ...");
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Dog{}";
    }
}
class Cat extends Animal {
    void meow() {
        System.out.println("cat meowing ...");
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Cat{}";
    }
}

class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testSongList();

        // Generic Demo

        // Allow
        List<Animal> animalList = List.of(new Dog(), new Cat(), new Dog());
        main.takeAnimals(animalList);

        // Not Allow
        List<Dog> dogList = List.of(new Dog(), new Dog());
//        main.takeAnimals(dogList);
        // => Prevent example: a Cat in a List of Dog

        // Solutions
        // => Using Wildcard <?>
        main.takeAnimalsV2(dogList);

        // Wildcard => ONLY DOG
        List<Dog> vaccinatedDogs = main.takeAnimalsV3(dogList);
        System.out.println(vaccinatedDogs);

        // Wildcard => ALL ANIMALS
        List<Animal> vaccinatedAnimals = main.takeAnimalsV3(animalList);
        System.out.println(vaccinatedAnimals);

        // Also
        List<? extends Animal> vaccinatedSomethings = main.takeAnimalsV3(animalList);
        System.out.println(vaccinatedSomethings);


    }

    // Generic
    public void takeAnimals(List<Animal> animals) {
        for (Animal animal: animals) {
            animal.eat();
        }
    }
    public void takeAnimalsV2(List<? extends Animal> animals) {
        for (Animal animal: animals) {
            animal.eat();
        }
    }
    public <T extends Animal> List<T> takeAnimalsV3 (List<T> list) {
        return list;
    }



    public void testSongList () {
        List<String> songs = getSongList();
//        songs.add("Added_Song");
//        System.out.println("Updated songs:");
        System.out.println(songs);
    }

    public List<String> getSongList() {
        List<String> songs = new ArrayList<>();
        songs.add("somersault");
        songs.add("cassidy");
        songs.add("$10");
        System.out.println(songs);
        // Cannot Add or Update list
        return Collections.unmodifiableList(songs);
    }

    public void creatingList () {
        List<String> strings = List.of("something", "someone", "somethough");
        List<Song> songList = List.of(
                new Song("havana", "cabello", 156),
                new Song("baby", "justin", 224),
                new Song("24k", "bruno", 224)
        );
    }
    public void creatingSet() {
        Set<Book> books = Set.of(
                new Book("How Cats Work"),
                new Book("Remix your Body"),
                new Book("Finding Emo")
        );
    }
    public void creatingMap() {
        Map<String, Integer> scores = Map.of(
                "Kathy", 42,
                "Bert", 343,
                "Skyler", 420
        );
    }
}