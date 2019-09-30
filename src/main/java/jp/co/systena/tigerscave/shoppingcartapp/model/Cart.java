package jp.co.systena.tigerscave.shoppingcartapp.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {


  // 注文リストのインスタンス化
  private List<Order> orderList = new ArrayList<Order>();


  // 注文情報の追加
  public void add(Order order) {
    orderList.add(order);
  }

  // 注文リストの取得
  public List<Order> getOrderList() {
    return this.orderList;
  }
}