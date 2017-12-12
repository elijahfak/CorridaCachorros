/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagens;

import cenario.Bosque;
import cenario.Pote;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elizeu
 */
public class CachorroVermelho extends Thread {

    int pote_n = 0;
    public boolean verificando = true;
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static CachorroVermelho instancia;

    public static CachorroVermelho getInstance() {
        //return GerrenciadorLivrosHolder.INSTANCE;
        if (instancia == null) {
            CachorroVermelho gu = new CachorroVermelho();
            gu.setDaemon(true);
            instancia = gu;
        }

        return instancia;
    }

    ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> future;

    public synchronized void set_verificando() {
        this.verificando = true;
    }

    public void parar_verificacao() {
        this.verificando = false;
    }

    @Override
    public void run() {
        future = execService.scheduleAtFixedRate(() -> {
//            System.out.println("Cachorro vermelho (verificando) " + this.verificando);
            if (this.verificando) {
                Pote pote = Bosque.getInstance().get_pote_n(pote_n);

                System.out.println(Bosque.PRINT_PREFIX[0] + "Cachorro " + Bosque.COR_NOME[0] + " verificando Pote " + (pote.get_num()) + " || " + pote.get_moedas() + Bosque.PRINT_SUFIX);
                if (pote.get_moedas() == 0) {
//                System.out.println(Bosque.PRINT_PREFIX[0] + "Cachorro "+Bosque.COR_NOME[0]+" inserindo uma moeda em Pote " + (pote_n) + Bosque.PRINT_SUFIX);

                    pote.add_1_moeda();
                }

                if (pote_n == 19) {
                    pote_n = 0;
                } else {
                    pote_n++;
                }
            } else {
//                future.cancel(true);
                future.cancel(false);
                execService.shutdown();

                try {
                    this.join();
                } catch (InterruptedException ex) {
                }
            }
        }, 0, Bosque.UNIT_TEMPO * 2, TimeUnit.MILLISECONDS);
    }

}
