(use 'overtone.live)

(def kick(sample (freesound-path 2086)))

(kick)

(def metro (metronome 240))

(defn looper [nome sound]
  (let [beat (nome)]
    (at (nome beat) (sound))
    (apply-at (nome (inc beat)) looper nome sound [])))

(looper metro kick)

(stop)
