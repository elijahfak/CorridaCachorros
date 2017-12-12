/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagens;

import cenario.Bosque;
import cenario.Pote;

/**
 *
 * @author Elizeu
 */
public class Cachorro extends Thread {

    boolean finalizar;
    int moedas;
    Pote pote_atual;
    int cor;
    
    boolean has_started = false;

    public Cachorro(int cor) {
        this.moedas = 0;
        this.cor = cor;
        this.finalizar = false;
        pote_atual = null;
    }

    @Override
    public void run() {
        try {
            if (Bosque.getInstance().is_disputa_acontecendo() && !this.finalizar) {
                this.anuncia_procura_pote(pote_atual.get_num());
                pote_atual.procurar(this);
                this.run();
                this.has_started = true;
            } else if (Bosque.getInstance().is_disputa_acontecendo() && this.finalizar) {
                Pote pote_volta = pote_atual.caminho_volta();
                this.anuncia_retorno_pote(pote_atual.get_num(), pote_volta.get_num());

                if (pote_volta.get_num() == 1) {
                    Bosque.getInstance().receber_cachorro(this);
                } else {
                    this.setPote_atual(pote_volta);
                    this.run();
                    this.has_started = true;
                }
            }

            Cachorro.sleep(Bosque.UNIT_TEMPO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isHas_started() {
        return has_started;
    }

    public void setHas_started(boolean has_started) {
        this.has_started = has_started;
    }

    public void add_moedas(int moedas) {
        this.moedas += moedas;
        if (this.moedas >= Bosque.MOEDAS_PARA_VOLTAR) {
//            System.out.println("Voltar para o dono");
            this.finalizar = true;
        }
    }

    public void dormir() throws InterruptedException {
        System.out.println("Dormir");
        Cachorro.sleep(Bosque.UNIT_TEMPO * Bosque.TEMPO_DORMIR);
//        this.start();
    }

    public void anuncia_procura_pote(int i) {
        System.out.println(Bosque.PRINT_PREFIX[this.getCor()] + "Cachorro " + Bosque.COR_NOME[this.getCor()] + " procurando moedas no pote " + (i) + Bosque.PRINT_SUFIX);

//        if (cor == Bosque.COR_AMARELO) {
//            System.out.println((char) 27 + "[33mCachrro Amarelo procurando moedas no pote" + (i) + " " + (char) 27 + "[0m");
//
//        }
//        if (cor == Bosque.COR_VERDE) {
//            System.out.println((char) 27 + "[32mThis Verde procurando moedas no pote" + (i) + " " + (char) 27 + "[0m");
//
//        }
//        if (cor == Bosque.COR_AZUL) {
//            System.out.println((char) 27 + "[34mCachrro Azul procurando moedas no pote" + (i) + " " + (char) 27 + "[0m");
//        }
    }

//    public synchronized void parar() throws InterruptedException {
////        this.setFinalizar(true);
//        this.join();
//    }
    public void anuncia_retorno_pote(int i, int j) {
        System.out.println(Bosque.PRINT_PREFIX[this.getCor()] + "Cachorro " + Bosque.COR_NOME[this.getCor()] + " voltando a partir do pote" + (i) + " para o pote " + j + Bosque.PRINT_SUFIX);

//        if (cor == Bosque.COR_AMARELO) {
//            System.out.println((char) 27 + "[33mCachrro Amarelo voltando a partir do pote" + (i) + " para o pote " + j + (char) 27 + "[0m");
//        }
//        if (cor == Bosque.COR_VERDE) {
//            System.out.println((char) 27 + "[32mThis Verde voltando a partir do pote" + (i) + " para o pote " + j + (char) 27 + "[0m");
//
//        }
//        if (cor == Bosque.COR_AZUL) {
//            System.out.println((char) 27 + "[34mCachrro Azul voltando a partir do pote" + (i) + " para o pote " + j + (char) 27 + "[0m");
//        }
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public Pote getPote_atual() {
        return pote_atual;
    }

    public void setPote_atual(Pote pote_atual) {
        this.pote_atual = pote_atual;
    }

//    public boolean isFinalizar() {
//        return finalizar;
//    }
//
//    public void setFinalizar(boolean finalizar) {
//        this.finalizar = finalizar;
//    }
    public int getCor() {
        return cor;
    }

}
