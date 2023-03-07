package com.furkansahan.soundrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsAdapter.MyViewHolder> {
    private final Context context;
    private final List<RecordingsList> list;


    public RecordingsAdapter(Context context, List<RecordingsList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecordingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recordings_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingsAdapter.MyViewHolder holder, int position) {
        RecordingsList list2=list.get(position);

        holder.recordingTitle.setText(list2.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView recordingTitle;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recordingTitle=itemView.findViewById(R.id.recordingTitle);
        }
    }
}
