package nightclub;

import java.util.*;

public class NightClub implements Runnable {
    public volatile boolean isRunning = false;
    private List<Person> people;
    private HashMap<Music, Set<Person>> index;

    NightClub(){
        people = new ArrayList<>();
        index = new HashMap<>();
    }

    public void addPerson(Person person){
        people.add(person);
        for(Music music: person.knownDances){
            index.putIfAbsent(music, new HashSet<>());
            index.get(music).add(person);
        }
    }


    public void playMusic(Music newMusic) {
        System.out.printf("\nNow playing %s\n", newMusic.name);
        Set<Person> dancing = index.getOrDefault(newMusic, new HashSet<>());
        for(Person person: people) {
            if(dancing.contains(person)) {
                System.out.printf("%s is now dancing to %s\n", person.name, newMusic.name);
            } else {
                System.out.printf("%s is now drinking vodka\n", person.name);
            }
        }
    }

    @Override
    public void run() {
        isRunning = true;
        try {
            while (isRunning) {
                playMusic(Music.random());
                Thread.sleep(5000); // 5000 milis = 5 sec
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("Martin", new Music[]{}),
                new Person("Fiona", new Music[]{Music.RNB}),
                new Person("Rupert", new Music[]{Music.RNB, Music.HIPHOP}),
                new Person("Helen", Music.values()),
                new Person("Jane", new Music[]{Music.ROCK, Music.POP})
        };
        NightClub nightClub = new NightClub();
        for(Person person: people) {
            nightClub.addPerson(person);
        }
        new Thread(nightClub).run();
    }
}
