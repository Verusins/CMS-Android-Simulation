package example.com.cmsandroidsimulation;

public class Marks {

    private int mata31;
    private int mata67;
    private int mata22;
    private int mata37;
    private int csca48;

    private int csca08;

    public Marks(int mata31, int mata67, int mata22, int mata37, int csca48, int csca08) {
        this.mata31 = mata31;
        this.mata67 = mata67;
        this.mata22 = mata22;
        this.mata37 = mata37;
        this.csca48 = csca48;
        this.csca08 = csca08;
    }

    public int getMata31() {
        return mata31;
    }

    public int getMata67() {
        return mata67;
    }

    public int getMata22() {
        return mata22;
    }

    public int getMata37() {
        return mata37;
    }

    public int getCsca48() {
        return csca48;
    }

    public void setMata31(int mata31) {
        this.mata31 = mata31;
    }

    public void setMata67(int mata67) {
        this.mata67 = mata67;
    }

    public void setMata22(int mata22) {
        this.mata22 = mata22;
    }

    public void setMata37(int mata37) {
        this.mata37 = mata37;
    }

    public void setCsca48(int csca48) {
        this.csca48 = csca48;
    }

    public int getCsca08() {
        return csca08;
    }

    public void setCsca08(int csca08) {
        this.csca08 = csca08;
    }

    // needs to throw exception for marks out of bounds
    public double getGPV(int mark){
        if (mark >= 85) return 4.0;
        else if (mark >= 80) return 3.7;
        else if (mark >= 77) return 3.3;
        else if (mark >= 73) return 3.0;
        else if (mark >= 70) return 2.7;
        else if (mark >= 67) return 2.3;
        else if (mark >= 63) return 2.0;
        else if (mark >= 60) return 1.7;
        else if (mark >= 57) return 1.3;
        else if (mark >= 53) return 1.0;
        else if (mark >= 50) return 0.7;
        else return 0.0;
    }

    public String getGradeFromMark(int mark){
        if (mark >= 90) return "A+";
        else if (mark >= 85) return "A";
        else if (mark >= 80) return "A-";
        else if (mark >= 77) return "B+";
        else if (mark >= 73) return "B";
        else if (mark >= 70) return "B-";
        else if (mark >= 67) return "C+";
        else if (mark >= 63) return "C";
        else if (mark >= 60) return "C-";
        else if (mark >= 57) return "D+";
        else if (mark >= 53) return "D";
        else if (mark >= 50) return "D-";
        else return "F";
    }

    public double getCGPA(){
        int[] marks = {mata31, mata67, mata22, mata37, csca48};
        int sum = 0;
        for (int mark: marks){
            sum += getGPV(mark);
        }
        return (double) sum/5;
    }

    public boolean passedAll(){
        int[] marks = {mata31, mata67, mata22, mata37, csca48};
        for (int mark: marks){
            if (mark < 50) return false;
        }
        return true;
    }

    public boolean passedAllAndA08(){

        return passedAll() && csca08 >= 50;
    }

}
