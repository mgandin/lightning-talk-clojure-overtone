(use 'overtone.live)

(use 'overtone.inst.drum)
(use 'overtone.inst.piano)
(use 'overtone.inst.synth)

(definst foo[freq 220] (sin-osc freq))

(foo)

(foo 440)
(foo 660)

(stop)

(definst trem[freq 220]
  (bpf(saw freq) (mouse-x 4 5000 EXP) (mouse-y 1 0.1 LIN)))

(trem)
(stop)

(defsynth synthe[freq 220]
  (out 0 (saw freq))
  (out 1 (sin-osc freq)))

(synthe)
(synthe 440)
(stop)

(def yeah(sample "/Users/gandinmathieu/Desktop/yeah.wav"))
(yeah)
(stop)

(kick)
(midi-connected-devices)
(on-event [:midi :note-on]
          (fn[e]
            (kick))
          :kick-handler)

(remove-event-handler :kick-handler)

