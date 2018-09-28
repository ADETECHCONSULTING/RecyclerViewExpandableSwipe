package traore.adama.flingtest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

public class DifferentViewTypeAdapter<T> extends RecyclerView.Adapter implements ItemTouchHelperAdapter {
    private List<T> items;
    private MainActivity activity;

    public DifferentViewTypeAdapter(MainActivity activity) {
        this.activity = activity;
    }

    public void resetData(List<T> items){
        this.items = items;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View pView = LayoutInflater.from(activity).inflate(R.layout.item_fling_test, parent, false);
                final ParentViewHolder pVHolder = new ParentViewHolder(pView);

                pVHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = pVHolder.getAdapterPosition();
                        DummyParent parentItem = ((DummyParent)items.get(position));
                        if(!parentItem.isOpened) {
                            items.addAll(position + 1, (Collection<? extends T>) parentItem.childItems);
                            notifyDataSetChanged();
                            parentItem.isOpened = true;
                        }else{
                            items.removeAll(parentItem.childItems);
                            notifyDataSetChanged();
                            parentItem.isOpened = false;
                        }
                    }
                });
                return pVHolder;

            case 1:
                return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.child_item, parent, false));
            default:
                return new ChildViewHolder(LayoutInflater.from(activity).inflate(R.layout.child_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                ParentViewHolder pViewHolder = (ParentViewHolder)holder;
                DummyParent element = (DummyParent) items.get(position);
                pViewHolder.lblItem.setText(element.parentName);
                break;
            case 1:
                ChildViewHolder cViewHolder = (ChildViewHolder)holder;
                DummyChild childElem = (DummyChild) items.get(position);
                cViewHolder.lblItem.setText(childElem.childName);
                break;
            default:
                //
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(items.get(position) instanceof DummyParent)
            viewType = 0;
        else if(items.get(position) instanceof DummyChild)
            viewType = 1;
        else
            viewType = 2;

        return viewType;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void onItemDismiss(int position) {
        if(items.get(position) instanceof DummyParent)
            return;

        items.remove(position);
        notifyItemRemoved(position);
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder{
        private TextView lblItem;
        private ImageView imgItem;

        public ParentViewHolder(View itemView) {
            super(itemView);
            lblItem = itemView.findViewById(R.id.lblItem);
            imgItem = itemView.findViewById(R.id.imgItem);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        private TextView lblItem;

        public ChildViewHolder(View itemView) {
            super(itemView);
            lblItem = itemView.findViewById(R.id.lblItem);
        }
    }
}
