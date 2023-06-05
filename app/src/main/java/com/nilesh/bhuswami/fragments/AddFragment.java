package com.nilesh.bhuswami.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nilesh.bhuswami.R;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    TextInputEditText tit, price, desc;
    Button selImg, postData;
    ImageView imgv;
    final int RESULT_LOAD_IMG = 100;
    StorageReference storageReference;
    StorageReference imageref;
    private Uri selectedImageUri;
    Uri fileUri;
    FirebaseDatabase database;
    ActivityResultLauncher<String> launcher;
    String ids, img_url, titl, descrip, prices;


    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        tit = v.findViewById(R.id.editTextPlotName);
        desc = v.findViewById(R.id.editTextDescri);
        price = v.findViewById(R.id.editTextPrice);
        selImg = v.findViewById(R.id.choose_button);
        postData = v.findViewById(R.id.addButton);
        imgv = v.findViewById(R.id.plot_image);
        selImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        postData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(selectedImageUri);
                titl = tit.getText().toString();
                descrip = desc.getText().toString();
                prices = price.getText().toString();



                uploadImage(selectedImageUri);

            }
        });

        return v;
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMG);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            imgv.setImageURI(selectedImageUri);



        }
    }


    private void uploadImage(Uri selectedImageUri) {
        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("plots");
        StorageReference imageName = storageReference1.child(selectedImageUri.getLastPathSegment());

        imageName.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final String randomCode2 = UUID.randomUUID().toString().substring(0, 7);
                        ids = randomCode2;

                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("plots")
                                .child(ids)
                                .child("image1_url");
                        db.setValue(uri.toString());
                        db.getDatabase().getReference("plots").child(ids).child("title").setValue(titl);
                        db.getDatabase().getReference("plots").child(ids).child("desc").setValue(descrip);
                        db.getDatabase().getReference("plots").child(ids).child("price").setValue(prices);
                    }

                });


            }
        });

    }
}