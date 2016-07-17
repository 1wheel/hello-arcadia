(if true
  1
  0)


(use 'aracadia.core)

(def our-sphere
  (create-primitive :sphere))

(object-named "Sphere")

(.. our-sphere transform position)

(def sphere-transform
  (cmpt our-sphere UnityEngine.Transform))

our-sphere.transform.position

(use 'arcadia.linear)

(set! (.. sphere-transform position)
  (UnityEngine.Vector3. 0 2 0))

(set! (.. sphere-transform position)
  (v3 0))

(set! (.. sphere-transform localScale)
  (v3 10))

(hook+ our-sphere :update
  (fn [obj]
    (set! (.. obj transform localScale)
      (v3 (* 10 (Mathf/Cos Time/time))))))


(hook+ our-sphere :update
  (fn [obj]
    (set! (.. obj transform position)
      (v3*
        (v3
          (Mathf/Cos Time/time)
          (Mathf/Sin Time/time)
          0)
        4))))

(defn move-circle [obj]
  (set! (.. obj transform position)
    (v3*
      (v3
        (Mathf/Cos Time/time)
        (Mathf/Sin Time/time)
        0)
      3)))

(hook+ our-sphere :update move-circle)

(hook+ our-sphere :update #'move-circle)

(import 'UnityEngine.Transform)

(cmpt our-sphere Transform)

(def floor (create-primitive :cube))

(set! (.. floor transform localScale)
  (v3 2000 1 2000))

(cmpt+ our-sphere UnityEngine.Rigidbody)

(set! (.. our-sphere transform position)
  (v3 0 5 0))

(set! (.. floor transform position)
  (v3 0 -5 0 ))

(hook- our-sphere :update)

(def sphere-pile
  (doall
    (map (fn [pos]
           (let [obj (create-primitive
                       :sphere)]
             (set! (.. obj
                     transform
                     position)
               pos)
             obj))
      (map (fn [y]
             (v3 0 (* y 10) 0))
        (range 100)))))

(dorun
  (map-indexed
    (fn [i obj]
      (let [pos1 (.. obj transform position)]
        (set! (.. obj transform position)
          (v3 (Mathf/Cos i) (.y pos1) (Mathf/Sin i)))))
    sphere-pile))

(dorun
  (map (fn [obj]
         (cmpt+ obj UnityEngine.Rigidbody))
    sphere-pile))

(dorun (map destroy sphere-pile))

(defn pulse-throb [obj]
  (set! (.. obj transform localScale)
    (let [t (* 4 Time/time)]
      (v3*
        (v3
          (Mathf/Sin t)
          1
          (Mathf/Cos t))
        3))))

(dorun (map destroy sphere-pile-2))

(dorun
  (map (fn [obj]
         (set! (.isKinematic (cmpt obj UnityEngine.Rigidbody))
           true))
    sphere-pile-2))

(def sphere-pile-2
  (doall
    (for [y (range 1000)]
      (let [obj (create-primitive :cube)]
        (set! (.. obj transform position)
          (v3 (Mathf/Cos y) y (Mathf/Sin y)))
        (cmpt+ obj UnityEngine.Rigidbody)
        (hook+ obj :update #'pulse-throb)
        obj))))



