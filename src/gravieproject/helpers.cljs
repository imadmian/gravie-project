(ns gravieproject.helpers
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
    [cljs-http.client :as http]
    [re-frame.core :as rf]
    [gravieproject.events]
    [ajax.core :as ajax]
    [reagent.core :as reagent :refer [atom]]
    [cljs.core.async :refer [<! >! chan]]))

(defn search-game [search keyword]
  (go
    (let [uri (str "http://www.giantbomb.com/api/search?api_key=10d1ec726bf9f68253c27cede52d20cc19a240fe&format=jsonp&resource=game&query=" search)
          response (js->clj (<! (http/jsonp uri
                                            {:callback-name "json_callback"
                                             :format "json"
                                             :timeout 3000})))

          result (->> (-> response :body :results)
                      (group-by :id)
                      (map (fn [[item value]]
                             {item (first value)}))
                      (into {}))]
    (rf/dispatch [keyword result]))))