package id.my.adi.laudryadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.adi.laudryadi.R;
import id.my.adi.laudryadi.model.ModelLayanan;

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.LayananViewHolder> {

    private final List<ModelLayanan> list;
    private View.OnClickListener onItemClicked;

    public AdapterLayanan(Context context, List<ModelLayanan> list) {
        this.list = list;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.onItemClicked = itemClickListener;
    }

    @NonNull
    @Override
    public LayananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layanan, parent, false);
        return new LayananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LayananViewHolder holder, int position) {
        ModelLayanan item = list.get(position);
        holder.tvTipe.setText(item.getName());
        holder.tvHarga.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class LayananViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipe, tvHarga;

        public LayananViewHolder(View itemView) {
            super(itemView);
            tvTipe = itemView.findViewById(R.id.tvItemLayTipe);
            tvHarga = itemView.findViewById(R.id.tvItemLayHarga);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClicked);
        }
    }
}