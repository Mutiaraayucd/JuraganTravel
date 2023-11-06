package com.nando.juragantravel.User;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nando.juragantravel.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<User> etList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.etList = userList;
    }

    @Override
    public int getCount() {
        return etList.size();
    }

    @Override
    public Object getItem(int position) {
        return etList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        User user = etList.get(position);

        TextView fullnameTextView = convertView.findViewById(R.id.etFullname);
        TextView usernameTextView = convertView.findViewById(R.id.etUsername);
        TextView emailTextView = convertView.findViewById(R.id.etEmail);
        TextView passwordTextView = convertView.findViewById(R.id.etPassword);
        TextView nohpTextView = convertView.findViewById(R.id.etNohp);
        TextView genderTextView = convertView.findViewById(R.id.etGender);
        TextView agamaTextView = convertView.findViewById(R.id.etAgama);
        TextView tgllahirTextView = convertView.findViewById(R.id.etTgllahir);
        TextView alamatTextView = convertView.findViewById(R.id.etAlamat);


        fullnameTextView.setText("Nama Lengkap: " + user.getUserFullname());
        usernameTextView.setText("Username: " + user.getUserName());
        emailTextView.setText("Email: " + user.getUserEmail());
        passwordTextView.setText("Password: " + user.getUserPassword());
        nohpTextView.setText("Nomer HP: " + user.getUserNohp());
        genderTextView.setText("Jenis Kelamin: " + user.getUserGender());
        agamaTextView.setText("Agama: " + user.getUserAgama());
        tgllahirTextView.setText("Tanggal Lahir: " + user.getUserTgllahir());
        alamatTextView.setText("Alamat: " + user.getUserAlamat());


        return convertView;
    }
}
