package com.adoreaosenhor.adore_ao_senhor.domain.music;

public enum Tonality {

    C("C"),
    C_SHARP("C#"),
    D_FLAT("Db"),
    D("D"),
    D_SHARP("D#"),
    E_FLAT("Eb"),
    E("E"),
    F("F"),
    F_SHARP("F#"),
    G_FLAT("Gb"),
    G("G"),
    G_SHARP("G#"),
    A_FLAT("Ab"),
    A("A"),
    A_SHARP("A#"),
    B_FLAT("Bb"),
    B("B");


    private String tonality;
    Tonality(String tonality) {
        this.tonality = tonality;
    }

    public String getTonality() {
        return tonality;
    }
}
