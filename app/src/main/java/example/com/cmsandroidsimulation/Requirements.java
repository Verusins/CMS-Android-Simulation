package example.com.cmsandroidsimulation;

import android.content.Context;

import example.com.cmsandroidsimulation.R;
public class Requirements {
    private String targetPost;
    private String admissionCategory;
    private Marks marks;

    public Requirements(){

    }

    public Requirements (String targetPost, String admissionCategory, Marks marks){
        this.targetPost = targetPost;
        this.admissionCategory = admissionCategory;
        this.marks = marks;
    }

    public void setTargetPost(String targetPost){
        this.targetPost = targetPost;
    }

    public void setAdmissionCategory(String admissionCategory){
        this.admissionCategory = admissionCategory;
    }

    public void setMarks(Marks marks){
        this.marks = marks;
    }

    public String getTargetPost(){
        return this.targetPost;
    }

    public String getAdmissionCategory(){
        return this.admissionCategory;
    }

    public Marks getMarks(){
        return this.marks;
    }

    public boolean sameAdmissionCategory(){
        return targetPost.contains(admissionCategory);
    }

    public boolean inCMS(Context c){
        return !(targetPost.equals(c.getString(R.string.post_quiz_q2_other)));
    }

    public String[] calculatePostResult(Context c){

        double cGPA = marks.getCGPA();
        String[] result = {"", ""};

        if (targetPost.equals(c.getString(R.string.post_quiz_q1_cs_minor))){
            if (marks.passedAllAndA08()){
                result[0] = c.getString(R.string.post_results_conditional_pass);
                result[1] = c.getString(R.string.post_results_in_cms_condition);
                return result;
            }
        }

        if (targetPost.equals(c.getString(R.string.post_quiz_q1_stats_minor))){
            result[0] = c.getString(R.string.post_results_pass);
            return result;
        }

        if (!marks.passedAll()
                && !(targetPost.equals(c.getString(R.string.post_quiz_q1_math_specialist))
                && admissionCategory.equals(c.getString(R.string.post_quiz_q2_math)))){
            result[0] = c.getString(R.string.post_results_fail);
            return result;
        }

        if (sameAdmissionCategory()){
            if (targetPost.equals(c.getString(R.string.post_quiz_q1_cs_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_cs_major))){
                boolean bInCsca48 = marks.getCsca48() >= 73;
                boolean cminusInA67A22A37 = !(marks.getMata67() < 60 && marks.getMata22() < 60
                        && marks.getMata37() < 60);

                if (cGPA >= 2.5 && bInCsca48 && cminusInA67A22A37){
                    result[0] = c.getString(R.string.post_results_pass);
                    return result;
                }
            }
            else if (targetPost.equals(c.getString(R.string.post_quiz_q1_stats_specialist))){
                if (cGPA >= 2.5){
                    result[0] = c.getString(R.string.post_results_pass);
                    result[1] = c.getString(R.string.post_results_stats_specialist_note);
                    return result;
                }
            }
            else if (targetPost.equals(c.getString(R.string.post_quiz_q1_math_specialist))){
                boolean bInA67A22A37 = !(marks.getMata67() < 73
                        && marks.getMata22() < 73
                        && marks.getMata37() < 73);
                if (cGPA >= 2.5 && bInA67A22A37){
                    result[0] = c.getString(R.string.post_results_pass);
                    return result;
                }
            }
            else if (targetPost.equals(c.getString(R.string.post_quiz_q1_stats_major))){
                if (cGPA >= 2.3){
                    result[0] = c.getString(R.string.post_results_pass);
                    return result;
                }
            }
            else if (targetPost.equals(c.getString(R.string.post_quiz_q1_math_major))) {
                boolean bInA67A22A37 = marks.getMata67() < 73
                        || marks.getMata22() < 73
                        || marks.getMata37() < 73;
                if (cGPA >= 2.3 && bInA67A22A37){
                    result[0] = c.getString(R.string.post_results_pass);
                    return result;
                }
            }
        }
        else if (inCMS(c)){
            if (targetPost.equals(c.getString(R.string.post_quiz_q1_cs_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_cs_major))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_stats_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_stats_major))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_math_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_math_major))) {
                result[0] = c.getString(R.string.post_results_conditional_pass);
                result[1] = c.getString(R.string.post_results_in_cms_condition);
                return result;
            }
        }
        else {
            if (targetPost.equals(c.getString(R.string.post_quiz_q1_cs_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_cs_major))){
                if (marks.getMata67() >= 80 && marks.getMata31() >= 80){
                    result[0] = c.getString(R.string.post_results_conditional_pass);
                    result[1] = c.getString(R.string.post_results_in_cms_condition);
                    return result;
                }
            }
            else if (targetPost.equals(c.getString(R.string.post_quiz_q1_stats_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_stats_major))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_math_specialist))
                    || targetPost.equals(c.getString(R.string.post_quiz_q1_math_major))) {
                result[0] = c.getString(R.string.post_results_conditional_pass);
                result[1] = c.getString(R.string.post_results_in_cms_condition);
                return result;
            }
        }

        result[0] = c.getString(R.string.post_results_fail);
        return result;
    }

}
