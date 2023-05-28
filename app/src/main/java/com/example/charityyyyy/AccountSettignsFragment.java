package com.example.charityyyyy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountSettignsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountSettignsFragment extends Fragment {

    EditText setting_name, setting_email, setting_phone, setting_password;
    Button update_name, update_email, update_phone, update_password, delete_button;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountSettignsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettignsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountSettignsFragment newInstance(String param1, String param2) {
        AccountSettignsFragment fragment = new AccountSettignsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View view = inflater.inflate(R.layout.fragment_settigns, container,false);
        setting_name = view.findViewById(R.id.settings_name);
        setting_email = view.findViewById(R.id.settings_mail);
        setting_phone = view.findViewById(R.id.settings_phone);
        setting_password = view.findViewById(R.id.settings_password);
        update_email = view.findViewById(R.id.update_email_button);
        update_name = view.findViewById(R.id.update_name_button);
        update_phone = view.findViewById(R.id.update_phone_button);
        update_password = view.findViewById(R.id.update_password_button);
        delete_button = view.findViewById(R.id.delete_button);
                if (user != null){
                    delete_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Afficher une boîte de dialogue de confirmation avant de supprimer le compte
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Confirm");
                            builder.setMessage("Are you sure you want to delete the account?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getActivity(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getActivity(),loginPage.class));
                                                } else {
                                                    Toast.makeText(getActivity(), "Failed to delete the account", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                }
                            });
                            builder.setNegativeButton("No", null);
                            builder.show();
                        }
                    });

                    setting_phone.setText(user.getPhoneNumber());
                    setting_email.setText(user.getEmail());

                    String new_name = setting_name.getText().toString();
                    String new_phone = setting_phone.getText().toString();


                    //update password
                    update_password.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String new_password = setting_password.getText().toString();
                            user.updatePassword(new_password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                    }  else {
                                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });

                    //update email
                    update_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String new_email = setting_email.getText().toString();
                            user.updateEmail(new_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                    }  else {
                                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                    //update phone


                }




        // Inflate the layout for this fragment
        return view;
    }
}