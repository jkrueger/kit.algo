(ns kit.algo)

(defn maybe
  "Return x if x == y. false otherwise"
  [x y]
  (if (= x y)
    x
    false))

(defn unless
  "Return false if x == y. x otherwise"
  [x y]
  (if (= x y)
    false
    x))

(defn update-all [m ks f]
  (update-in m ks #(map f %)))
