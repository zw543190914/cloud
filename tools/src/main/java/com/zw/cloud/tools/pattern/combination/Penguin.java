package com.zw.cloud.tools.pattern.combination;

import java.util.List;

public abstract class Penguin {
    protected String name;

    public Penguin(String name) {
        this.name = name;
    }

    public abstract void beating();

    public void add(Penguin p) {
        throw new UnsupportedOperationException();
    }
    public void remove(Penguin p) {
        throw new UnsupportedOperationException();
    }
    public Penguin getChild(int i) {
        throw new UnsupportedOperationException();
    }
    public List<Penguin> getChilds() {
        throw new UnsupportedOperationException();
    }

}
