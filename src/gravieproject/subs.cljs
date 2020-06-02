(ns gravieproject.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub :active-nav
  (fn [db _]
    (get-in db [:nav :active-nav])))

(reg-sub :item-list
 (fn [db _]
   (:items db)))

(reg-sub :orders
 (fn [db [_ type]]
   (type db)))

(reg-sub :records
         (fn [db _]
           (:records db)))

(reg-sub :record-search
         (fn [db _]
           (:record-search db)))