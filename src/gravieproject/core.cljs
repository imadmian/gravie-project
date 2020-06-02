(ns gravieproject.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :as rf]
            [gravieproject.db]
            [gravieproject.events]
            [gravieproject.subs]
            [gravieproject.helpers :as help]
            [gravieproject.components.header :refer [header]]
            [gravieproject.views.home :as home]
            [gravieproject.views.search :as search]))

(enable-console-print!)

(defn pages [page-name]
  (case page-name
    :home [home/main]
    :search [search/main]
    [home/main]))

(defn main []
  (let [active-nav @(rf/subscribe [:active-nav])
        records @(rf/subscribe [:records])]
    (when (empty? records)
      (rf/dispatch [:initialize-db])
      (help/search-game "game" :load-data))

    [:div.container
     [header]
     [pages active-nav]]))

(reagent/render-component [main]
                          (. js/document (getElementById "app")))


(defn on-js-reload [])