(ns gravieproject.db
  (:require [re-frame.core :as rf]))

(def initial-app-db {:orders {}
                     :records {}
                     :nav {:active-page :home
                           :active-nav :home}})

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    initial-app-db))