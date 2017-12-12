/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenario;

import java.util.ArrayList;
import personagens.Cachorro;

/**
 *
 * @author Elizeu
 */
public class Pote extends Thread {

    public static final int MOEDAS_INICIAIS = 20;
    private final Object lock1 = new Object();
    int num;
    int moedas;

    ArrayList<Pote> caminhos;
    ArrayList<Cachorro> cachorros_dormindo;

    public Pote(int num) {
        this.num = num;
        this.moedas = Pote.MOEDAS_INICIAIS;
        this.caminhos = new ArrayList<Pote>();
        this.cachorros_dormindo = new ArrayList<Cachorro>();
    }

    public void add_cachorro_dormindo(Cachorro cachorro) throws InterruptedException {
        cachorro.dormir();
        this.cachorros_dormindo.add(cachorro);
    }

    public void procurar(Cachorro cachorro_procurando) throws InterruptedException {
        cachorro_procurando.sleep(Bosque.UNIT_TEMPO);

        int tres_moedas = this.get_3_moedas();
        if (tres_moedas == 0) {
//            cachorro_procurando.dormir();
//            this.cachorros_dormindo.add(cachorro_procurando);
            this.add_cachorro_dormindo(cachorro_procurando);
        } else {
            synchronized (this) {
                cachorro_procurando.add_moedas(tres_moedas);
                this.desconta_moedas(tres_moedas);
                int n_caminho = Bosque.numero_entre(0, this.caminhos.size());
                cachorro_procurando.setPote_atual(
                        this.caminhos.get(n_caminho)
                );
            }
        }
    }

    public Pote caminho_volta() {
//        int min = 20;
//        for (Pote p : this.caminhos) {
//            min = Math.min(min, p.get_num());
//        }
//
//        return Bosque.getInstance().get_pote_n(min);

        Pote volta = null;
        int min = 20;
        for (Pote p : this.caminhos) {
            if (p.get_num() <= min) {
                volta = p;
                min = volta.get_num();
            }
        }
        return volta;
    }

    public int get_3_moedas() {
        return Math.min(this.get_moedas(), 3);
    }

    public int get_num() {
        return num;
    }

    public int get_moedas() {
        return moedas;
    }

    public void add_1_moeda() {
        if (this.moedas == 0) {
            this.moedas = 1;
        }
    }

    public void desconta_moedas(int moedas) {
        this.moedas -= moedas;
    }

    public ArrayList get_caminhos() {
        return caminhos;
    }

    public void set_caminhos(ArrayList caminhos) {
        this.caminhos = caminhos;
//        this.teste();
    }

    public ArrayList get_cachorros_dormindo() {
        return cachorros_dormindo;
    }

//    public void add_cachorro_dormindo(Cachorro cachorros) {
//        this.cachorros_dormindo.add(cachorros);
//    }
    public void teste() {
//        int min = 20;
//        for (Pote p : this.caminhos) {
//            min = Math.min(min, p.get_num());
//        }

        Pote volta = null;
        int min = 20;
        for (Pote p : this.caminhos) {
            if (p.get_num() <= min) {
                volta = p;
            }
        }

        System.out.println(this.num + " = " + volta.get_num());
    }

}
