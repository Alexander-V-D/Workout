package com.example.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class StopWatchFragment : Fragment() {
    private lateinit var timeTracker: TimeTracker
    private lateinit var timeView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    lateinit var layout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_stop_watch, container, false)
        run(savedInstanceState)
        return layout
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putLong("base", timeTracker.base)
        savedInstanceState.putLong("timeAtStop", timeTracker.timeAtStop)
        savedInstanceState.putBoolean("isStopped", stopButton.isEnabled)
        savedInstanceState.putBoolean("isStarted", startButton.isEnabled)
        savedInstanceState.putBoolean("isResetEnabled", resetButton.isEnabled)
        savedInstanceState.putCharSequence("text", timeView.text)
    }
    private fun setListeners() {
        startButton.setOnClickListener {
            timeTracker.start()
            startButton.isEnabled = false
            stopButton.isEnabled = true
            resetButton.isEnabled = true
        }
        stopButton.setOnClickListener {
            timeTracker.stop()
            startButton.isEnabled = true
            stopButton.isEnabled = false
        }
        resetButton.setOnClickListener {
            timeTracker.reset()
            startButton.isEnabled = true
            stopButton.isEnabled = false
            resetButton.isEnabled = false
        }
    }
    private fun run(savedInstanceState: Bundle?) {
        findViews(layout)
        timeTracker = TimeTracker(timeView)
        setListeners()
        if(savedInstanceState == null) {
            timeView.text = "00:00:00"
            setButtonVisibilityAtStart()
        } else {
            importFromInstanceState(savedInstanceState)
        }
        timeTracker.runTracker()
    }
    private fun setButtonVisibilityAtStart() {
        startButton.isEnabled = true
        stopButton.isEnabled = false
        resetButton.isEnabled = false
    }
    private fun findViews(view: View) {
        timeView = view.findViewById(R.id.time_view)
        startButton = view.findViewById(R.id.start)
        stopButton = view.findViewById(R.id.stop)
        resetButton = view.findViewById(R.id.reset)
    }
    private fun importFromInstanceState(savedInstanceState: Bundle) {
        // если активность создается заново, то переносятся даннные из предыдущей
        timeTracker.base = savedInstanceState.getLong("base")
        // перенос состояния кнопок
        startButton.isEnabled = savedInstanceState.getBoolean("isStarted")
        stopButton.isEnabled = savedInstanceState.getBoolean("isStopped")
        resetButton.isEnabled = savedInstanceState.getBoolean("isResetEnabled")
        // перенос времени ожидания
        timeTracker.timeAtStop = savedInstanceState.getLong("timeAtStop")
        timeTracker.wasRunning = resetButton.isEnabled
        timeView.text = savedInstanceState.getCharSequence("text")
        if (!startButton.isEnabled) timeTracker.running = true
    }
}