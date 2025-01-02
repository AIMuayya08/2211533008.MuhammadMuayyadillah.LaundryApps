package id.my.adi.laudryadi.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List; // Correct import for List

import id.my.adi.laudryadi.R;
import id.my.adi.laudryadi.model.ModelPelangganActivity;

public class AdapterPelanggan extends RecyclerView.Adapter<AdapterPelanggan.ViewHolder> {

    private static final String TAG = AdapterPelanggan.class.getSimpleName();
    private Context context;
    private List<ModelPelangganActivity.ModelPelanggan> list;
    private View.OnClickListener onItemClicked;

    // Constructor
    public AdapterPelanggan(Context context, List<ModelPelangganActivity.ModelPelanggan> list) { // Use List here
        this.context = context;
        this.list = list;
    }

    // Set Item Click Listener
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.onItemClicked = itemClickListener;
    }

    // Create ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelanggan, parent, false);
        return new ViewHolder(view);
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPelangganActivity.ModelPelanggan item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvHp.setText(item.getHp());
    }

    // Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }

    // Clear the list
    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHp;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvItemPelangganName); // Ensure IDs match your XML
            tvHp = itemView.findViewById(R.id.tvItemPelangganTelp);   // Ensure IDs match your XML

            // Check if onItemClicked is set before attaching it
            if (onItemClicked != null) {
                itemView.setTag(this);
                itemView.setOnClickListener(onItemClicked);
            }
        }
    }
}
