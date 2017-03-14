package cs.dal.krush.studentFragments;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cs.dal.krush.R;
import cs.dal.krush.models.DBHelper;

import static android.app.Activity.RESULT_OK;
import static cs.dal.krush.R.id.profile_name;


public class StudentProfileEditFragment extends Fragment implements View.OnClickListener
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_GALLERY = 2;
    private String imagePath = "";
    Button saveProfile;
    Button changePicture;
    View myView;
    TextView profile_name_view;
    EditText email_view, school_view;
    int user_id;
    private DBHelper mydb;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.student_profile_edit, container, false);

        // Get TextViews
        profile_name_view = (TextView) myView.findViewById(R.id.profile_name_edit);
        email_view = (EditText) myView.findViewById(R.id.profile_email_edit);
        school_view = (EditText) myView.findViewById(R.id.profile_school_edit);

        //Database connection
        mydb = new DBHelper(getContext());
        cursor = mydb.student.getData(user_id);
        cursor.moveToFirst();

        // Set data
        String name = cursor.getString(cursor.getColumnIndex("f_name")) + " " + cursor.getString(cursor.getColumnIndex("l_name"));
        String email = cursor.getString(cursor.getColumnIndex(("email")));
        int school_id = cursor.getInt(cursor.getColumnIndex("school_id"));
        Cursor schoolCursor = mydb.school.getData(school_id);
        schoolCursor.moveToFirst();
        String school = schoolCursor.getString(schoolCursor.getColumnIndex("name"));

        profile_name_view.setText(name);
        email_view.setText(email);
        school_view.setText(school);


        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        profile_name_view.setTypeface(typeFace);
        email_view.setTypeface(typeFace);
        school_view.setTypeface(typeFace);

        saveProfile = (Button) myView.findViewById(R.id.save_profile_button);
        changePicture = (Button) myView.findViewById(R.id.change_picture_button);

        saveProfile.setOnClickListener(this);
        changePicture.setOnClickListener(this);

        return myView;

    }

    public void setUser_id(int id)
    {
        user_id = id;
    }

    //Button listeners

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.save_profile_button:
                // Save information in fields to database when functionality is hooked up

                // Save data and Return to profile view
                try
                {
                    // Get data from fields
                    String new_email = email_view.getText().toString();

                    // Write new fields to table
                    ContentValues cv = new ContentValues();
                    cv.put("email", new_email);
                    mydb.getWritableDatabase().update("students", cv,"id="+user_id, null);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    StudentProfileFragment profile = new StudentProfileFragment();
                    transaction.replace(R.id.student_fragment_container, profile);
                    transaction.commit();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

                break;

            case R.id.change_picture_button:
                changePicture();
                break;
        }
    }

    public void changePicture()
    {
        final String[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Change Profile Picture");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                switch (options[item])
                {
                    case "Take Photo":
                        try
                        {
                            openCamera();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case "Choose from Gallery":
                        try
                        {
                            openGallery();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }

        });
        builder.show();
    }

    private void openCamera() throws IOException
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            // Create file to save photo
            File imageFile = createImage();
            Uri uri = FileProvider.getUriForFile(getActivity(),"cs.dal.krush.fileprovider", imageFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGallery() throws IOException
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Image"), REQUEST_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            // Set profile picture to image that was just captured
            ImageView profile_picture_view = (ImageView) myView.findViewById(R.id.profile_picture_edit);
            Bitmap profile_pic = BitmapFactory.decodeFile(imagePath);
            profile_picture_view.setImageBitmap(profile_pic);
        }

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK)
        {
            ImageView profile_picture_view = (ImageView) myView.findViewById(R.id.profile_picture_edit);
            try
            {
                Bitmap profile_pic = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
                profile_picture_view.setImageBitmap(profile_pic);

                //Save image path to db
                imagePath = data.getDataString();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    protected File createImage() throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "profile_picture_" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);

        imagePath = image.getAbsolutePath();

        return image;

    }
}
