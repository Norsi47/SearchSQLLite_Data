package onye.Norsi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import onye.Norsi.Model.Friends;
import onye.Norsi.R;

//step 2 added this
class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView name, address, email, phone;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        //id from ui side, all TextView
        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        email = (TextView) itemView.findViewById(R.id.email);
        phone = (TextView) itemView.findViewById(R.id.phone);
    }
}

//step 1
//step 3 extended Recylcer view
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    //made the empty class with variables in here
    private List<Friends> friends;

    //alt ins (constructor)
    //constructor for the variables Context and Friends list
    public SearchAdapter(Context context, List<Friends> friends) {
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.layout_item, parent, false);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        //holder names and positions for variables
        holder.name.setText(friends.get(position).getName());
        holder.address.setText(friends.get(position).getAddress());
        holder.email.setText(friends.get(position).getEmail());
        holder.phone.setText(friends.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
