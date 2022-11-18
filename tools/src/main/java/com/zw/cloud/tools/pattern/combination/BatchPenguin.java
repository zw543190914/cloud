package com.zw.cloud.tools.pattern.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * 组合模式的好处，就是我们不用关系每个对象里面的子成员，
 * 只要我们把子对象add()进去后，调用父节点的beating()操作后，
 * 会执行子成员，以及子成员包括下面所有子节点的beating()操作，形成一个递归操作。
 *
 * 组合模式在菜单、文件、文件夹的管理上用的非常多
 * 购物车 --> 包含 地址，支付方式，商品，物流， 商品又包含 店铺，优惠劵
 */
public class BatchPenguin extends Penguin {
    private List<Penguin> penguinList = new ArrayList<>();

    public BatchPenguin(String name) {
        super(name);
    }

    @Override
    public void beating() {
        System.out.println(this.name + "打豆豆");
        penguinList.forEach(Penguin::beating);
    }

    @Override
    public void add(Penguin p) {
        penguinList.add(p);
    }

    @Override
    public void remove(Penguin p) {
        penguinList.remove(p);
    }

    @Override
    public Penguin getChild(int i) {
        return penguinList.get(i);
    }

    @Override
    public List<Penguin> getChilds() {
        return penguinList;
    }

    public static void main(String[] args) {
        BatchPenguin grandFatherPenguin = new BatchPenguin("grandFatherPenguin");
        BatchPenguin fatherPenguin = new BatchPenguin("fatherPenguin");
        BatchPenguin motherFatherPenguin = new BatchPenguin("motherFatherPenguin");
        BatchPenguin childrenPenguin1 = new BatchPenguin("childrenPenguin1");
        BatchPenguin childrenPenguin2 = new BatchPenguin("childrenPenguin2");
        BatchPenguin childrenPenguin3 = new BatchPenguin("childrenPenguin3");
        BatchPenguin childrenPenguin4 = new BatchPenguin("childrenPenguin4");
        grandFatherPenguin.add(fatherPenguin);
        grandFatherPenguin.add(motherFatherPenguin);

        fatherPenguin.add(childrenPenguin1);
        fatherPenguin.add(childrenPenguin2);

        motherFatherPenguin.add(childrenPenguin3);
        motherFatherPenguin.add(childrenPenguin4);

        grandFatherPenguin.beating();
    }

}
