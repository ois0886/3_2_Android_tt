package com.example.animationpractice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.transition.*
import com.example.animationpractice3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scene1 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_1, this)
        scene2 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_2, this)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        viewModel.scene1Text1.observe(this) {
            binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText1)
                .setText(viewModel.scene1Text1.value)
        }
        viewModel.scene1Text2.observe(this) {
            binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText2)
                .setText(viewModel.scene1Text2.value)
        }

        binding.radioGp.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_student -> {
                    TransitionManager.go(scene1, ChangeBounds())
                    binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText1)
                        .setText(viewModel.scene1Text1.value)
                    binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText2)
                        .setText(viewModel.scene1Text2.value)
                }
                R.id.radioButton_worker -> {
                    viewModel.scene1Text1.value =
                        binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText1).text.toString()
                    viewModel.scene1Text2.value =
                        binding.sceneRoot.findViewById<EditText>(R.id.scene1_editText2).text.toString()
                    TransitionManager.go(scene2, ChangeBounds())
                }
            }
        }
    }
}