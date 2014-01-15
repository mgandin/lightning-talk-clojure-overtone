(use 'overtone.live)

(def yeah (sample "/Users/gandinmathieu/Desktop/yeah.wav"))

(yeah)

(stop)

(def yeah-buf (load-sample "/Users/gandinmathieu/Desktop/yeah.wav"))

(defsynth reverb-on-left []
  (let [dry (play-buf 1 yeah-buf)
        wet (free-verb dry 1)]
    (out 0 [wet dry])))

(reverb-on-left)
(stop)

(def bass (sample (freesound-path 128306)))

(bass)

(def scary-piano (sample (freesound-path 166748)))
(scary-piano)
(stop)
