package com.example.workout

class Workout(private val name: String = "", private val description: String = ""){
    private val mName = name
    private val mDescription = description
    fun getName() = mName
    fun getDescription() = mDescription
    override fun toString(): String {
        return mName
    }
}

object WorkoutClass {
    var workouts = arrayOf(
        Workout("The Limb Loosener",
            "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
        Workout("Core Agony",
            "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
        Workout("The Wimp Special",
            "5 Pull-ups\n10 Push-ups\n15 Squats"),
        Workout("Strength and Length",
            "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    )
    var id = 0L
}