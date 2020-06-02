(ns gravieproject.components.nav
  (:require [re-frame.core :as rf]))

(defn nav []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :home
                    :name "Home"
                    :href "#home"}
                   {:id :search
                    :name "Search"
                    :href "#search"}]]
    [:<>
     (for [{:keys [id name href dispatch]} nav-items]
       ^{:key id} [:a {:href href
                       :style {:padding-bottom "7px"
                               :border-bottom (when (= active-nav id)
                                                "2px solid #102143")}

                       :on-click #(rf/dispatch [:set-active-nav id])}
                   name])]))