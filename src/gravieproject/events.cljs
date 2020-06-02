(ns gravieproject.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
            [ajax.core :as ajax]))

(reg-event-db
  :set-active-nav
  (fn [db [_ active-nav]]
    (assoc-in db [:nav :active-nav] active-nav)))

(reg-event-db
  :add-order
  (fn [db [_ id type]]
    (update-in db [type id] inc)))

(reg-event-db
  :remove-order
  (fn [db [_ id type]]
    (update-in db [type] dissoc id)))

(reg-event-db
  :remove-all-orders
  (fn [db [_ type]]
    (update-in db [type] {})))

(reg-event-db
  :load-data
  (fn [db [_ data]]
    (assoc-in db [:records] data)))

(reg-event-db
  :record-search
  (fn [db [_ data]]
    (assoc-in db [:record-search] data)))