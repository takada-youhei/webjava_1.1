package jp.co.systena.tigerscave.shoppingcartapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.shoppingcartapp.model.Cart;
import jp.co.systena.tigerscave.shoppingcartapp.model.Item;
import jp.co.systena.tigerscave.shoppingcartapp.model.ListForm;
import jp.co.systena.tigerscave.shoppingcartapp.model.ListService;
import jp.co.systena.tigerscave.shoppingcartapp.model.Order;


// ■コントローラークラス
@Controller
public class ShoppingCartController {

  // セッション管理
  @Autowired
  HttpSession session;


  //URLとのマッピング(http://localhost:8080/listのGETメソッドにアクセスされると以下が実行)
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ModelAndView index(ModelAndView mav) {

    // 商品情報
    Map<Integer, Item> itemListMap = getItemListMap();
    mav.addObject("itemList", itemListMap);

    // 注文情報
    Cart cart = getCart();
    mav.addObject("orderList", cart.getOrderList());

    // 合計金額計算
    int totalPrice = 0;
    for (Order order : cart.getOrderList()) {
      if (itemListMap.containsKey(order.getItemid())) {
        totalPrice += order.getNum() * itemListMap.get(order.getItemid()).getPrice();
      }
    }
    mav.addObject("totalPrice", totalPrice);
    mav.setViewName("ShoppingCartView");
    return mav;
  }


  // 注文情報のセッション追加
  private Cart getCart() {
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }
    return cart;
  }


  // 商品情報の追加
  private Map<Integer, Item> getItemListMap() {
    ListService service = new ListService();
    List<Item> itemList = service.getItemList();
    Map<Integer, Item> itemListMap = new HashMap<Integer, Item>();
    for (Item item : itemList) {
      itemListMap.put(item.getItemid(), item);
    }
    return itemListMap;
  }



  //URLとのマッピング(http://localhost:8080/listのPOSTメソッドにアクセスされると以下が実行)
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  private ModelAndView order(ModelAndView mav, @Valid ListForm listForm,
      BindingResult bindingResult, HttpServletRequest request) {

	// 注文情報の最新化
    Cart cart = getCart();


    // エラー処理
    if (bindingResult.getAllErrors().size() > 0) {
      mav.addObject("listForm", listForm);
      Map<Integer, Item> itemListMap = getItemListMap();
      mav.addObject("itemList", itemListMap);
      mav.setViewName("ShoppingCartView");
      return mav;
    }


    // 注文情報の追加
    boolean flag = false;
    // 既にカートに同一商品があれば加算
    for (Order check : cart.getOrderList()) {
      if (check.itemid == listForm.getItemid()) {
    	flag = true;
       	check.setNum(check.num + listForm.getNum());
      }
    }

    // 新規注文であればカートに追加
    if (flag == false) {
      Order order = new Order();
      order.setItemid(listForm.getItemid());
      order.setNum(listForm.getNum());
      cart.add(order);
    }
    session.setAttribute("cart", cart);
    return new ModelAndView("redirect:/list"); // リダイレクト
  }

}