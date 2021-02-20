package comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Comparable and Comparator
class MovieComp implements Comparable<MovieComp> {
    private double rating;
    private String name;
    private int year;

    // sort movies by year
    public int compareTo(MovieComp m) {
        return this.year - m.year;
    }

    public MovieComp(String name, double rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}

class RatingComp implements Comparator<MovieComp> {
    public int compare(MovieComp m1, MovieComp m2) {
        if (m1.getRating() < m2.getRating()) return -1;
        if (m1.getRating() > m2.getRating()) return 1;
        else return 0;
    }
}

class NameComp implements Comparator<MovieComp> {
    public int compare(MovieComp m1, MovieComp m2) {
        return m1.getName().compareTo(m2.getName());
    }
}

public class Comp {
    public static void main(String[] args) {
        ArrayList<MovieComp> list = new ArrayList<MovieComp>();
        list.add(new MovieComp("First", 7.2, 2015));
        list.add(new MovieComp("Second", 8.7, 2009));
        list.add(new MovieComp("Third", 4.5, 2018));
        list.add(new MovieComp("Fourth", 9.8, 2023));


        // uses Comparable
        System.out.println("Movies after sorting: ");
        Collections.sort(list); // sort
        for (MovieComp m : list) {
            System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
        }


        System.out.println("");
    }
}























