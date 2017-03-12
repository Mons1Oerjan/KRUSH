package cs.dal.krush.studentFragments;

import android.content.DialogInterface;
import android.content.Intent;
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
import static android.app.Activity.RESULT_OK;


public class StudentProfileEditFragment extends Fragment implements View.OnClickListener
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_GALLERY = 2;
    private String imagePath = "";
    Button saveProfile;
    Button changePicture;
    View myView;
    TextView profile_name;
    EditText email, school;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.student_profile_edit, container, false);

        // Get TextViews
        profile_name = (TextView) myView.findViewById(R.id.profile_name_edit);
        email = (EditText) myView.findViewById(R.id.profile_email_edit);
        school = (EditText) myView.findViewById(R.id.profile_school_edit);


        //fetch custom app font
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //Set custom app font
        profile_name.setTypeface(typeFace);
        email.setTypeface(typeFace);
        school.setTypeface(typeFace);

        saveProfile = (Button) myView.findViewById(R.id.save_profile_button);
        changePicture = (Button) myView.findViewById(R.id.change_picture_button);

        saveProfile.setOnClickListener(this);
        changePicture.setOnClickListener(this);

        return myView;

    }

    //Button listeners

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.save_profile_button:
                // Save information in fields to database when functionality is hooked up

                // Return to profile view
                try
                {
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

        //Save path to db
        //user.profile = image.getAbsolutePath();
        imagePath = image.getAbsolutePath();

        return image;

    }
}
