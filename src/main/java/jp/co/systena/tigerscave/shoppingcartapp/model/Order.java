package jp.co.systena.tigerscave.shoppingcartapp.model;

public class Order {

  // フィールド
  public int itemid;  // 商品ID
  public int num;     // 注文個数


  // 商品IDのゲッター、セッター
  public int getItemid() {
    return itemid;
  }
  public void setItemid(int itemid) {
    this.itemid = itemid;
  }

  // 注文個数のゲッター、セッター
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
}