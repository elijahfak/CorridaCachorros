/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagens;

import cenario.Bosque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elizeu
 */
public class Cacador {

    int cor;
    int moedas;
    private int cachorro_em_campo;
    Cachorro cachorro1;
    Cachorro cachorro2;

    public Cacador(int cor) {
        this.cor = cor;
        this.moedas = 0;
        this.cachorro_em_campo = 2;
        this.cachorro1 = new Cachorro(this.cor);
        this.cachorro2 = new Cachorro(this.cor);

//        Bosque.getInstance().add_thread(cachorro1);
//        Bosque.getInstance().add_thread(cachorro2);
    }

    public Cachorro getCachorro1() {
        return cachorro1;
    }

    public Cachorro getCachorro2() {
        return cachorro2;
    }

    public Cachorro get_cachorro_vez() {
        if (cachorro_em_campo == 1) {
            return cachorro1;
        } else {
            return cachorro2;

        }
    }

    public void lancar_cachorro() {
//        if (this.cachorro_em_campo == 0) {
//            this.cachorro_em_campo = 1;
//            anuncia_lancar_cachorro(this.cachorro_em_campo);
//            
//            cachorro1.setPote_atual(Bosque.getInstance().get_pote_n(1));
//            cachorro1.start();
//        } else 

//        if (this.getCor() == 3) {
//            System.out.println("cachorro_em_campo " + cachorro_em_campo);
//        }

        if (cachorro_em_campo == 1) {
//            this.cachorro_em_campo = 2;
            this.set_cachorro_em_campo(2);
            anuncia_lancar_cachorro();

//            try {
//                cachorro1.join();
//            } catch (InterruptedException ex) {
//                System.out.println(ex.getMessage());
//            }
            cachorro2.setPote_atual(Bosque.getInstance().get_pote_n(1));
//            if (!cachorro2.isAlive()) {
                cachorro2.start();
//            }
        } else {
//            this.cachorro_em_campo = 1;
            this.set_cachorro_em_campo(1);
            anuncia_lancar_cachorro();

//            try {
//                cachorro2.join();
//            } catch (InterruptedException ex) {
//                System.out.println(ex.getMessage());
//            }
//            System.out.println("yyyyyyyyyyyyyyy");
            cachorro1.setPote_atual(Bosque.getInstance().get_pote_n(1));
//            if (!cachorro1.isAlive()) {
                cachorro1.start();
//            }
        }
//        }
    }

    public void set_cachorro_em_campo(int i) {
        this.cachorro_em_campo = i;
    }

    public void receber_cachorro(Cachorro cachorro) {
        this.moedas += cachorro.getMoedas();
        cachorro.setMoedas(0);
        this.anuncia_qtd_moedas();

//        try {
//            if (cachorro_em_campo == 1) {
//                cachorro1.join();
//            } else {
//                cachorro2.join();
//            }
//        } catch (InterruptedException ex) {
//        }
//System.out.println("123");
//        try {
//            cachorro.join();
//        } catch (InterruptedException ex) {
//        }
//        System.out.println("456");
    }

//    public void parar_caes() {
//        this.cachorro1.setFinalizar(true);
//        this.cachorro2.setFinalizar(true);
//
//        try {
//            this.cachorro1.join();
//            this.cachorro2.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Cacador.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (cachorro_em_campo == 1) {
//            this.cachorro1.setFinalizar(true);
////                this.cachorro1.join();
//        } else {
//            this.cachorro2.setFinalizar(true);
////                this.cachorro2.join();
//        }
//        if (cachorro_em_campo == 1) {
//            this.cachorro1.parar();
//        } else {
//            this.cachorro2.parar();
//        }
//    }
    public int getMoedas() {
        return this.moedas;
    }

    public int getCor() {
        return cor;
    }

    public void anuncia_qtd_moedas() {
        System.out.println(Bosque.PRINT_PREFIX[this.getCor()] + "Caçador " + Bosque.COR_NOME[this.getCor()] + " possui " + (this.getMoedas()) + " moedas" + Bosque.PRINT_SUFIX);

//        if (this.getCor() == Bosque.COR_AMARELO) {
//            System.out.println((char) 27 + "[33mCaçador " + this.getCorNome() + " possui " + (this.getMoedas()) + " moedas" + (char) 27 + "[0m");
//
//        }
//        if (this.getCor() == Bosque.COR_VERDE) {
//            System.out.println((char) 27 + "[32mCaçador " + this.getCorNome() + " possui " + (this.getMoedas()) + " moedas" + (char) 27 + "[0m");
//
//        }
//        if (this.getCor() == Bosque.COR_AZUL) {
//            System.out.println((char) 27 + "[34mCaçador " + this.getCorNome() + " possui " + (this.getMoedas()) + " moedas" + (char) 27 + "[0m");
//        }
    }

    public void anuncia_lancar_cachorro() {
        System.out.println(Bosque.PRINT_PREFIX[this.getCor()] + "Caçador " + Bosque.COR_NOME[this.getCor()] + " lança cachorro " + cachorro_em_campo + Bosque.PRINT_SUFIX);
    }
}
