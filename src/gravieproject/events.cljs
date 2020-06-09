(ns gravieproject.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-db
  :set-active-nav
  (fn [db [_ active-nav]]
    (assoc-in db [:nav :active-nav] active-nav)))

(reg-event-db
  :add-order
  (fn [db [_ id]]
    (update-in db [:orders id] inc)))

(reg-event-db
  :remove-order
  (fn [db [_ id]]
    (update-in db [:orders] dissoc id)))

(reg-event-db
  :remove-all-orders
  (fn [db _]
    (update-in db [:orders] {})))

(reg-event-db
  :load-data
  (fn [db [_ data]]
    (assoc-in db [:records] data)))

(reg-event-db
  :record-search
  (fn [db [_ data]]
    (-> db
        (assoc-in [:record-search] data)
        (assoc-in [:records] (merge (db :records) data)))))