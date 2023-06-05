package com.nilesh.bhuswami.models;

public class Plots {

   String ids,image1_url,price,title;

   public Plots() {
   }

   public Plots(String ids, String image1_url, String price, String title) {
      this.ids = ids;
      this.image1_url = image1_url;
      this.price = price;
      this.title = title;
   }

   public String getIds() {
      return ids;
   }

   public void setIds(String ids) {
      this.ids = ids;
   }

   public String getImage1_url() {
      return image1_url;
   }

   public void setImage1_url(String image1_url) {
      this.image1_url = image1_url;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
