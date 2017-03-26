package cs.dal.krush.adapters;

/**
 * Created by greg on 25/03/17.
 */

public class TutorDayTimeRowitem {
    private String text;

    public TutorDayTimeRowitem(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text + "\n" + text;
    }
}
