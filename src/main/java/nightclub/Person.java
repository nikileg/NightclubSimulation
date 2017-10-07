package nightclub;

public class Person {
    String name;
//    final String gender;
    Music[] knownDances;

    Person(String name, Music[] musics){
        this.name = name;
        this.knownDances = musics;
    }

    public static void main(String[] args) {
        Person p = new Person("John", new Music[]{});
        System.out.println(p);
    }
}