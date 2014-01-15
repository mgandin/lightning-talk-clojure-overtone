(ns tutorial.core)
(use 'overtone.live)
(use 'overtone.inst.synth)
(use 'overtone.inst.drum)
(use 'overtone.synth.sts)


; Some free samples from freesound.org
(def kickkick (sample (freesound-path 2086)))
(def clapclap (sample (freesound-path 48310)))
(def snaresnare (sample (freesound-path 26903)))
(def water (sample (freesound-path 50623)))
(def bassbass (sample (freesound-path 128306)))
(def scary-piano (sample (freesound-path 166748)))
(def voice (sample (freesound-path 147881)))
(def ghost (sample (freesound-path 180023)))

; Some beats
(def two-hundred-forty-bpm (metronome 240))
(def one-hundred-twenty-bpm (metronome 120))
(def sixty-bpm (metronome 60))
(def thirty-bpm (metronome 30))

; A looper who play a sound at a beat
(defn looper [nome sound]
  (let [beat (nome)]
    (at (nome beat) (sound))
    (apply-at (nome (inc beat)) looper nome sound[])))

; A synth note
(definst one-note[attack 1 sustain 1 release 1 freq 220 vol 1]
  (*
   (env-gen (env-lin attack sustain release) 1 1 0 1 FREE)
   (sin-osc freq)))

; Some noise
(definst crack[] (crackle))

; A strange saw
(definst crazy-saw [freq 220 depth 10 rate 6 length 3]
  (* 0.3
     (line:kr 0 1 length FREE)
     (saw (+ freq (* depth (sin-osc:kr rate))))))

; A little synth with modulation
(defsynth synthe[freq 220]
  (out 0 (bpf (saw freq) (mouse-x 4 5000 EXP) (mouse-y 0.1 1 LIN)))
  (out 1 (bpf (sin-osc freq) (mouse-x 4 5000 EXP) (mouse-y 0.1 1 LIN))))

; Attach an event with a prophet synth on midi controller
(on-event [:midi :note-on]
          (fn [m]
            (let [note (:note m)]
              (prophet :freq (midi->hz note)
                       :decay 5
                       :rq 0.6
                       :cutoff-freq 1000)))
          ::prophet-midi)


; Let's go
(looper two-hundred-forty-bpm kickkick)
(looper two-hundred-forty-bpm kick)
(looper sixty-bpm clap)
(looper sixty-bpm bass)
(looper sixty-bpm bassbass)

(looper thirty-bpm voice)
(looper thirty-bpm ghost)

(synthe)

; Drop the bass !
(for [i (range 1 50)] (demo(kick)))

; Some strange sounds
(for [i (range 1 50)] (demo(water)))


; Transition which sounds like a vinyl crackle
(stop)
(for [i (range 1 10)]
  (crazy-saw i i i i))

(one-note)
(scary-piano)

; Let's go again
(looper two-hundred-forty-bpm kick)
(looper sixty-bpm overpad)
(voice)
(whoahaha)

; End
(stop)
(crack)
(stop)
(remove-event-handler ::prophet-midi)

