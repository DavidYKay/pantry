(ns pantry-server.recipes.parser
  (:refer-clojure :exclude [char])
  (:use [the.parsatron])

  (:require [clojure.string :as string])
  )

(defparser whitespace []
  (many1 (token #{\space \newline \tab})))

(defparser optional-whitespace []
  (many (token #{\space \newline \tab})))

(defparser word []
  (many1 (letter)))

(defparser words []
  (let->> [raw-words (many1
                       (>>
                         (optional-whitespace)
                         (word)
                         ;(optional-eof)
                         ))]
    (always raw-words)))

(defparser unit-parser []
  (word))

(defparser ingredient []
  (words))

(defparser optional-ingredient-parser []
  (either
    (ingredient)
    (eof)))

(defparser master-parser []
  (let->> [
           ;_ (optional-whitespace)
           quantity (many (digit))
           _ (optional-whitespace)
           unit (unit-parser)
           _ (optional-whitespace)
           optional-ingredient (optional-ingredient-parser)
           _ (eof)
           ]
    (let [unit (apply str unit)
          quantity (read-string (apply str quantity))]
      (if (nil? optional-ingredient)
        (always {:unit unit
                 :quantity quantity})
        (let [
              ingredients (map #(apply str %) optional-ingredient)
              ingredient (string/join " " ingredients)
              ]
          ; (println "ingredient: " ingredient)
          (always {:unit unit
                   :quantity quantity
                   :ingredient ingredient}))))))

(defparser word-token []
  (between
    (whitespace)
    (eof)
    (many (choice
            (letter)
            (digit)
            (whitespace)))))

(defparser word-strip []
  (let->> [
           captured (word)
           _ (either
               (whitespace)
               (eof))
           ]
    (always (apply str captured))
    ))

(defparser new-words []
  (many1
    (word-strip)))

(defn word-parse [input]
  (run (new-words)
       (clojure.string/trim input)
       ))

(defn parse-string [input]
  (run (master-parser)
       (clojure.string/trim input)
       ))
