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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grifsport.databinding.ActivityOneEventOrganizerBinding;
import com.example.grifsport.databinding.ActivityOneEventWorkerBinding;

import java.util.ArrayList;

public class AdapterEventWorker extends  RecyclerView.Adapter<AdapterEventWorker.HolderOneNoteWorker> implements Filterable  {

    //private OnNoteListener listener;
    private String access;
    private Context context;
    public java.util.ArrayList<EventResponse> ArrayList,filterList;
    private ActivityOneEventWorkerBinding binding;
    java.util.ArrayList <Integer> ListId = new ArrayList<>();
   // private  FilterNote filter;

    //private  FilterPdfUser filter;
    //фрагмент одной кв.
    public AdapterEventWorker(Context context, java.util.ArrayList<EventResponse> pdfArrayList, String access) {
        this.context = context;
        this.ArrayList = pdfArrayList;
        this.access=access;
        try {
       //     this.listener = ((AdapterEvent.OnNoteListener)context);
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }
        this.filterList =ArrayList;
    }


    @Override
    public HolderOneNoteWorker onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = ActivityOneEventWorkerBinding.inflate(LayoutInflater.from(context),parent,false);

        this.context=parent.getContext();
        return new HolderOneNoteWorker(binding.getRoot());
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
    public void onBindViewHolder( HolderOneNoteWorker holder, int position) {
        //из листа всех квартир берем один конкретный экземпляр


        EventResponse model = ArrayList.get(position);
        //заполнение переменных каждого экз-ра на форме
        ListId.add(model.getId());
        String  assesment = model.getName();
        String description = model.getDescription();
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

        String nastroi="";




        //запись значений в поля textbox
        holder.NoteDescription.setText(description);
        holder.NoteDate.setText(day+" "+month+" "+ year+"       "+hour+":"+mins);
        holder.NoteAssesment.setText(model.getName());
        ArrayList<ImageView> images=new ArrayList<>();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPut = new Intent(context,InfoEventActivity.class);
                intentPut.putExtra("event_id", model.getId());
                intentPut.putExtra("access", access);
                context.startActivity(intentPut);
            }
        });


        Glide
                .with(holder.circleImageView.getContext())
                .load(model.getImage())
                .error(R.drawable.olymicpng)
                .into(holder.circleImageView);









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

    class HolderOneNoteWorker extends RecyclerView.ViewHolder{
        ImageView circleImageView;
        TextView NoteDescription,NoteDate,NoteAssesment;
        CardView cardView;
        public HolderOneNoteWorker( View itemView) {
            super(itemView);
            circleImageView=binding.profileimage;
            NoteAssesment=binding.assesment;
            NoteDate=binding.dataCondition;
            NoteDescription=binding.descriptionCondition;
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

