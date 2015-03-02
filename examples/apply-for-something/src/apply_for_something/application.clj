(ns apply-for-something.application)

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn create-application []
  {:id (uuid)})
