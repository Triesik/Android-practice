package organizer.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import organizer.app.R;
import organizer.app.data.data.Person;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<Person> users;
    private onClickListener onClickListener;

    public UserAdapter(List<Person> users, onClickListener onClickListener) {
        this.users = users;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());

    }

    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView icon;
        Button btn_edit;

        onClickListener onClickListener;

        ViewHolder(View itemView, onClickListener onClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            this.onClickListener = onClickListener;

            btn_edit.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            onClickListener.onClick(getAdapterPosition(), itemView);
        }
    }

    public interface onClickListener {
        void onClick(int position, View view);
    }
}
