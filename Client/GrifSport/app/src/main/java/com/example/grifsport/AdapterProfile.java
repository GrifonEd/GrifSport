package com.example.grifsport;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityOneApplicationBinding;
import com.example.grifsport.databinding.ActivityOneProfileBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProfile extends  RecyclerView.Adapter<AdapterProfile.HolderOneProfile> implements Filterable  {

    //private OnNoteListener listener;
    private int profile_id;
    private int event_id;
    private String access;
    private Context context;
    public java.util.ArrayList<ProfileResponse> ArrayList,filterList;
    private ActivityOneProfileBinding binding;
    java.util.ArrayList <Integer> ListId = new ArrayList<>();
   // private  FilterNote filter;

    //private  FilterPdfUser filter;
    //фрагмент одной кв.
    public AdapterProfile(Context context, java.util.ArrayList<ProfileResponse> pdfArrayList, String access, int profile_id,int event_id) {
        this.context = context;
        this.ArrayList = pdfArrayList;
        this.access=access;
        this.profile_id=profile_id;
        this.event_id=event_id;
        try {
       //     this.listener = ((AdapterEvent.OnNoteListener)context);
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }
        this.filterList =ArrayList;
    }


    @Override
    public HolderOneProfile onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = ActivityOneProfileBinding.inflate(LayoutInflater.from(context),parent,false);

        this.context=parent.getContext();
        return new HolderOneProfile(binding.getRoot());
    }
    public  void showPopup(View v,int idCondition,int position){
        PopupMenu popupMenu=new PopupMenu(context,v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    /*
                    case R.id.change:
                        Intent intentPut = new Intent(context,UpdateNoteActivity.class);
                        intentPut.putExtra("idCondition", idCondition);
                        intentPut.putExtra("access", access);
                        context.startActivity(intentPut);

                        return true;
                    case R.id.delete1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Вы точно хотите удалить заметку?").setMessage("");
                        builder.setPositiveButton("ДА", new DialogInterface.OnClickListener() { // Кнопка Да
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(v.getContext(), "Заметка успешно удалена!", Toast.LENGTH_SHORT).show();
                                Call<ConditionResponse> call1 = ApiClient.DeleteConditionService().deleteConditions(idCondition);
                                call1.enqueue(new Callback<ConditionResponse>() {
                                    @Override
                                    public void onResponse(Call<ConditionResponse> call, Response<ConditionResponse> response) {
                                        Log.d("Norm", response.headers().toString());
                                        Log.d("Norm", response.toString());
                                        if (response.isSuccessful()) {
                                            Intent intent2 = new Intent();
                                            listener.onNoteListener(intent2);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ConditionResponse> call, Throwable t) {
                                        Log.d("Norm", "hm");
                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("нет", new DialogInterface.OnClickListener() { // Кнопка Нет
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Отпускает диалоговое окно
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return true;

                     */
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.spisok_menu);
        popupMenu.show();
    }


    @Override
    public void onBindViewHolder( HolderOneProfile holder, int position) {
        //из листа всех квартир берем один конкретный экземпляр


        ProfileResponse model = ArrayList.get(position);
        //заполнение переменных каждого экз-ра на форме


        /*
        String date = model.getEvent_end_date();
        Log.e("data",date);
        String[]words = date.split("-");
        String year=words[0];
        String month=words[1];
        String[]words1=words[2].split("T");
        String day=words1[0];
        String[] words2=words1[1].split(":");
        String hour=words2[0];
        String mins   =words2[1];
        Log.d("Privet",year+","+month+","+day+","+hour+","+mins);

        Character first=day.charAt(0);
        if (first=='0') {
            day=day.substring(1);
        }

        switch (month){
            case("01"):
                month="Января";
                break;
            case("02"):
                month="Февраля";
                break;
            case("03"):
                month="Марта";
                break;
            case("04"):
                month="Апреля";
                break;
            case("05"):
                month="Мая";
                break;
            case("06"):
                month="Июня";
                break;
            case("07"):
                month="Июля";
                break;
            case("08"):
                month="Августа";
                break;
            case("09"):
                month="Сентября";
                break;
            case("10"):
                month="Октября";
                break;
            case("11"):
                month="Ноября";
                break;
            case("12"):
                month="Декабря";
                break;

        }

 holder.NoteDate.setText(day+" "+month+" "+ year+"       "+hour+":"+mins);


         */



        String age ;
        holder.NoteDate.setText(model.getFirst_name()+" "+model.getSecond_name());
        holder.NoteAssesment.setText(model.getCity());
        if(model.getAge()%10==1)
            age = "год";
        else if(model.getAge()%10==2 || model.getAge()%10==3 || model.getAge()%10==4)
            age = "года";
        else
            age = "лет";
        holder.NoteDescription.setText(model.getAge()+" "+age);

        Glide
                .with(holder.circleImageView.getContext())
                .load(R.drawable.olymicpng)
                .error(R.drawable.olymicpng)
                .into(holder.circleImageView);





        ArrayList<ImageView> images=new ArrayList<>();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent intentPut = new Intent(context,InfoProfileActivity.class);
                intentPut.putExtra("profile_id", model.getId());
                intentPut.putExtra("event_id", event_id);
                intentPut.putExtra("access", access);
                context.startActivity(intentPut);
                */

            }
        });


        holder.inviteBtn.setOnClickListener(new View.OnClickListener() {
            String vacancy="Участник";
                                                @Override
                                                public void onClick(View view) {
                                                    final String[] catNamesArray = {"Участник", "Работник", "Судья","Зритель"};
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                                    builder.setTitle("Выберите роль для приглашения")
                                                            // добавляем переключатели
                                                            .setSingleChoiceItems(catNamesArray, -1,
                                                                    new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog,
                                                                                            int item) {
                                                                            vacancy = catNamesArray[item];
                                                                        }
                                                                    })
                                                            .setPositiveButton("Пригласить", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    InviteResponsePost inviteResponse = new InviteResponsePost(0,model.getId(),event_id,vacancy);
                                                                    Call<InviteResponsePost> invite = ApiClient.inviteServicePost().postInvite(inviteResponse);
                                                                    invite.enqueue(new Callback<InviteResponsePost>() {
                                                                        @Override
                                                                        public void onResponse(Call<InviteResponsePost> call, Response<InviteResponsePost> response) {
                                                                            if(response.isSuccessful()){
                                                                                Toast.makeText(view.getContext(),"Приглашение успешно отправлено",Toast.LENGTH_SHORT).show();
                                                                               binding.inviteBtn.setBackgroundColor(R.color.material_dynamic_primary99);
                                                                            }
                                                                            else {
                                                                                Log.e("eror", response.errorBody().toString());
                                                                                if(response.code()==500)
                                                                                    Toast.makeText(view.getContext(),"Пользователь уже приглашен на данное мероприятие",Toast.LENGTH_SHORT).show();

                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onFailure(Call<InviteResponsePost> call, Throwable t) {

                                                                        }
                                                                    });

                                                                }
                                                            })
                                                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int id) {

                                                                }
                                                            });
                                                    builder.show();

                                                }
                                            });








        //Кнопка Open нажатие


    }



    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    /*
    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new FilterNote(filterList,this);
        }
        return filter;
    }
     */

    class HolderOneProfile extends RecyclerView.ViewHolder{
       ImageView circleImageView;
        TextView NoteDescription,NoteDate,NoteAssesment;
        CardView cardView;
        Button inviteBtn;
        //ImageButton inviteBtn,buttonNo;
        public HolderOneProfile( View itemView) {
            super(itemView);
            circleImageView=binding.profileimage;
            NoteAssesment=binding.assesment;
            NoteDate=binding.dataCondition;
            NoteDescription=binding.descriptionCondition;
            inviteBtn=binding.inviteBtn;
            cardView = binding.cardView;
        }
    }

    public  interface  OnNoteListener{
        public void onNoteListener(Intent intent);
    }

    public static Drawable tintIcon(Context context,  Drawable icon, int color) {
        icon = DrawableCompat.wrap(icon).mutate();
        DrawableCompat.setTintList(icon, ContextCompat.getColorStateList(context, color));
        DrawableCompat.setTintMode(icon, PorterDuff.Mode.SRC_IN);
        return icon;
    }

}

