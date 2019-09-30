package jp.co.systena.tigerscave.shoppingcartapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListService {

  public List<Item> getItemList() {


    List<Item> itemList = new ArrayList<Item>();

    Item item = new Item();


    item = new Item(10, "水", 80);
    itemList.add(item);


    item = new Item(20, "お茶", 120);
    itemList.add(item);


    item = new Item(30, "コーヒー", 300);
    itemList.add(item);


    return itemList;
  }
}