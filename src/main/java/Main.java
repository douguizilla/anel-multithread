public class Main {
    public static void main(String[] args) {
        char[] str = new RandomString(80).get().toCharArray();
        System.out.print("Palavra a ser processada: ");
        System.out.println(str);
        try {
            Ring ring = new Ring(30);
            ring.start(str);
            ring.waitFinish();
            System.out.print("Resultado exibido no processo: ");
            System.out.println(str);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

class Ring {
    int quantity;
    RingItem[] ringItems;
    Thread[] threads;

    public Ring(int quantity){
        this.ringItems = new RingItem[quantity];
        this.threads = new Thread[quantity];
        this.quantity = quantity;
    }

    public void start(char [] message){
        for(int i = 0; i < quantity; i++){
            ringItems[i] = new RingItem(i);
            if(i > 0){
                ringItems[i-1].setNextRing(ringItems[i]);
            }
        }
        ringItems[quantity-1].setNextRing(ringItems[0]);
        for(int i = 0; i < quantity; i++) {
            threads[i] = new Thread(ringItems[i]);
            threads[i].start();
        }
        ringItems[0].wakeUp(message);
    }

    public void waitFinish() throws InterruptedException {
        for(int i = 0; i < quantity; i++){
            threads[i].join();
        }
    }
}
