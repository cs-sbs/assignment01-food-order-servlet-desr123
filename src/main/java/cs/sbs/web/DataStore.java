package cs.sbs.web;

import cs.sbs.web.model.MenuItem;
import cs.sbs.web.model.Order;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;

    // 模拟菜单数据
    public List<MenuItem> menuList = new ArrayList<>();
    // 模拟订单数据
    public List<Order> orderList = new ArrayList<>();
    // 订单ID自增计数器
    public int orderIdCounter = 1001;

    private DataStore() {
        // 初始化菜单（和作业示例一致）
        menuList.add(new MenuItem("Fried Rice", 8));
        menuList.add(new MenuItem("Fried Noodles", 9));
        menuList.add(new MenuItem("Burger", 10));
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
}