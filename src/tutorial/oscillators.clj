(use 'overtone.live)
(odoc env-gen)
(odoc env-lin)
(odoc sin-osc)
(odoc lf-pulse)

(definst sin-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 1]
  (* (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
     (sin-osc freq)
     vol)
   )
(sin-wave)


(definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 1]
  (* (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
     (saw freq)
     vol)
  )

(saw-wave)

(definst square-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 1]
  (* (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
     (lf-pulse freq)
     vol)
  ) ; marche pas ...

(square-wave)

(definst noisy [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 1]
  (* (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
     (pink-noise)
     vol)
  )

(noisy)

(definst spooky-house [freq 440 width 0.1
                       attack 0.01 sustain 0.4 release 0.1 vol 1]
  (* (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
     (sin-osc (+ freq (* 20 (lf-pulse:kr 0.5 0 width))))))

(spooky-house)

