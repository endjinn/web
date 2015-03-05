(ns apply-for-something.stages)

(defn stage-entry [input, next-stage]
  (let [stage (first input)
        template (second input)
        stage-data (if (nil? next-stage) [template] [template next-stage])]
    [(first input) stage-data]))

(defn index-of [item list]
  (first (keep-indexed #(if (= item %2) %1) list)))

(defn next-item [list item]
  (let [item-idx (index-of item list)
        last-idx (dec (count list))]
    (if (< item-idx last-idx)      
      (get list (inc item-idx)))))

(defn configure-stage [stages-list result stage]
  (let [next-item (first (next-item stages-list stage))] 
    (apply conj result  (stage-entry stage next-item))))

(defn configure-stages [stages-list]
  (let [data (reduce (partial configure-stage stages-list) [] stages-list)]
    (apply array-map data)))

(defn defstages [stages-list]
  (def ^:dynamic *stages* (configure-stages stages-list)))

(defn get-stage-template [stage]
  (first (stage *stages*)))

(defn get-next-stage [stage]
  (name (second (stage *stages*))))
