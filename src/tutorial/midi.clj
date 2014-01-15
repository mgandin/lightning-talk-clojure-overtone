(use 'overtone.live)
(use 'overtone.synth.sts)
(use 'overtone.inst.synth)
(use 'overtone.inst.drum)
(use 'overtone.inst.piano)

(connected-midi-devices)

(definst steel-drum [note 60 amp 0.8]
  (let [freq (midicps note)]
    (* amp
       (env-gen (perc 0.01 0.2) 1 1 0 1 :action FREE)
       (+ (sin-osc (/ freq 2))
          (rlpf (saw freq) (* 1.1 freq) 0.4)))))

(steel-drum)


(prophet :freq 110 :decay 5 :rq 0.6 :cutoff-freq 2000)
(prophet :freq 130 :decay 5 :rq 0.6 :cutoff-freq 2000)

(kick)

(on-event [:midi :note-on]
          (fn [e]
            (let [note (:note e)]
              (kick)))
          ::kick-handler)

(remove-event-handler ::kick-handler)


(stop)

(on-event [:midi :note-on]
          (fn [m]
            (let [note (:note m)]
              (prophet :freq (midi->hz note)
                       :decay 5
                       :rq 0.6
                       :cutoff-freq 1000)))
          ::prophet-midi)


(remove-event-handler ::prophet-midi)

(on-event [:midi :note-on]
          (fn [e]
            (let [note (:note e)
                  vel  (:velocity e)]
              (steel-drum note vel)))
          ::keyboard-handler)

(remove-event-handler ::keyboard-handler)

(defsynth pad1 [freq 110 amp 1 gate 1 out-bus 0]
  (out out-bus
       (* (saw [freq (* freq 1.01)])
          (env-gen (adsr 0.01 0.1 0.7 0.5) :gate gate :action FREE))))
(pad1)

(defsynth pad2 [freq 440 amp 0.4 amt 0.3 gate 1.0 out-bus 0]
  (let [vel        (+ 0.5 (* 0.5 amp))
        env        (env-gen (adsr 0.01 0.1 0.7 0.5) gate 1 0 1 FREE)
        f-env      (env-gen (perc 1 3))
        src        (saw [freq (* freq 1.01)])
        signal     (rlpf (* 0.3 src)
                         (+ (* 0.6 freq) (* f-env 2 freq)) 0.2)
        k          (/ (* 2 amt) (- 1 amt))
        distort    (/ (* (+ 1 k) signal) (+ 1 (* k (abs signal))))
        gate       (pulse (* 2 (+ 1 (sin-osc:kr 0.05))))
        compressor (compander distort gate 0.01 1 0.5 0.01 0.01)
        dampener   (+ 1 (* 0.5 (sin-osc:kr 0.5)))
        reverb     (free-verb compressor 0.5 0.5 dampener)
        echo       (comb-n reverb 0.4 0.3 0.5)]
    (out out-bus
         (* vel env echo))))

(pad2)

(stop)

(def pad-s (pad2))

(ctl pad-s :gate 0)

(defonce memory (agent {}))

(on-event [:midi :note-on]
          (fn [m]
            (send memory
                  (fn [mem]
                    (let [n (:note m)
                          s (pad2 :freq (midi->hz n))]
                      (assoc mem n s)))))
          ::play-note)


(on-event [:midi :note-off]
          (fn [m]
            (send memory
                  (fn [mem]
                    (let [n (:note m)]
                      (when-let [s (get mem n)]
                        (ctl s :gate 0))
                      (dissoc mem n))))
)
          ::release-note)

(remove-event-handler ::play-note)
(remove-event-handler ::release-note)

(midi-connected-devices)

(piano)

(on-event [:midi :note-on]
          (fn [e]
            (piano))
          ::piano-handler)

(remove-event-handler ::piano-handler)


