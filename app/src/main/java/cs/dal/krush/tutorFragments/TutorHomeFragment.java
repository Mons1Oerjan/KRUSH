package cs.dal.krush.tutorFragments;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.TutorHomeUpcomingSessionsCursorAdapter;
import cs.dal.krush.models.DBHelper;

/**
 * Sets up the Tutor Home fragment. This fragment belongs to the TutorMainActivity class
 * and is accessed through the tutor's bottom navigation bar.
 *
 *  Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class TutorHomeFragment extends Fragment {

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_home, container, false);
        USER_ID = getArguments().getInt("USER_ID");

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        DBHelper mydb = new DBHelper(C);

        Cursor tutor = mydb.tutor.getData(USER_ID);
        tutor.moveToFirst();

        //fetch UI elements:
        ListView upcomingSessionsListView = (ListView)view.findViewById(R.id.upcomingSessionsListView);
        RatingBar tutorRating = (RatingBar) view.findViewById(R.id.rating);
        TextView pageTitle = (TextView)view.findViewById(R.id.homeTitleLabel);
        TextView sessionsLabel = (TextView)view.findViewById(R.id.upcomingSessionsLabel);
        TextView ratingTitle = (TextView) view.findViewById(R.id.tutorRating);
        FloatingActionButton tutorHelpButton = (FloatingActionButton)view.findViewById(R.id.tutorHelpButton);


        String currentTutorRating = tutor.getString(tutor.getColumnIndex("rating"));
        if (currentTutorRating != null)
            tutorRating.setRating(Float.parseFloat(currentTutorRating));

        //fetch custom app font:
        final Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);
        sessionsLabel.setTypeface(typeFace);
        ratingTitle.setTypeface(typeFace);

        //get all tutoring sessions by the tutor:
        Cursor cursorSessionsResponse = mydb.tutoringSession.getDataByTutorIdForCursorAdapter(USER_ID);

        //set sessions listview adapter:
        TutorHomeUpcomingSessionsCursorAdapter sessionsAdapter = new TutorHomeUpcomingSessionsCursorAdapter(C, cursorSessionsResponse);
        upcomingSessionsListView.setAdapter(sessionsAdapter);

        // TODO: 2017-03-18 We need to write the instructions once the actual functionality is implemented to accurately write to be
        final String text = "Lorem ipsum dolor sit amet, pri magna delicata an. An " +
                "imperdiet, vitae nemore duo eu. Sed ne etiam inermis, aperiam convenire " +
                "appellantur ad ius, quo elit consequat vulputate eu. Eu cum choro " +
                "constituto, at per justo nostrum abhorreant. Ridens lobortis vix an." +
                " Impetus salutatus pro ea, ex recteque neglegentur signiferumque vim. " +
                "Vim ex scaevola scriptorem, usu te quando nonumes delectus.";


        //display student help dialog
        tutorHelpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(getContext());
                // Include dialog.xml file
                dialog.setContentView(R.layout.student_help);
                // Set dialog title
                dialog.setTitle("Custom Dialog");
                dialog.show();

                //fetch UI components
                TextView studentHelpHeader = (TextView) dialog.findViewById(R.id.studentHelpHeader);
                TextView studentHelpIntro = (TextView) dialog.findViewById(R.id.studentHelpIntro);
                TextView homeStudentHelpLabel = (TextView) dialog.findViewById(R.id.homeStudentHelpLabel);
                TextView homeStudentHelpText = (TextView) dialog.findViewById(R.id.homeStudentHelpText);
                TextView bookingStudentHelpLabel = (TextView) dialog.findViewById(R.id.bookingStudentHelpLabel);
                TextView bookingStudentHelpText = (TextView) dialog.findViewById(R.id.bookingStudentHelpText);
                TextView seesionsStudentHelpLabel = (TextView) dialog.findViewById(R.id.seesionsStudentHelpLabel);
                TextView sessionsStudentHelpText = (TextView) dialog.findViewById(R.id.sessionsStudentHelpText);
                TextView profileStudentHelpLabel = (TextView) dialog.findViewById(R.id.profileStudentHelpLabel);
                TextView profileStudentHelpText = (TextView) dialog.findViewById(R.id.profileStudentHelpText);


                //set logo font style
                studentHelpHeader.setTypeface(typeFace);
                homeStudentHelpLabel.setTypeface(typeFace);
                bookingStudentHelpLabel.setTypeface(typeFace);
                seesionsStudentHelpLabel.setTypeface(typeFace);
                profileStudentHelpLabel.setTypeface(typeFace);

                //set text in dialogue
                studentHelpIntro.setText(text);
                homeStudentHelpText.setText(text);
                bookingStudentHelpText.setText(text);
                sessionsStudentHelpText.setText(text);
                profileStudentHelpText.setText(text);

                //close dialogue button
                Button closeButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
