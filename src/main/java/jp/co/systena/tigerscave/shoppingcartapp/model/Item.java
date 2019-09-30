package jp.co.systena.tigerscave.shoppingcartapp.model;

public class Item {

  // フィールド
  private int itemid;    // 商品ID
  private String name;   // 商品名
  private int price;     // 金額


  // 商品IDのゲッター、セッター
  public int getItemid() {
    return itemid;
  }
  public void setItemid(int itemid) {
    this.itemid = itemid;
  }

  // 商品名のゲッター、セッター
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  // 金額のゲッター、セッター
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

  public Item() {

  }

  // 商品情報の登録
  public Item(int itemid, String name, int price) {
    this.itemid = itemid;
    this.name = name;
    this.price = price;
  }

}