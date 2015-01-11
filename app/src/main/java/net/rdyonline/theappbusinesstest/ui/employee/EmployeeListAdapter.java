package net.rdyonline.theappbusinesstest.ui.employee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.ui.RoundedTransformation;

import java.util.List;

/**
 * Created by rdy on 11/01/15.
 */
public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {

    private List<Employee> data;
    private OnEmployeeItemClickListener listener;

    private Context context;

    public EmployeeListAdapter(List<Employee> ds, OnEmployeeItemClickListener listener,
                               Context context) {
        data = ds;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_employee, viewGroup, false);

        ViewHolder vh = new ViewHolder(v, listener);
        this.context = viewGroup.getContext();

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setItem(data.get(i), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Employee employee;
        private OnEmployeeItemClickListener listener;

        public ImageView avatar;
        public TextView name;

        public void setItem(Employee item, Context context) {
            employee = item;

            name.setText(employee.getName());
            Picasso.with(context).load(employee.getImage()).transform(new RoundedTransformation())
                    .into(avatar);
        }

        public ViewHolder(View v, OnEmployeeItemClickListener listener) {
            super(v);
            v.setOnClickListener(this);
            this.listener = listener;

            name = (TextView) v.findViewById(R.id.txt_name);
            avatar = (ImageView) v.findViewById(R.id.img_avatar);
        }

        @Override
        public void onClick(View view) {
            listener.employeeSelected(employee);
        }
    }

    public interface OnEmployeeItemClickListener {
        void employeeSelected(Employee employee);
    }

}
