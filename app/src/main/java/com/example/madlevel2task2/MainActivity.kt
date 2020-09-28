package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_question.*


private val questions = arrayListOf<Question>()

private val questionsAdapter = QuestionsAdapter(questions)
private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(
            binding.root
        )
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionsAdapter
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Question.QUESTIONS.indices) {
            questions.add(
                Question(
                    Question.QUESTIONS[i],
                    Question.ANSWERS[i]
                )
            )
        }

        Answer().attachToRecyclerView(rvQuestions)
    }


    private fun Answer(): ItemTouchHelper {

        //Creates touchhelper which enables swiping left or right.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)  {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                //It checks the questions position and if the answer is true on that position and the user swipes right (direction 8 in INT) it removes the question.
                // If the answer is false and the direction is 4 it does the same. Else it shows a Snackbar.
                if (questions[position].answer && direction == 8 || !questions[position].answer && direction == 4){
                    questions.removeAt(position)
                    questionsAdapter.notifyDataSetChanged()}
                 else {
                    Snackbar.make(item_question, "Wrong, are you sure?", Snackbar.LENGTH_SHORT)
                        .show()
                    questionsAdapter.notifyDataSetChanged()

                }
            }

        }
        return ItemTouchHelper(callback)
    }
    }


