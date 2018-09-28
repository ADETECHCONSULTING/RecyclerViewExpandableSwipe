package traore.adama.flingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DummyParent> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rcvMain = findViewById(R.id.rcvMain);

        //items
        String[] arrays = getResources().getStringArray(R.array.items);
        list = new ArrayList<>();
        list.addAll(putSomeParent(10));

        //RecyclerView implem
        //FlingAdapter adapter = new FlingAdapter(this);
        DifferentViewTypeAdapter adapter1 = new DifferentViewTypeAdapter(this);
        rcvMain.setAdapter(adapter1);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter1.resetData(list);

        ItemTouchHelper.Callback callback = new SwipeCallback(adapter1);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rcvMain);
    }

    private List<DummyChild> putSomeChild(int qte, DummyParent parent){
        List<DummyChild> children = new ArrayList<>();
        for(int i = 0; i < qte; i++){
            DummyChild temp = new DummyChild(parent,"Child "+i);
            children.add(temp);
        }
        return children;
    }

    private List<DummyParent> putSomeParent(int qte){
        List<DummyParent> parents = new ArrayList<>();
        for(int i = 0; i < qte; i++){
            DummyParent temp = new DummyParent("Parent "+i);
            temp.childItems = putSomeChild(i, temp);
            parents.add(temp);
        }
        return parents;
    }
}
