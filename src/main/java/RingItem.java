public class RingItem extends Thread{

    int id;
    char[] message;
    boolean isMyTime;
    boolean done;
    RingItem nextRing;

    public RingItem(
            int id
    ){
        this.id = id;
        this.isMyTime = false;
        this.done = false;
    }

    @Override
    public void run() {
        while(!this.done){
            //se não é a minha vez, durmo por 1s
            if(!this.isMyTime){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
            }else{ //se é a minha vez faço:
                System.out.print("Thread " + this.id + " recebeu a mensagem ");
                System.out.println(this.message);
                boolean existLowerCharacter = false;
                for(int i = 0; i < this.message.length; i++){
                    if(Character.isLowerCase(this.message[i])){
                        //sinalizo que existe caracter minusculo para converter
                        existLowerCharacter = true;

                        //converto para maiusculo
                        this.message[i] = Character.toUpperCase(this.message[i]);

                        //saio do laço
                        break;
                    }
                }
                if(existLowerCharacter){
                    //se achei caracter minusculo, vou dormir
                    this.sleep();
                }else{
                    //se eu nao achei caracter minusculo o trabalho finaliza
                    this.done = true;
                }
                //passo a mensagem adiante para continuar o processo, ou parar a execução por ter finalizado o trabalho
                nextShouldRun(this.message);
            }
        }
        System.out.println("Thread " + this.id + " recebeu a mensagem toda em Uppercase, por isso está saindo...");
    }

    public void nextShouldRun(char[] message){
        this.nextRing.wakeUp(message);
    }

    public void setNextRing(RingItem nextRing) {
        this.nextRing = nextRing;
    }

    public void wakeUp(char[] message){
        this.message = message;
        this.isMyTime = true;
    }

    public void sleep(){
        this.isMyTime = false;
    }
}
