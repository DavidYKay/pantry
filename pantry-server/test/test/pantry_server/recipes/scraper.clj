(ns test.pantry-server.recipes.scraper
  (:require [pantry-server.env :as env])
  (:use [pantry-server.recipes.scraper]
        [midje.sweet]))


(fact
  (+ 1 1) => 2
  (+ 1 2) => 3
  )

 (fact "it can extract ingredients from foodnetwork.com's HTML"
       (let [food-network-recipe (env/janky-text-resource "eggparm.html")]
       ;(let [food-network-recipe "<html> </html>"]
         (scrape-page food-network-recipe) => ["barf"]))
