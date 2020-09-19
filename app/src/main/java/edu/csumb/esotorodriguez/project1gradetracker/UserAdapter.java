package edu.csumb.esotorodriguez.project1gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private OnItemClickListener listener;
    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textViewUsername.setText(currentUser.getUsername());
        holder.textViewPassword.setText(currentUser.getPassword());
        holder.textViewFirstName.setText(currentUser.getFirstName());
        holder.textViewLastName.setText(currentUser.getLastName());
        holder.textViewUserID.setText(String.valueOf(currentUser.getUserID()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public User getUserAt(int position){
        return users.get(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private TextView textViewUsername;
        private TextView textViewPassword;
        private TextView textViewFirstName;
        private TextView textViewLastName;
        private TextView textViewUserID;


        public UserHolder(View itemView) {
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.text_view_username);
            textViewPassword = itemView.findViewById(R.id.text_view_password);
            textViewFirstName = itemView.findViewById(R.id.text_view_fname);
            textViewLastName = itemView.findViewById(R.id.text_view_lname);
            textViewUserID = itemView.findViewById(R.id.text_view_userID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(users.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
