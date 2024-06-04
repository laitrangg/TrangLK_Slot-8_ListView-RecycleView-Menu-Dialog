package com.example.membermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private List<Member> memberList;
    private Context context;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public MemberAdapter(List<Member> memberList, Context context, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        this.memberList = memberList;
        this.context = context;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhvien, parent, false);
        return new MemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.nameTextView.setText(member.getName());
        holder.descriptionTextView.setText(member.getDescription());

        Glide.with(context)
                .load(member.getAvatar())
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.avatarImageView);

        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(member));
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(v, member);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Member member);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, Member member);
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView avatarImageView;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            avatarImageView = itemView.findViewById(R.id.imageViewAvatar);
        }
    }
}
