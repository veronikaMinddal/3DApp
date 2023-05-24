public class Calculator {

    public double raftQuantityCalculator(double length) {
        double numberOfPosts = (length/50)-1;
        return numberOfPosts;
    }

    public double  postQuantityCalculator(double length, double width) {
        if(length > 450){return 6;}
        else if(width > 450){return 6;}
        else return 4;
    }




}
