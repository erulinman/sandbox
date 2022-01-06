package info.erulinman.animations.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import info.erulinman.animations.R
import info.erulinman.animations.databinding.FragmentAnimationsBinding

class ListFragment : Fragment(R.layout.fragment_animations) {

    private var _binding: FragmentAnimationsBinding? = null
    private val binding: FragmentAnimationsBinding
        get() {
            checkNotNull(_binding)
            return _binding as FragmentAnimationsBinding
        }
    private var _adapter: ListAdapter? = null
    private val adapter: ListAdapter
        get() {
            checkNotNull(_adapter)
            return _adapter as ListAdapter
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAnimationsBinding.bind(view)
        _adapter = ListAdapter { item -> when (item?.name) {
            "AnimationUtils" -> findNavController().navigate(
                R.id.action_startFragment_to_animationUtilsFragment
            )
        }}
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}