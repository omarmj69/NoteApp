package com.example.noteapp.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;
import com.example.noteapp.NotesClickListener;
import com.example.noteapp.R;

import java.util.List;

public class NotesListAdapter extends  RecyclerView.Adapter<NotesViewHolder>{
    Context context;
    List<Notes> list;
    List<Dependencies> list2;
    List<Agent> list3;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list,List<Dependencies> dependencies,List<Agent> agents, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.list2 = dependencies;
        this.list3 = agents;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        holder.textView_dependencies.setText(list2.get(position).getName());

        holder.textView_agent.setText(list3.get(position).getName());
        holder.textView_descraption.setText(list3.get(position).getDescraption());




        if(list.get(position).isPinned()){
            holder.pin.setImageResource(R.drawable.pin);
        }
        else {
            holder.pin.setImageResource(0);
        }
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCclick(list.get(holder.getAdapterPosition()),list2.get(holder.getAdapterPosition()), list3.get(holder.getAdapterPosition()));
            }

        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> fillterdList){
        list = fillterdList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{
    TextView textView_title, textView_notes, textView_date,textView_dependencies,textView_agent,textView_descraption;
    CardView notes_container;
    ImageView pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_dependencies = itemView.findViewById(R.id.dependencies);
        textView_title = itemView.findViewById(R.id.textview_title);
        textView_notes = itemView.findViewById(R.id.textview_Notes);
        textView_date = itemView.findViewById(R.id.textview_date);
        textView_agent = itemView.findViewById(R.id.agent);
        textView_descraption = itemView.findViewById(R.id.descraption);
        notes_container = itemView.findViewById(R.id.notes_container);
        pin = itemView.findViewById(R.id.pin);
    }
}

