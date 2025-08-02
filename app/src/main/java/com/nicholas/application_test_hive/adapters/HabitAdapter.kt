package com.nicholas.application_test_hive.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.application_test_hive.R
import com.nicholas.application_test_hive.models.Habit

class HabitAdapter(private val onMarkDone: (Habit) -> Unit) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    private var habits: List<Habit> = emptyList()

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textHabitName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textHabitDescription)
        val lastCompletedTextView: TextView = itemView.findViewById(R.id.textLastCompleted)
        val markDoneButton: Button = itemView.findViewById(R.id.btnMarkDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.nameTextView.text = habit.name
        holder.descriptionTextView.text = habit.description
        holder.lastCompletedTextView.text = habit.lastCompletedDate ?: "Not completed yet"
        holder.markDoneButton.setOnClickListener { onMarkDone(habit) }
    }

    override fun getItemCount(): Int = habits.size

    fun updateHabits(newHabits: List<Habit>) {
        habits = newHabits
        notifyDataSetChanged()
    }
}