package org.cambala.measurement;

public interface Measure {

    String DOT = ".";

    String getShortName();

    default String getDottedShortName() {
        return getShortName() + DOT;
    }
}
