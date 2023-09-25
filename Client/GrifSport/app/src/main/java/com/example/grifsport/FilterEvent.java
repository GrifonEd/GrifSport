package com.example.grifsport;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterEvent extends Filter {

    ArrayList<EventResponse> filterList;

    AdapterEvent adapterNote;

    public FilterEvent(ArrayList<EventResponse> filterList, AdapterEvent adapterNote) {
        this.filterList = filterList;
        this.adapterNote = adapterNote;
    }
    //поиск
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();

        if(charSequence!= null || charSequence.length()>0){
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<EventResponse> filteredModels = new ArrayList<>();
            for(int i = 0;i<filterList.size();i++){
                if(filterList.get(i).getName().toUpperCase().contains(charSequence)){
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;

        }
        else{
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }




    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapterNote.ArrayList = (ArrayList<EventResponse>)filterResults.values;

        adapterNote.notifyDataSetChanged();
    }
}
