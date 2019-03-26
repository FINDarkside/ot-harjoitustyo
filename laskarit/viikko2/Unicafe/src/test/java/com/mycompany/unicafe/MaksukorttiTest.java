package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(150);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinArvoAsetetaanLuonnissa() {
        assertEquals(150, kortti.saldo());
    }

    @Test
    public void lataaRahaaKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(155, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        assertEquals(145, kortti.saldo());
    }

    @Test
    public void saldoEiVaheneJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(151);
        assertEquals(150, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTrueJosRahaRiittaa() {
        assertEquals(true, kortti.otaRahaa(5));
    }

    @Test
    public void otaRahaaPalauttaaFalseJosRahaEiRiita() {
        assertEquals(false, kortti.otaRahaa(155));
    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo: 1.50", kortti.toString());
    }

    @Test
    public void toStringToimiiOikein2() {
        kortti.lataaRahaa(51);
        assertEquals("saldo: 2.01", kortti.toString());
    }
}
