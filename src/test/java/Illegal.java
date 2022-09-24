public class Illegal extends IllegalArgumentException{


    public static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String [] args){
//        System.out.println(sum(1, 2));
        int a = 2047483647;
        int b = 2047483647;
        int c = 0;

        c = a / 2;
        System.out.println(c);
    }


}
