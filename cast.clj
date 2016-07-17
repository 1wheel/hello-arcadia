(+ 10 10)

(use 'arcadia.core)

(create-primitive :sphere)

;; not working?
(object-named "sphere")


(def our-sphere
  (create-primitive :sphere))


our-sphere

(object-named "Sphere")

(objects-named "Sphere")

;;print out position 
;;what do double dots do? like sphere.transform.position
(.. our-sphere transform position)

;;do syntax is talking directly to CLR, the host platform
;;
(. our-sphere transform)

;;cmpt define in arcadia core, gets things out of unity objects
(cmpt our-sphere UnityEngine.Transform)

(def sphere-transform
  (cmpt our-sphere UnityEngine.Transform))

(type sphere-transform)

(.. sphere-transform position)

;;"set bang" sets properties
(set! (.. sphere-transform position)
  (UnityEngine.Vector3. 0 2 0))

(use 'arcadia.linear)

(set! (.. sphere-transform position)
  (v3 0 2 0))


(set! (.. sphere-transform localScale)
  (v3 3))


(defn move-sphere [obj]
  (set! (.. obj transform position)
    (v3*
      (v3
        (* 1 Mathf/Cos Time/time)
        (* 1 (Mathf/Sin Time/time))
        0)
      1)))

(defn move-sphere [obj]
  (set! (.. obj transform position)
    (v3*
      (v3
        (Mathf/Cos (* 10 Time/time))
        (Mathf/Sin Time/time)
        0)
      1)))


(hook+ our-sphere :update move-sphere)


(hook+ our-sphere :update #'move-sphere)



(hook- our-sphere :update)


(+ 10 1)

;;(cmpt our-sphere UnityEngine.Transform)
;;(cmpt our-sphere Transform)


(def floor (create-primitive :cube))

(set! (.. floor transform localScale)
  (v3 20 1 20))

(set! (.. floor transform position)
  (v3 0 -5 0))



(cmpt+ our-sphere UnityEngine.Rigidbody)



(map (fn [y] (y)
  (range 100)))


(map inc [1 2 3 4 5])

(map (fn [y] y) [1 2 3 4 5])

(map (fn [y] (+ 10 y)) [1 2 3 4 5])

(def positions (map (fn [y](
  (v3 0 (* y 10) 0))
  (range 100))))


(def sphere-pile
  (doall
    (map (fn [pos]
      (let [obj (create-primitive
        :sphere)]
      ))))

(dorun
  (map-index
    (fn [i obj]
      (set! (.. obj transform position)
        (v3 (Mathf/Cos i) 0 (Mathf/Sin i))))))




(defn pulse-throb [obj]
  (set! (.. obj transform localScale)
    (v3 (+ 10 (Mathf/Sin Time/time)))))

  
(def sphere-pile-2
  (doall
    (for [y (range 1000)]
      (let [obj (create-primitive :sphere)]
        (set! (.. obj transform position)
          (v3 (Mathf/Cos y) y (Mathf/Sin y)))
        (cmpt+ obj UnityEngine.Rigidbody)
        (hook+ obj :update #'pulse-throb)
        obj))))

(dorun (map destroy sphere-pile))
