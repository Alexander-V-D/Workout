package com.example.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class WorkoutDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val stopwatch = StopWatchFragment()
            val ft = childFragmentManager.beginTransaction()
            ft.add(R.id.stopwatch_container, stopwatch)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if(view != null) {
            val title = view.findViewById<TextView>(R.id.textTitle)
            val description = view.findViewById<TextView>(R.id.textDescription)
            val workout = WorkoutClass.workouts[WorkoutClass.id.toInt()]
            title.text = workout.getName()
            description.text = workout.getDescription()
        }
    }
}