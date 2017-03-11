package cs.dal.krush;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cs.dal.krush.helpers.BottomNavigationViewHelper;
import cs.dal.krush.studentFragments.StudentProfileEditFragment;
import cs.dal.krush.studentFragments.StudentProfileFragment;
import cs.dal.krush.tutorFragments.TutorCalendarFragment;
import cs.dal.krush.tutorFragments.TutorHomeFragment;
import cs.dal.krush.tutorFragments.TutorProfileEditFragment;
import cs.dal.krush.tutorFragments.TutorProfileFragment;
import cs.dal.krush.tutorFragments.TutorSessionsFragment;

import static cs.dal.krush.StudentMainActivity.REQUEST_GALLERY;
import static cs.dal.krush.StudentMainActivity.REQUEST_IMAGE_CAPTURE;


public class TutorMainActivity extends FragmentActivity {
    BottomNavigationView bottomNav;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_GALLERY = 2;
    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_main);

        //Set initial fragment to tutor home page
        TutorHomeFragment homeFragment = new TutorHomeFragment();
        homeFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.tutor_fragment_container, homeFragment).commit();

         //Custom bottom nav bar with disabled shifting
        bottomNav = (BottomNavigationView) findViewById(R.id.tutor_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

        //Nav bar listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menu_item = item.getItemId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch(menu_item) {
                    case R.id.menu_home:
                        TutorHomeFragment home = new TutorHomeFragment();
                        transaction.replace(R.id.tutor_fragment_container, home);
                        transaction.commit();
                        return true;
                    case R.id.menu_profile:
                        TutorProfileFragment profile = new TutorProfileFragment();
                        transaction.replace(R.id.tutor_fragment_container, profile);
                        transaction.commit();
                        return true;
                    case R.id.menu_sessions:
                        TutorSessionsFragment sessions = new TutorSessionsFragment();
                        transaction.replace(R.id.tutor_fragment_container, sessions);
                        transaction.commit();
                        return true;
                    case R.id.menu_calendar:
                        TutorCalendarFragment calendar = new TutorCalendarFragment();
                        transaction.replace(R.id.tutor_fragment_container, calendar);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });
    }

    public void editProfile(View view)
    {
        try
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            TutorProfileEditFragment edit = new TutorProfileEditFragment();
            transaction.replace(R.id.tutor_fragment_container, edit);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void saveProfile(View view)
    {
        // Save information in fields to database when functionality is hooked up

        // Return to profile view
        try
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            TutorProfileFragment profile = new TutorProfileFragment();
            transaction.replace(R.id.tutor_fragment_container, profile);
            transaction.commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void changePicture(View view)
    {
        final String[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(TutorMainActivity.this);
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
        if(cameraIntent.resolveActivity(getPackageManager()) != null)
        {
            // Create file to save photo
            File imageFile = createImage();
            Uri uri = FileProvider.getUriForFile(this,"cs.dal.krush.fileprovider", imageFile);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            // Set profile picture to image that was just captured
            ImageView profile_picture_view = (ImageView) findViewById(R.id.profile_picture_edit);
            Bitmap profile_pic = BitmapFactory.decodeFile(imagePath);
            profile_picture_view.setImageBitmap(profile_pic);
        }

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK)
        {
            ImageView profile_picture_view = (ImageView) findViewById(R.id.profile_picture_edit);
            try
            {
                Bitmap profile_pic = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
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
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);

        //Save path to db
        //user.profile = image.getAbsolutePath();

        imagePath = image.getAbsolutePath();

        return image;

    }
}
