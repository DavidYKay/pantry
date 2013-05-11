(ns test.pantry-server.util.conversions
  (:use [pantry-server.util.conversions]
        [midje.sweet]))

(fact
  (+ 1 1) => 2
  (+ 1 2) => 3
  )


(unfinished
  )

(fact
  "it should be able to parse raw units"

  (parse-string "1 quart") =>
    {:unit "quart"
     :quantity 1
     }

  (parse-string "1 cup") =>
    {:unit "cup"
     :quantity 1}

  (parse-string "2 cup") =>
    {:unit "cup"
     :quantity 2}
  )

(fact
  "it should be able to parse units with food"

  ;(parse-string "1 cup chicken soup") =>
    ;{:unit "cup"
     ;:quantity 2}

  )

(fact "word should not match whitespace"
      ;(word-parse "cup soup ") => '( (\c \u \p) (\s \o \u \p))
      (word-parse "cup soup ") => '("cup" "soup")
      (word-parse " cup soup ") => '("cup" "soup")
      (word-parse " cup soup") => '("cup" "soup")
      )

;(fact "word parser should allow for whitespace"
      ;(word-parse "cup soup") => ["cup" "soup"]

      ;)

(fact "new parser should work"

  (parse-string "1 cup") => {:unit "cup"
                             :quantity 1}
  (parse-string "  1 cup") => {:unit "cup"
                               :quantity 1}
  (parse-string "1 cup  ") => {:unit "cup"
                               :quantity 1}
  (parse-string "  1 cup  ") => {:unit "cup"
                                 :quantity 1}
  (parse-string "1 cup soup") => {:unit "cup"
                                  :quantity 1
                                  :ingredient "soup"}
  (parse-string "  1 cup soup") => {:unit "cup"
                                    :quantity 1
                                    :ingredient "soup"}

  (parse-string "1 cup soup  ") => {:unit "cup"
                                    :quantity 1
                                    :ingredient "soup"}

  (parse-string "  1 cup soup  ") => {:unit "cup"
                                      :quantity 1
                                      :ingredient "soup"}

  (parse-string "1 cup mushroom soup") => {:unit "cup"
                                           :quantity 1
                                           :ingredient "mushroom soup"}

  )
