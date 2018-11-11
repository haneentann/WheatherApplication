package com.example.hp1.wheatherapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
//https://www.youtube.com/watch?v=mSi7bNk4ySM&list=PLGCjwl1RrtcTXrWuRTa59RyRmQ4OedWrt&index=13
public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    private ImageView imageView;
    private Button btGallery, btTakePhot;

    //A bitmap is a type of memory organization or image file format used to store digital images.
    //The term bitmap comes from the computer programming terminology, meaning just a map of bits,
    // a spatially mapped array of bits.
    private Bitmap bitmap;

    //to save image into the firebase storage need to declare the reference to it
    private StorageReference storageReference;
    private ProgressDialog progres;

    //reference to database
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Users");

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        storageReference = FirebaseStorage.getInstance().getReference();

        progres = new ProgressDialog(this);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        btTakePhot = findViewById(R.id.btTakePhoto);
        btTakePhot.setOnClickListener(this);

        imageView = findViewById(R.id.captureImageView);
        mAuth = FirebaseAuth.getInstance();

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        if(view == btTakePhot){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }
    //Starting another activity doesn't have to be one-way. You can also start another activity and receive a result back
    //For example, your app can start a camera app and receive the captured photo as a result. Or,
    // you might start the People app in order for the user to select a contact and you'll receive the contact details as a result.
    //Of course, the activity that responds must be designed to return a result. When it does, it sends the result as another Intent object. Your activity receives it in the onActivityResult() callback
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if the request was from camera and the result was OK meanning the camera worked
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            //show user uploading message
            progres.setMessage("Uploading...");
            progres.show();

            Uri imageUri = data.getData();

            //there is an exception due to missing path need to fix for now user gallery
            StorageReference filepath = storageReference.child("Photos").child(currentUser.getUid()).child(imageUri.getLastPathSegment());
           filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CameraGalleryActivity.this, "Image uploaded successfuly", Toast.LENGTH_SHORT).show();
                    progres.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CameraGalleryActivity.this, "Faile to upload image", Toast.LENGTH_SHORT).show();
                }
            });

            //the image captured is saved in the data object
            //bitmap = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(bitmap);
            //   saveImage(photo);
        }else if(requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {

            //show user uploading message
            progres.setMessage("Uploading...");
            progres.show();
            //URI - unified resource locator is something like URL but can hold different type of paths
            // examples: file:///something.txt, http://www.example.com/, ftp://example.com
            // A Uri object is usually used to tell a ContentProvider what we want to access by reference
            Uri targetUri = data.getData();
            //locate the path in the storage directory in firebase
            final StorageReference filepath = storageReference.child("Photos").child(currentUser.getUid()).child(targetUri.getLastPathSegment());
            //upload the image to fire base

            filepath.putFile(targetUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    progres.dismiss();
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Picasso.with(CameraGalleryActivity.this).load(downloadUri).fit().centerCrop().into(imageView);

                    } else {
                        Toast.makeText(CameraGalleryActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
/*

            try {
                //Decode an input stream into a bitmap.
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


 */