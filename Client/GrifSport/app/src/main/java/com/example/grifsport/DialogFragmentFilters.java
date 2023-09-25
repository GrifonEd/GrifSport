package com.example.grifsport;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentFilters#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentFilters extends androidx.fragment.app.DialogFragment {
    public static final String TAG_WEIGHT_SELECTED = "weight";
    private int event_id;
    private int profile_id;
    public interface OnInputSelected{
        void sendInput(String sport,String age,String gender,String city);
    }
    public OnInputSelected mOnInputSeleceted;
    private String vacancy;
    private  String description="";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogFragmentFilters() {
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
    public static DialogFragmentFilters newInstance(String param1, String param2) {
        DialogFragmentFilters fragment = new DialogFragmentFilters();
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
        View v = inflater.inflate(R.layout.fragment_dialog_filters, null);
        TextView sportFilter= v.findViewById(R.id.SportEdit);
        TextView disciplineFilter= v.findViewById(R.id.DisciplineEdit);
        TextView ageFilter= v.findViewById(R.id.AgeEdit);
        TextView genderFilter= v.findViewById(R.id.GenderEdit);
        TextView cityFilter= v.findViewById(R.id.CityEdit);

        sportFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SportPickDialog(sportFilter);
            }
        });

        disciplineFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SportPickDialog(sportFilter);
            }
        });

        ageFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgePickDialog(ageFilter);
            }
        });

        genderFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               GenderPickDialog(genderFilter);
            }
        });

        cityFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityPickDialog(cityFilter);
            }
        });

        sportFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(sportFilter.getText().toString().equals("") || sportFilter.getText().toString().equals("Без фильтра")) {
                    disciplineFilter.setVisibility(View.GONE);
                }
                else{
                   // disciplineFilter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        Button button_back=v.findViewById(R.id.application_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(TAG_WEIGHT_SELECTED, 123);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                mOnInputSeleceted.sendInput("","","","");
                onDismiss(getDialog());
            }
        });

        Button filterOk=v.findViewById(R.id.filterOk);



        filterOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(TAG_WEIGHT_SELECTED, 123);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                mOnInputSeleceted.sendInput(sportFilter.getText().toString(),ageFilter.getText().toString(),genderFilter.getText().toString(),cityFilter.getText().toString());
                onDismiss(getDialog());

            }
        });




        return v;
    }
    private void SportPickDialog(TextView sportText) {
        final String[] SportArray = new String[]{"Без фильтра","Шахматы","Скалолазание","Керлинг","Ниндзя-спорт","Бокс"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите вид спорта")
                .setItems(SportArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String sub = SportArray[i];
                        sportText.setText(sub);

                    }
                })
                .show();

    }

    private void AgePickDialog(TextView ageText) {
        final String[] AgeArray = new String[]{"Без фильтра"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите вид спорта")
                .setItems(AgeArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String sub = AgeArray[i];
                        ageText.setText(sub);

                    }
                })
                .show();

    }

    private void CityPickDialog(TextView cityText) {
        final String[] CityArray = new String[]{"Без фильтра","Уфа","Москва","Санкт-Петербург","Екатеринбург","Тюмень","Сыктывкар"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите город проведения")
                .setItems(CityArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String sub = CityArray[i];
                        cityText.setText(sub);

                    }
                })
                .show();
    }

    private void GenderPickDialog(TextView genderText) {
        final String[] GenderArray = new String[]{"Мужчины и женщины","Мужчины","Женщины"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите пол")
                .setItems(GenderArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String sub = GenderArray[i];
                        genderText.setText(sub);

                    }
                })
                .show();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mOnInputSeleceted = (OnInputSelected)getTargetFragment();
        }
        catch (ClassCastException e){

        }
    }
}