package com.example.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import java.lang.ClassCastException

class WorkoutListFragment : ListFragment() {
    interface Listener {
        fun itemClicked(id: Long)
    }
    lateinit var listener: Listener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val names = Array(WorkoutClass.workouts.size) { "" }
        for (i in WorkoutClass.workouts.indices) {
            names[i] = WorkoutClass.workouts[i].getName()
        }
        val adapter = ArrayAdapter(
            inflater.context,
            android.R.layout.simple_list_item_1,
            names
        )
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Listener) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + "must implement Listener")
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener.itemClicked(id)
    }
}