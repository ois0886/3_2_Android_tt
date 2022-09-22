package com.example.animationpractice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.transition.*
import com.example.animationpractice3.databinding.ActivityMainBinding
import com.example.animationpractice3.databinding.Scene1Binding
import com.example.animationpractice3.databinding.Scene2Binding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scene1Binding: Scene1Binding
    private lateinit var scene2Binding: Scene2Binding
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene

    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scene1 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_1, this)
        scene2 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_2, this)

        /*
        binding.textViewCount.text = getString(R.string.count_in_activity, count)
        binding.textViewCountViewmodel.text = getString(R.string.count_in_ViewModel, viewModel.count)
        viewModel.countLivedata.observe(this) {
            binding.textViewLivedata.text = getString(R.string.count_in_ViewModel_LiveData, it)
        }
        */

        myViewModel.scene1Livedata.observe(this) {
        }
        myViewModel.scene2Livedata.observe(this) {
        }

        binding.radioGp.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_student -> {
                    TransitionManager.go(scene1, ChangeBounds())
                }
                R.id.radioButton_worker -> {
                    TransitionManager.go(scene2, ChangeBounds())
                }
            }
        }
    }
}