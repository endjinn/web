(ns apply-for-something.responses
  (:use [hbs.core]
        [hbs.helper]
        [ring.util.response])) 

(set-template-path! "/templates" "")
(register-js-helpers! "resources/templates/helpers.js")

(defn stage-class [stage current-stage]
  (if (= stage current-stage)
    "selected"
    ""))


(defhelper progress-stage [ctx options]
  (let [stage ctx
        current-stage (.get options "current-stage")
        stage-context {:stageClass (stage-class stage current-stage)
                       :stageText (param options 0)}]
    (safe-str (render-file "progress-stage.html" stage-context))))


(defn render-html-template-to-response
  ([template] (render-html-template-to-response template {}))
  ([template model]   
   (let [data (merge model {:current-stage template})]
     (-> (response (render-file template data))
         (content-type "text/html")))))

(defn redirect-to-next-section [section application-id]
  (redirect-after-post (format "/application-forms/%s/%s" application-id section)))
