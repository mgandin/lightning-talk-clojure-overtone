(println "Clojure FTW !")

(+ 1 1)

(* (/ 2 4) (/ 4 2))

(+ 2 2 2 2)

(/ 8 2 2)

(- 8 1 2)

(= 1 1)

(< 1 2)

(< 1 2 3)

(< 1 3 2)

(> 42 41)

(str "the universal response is " 42)

(if (< 1 2)
  (println "1 < 2"))

(= (list 1 2 3) '(1 2 3))

(defn hello [name]
  (str "hello, " name))

(hello "Mathieu")

(def map1 [:one :two :three])

(def map2 [:four :five :six])


(defn first-map [aMap] (first aMap))

(first-map map2)

(let [a 1
      b a] (+ a b))
