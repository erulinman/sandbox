package info.erulinman.animations.animationutils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import info.erulinman.animations.R
import info.erulinman.animations.databinding.FragmentAnimationUtilsBinding
import info.erulinman.animations.animationutils.AnimationUtilsViewModel.*

class AnimationUtilsFragment : Fragment(R.layout.fragment_animation_utils) {

    private val viewModel by viewModels<AnimationUtilsViewModel>()

    private var _binding: FragmentAnimationUtilsBinding? = null
    private val binding: FragmentAnimationUtilsBinding
        get() {
            checkNotNull(_binding)
            return _binding as FragmentAnimationUtilsBinding
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimationUtilsBinding.bind(view).apply {
            plusFab.setOnClickListener { viewModel.updateCounter() }
        }
        setRadioGroupListener()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRadioGroupListener() {
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            viewModel.state.value = when(id) {
                R.id.rb1 -> STATE.STOPPED
                R.id.rb2 -> STATE.STARTED
                R.id.rb3 -> STATE.COUNTING
                else -> error("There is now value for AnimationsViewModel.state")
            }
        }
    }

    private fun observeViewModel() = viewModel.apply {
        state.observe(viewLifecycleOwner) lambda@{ state ->
            if (state == null) return@lambda
            when(state) {
                STATE.STOPPED -> binding.apply {
                    mainFab.startAnimationWithActionAfter(R.anim.rotate, R.drawable.ic_play) {
                        viewModel.state.value = STATE.STARTED
                    }
                    setCounterVisibility(View.GONE)
                    rb1.isChecked = true
                }
                STATE.STARTED -> binding.apply {
                    mainFab.startAnimationWithActionAfter(R.anim.rotate, R.drawable.ic_pause) {
                        viewModel.state.value = STATE.STOPPED
                    }
                    setCounterVisibility(View.GONE)
                    rb2.isChecked = true
                }
                STATE.COUNTING -> binding.apply {
                    mainFab.startAnimationWithActionAfter(R.anim.rotate, R.drawable.ic_delete) {
                        viewModel.updateCounter(true)
                        viewModel.state.value = STATE.STOPPED
                    }
                    setCounterVisibility(View.VISIBLE)
                    rb3.isChecked = true
                }
            }
        }
        counter.observe(viewLifecycleOwner) {
            it?.let { binding.tvCounter.text = it.toString() }
        }
    }

    private fun setCounterVisibility(visibility: Int) = binding.apply {
        plusFab.visibility = visibility
        tvCounter.visibility = visibility
    }
}