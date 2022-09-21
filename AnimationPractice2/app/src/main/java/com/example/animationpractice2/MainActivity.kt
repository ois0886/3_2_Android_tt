package com.example.animationpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.*
import com.example.animationpractice2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scene1 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_1, this)
        scene2 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.scene_2, this)

        binding.radioGp.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton_student -> TransitionManager.go(scene1, ChangeBounds())
                R.id.radioButton_worker -> TransitionManager.go(
                    scene2,
                    Fade().addListener(Scene1to2TransitionListener())
                )
            }
        }
    }

    inner class Scene1to2TransitionListener : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) {
        }

        override fun onTransitionEnd(transition: Transition) {
            println("onTransitionEnd ########################")
        }

        override fun onTransitionCancel(transition: Transition) {
        }

        override fun onTransitionPause(transition: Transition) {
        }

        override fun onTransitionResume(transition: Transition) {
        }
    }
}