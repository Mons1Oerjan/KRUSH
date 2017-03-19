package cs.dal.krush.studentFragments;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.StudentCursorAdapters.HomeQuickBookCursorAdapter;
import cs.dal.krush.StudentCursorAdapters.SessionCursorAdapter;
import cs.dal.krush.appFragments.SessionDetailsFragment;
import cs.dal.krush.models.DBHelper;


/**
 * Sets up the Student Home fragment. This fragment belongs to the StudentMainActivity class
 * and is accessed through the student's bottom navigation bar.
 *
 * Source:
 * [5] List View. (n.d.). Retrieved March 12, 2017,
 * from https://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class StudentHomeFragment extends Fragment {

    static int USER_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.student_home, container, false);
        USER_ID = getArguments().getInt("USER_ID");

        //get Context:
        Context C = getActivity().getApplicationContext();

        //init DB connection:
        DBHelper mydb = new DBHelper(C);

        //fetch UI elements:
        ListView upcomingSessionsListView = (ListView)view.findViewById(R.id.upcomingSessionsListView);
        ListView tutorsListView = (ListView)view.findViewById(R.id.availableTutorsListView);
        TextView pageTitle = (TextView)view.findViewById(R.id.homeTitleLabel);
        TextView sessionsLabel = (TextView)view.findViewById(R.id.upcomingSessionsLabel);
        TextView bookTutorLabel = (TextView)view.findViewById(R.id.bookTutorLabel);
        FloatingActionButton helpButton = (FloatingActionButton)view.findViewById(R.id.helpButtonStudent);

        //fetch custom app font:
        final Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);
        sessionsLabel.setTypeface(typeFace);
        bookTutorLabel.setTypeface(typeFace);

        //get all tutoring sessions by the student:
        final Cursor cursorSessionsResponse = mydb.tutoringSession.getDataByStudentIdForCursorAdapter(USER_ID);

        //set sessions listview adapter:
        SessionCursorAdapter sessionsAdapter = new SessionCursorAdapter(C, cursorSessionsResponse);
        upcomingSessionsListView.setAdapter(sessionsAdapter);

        //get all distinct tutors that the user has previously had a tutoring session with:
        final Cursor cursorTutorResponse = mydb.tutor.getPreviouslyUsedTutorsForCursorAdapter(USER_ID);

        //set tutor's listview adapter:
        HomeQuickBookCursorAdapter quickBookAdapter = new HomeQuickBookCursorAdapter(C, cursorTutorResponse);
        tutorsListView.setAdapter(quickBookAdapter);

        // Click listeners for listviews
        upcomingSessionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {

                //Get id of session clicked on
                cursorSessionsResponse.moveToPosition(position);
                int SESSION_ID = cursorSessionsResponse.getInt(cursorSessionsResponse.getColumnIndex("id"));

                // Add USER_ID and SESSION_ID to session details fragment for displaying
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", USER_ID);
                bundle.putInt("SESSION_ID", SESSION_ID);

                // Swap into new fragment
                SessionDetailsFragment session = new SessionDetailsFragment();
                session.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.student_fragment_container, session);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });

        tutorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                cursorTutorResponse.moveToPosition(position);
                int TUTOR_ID = cursorTutorResponse.getInt(cursorTutorResponse.getColumnIndex("_id"));

                // Add USER_ID and SESSION_ID to session details fragment for displaying
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", USER_ID);
                bundle.putInt("TUTOR_ID", TUTOR_ID);

                // Swap into new fragment
                StudentTutorDetailsFragment tutor = new StudentTutorDetailsFragment();
                tutor.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.student_fragment_container, tutor);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        // TODO: 2017-03-18 We need to write the instructions once the actual functionality is implemented to accurately write to be
        final String text = "Lorem ipsum dolor sit amet, pri magna delicata an. An " +
                "imperdiet, vitae nemore duo eu. Sed ne etiam inermis, aperiam convenire " +
                "appellantur ad ius, quo elit consequat vulputate eu. Eu cum choro " +
                "constituto, at per justo nostrum abhorreant. Ridens lobortis vix an." +
                " Impetus salutatus pro ea, ex recteque neglegentur signiferumque vim. " +
                "Vim ex scaevola scriptorem, usu te quando nonumes delectus.";

        //display student help dialog
        helpButton.setOnClickListener(new View.OnClickListener() {
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