package cs.dal.krush.studentFragments;


import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

/**
 * StudentPaymentFragment is used to process payments from student's credit cards and increments
 * the revenue of tutor based on the cost of the session.
 */
public class StudentPaymentFragment extends Fragment {

    private boolean isValid;
    private String creditCarNumber;
    private String cvvNumbers;
    private String expirationMonth;
    private DBHelper mydb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_payment, container, false);
        // TODO: 2017-03-17 Get student and tutor ID from bundle
        // int userId = Integer.parseInt(getArguments().getString("UserID"));
        // int tutorId = Integer.parseInt(getArguments().getString("TutorID"));

        //initialize database connection
        mydb = new DBHelper(getActivity().getApplicationContext());

        //fetch UI components
        final TextView pageHeader = (TextView) view.findViewById(R.id.paymentHeader);
        final TextView costLabel = (TextView) view.findViewById(R.id.costLabel);
        final TextView tutoringCost = (TextView) view.findViewById(R.id.tutoringCost);
        final TextView cardNumberLabel = (TextView) view.findViewById(R.id.cardNumberLabel);
        final TextView expDateLabel = (TextView) view.findViewById(R.id.expDateLabel);
        final TextView cvvLabel = (TextView) view.findViewById(R.id.cvvLabel);
        final EditText creditNumberInput = (EditText) view.findViewById(R.id.creditNumberInput);
        final EditText monthInput = (EditText) view.findViewById(R.id.monthInput);
        final EditText cvvNumberInput = (EditText) view.findViewById(R.id.cvvNumberInput);
        final Button submitPayment = (Button) view.findViewById(R.id.submitPayment);

        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set logo font style
        pageHeader.setTypeface(typeFace);
        costLabel.setTypeface(typeFace);
        cardNumberLabel.setTypeface(typeFace);
        expDateLabel.setTypeface(typeFace);
        cvvLabel.setTypeface(typeFace);

        //set cost label
        // TODO: 2017-03-17 Get tutoring session cost from bundle 
        tutoringCost.setText("$" + 99.99);

        //submit payment
        submitPayment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isValid = true;
                creditCarNumber = creditNumberInput.getText().toString();
                expirationMonth = monthInput.getText().toString();
                cvvNumbers = cvvNumberInput.getText().toString();

                //validate inputs
                if(creditCarNumber.length() != 12) {
                    creditNumberInput.setError("Credit card number required!");
                    isValid = false;
                }

                if(expirationMonth.length() != 4) {
                    monthInput.setError("Expiration required!");
                    isValid = false;
                }

                if(cvvNumbers.length() != 3) {
                    cvvNumberInput.setError("CVV required!");
                    isValid = false;
                }

                //increment tutor's revenue and return to student home
                if(isValid) {
                    // TODO: 2017-03-18  Use tutorID and cost from bundle
                    mydb.tutor.incrementTutorRevenue(1, 99.99);

                    // TODO: 2017-03-18 Need intend - likely back to the student home
                }
            }
        });

        return view;
    }
}