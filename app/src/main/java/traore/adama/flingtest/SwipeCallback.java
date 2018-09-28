package traore.adama.flingtest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SwipeCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter adapter;

    public SwipeCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipFlags;
        int dragFlags;
        if(viewHolder instanceof DifferentViewTypeAdapter.ParentViewHolder){
            dragFlags = 0;
            swipFlags = 0;
        }else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

}
