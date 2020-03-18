package com.example.tema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(User user);
    }

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<User> mUsers;
    private OnDeleteClickListener onDeleteClickListener;

    public UserListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (mUsers != null) {
            User user = mUsers.get(position);
            String mark_string = Integer.toString(user.getMark());
            String data = user.getFullName() + mark_string;
            holder.setData(data, position);
            holder.setListeners();
        } else {
            holder.userItemView.setText(R.string.error);
        }

    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        }
        return 0;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userItemView;
        private Button remove_user_btn;
        private int position;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.ivUser);
            remove_user_btn = itemView.findViewById(R.id.remove_user);
        }

        public void setData(String userText, int position) {
            userItemView.setText(userText);
            this.position = position;
        }

        public void setListeners() {
            remove_user_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mUsers.get(position));
                    }
                }
            });
        }
    }
}
