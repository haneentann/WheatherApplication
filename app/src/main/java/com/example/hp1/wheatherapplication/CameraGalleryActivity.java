package com.example.hp1.wheatherapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 0;
    ImageView imageView;
    Button btGallery, btTakePhot;

    //A bitmap is a type of memory organization or image file format used to store digital images.
    //The term bitmap comes from the computer programming terminology, meaning just a map of bits,
    // a spatially mapped array of bits.
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        btTakePhot = findViewById(R.id.btTakePhoto);
        btTakePhot.setOnClickListener(this);

        imageView = findViewById(R.id.captureImageView);
        //imageView.setImageDrawable(R.drawable.download);
    }

    @Override
    public void onClick(View view) {
        if(view == btTakePhot){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else{
            //later
        }
    }
    //Starting another activity doesn't have to be one-way. You can also start another activity and receive a result back
    //For example, your app can start a camera app and receive the captured photo as a result. Or,
    // you might start the People app in order for the user to select a contact and you'll receive the contact details as a result.
    //Of course, the activity that responds must be designed to return a result. When it does, it sends the result as another Intent object. Your activity receives it in the onActivityResult() callback
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if the request was from camera and the result was OK meanning the camera worked
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            //the image captured is saved in the data object
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
         //   saveImage(photo);
        }
    }

    public String saveImage(Bitmap bitmap){
        File root = Environment.getExternalStorageDirectory();
        /**
         internal storage launching .
         */
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String filePath = root.getAbsolutePath()+"/DCIM/Camera/IMG_"+timeStamp+".jpg";
        File file = new File(filePath);
        // determinig the type of the file and its place.

        try
        {
            // if gallary not full create a file and save images
            file.createNewFile();// create new file to save image.
            FileOutputStream ostream = new FileOutputStream(file);//saves root in this file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);// compass bitmap in file
            ostream.close();// close
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();
        }
        return filePath;
    }
}
