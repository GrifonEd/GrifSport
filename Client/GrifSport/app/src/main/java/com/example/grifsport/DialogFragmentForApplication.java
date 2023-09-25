package com.example.grifsport;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentForApplication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentForApplication extends androidx.fragment.app.DialogFragment {

    private int event_id;
    private int profile_id;
    private String vacancy;
    private  String description="";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogFragmentForApplication() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragmentForApplication newInstance(String param1, String param2) {
        DialogFragmentForApplication fragment = new DialogFragmentForApplication();
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


        event_id = getArguments().getInt("event_id",-1);
        profile_id = getArguments().getInt("profile_id",-1);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialog_for_application, null);
        EditText descriptionTextApplication= v.findViewById(R.id.descriptionTextApplication);
        descriptionTextApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                description=descriptionTextApplication.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Button button_back=v.findViewById(R.id.application_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDismiss(getDialog());
            }
        });

        Button application_post=v.findViewById(R.id.application_post);
        application_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationResponsePost applicationResponse = new ApplicationResponsePost(0,profile_id,event_id,vacancy,description);
                Call<ApplicationResponsePost> callApplication =ApiClient.applicationServicePost().postApplication(applicationResponse);
                callApplication.enqueue(new Callback<ApplicationResponsePost>() {
                    @Override
                    public void onResponse(Call<ApplicationResponsePost> call, Response<ApplicationResponsePost> response) {

                        if(response.isSuccessful())
                            Toast.makeText(getContext(),"Заявка успешно создана!",Toast.LENGTH_SHORT).show();
                        else {
                            Log.e("eror", response.errorBody().toString());
                            if(response.code()==500)
                                Toast.makeText(getContext(),"Вы уже зарегистрированы на данное мероприятие!",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ApplicationResponsePost> call, Throwable t) {

                    }
                });
            }
        });
        Spinner spinnerVacancy = v.findViewById(R.id.changeVacancy);
        String[] typesVacancy = {"Участник","Зритель","Судья","Работник"};
        vacancy="Участник";
        ArrayAdapter<String> adapterTypesVacancy  = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,typesVacancy);
        adapterTypesVacancy.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerVacancy.setAdapter(adapterTypesVacancy);
        spinnerVacancy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapterView.getChildAt(0)).setTextSize(15);
                vacancy =  ((TextView) adapterView.getChildAt(0)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        getDialog().setTitle("Заявка на участие!");
        return v;
    }
}