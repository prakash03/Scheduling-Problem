import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Barber {
    private char name;
    private int earning;

    public Barber(char name){
        this.name = name;
        this.earning = 0;
    }

    public int getEarning(){
        return earning;
    }

    public char getName(){
        return name;
    }

    public void setEarning(int earning) {
        this.earning = earning;
    }


    private static int serviceRequired(){
        int[] array = {10,20,30,40};
        int value = 0;
        int rnd = new Random().nextInt(array.length);
        value = array[rnd];
        return value;
    }

//    public static List<Integer> listManipulator(List<Integer> list , int diff){
//        for (int i=1;i<list.size();i++){
//            if((list.get(0)-list.get(i))>=diff){
//                list.add(0,list.get(i));
//                list.remove(i+1);
//                return list;
//            }
//        }
//        return list;
//    }

    public static List<Barber> barberManipulation (List<Barber> list, int diff){
        for(int i=1; i< list.size(); i++){  //O(n) operation.
            if((list.get(0).getEarning() -list.get(i).getEarning()) >= diff){
                list.add(0,list.get(i)); //O(n) operation.
                list.remove(i+1);
                return list;
            }
        }
        return list;
    }

    public static void optimalAssignment(List<Barber> barberList, int roundCount, int customerCount, int diff){
        for(int i=0; i<barberList.size();i++){
           int service = serviceRequired();
           int earning = barberList.get(i).getEarning() + service;
           barberList.get(i).setEarning(earning);
           System.out.println(barberList.get(i).getName() + "'s earning in first round is " + barberList.get(i).getEarning());
        }


        List<Barber> newList = barberList;
        for(int j=0; j<roundCount; j++ ) { //O(rounds)
            barberList = newList;
            newList = new ArrayList<>();
            System.out.println("List after round " + (j+1) + " is.");
            System.out.println("-----------------------");
            System.out.println("Barber Name: " + barberList.get(0).getName()+"  "+ barberList.get(1).getName()+"  "+ barberList.get(2).getName()+ "  "+ barberList.get(3).getName()+ "  "+ barberList.get(4).getName());
            System.out.println("Earning $ : "+barberList.get(0).getEarning()+" "+ barberList.get(1).getEarning()+" "+ barberList.get(2).getEarning()+ " "+ barberList.get(3).getEarning()+ " "+ barberList.get(4).getEarning());
            System.out.println("-----------------------");
            System.out.println(" ");

            if(j!=roundCount-1) {
                System.out.println("Starting round "+ (j+2));
                System.out.println("-----------------------");
                for (int i = 0; i < 5; i++) { //O(n)
                    int service = serviceRequired();
                    System.out.println("Next Service required : $" + service);
                    barberList = barberManipulation(barberList, diff);
                    int earning = barberList.get(0).getEarning() + service;
                    barberList.get(0).setEarning(earning);
                    System.out.println("Next Barber to be assigned: "+ barberList.get(0).getName());
                    newList.add(barberList.get(0));
                    barberList.remove(0);
                }
            }
        }
    }

    public static void main(String[] args){
        Barber barberA = new Barber('A');
        Barber barberB = new Barber('B');
        Barber barberC = new Barber('C');
        Barber barberD = new Barber('D');
        Barber barberE = new Barber('E');
        List<Barber> barberList = new ArrayList<>();
        Barber[] initialOrder = {barberA,barberB,barberC, barberD, barberE};
        for(Barber barber:initialOrder){
            barberList.add(barber);
        }
        //System.out.println("The Barber list oder is: "+ Arrays.toString(barberList.toArray()));
        int roundCount = 6;
        int customerCount = 20;
        int diff = 10;
        System.out.println("Difference assumed to be d = " + diff);
        System.out.println("First come First Serve Based Assignment in Round 1");
        optimalAssignment(barberList,roundCount,customerCount,diff);

    }
}
