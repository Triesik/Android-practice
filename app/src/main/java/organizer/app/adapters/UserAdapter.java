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

    List<Person> PersonList;
    private onClickListener onClickListener;

    public UserAdapter(List<Person> PersonList, onClickListener onClickListener) {
        this.PersonList = PersonList;
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
        holder.name.setText(PersonList.get(position).getName());
        holder.address.setText(PersonList.get(position).getAddress());
        holder.phonenumber.setText(PersonList.get(position).getPhoneNumber());

    }

    public int getItemCount() {
        return PersonList.size();
    }

    public void setNotes(List<Person> personList){
        PersonList = personList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView address;
        TextView phonenumber;
        ImageView icon;
        onClickListener onClickListener;

        ViewHolder(View itemView, onClickListener onClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            address = itemView.findViewById(R.id.tv_address);
            phonenumber = itemView.findViewById(R.id.tv_phonenumber);

            this.onClickListener = onClickListener;

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
