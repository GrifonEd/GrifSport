package com.example.grifsport;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.grifsport.databinding.ActivityOneInviteBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterInvite extends  RecyclerView.Adapter<AdapterInvite.HolderOneInvite> implements Filterable  {

    //private OnNoteListener listener;
    private OnInviteListener listener;
    private int profile_id;
    private String access;
    private Context context;
    public java.util.ArrayList<InviteResponse> ArrayList,filterList;
    private ActivityOneInviteBinding binding;
    java.util.ArrayList <Integer> ListId = new ArrayList<>();
   // private  FilterNote filter;

    //private  FilterPdfUser filter;
    //фрагмент одной кв.
    public AdapterInvite(Context context, java.util.ArrayList<InviteResponse> pdfArrayList, String access, int profile_id) {
        this.context = context;
        this.ArrayList = pdfArrayList;
        this.access=access;
        this.profile_id=profile_id;
        try {
           this.listener = ((AdapterInvite.OnInviteListener)context);
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }
        this.filterList =ArrayList;
    }


    @Override
    public HolderOneInvite onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = ActivityOneInviteBinding.inflate(LayoutInflater.from(context),parent,false);

        this.context=parent.getContext();
        return new HolderOneInvite(binding.getRoot());
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
    public void onBindViewHolder( HolderOneInvite holder, int position) {
        //из листа всех квартир берем один конкретный экземпляр


        InviteResponse model = ArrayList.get(position);
        //заполнение переменных каждого экз-ра на форме



        String date = model.getEvent().getEvent_start_date();
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





        String vacancy="";

        if(model.getVacancy().equals("Судья"))
            vacancy="судьи.";
        else if(model.getVacancy().equals("Участник"))
            vacancy="участника.";
        else if(model.getVacancy().equals("Зритель"))
            vacancy="зрителя.";
        else if(model.getVacancy().equals("Работник"))
            vacancy="работника.";
        holder.profileinvite.setText(model.getProfile().getSecond_name()+" "+model.getProfile().getFirst_name()+", приглашаем принять участие в нашем мероприятии в роли "+vacancy);
        holder.NoteDescription.setText(model.getEvent().getDescription());
        holder.NoteDate.setText(day+" "+month+" "+ year+"       "+hour+":"+mins);
        holder.NoteAssesment.setText(model.getEvent().getName());

        Glide
                .with(holder.circleImageView.getContext())
                .load(model.getEvent().getImage())
                .error(R.drawable.olymicpng)
                .into(holder.circleImageView);





        ArrayList<ImageView> images=new ArrayList<>();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*
                Intent intentPut = new Intent(context,InfoEventForApplicationActivity.class);
                intentPut.putExtra("event_id", model.getId());
                intentPut.putExtra("profile_id", profile_id);
                intentPut.putExtra("access", access);
                context.startActivity(intentPut);

                */
            }
        });





        holder.buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(model.getVacancy().equals("Судья")) {
                   Call<JudgeForEventResponse> newjudge = ApiClient.judgeForEventServicePost().judgeForEventServicePost(model.getEvent().getId(),model.getProfile().getId());
                   newjudge.enqueue(new Callback<JudgeForEventResponse>() {
                       @Override
                       public void onResponse(Call<JudgeForEventResponse> call, Response<JudgeForEventResponse> response) {
                           if(response.isSuccessful()){
                               Toast.makeText(context,"Вы успешно зарегистрированы на мероприятие в роли судьи!",Toast.LENGTH_SHORT).show();
                               Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                               callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                   @Override
                                   public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                       if(response.isSuccessful()){
                                           Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                           Intent intent2 = new Intent();
                                           listener.onInviteListener(intent2);
                                       }
                                   }

                                   @Override
                                   public void onFailure(Call<InviteResponse> call, Throwable t) {

                                   }
                               });
                           }
                           else{
                               if(response.code()==500){
                                   Toast.makeText(context,"Вы уже были зарегистрированы на данное мероприятие!",Toast.LENGTH_SHORT).show();
                                   Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                   callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                       @Override
                                       public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                           if(response.isSuccessful()){
                                               Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                               Intent intent2 = new Intent();
                                               listener.onInviteListener(intent2);
                                           }
                                       }

                                       @Override
                                       public void onFailure(Call<InviteResponse> call, Throwable t) {

                                       }
                                   });
                               }
                           }
                       }

                       @Override
                       public void onFailure(Call<JudgeForEventResponse> call, Throwable t) {

                       }
                   });
                }
                else if(model.getVacancy().equals("Участник")){
                    Call<ParticipantForEventResponse> newParticipant = ApiClient.participantForEventServicePost().ParticipantForEventServicePost(model.getEvent().getId(),model.getProfile().getId());
                    newParticipant.enqueue(new Callback<ParticipantForEventResponse>() {
                        @Override
                        public void onResponse(Call<ParticipantForEventResponse> call, Response<ParticipantForEventResponse> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(context,"Вы успешно зарегистрированы на мероприятие в роли участника!",Toast.LENGTH_SHORT).show();
                                Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                    @Override
                                    public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                        if(response.isSuccessful()){
                                            Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                            Intent intent2 = new Intent();
                                            listener.onInviteListener(intent2);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<InviteResponse> call, Throwable t) {

                                    }
                                });
                            }
                            else{
                                if(response.code()==500){
                                    Toast.makeText(context,"Вы уже были зарегистрированы на данное мероприятие!",Toast.LENGTH_SHORT).show();
                                    Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                    callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                        @Override
                                        public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                            if(response.isSuccessful()){
                                                Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                                Intent intent2 = new Intent();
                                                listener.onInviteListener(intent2);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<InviteResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ParticipantForEventResponse> call, Throwable t) {

                        }
                    });
                }
                else if(model.getVacancy().equals("Зритель")) {
                    Call<ViewerForEventResponse> newViewer = ApiClient.viewerForEventServicePost().viewerForEventServicePost(model.getEvent().getId(),model.getProfile().getId());
                    newViewer.enqueue(new Callback<ViewerForEventResponse>() {
                        @Override
                        public void onResponse(Call<ViewerForEventResponse> call, Response<ViewerForEventResponse> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(context,"Вы успешно зарегистрированы на мероприятие в роли зрителя!",Toast.LENGTH_SHORT).show();
                                Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                    @Override
                                    public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                        if(response.isSuccessful()){
                                            Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                            Intent intent2 = new Intent();
                                            listener.onInviteListener(intent2);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<InviteResponse> call, Throwable t) {

                                    }
                                });
                            }
                            else{
                                if(response.code()==500){
                                    Toast.makeText(context,"Вы уже были зарегистрированы на данное мероприятие!",Toast.LENGTH_SHORT).show();
                                    Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                    callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                        @Override
                                        public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                            if(response.isSuccessful()){
                                                Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                                Intent intent2 = new Intent();
                                                listener.onInviteListener(intent2);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<InviteResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ViewerForEventResponse> call, Throwable t) {

                        }
                    });
                }
                else if(model.getVacancy().equals("Работник")){
                    Call<WorkerForEventResponse> newWorker = ApiClient.workerForEventServicePost().WorkerForEventServicePost(model.getEvent().getId(),model.getProfile().getId());
                    newWorker.enqueue(new Callback<WorkerForEventResponse>() {
                        @Override
                        public void onResponse(Call<WorkerForEventResponse> call, Response<WorkerForEventResponse> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(context,"Вы успешно зарегистрированы на мероприятие в роли работника!",Toast.LENGTH_SHORT).show();
                                Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                    @Override
                                    public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                        if(response.isSuccessful()){
                                            Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                            Intent intent2 = new Intent();
                                            listener.onInviteListener(intent2);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<InviteResponse> call, Throwable t) {

                                    }
                                });
                            }
                            else{
                                if(response.code()==500){
                                    Toast.makeText(context,"Вы уже были зарегистрированы на данное мероприятие!",Toast.LENGTH_SHORT).show();
                                    Call <InviteResponse> callDeleteInvite = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                                    callDeleteInvite.enqueue(new Callback<InviteResponse>() {
                                        @Override
                                        public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                                            if(response.isSuccessful()){
                                                Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                                                Intent intent2 = new Intent();
                                                listener.onInviteListener(intent2);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<InviteResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<WorkerForEventResponse> call, Throwable t) {

                        }
                    });
                }

            }
        });

        holder.buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call <InviteResponse> call = ApiClient.inviteServiceDelete().InviteServiceDelete(model.getId());
                call.enqueue(new Callback<InviteResponse>() {
                    @Override
                    public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context,"Приглашение успешно удалено!",Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent();
                            listener.onInviteListener(intent2);
                        }
                    }

                    @Override
                    public void onFailure(Call<InviteResponse> call, Throwable t) {

                    }
                });
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

    class HolderOneInvite extends RecyclerView.ViewHolder{
       ImageView circleImageView;
        TextView NoteDescription,NoteDate,NoteAssesment,profileinvite;
        CardView cardView;
        ImageButton buttonOk,buttonNo;
        public HolderOneInvite( View itemView) {
            super(itemView);
            circleImageView=binding.profileimage;
            NoteAssesment=binding.assesment;
            NoteDate=binding.dataCondition;
            NoteDescription=binding.descriptionCondition;
            buttonNo=binding.buttonOtkaz;
            buttonOk=binding.buttonApply;
            cardView = binding.cardView;
            profileinvite=binding.startText;
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

    public  interface  OnInviteListener{
        public void onInviteListener(Intent intent);
    }

}

