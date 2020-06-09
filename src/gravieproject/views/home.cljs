(ns gravieproject.views.home
  (:require [re-frame.core :as rf]))

(defn total [orders items]
  (->> @orders
       (map (fn [[id quant]]
              (* quant 10)))
       (reduce +)))

(defn orders []
  (let [orders (rf/subscribe [:orders])
        records @(rf/subscribe [:records])]
    [:aside
     (if (empty? @orders)
       [:div.empty
        [:div.title "You don't have any orders"]
        [:div.subtitle "Click on a + to add an order"]]

       [:div.order
        [:div.body
         (for [[id quant] @orders]
           [:div.item {:key id}
            [:div.img
             [:img {:src (get-in records [id :image :tiny_url])
                    :alt (get-in records [id :name])}]]
            [:div.content
             [:p.title (str (get-in records [id :name]) " \u00D7 " quant)]]
            [:div.action
             [:div.price (str "$" (* 10 quant))]
             [:button.btn.btn--link.tooltip
              {:data-tooltip "Remove"
               :on-click #(rf/dispatch [:remove-order id])}
              [:i.icon.icon--cross]]]])]
        [:div.total
         [:hr]
         [:div.item
          [:div.content "Total"]
          [:div.action
           [:div.price (str "$" (total orders records))]]
          [:button.btn.btn--link.tooltip
           {:data-tooltip "Remove All"
            :on-click #(rf/dispatch [:remove-all-orders])}
           [:i.icon.icon--delete]]]]])]))

(defn items [records]
  [:main
   [:div.gigs
    (for [{:keys [id image name date_added deck]} (vals records)]
      [:div.gig {:key id}
       [:img.gig__artwork {:src (some-> image :screen_url) :alt name}]
       [:div.gig__body
        [:div.gig__title
         [:div.btn.btn--primary.float--right.tooltip
          {:data-tooltip "Add to rent"
           :on-click #(rf/dispatch [:add-order id])}
          [:i.icon.icon--plus]] name]
        [:p.gig__price date_added]
        [:p.gig__desc deck]]])]])

(defn main []
  (let [records @(rf/subscribe [:records])]
    [:<>
     [items records]
     [orders]]))