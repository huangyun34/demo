package com.my.admin.functionalInter;

import java.util.List;
import java.util.Objects;

//接口具体实现类
public class FACI implements FAC {

    private List<Integer> list;

    public FACI(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void hh(FA fa) {
        Objects.requireNonNull(list, "列表不存在");
        for (int i = 0; i < list.size(); i++) {
            fa.say(list.get(i));
        }
        fa.say1();
        FA.say5();
    }
}
