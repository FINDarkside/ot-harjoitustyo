package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @Test
    public void luodunKassanRahamaaraOn100000() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void luotuKassaMyynytNollaMaukasta() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void luotuKassaMyynytNollaEdullista() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAterianKateisOstoKasvattaaMyytyjä1() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAterianKateisOstoKasvattaaMyytyjä2() {
        kassa.syoEdullisesti(241);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAterianKateisOstoKasvattaaKassaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void edullisenAterianKateisOstoKasvattaaKassaa2() {
        kassa.syoEdullisesti(241);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void edullisenAterianKateisOstoPalauttaaVaihtorahan1() {
        assertEquals(0, kassa.syoEdullisesti(240));
    }

    @Test
    public void edullisenAterianKateisOstoPalauttaaVaihtorahan2() {
        assertEquals(1, kassa.syoEdullisesti(241));
    }

    @Test
    public void edullisenAterianKateisOstoEiKasvataMyytyjaLiianPienellaRahalla() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAterianKateisOstoEiKasvataKassaaLiianPienellaRahalla() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisenAterianVaihtoRahaPalauttaaKokoRahanJosEiRiitaOstoon() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }

    @Test
    public void maukkaanAterianKateisOstoKasvattaaMyytyjä1() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAterianKateisOstoKasvattaaMyytyjä2() {
        kassa.syoMaukkaasti(401);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAterianKateisOstoKasvattaaKassaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanAterianKateisOstoKasvattaaKassaa2() {
        kassa.syoMaukkaasti(401);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanAterianKateisOstoPalauttaaVaihtorahan1() {
        assertEquals(0, kassa.syoMaukkaasti(400));
    }

    @Test
    public void maukkaanAterianKateisOstoPalauttaaVaihtorahan2() {
        assertEquals(1, kassa.syoMaukkaasti(401));
    }

    @Test
    public void maukkaanAterianKateisOstoEiKasvataMyytyjaLiianPienellaRahalla() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAterianKateisOstoEiKasvataKassaaLiianPienellaRahalla() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanAterianVaihtoRahaPalauttaaKokoRahanJosEiRiitaOstoon() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }

    // Kortti testit
    @Test
    public void edullisenAteriankorttiOstoKasvattaaMyytyjä1() {
        Maksukortti k = new Maksukortti(240);
        kassa.syoEdullisesti(k);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAteriankorttiOstoKasvattaaMyytyjä2() {
        Maksukortti k = new Maksukortti(241);
        kassa.syoEdullisesti(k);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAteriankorttiOstoEiKasvataKassaa() {
        Maksukortti k = new Maksukortti(12323);
        kassa.syoEdullisesti(k);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisenAteriankorttiOstoPalauttaaTrue() {
        assertEquals(true, kassa.syoEdullisesti(new Maksukortti(10000)));
    }

    @Test
    public void edullisenAteriankorttiOstoEiKasvataMyytyjaLiianPienellaSaldolla() {
        kassa.syoEdullisesti(new Maksukortti(200));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAteriankorttiOstoEiKasvataKassaaLiianPienellaSaldolla() {
        kassa.syoEdullisesti(new Maksukortti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisenAteriankorttiOstoPalauttaaFalseLiianPienellaSaldolla() {
        assertEquals(false, kassa.syoEdullisesti(new Maksukortti(200)));
    }

    @Test
    public void edullisenAteriankorttiOstoVahentaaKortinSaldoaOikein() {
        Maksukortti k = new Maksukortti(500);
        kassa.syoEdullisesti(k);
        assertEquals(260, k.saldo());
    }

    @Test
    public void edullisenAteriankorttiOstoLiianPienellaSaldollaEiVahennaKortinSaldoa() {
        Maksukortti k = new Maksukortti(100);
        kassa.syoEdullisesti(k);
        assertEquals(100, k.saldo());
    }

    @Test
    public void maukkaanAteriankorttiOstoKasvattaaMyytyjä1() {
        Maksukortti k = new Maksukortti(400);
        kassa.syoMaukkaasti(k);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAteriankorttiOstoKasvattaaMyytyjä2() {
        Maksukortti k = new Maksukortti(401);
        kassa.syoMaukkaasti(k);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAteriankorttiOstoEiKasvataKassaa() {
        Maksukortti k = new Maksukortti(400);
        kassa.syoMaukkaasti(k);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanAteriankorttiOstoPalauttaaTrue() {
        assertEquals(true, kassa.syoMaukkaasti(new Maksukortti(10000)));
    }

    @Test
    public void maukkaanAteriankorttiOstoEiKasvataMyytyjaLiianPienellaSaldolla() {
        kassa.syoMaukkaasti(new Maksukortti(300));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanAteriankorttiOstoEiKasvataKassaaLiianPienellaSaldolla() {
        kassa.syoMaukkaasti(new Maksukortti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanAteriankorttiOstoPalauttaaFalseLiianPienellaSaldolla() {
        assertEquals(false, kassa.syoMaukkaasti(new Maksukortti(200)));
    }

    @Test
    public void maukkaanAteriankorttiOstoVahentaaKortinSaldoaOikein() {
        Maksukortti k = new Maksukortti(500);
        kassa.syoMaukkaasti(k);
        assertEquals(100, k.saldo());
    }

    @Test
    public void maukkaanAteriankorttiOstoLiianPienellaSaldollaEiVahennaKortinSaldoa() {
        Maksukortti k = new Maksukortti(100);
        kassa.syoMaukkaasti(k);
        assertEquals(100, k.saldo());
    }

    @Test
    public void kortilleRahanLatausKasvattaaSaldoaOikein() {
        Maksukortti k = new Maksukortti(100);
        kassa.lataaRahaaKortille(k, 100);
        assertEquals(200, k.saldo());
    }

    @Test
    public void kortilleRahanLatausKasvattaaKassanSaldoa() {
        Maksukortti k = new Maksukortti(100);
        kassa.lataaRahaaKortille(k, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }

    @Test
    public void kortilleNegatiivisenMaaranLatausEiMuutaSaldoa() {
        Maksukortti k = new Maksukortti(100);
        kassa.lataaRahaaKortille(k, -50);
        assertEquals(100, k.saldo());
    }

    @Test
    public void kortilleNegatiivisenMaaranLatausEiMuutaKassanSaldoa() {
        Maksukortti k = new Maksukortti(100);
        kassa.lataaRahaaKortille(k, -50);
        assertEquals(100000, kassa.kassassaRahaa());
    }

}
