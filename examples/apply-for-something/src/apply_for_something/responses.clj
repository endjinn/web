(ns apply-for-something.responses
  (:use [hbs.core]
        [hbs.helper :only [param defhelper safe-str]]
        [ring.util.response])) 

(set-template-path! "/templates" "")
;;(register-js-helpers! "resources/templates/helpers.js")

(defn stage-class [stage current-stage style]
  (let [result (if (= stage current-stage) "active" "")]
      (if nil? style) result (str result " " style)))

(defn stage-number [stage]
  (read-string (first (clojure.string/split stage #"-"))))

(defn get-style [options]
  (let [option-count (count (.params options))]
    (if (> option-count 1)    
      (param options 1)
      nil)))

(defhelper stage [ctx options]
  (let [stage ctx
        current-stage (.get options "current-stage")
        stage-context {:stageClass (stage-class stage current-stage (get-style options))
                       :stageText (param options 0)
                       :stageNumber (stage-number stage)}]
    (safe-str (render-file "progress-stage.html" stage-context))))


(defn render-html-template-to-response
  ([template] (render-html-template-to-response template {}))
  ([template model]   
   (let [data (merge model {:current-stage template})]
     (-> (response (render-file template data))
         (content-type "text/html")))))

(defn redirect-to-next-stage [stage application-id]
  (redirect-after-post (format "/application-forms/%s/%s" application-id stage)))
