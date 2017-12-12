# CorridaCachorros
Trabalho todo

Trabalho de Sistemas Operacionais (UFSC).


Descrição do Problema

O objetivo deste trabalho é o aluno aplicar conceitos de threads, exclusão mútua e escalonamento de processos por meio de uma simulação de uma competição de caça às moedas.

Em um bosque existem 20 potes com 4 moedas cada. Existem 3 caçadores de moeda (amarelo, verde e azul) e cada um tem 2 cachorros. Cada caçador envia seus cachorros para procurar os potes e quando um encontra um pote com moedas, ele consegue levar até 3 moedas do pote. Após isso, o cachorro vai procurar o próximo pote, o qual é escolhido aleatoriamente dentre as opções disponíveis na malha da figura abaixo, podendo, inclusive, voltar para o pote anterior.  Por exemplo, o cachorro verde, após coletar moedas no pote 2, deve escolher aleatoriamente um caminho para o pote 1, 3, 4 ou 5. Se um cachorro encontrar um pote vazio, ele deve dormir no pote até que o cachorro salva-vida (o vermelho) coloque uma moeda no pote para que o cachorro preso possa levar e seguir seguir sua missão. O cachorro vermelho visita todos os potes (de acordo com a ordem numérica) para determinar os que estão vazios. Nos potes vazios, o cachorro vermelho coloca uma moeda.

A regra da competição é que só deve ter, no máximo, 3 cachorros no bosque e cada pote só pode ser acessado por apenas um cachorro por vez (região crítica). Quando um cachorro tiver 20 moedas, ele deve voltar imediatamente (e diretamente) para o dono, entregar as moedas e voltar para fila de entrada do bosque, enquanto isso, o segundo cachorro já deverá ter entrado no bosque. O caçador vencedor será aquele que obtiver 50 moedas primeiro. Assim, um cachorro também pode também voltar quando o total de moedas que ele tem mais as moedas que o seu caçador já possui for 50.

Cada cachorro leva 1 unidade de tempo para sair de um pote para outro, e 1 unidade de tempo para pegar as moedas de um pote, caso haja. 

Implementação

O Trabalho deve ser implementado em Java. Cada cachorro é uma thread  Cachorro (2 amarelos, 2 verdes e 2 azuis). Você pode - mas não é obrigado - usar um ExecutorService para limitar que apenas 3 threads Cachorro (um de cada cor) entrem na classe Bosque. A classe Bosque deve conter os 20 potes, que podem ser implementados como um array de objetos Pote de tamanho 20.  O objeto Pote pode ter como atributos quantidade de moedas, potes em que tem conexão, a referência das threads Cachorro que estão dormindo nele naquele momento, etc.

Existe várias formas de implementar a classe Bosque. Por exemplo, um método PegarMoedaNoPote_i para cada pote ou apenas um método PegarMoeda para todos potes, dentre outras soluções. Ao invocar esse método, uma thread Cachorro  consegue verificar se tem moedas para pegar, e pega se tiver. Se retornar zero, este cachorro deve dormir até ser interrompido pelo Cachorro Vermelho. Crie um métodoColocarMoeda na classe Bosque para o Cachorro Vermelho colocar novas moedas nos potes que estiverem vazios. 

Toda vez que um cachorro encontrar um pode vazio ele deve dormir neste pote por 60 unidades de tempo (sleep(60 unidades de tempo)). O acesso ao pote é exclusivo, ou seja, não pode haver, em um mesmo instante, mais de um cachorro em um pote. No entanto, pode haver mais de um cachorro se estes estiverem todos dormindo, devido ao pote estar vazio. 

A thread Cachorro Vermelho, a cada 2 unidades de tempo, deve verificar quais potes estão vazios e, para esses, colocar uma moeda. Após colocar a moeda, o cachorro Vermelho deve verificar se existe algum cachorro dormindo neste pote. Caso haja(m), ele deve acordar esse(s) cachorro(s). Os cachorros, ao serem acordados, devem tentar pegar a única moeda do pote. Como somente um deve conseguir, os outros (se houver) devem dormir novamente mais 3 unidades de tempo.

O Cachorro Vermelho salva-vida pode ser implementado usando ScheduledExecutorService, a cada intervalo de 2 unidades de tempo essa thread deve ser lançada para cumprir sua missão de colocar moedas nos potes vazios. 

Cada thread Cachorro deve ter uma variável local int  indicando a quantidade de moeda coletada até o presente momento. Quando esse valor for igual a 20 o cachorro deve retornar ao dono para entregar as moedas. O programa deve imprimir a dupla de cachorros vencedora juntamente com as duplas segunda e terceira colocada indicando a quantidade de moedas coletadas.

Utilize o sleep() para simular cada atividade (saltos, pegar moeda, etc.). Uma unidade de tempo pode ser 100 milisegundos.

Antes de partir para implementação do código, faça a modelagem do sistema para que você tenha certeza de que entendeu corretamente o problema e, posteriormente, para o professor analisar a solução. 
