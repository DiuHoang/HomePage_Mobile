package com.example.bakeryproject_diu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> mBooks;

    public void setData(List<Book> list){
        this.mBooks = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        if(book == null){
            return;
        }
        holder.imgView.setImageResource(book.getResourceId());
        holder.textListView.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mBooks != null){
            return mBooks.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgView;
        private TextView textListView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgItem);
            textListView = itemView.findViewById(R.id.textItem);
        }
    }
}
