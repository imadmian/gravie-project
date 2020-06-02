(ns gravieproject.components.header
  (:require [gravieproject.components.nav :refer [nav]]))

(defn header []
  [:header
   [:img.logo {:src "/img/Gravie-Logo_Navy.png" :width "160px" :height "auto"}]
   [nav]])