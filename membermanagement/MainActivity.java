package com.example.membermanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MemberAdapter adapter;
    private List<Member> memberList;
    private EditText editTextName, editTextDescription;
    private Button btnAdd, btnEdit, btnDel;
    private Member selectedMember;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutthanhvien);
        Init();
        Event();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuqltv, menu);
        return true;
    }

    private void Init() {
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        recyclerView = findViewById(R.id.recyclerView);
        imageViewAvatar = findViewById(R.id.imageView);
    }

    private void Event() {
        memberList = new ArrayList<>();
        adapter = new MemberAdapter(memberList, this, this::onItemClick, this::onItemLongClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        memberList.add(new Member("Lại Khánh Trang", "Group 6", "https://scontent.fhan15-1.fna.fbcdn.net/v/t39.30808-6/428707753_1447942199153155_4577551277855383475_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=5f2048&_nc_ohc=zdoxhLVeYTIQ7kNvgGe9FE_&_nc_ht=scontent.fhan15-1.fna&oh=00_AYAtBHvJ3VNSRtLnEwLfA-olQwFac4np_emRNb-FeqPLEg&oe=66652554"));
        memberList.add(new Member("Phạm Ngọc Hòa", "Group 4", "https://scontent.fhan15-1.fna.fbcdn.net/v/t39.30808-1/444497156_1632249690929469_6508179251288413340_n.jpg?stp=cp0_dst-jpg_p40x40&_nc_cat=109&ccb=1-7&_nc_sid=5f2048&_nc_ohc=lZsdDxXsIpcQ7kNvgFqUrw_&_nc_ht=scontent.fhan15-1.fna&oh=00_AYDgeviUafVmNJenvyCPPVtL0gd43o-eDvs9IWKWN4ImHA&oe=66652B29"));
        memberList.add(new Member("Nguyễn Quỳnh Nga", "Group 6", "https://scontent.fhan15-2.fna.fbcdn.net/v/t1.6435-1/167613204_2869372683351703_8554863364642963687_n.jpg?stp=dst-jpg_p200x200&_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_ohc=j5ginG3Uxn4Q7kNvgGOLZIE&_nc_ht=scontent.fhan15-2.fna&oh=00_AYBiTXnCRoi3n_HHQuDZKddhKvtE8Fa__Mwynf_bdZrZCg&oe=6686CFCF"));
        memberList.add(new Member("Nguyễn Châu Châu", "Group 4", "https://scontent.fhan15-1.fna.fbcdn.net/v/t1.6435-9/110146834_2676948319228698_7582157011425985821_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5f2048&_nc_ohc=6dT5Ady1MaEQ7kNvgEW-YU5&_nc_ht=scontent.fhan15-1.fna&oh=00_AYCMSWkXIMZTtPKxcV7dHgPQhMFyatDL982Owt4Nng9OGQ&oe=6686A934"));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMember();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMember(selectedMember);
            }
        });
    }

    private void addMember() {
        AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
        al.setTitle("Do you want to insert?");

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                String description = editTextDescription.getText().toString();
                if (name.isEmpty() || description.isEmpty()){
                    Toast.makeText(MainActivity.this, "You must input data", Toast.LENGTH_SHORT).show();
                }else {
                    memberList.add(new Member(name, description));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You picked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.create().show();
    }

    private void editMember() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        if (name.trim().length() == 0 && description.trim().length() == 0)
            Toast.makeText(MainActivity.this, "You must input data", Toast.LENGTH_SHORT).show();
        else {
            AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
            al.setTitle("Do you want to update?");
            al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedMember.setName(name);
                    selectedMember.setDescription(description);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
            al.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "You picked No", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            al.create().show();
        }
    }

    private void editMemberLongClick(Member member) {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        if (name.trim().length() == 0 && description.trim().length() == 0)
            Toast.makeText(MainActivity.this, "You must input data", Toast.LENGTH_SHORT).show();
        else {
            AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
            al.setTitle("Do you want to update?");
            al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    member.setName(name);
                    member.setDescription(description);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
            al.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "You picked No", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            al.create().show();
        }
    }

    private void deleteMember(Member member) {
        AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
        al.setTitle("Do you want to delete this member?");

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                memberList.remove(member);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You picked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.create().show();
    }

    private void duplicateMember(Member member) {
        AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
        al.setTitle("Do you want to duplicate this member?");

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                memberList.add(new Member(member.getName(), member.getDescription()));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Duplicate successfully", Toast.LENGTH_SHORT).show();
            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You picked No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        al.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.create().show();
    }

    private void viewThanhVienDetail(Member member) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Member Detail");
        builder.setMessage("Name: " + member.getName() + "\nDescription: " + member.getDescription());
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void onItemClick(Member member) {
        selectedMember = member;
        editTextName.setText(member.getName());
        editTextDescription.setText(member.getDescription());
        Glide.with(this)
                .load(member.getAvatar())
                .into(imageViewAvatar);
    }

    private void onItemLongClick(View view, Member member) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_management, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_manage_add) {
                duplicateMember(member);
                return true;
            } else if (itemId == R.id.menu_manage_edit) {
                editMemberLongClick(member);
                return true;
            } else if (itemId == R.id.menu_manage_delete) {
                deleteMember(member);
                return true;
            } else if (itemId == R.id.menu_manage_detail) {
                viewThanhVienDetail(member);
                return true;
            } else {
                return false;
            }
        });
        popup.show();
    }
}
