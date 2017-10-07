package nightclub;

import java.util.Random;

public enum Music {
    ELECTRODANCE("Electrodance"),
    RNB("RnB"),
    HIPHOP("Hip-hop"),
    POP("Pop"),
    ROCK("Rock"),
    PUNK("Punk");

    final String name;
    Music(String name){
        this.name = name;
    }

    private static Music[] values = Music.values();
    private static Random rnd = new Random();
    static Music random(){
        int len = values.length;
        return values[rnd.nextInt(len)];
    }
}
