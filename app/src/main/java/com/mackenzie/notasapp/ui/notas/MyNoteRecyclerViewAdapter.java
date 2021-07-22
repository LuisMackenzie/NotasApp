package com.mackenzie.notasapp.ui.notas;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mackenzie.notasapp.R;
import com.mackenzie.notasapp.databinding.FragmentItemBinding;
import com.mackenzie.notasapp.db.entity.NoteEntity;

import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context context;
    private FragmentItemBinding binding;
    // private NoteViewModel noteViewModel;


    public MyNoteRecyclerViewAdapter(List<NoteEntity> mValues, Context context) {
        this.mValues = mValues;
        this.context = context;
        // noteViewModel = new ViewModelProvider((FragmentActivity) this.context).get(NoteViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitulo());
        holder.tvContenido.setText(holder.mItem.getContenido());
        if (holder.mItem.isFavorita()) {
            holder.favorite.setImageResource(R.drawable.ic_favorite_black);
        }

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mItem.setFavorita(true);
                // mValues.notifyAll();
                Toast.makeText(view.getContext(), "Pulsaste el corazon", Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Pulsaste la nota", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setData(List<NoteEntity> noteEntityList) {
        this.mValues = noteEntityList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NoteEntity> newNotes) {
        this.mValues = newNotes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // public final View mView;
        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView favorite;
        public final CardView cardView;
        public NoteEntity mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            // mView = binding.getRoot();
            tvTitulo = binding.tvTitulo;
            tvContenido = binding.tvContent;
            favorite = binding.ivFavorite;
            cardView = binding.cardview;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}