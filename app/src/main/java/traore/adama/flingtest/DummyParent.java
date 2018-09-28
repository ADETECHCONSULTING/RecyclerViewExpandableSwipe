package traore.adama.flingtest;

import java.util.List;

public class DummyParent {
    public String parentName;
    public List<DummyChild> childItems;
    public boolean isOpened = false;

    public DummyParent(String parentName) {
        this.parentName = parentName;
    }
}
