(ns pantry-server.recipes.scraper
  (require [clj-http.client :as client])
  )

(defn get-html [url]
  (:body
     (client/get
          url)))

(defn scrape-page [page-html]
  [])
