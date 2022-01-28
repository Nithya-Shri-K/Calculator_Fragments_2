package com.example.calculatorfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import com.example.calculatorfragments.databinding.FragmentMainScreenBinding

class MainScreen : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        with(binding) {
            add.setOnClickListener { navigate(Operation.ADD) }
            subtract.setOnClickListener { navigate(Operation.SUBTRACT) }
            multiply.setOnClickListener { navigate(Operation.MULTIPLY) }
            divide.setOnClickListener { navigate(Operation.DIVIDE) }
            buttonReset.setOnClickListener {
                layoutResult.visibility = View.GONE
                textResult.text = ""
                layoutButtons.visibility = View.VISIBLE

            }
        }
        if(savedInstanceState?.getInt(IS_RESULT_PAGE) == 1){
                modifyScreenForResult()
                binding.textResult.text = savedInstanceState.getString(RESULT)
            }

        setFragmentResultListener(REQUEST_KEY){ requestKey, bundle ->
                binding.textResult.text = getString(R.string.result_text,bundle.getString(RESULT),bundle.getString(
                    OPERAND1),bundle.getString(OPERAND2),bundle.getString(OPERATION))
                modifyScreenForResult()
            }
            return binding.root
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (this::binding.isInitialized && binding.layoutResult.isVisible) {
                outState.putInt(IS_RESULT_PAGE, 1)
                outState.putString(RESULT, binding.textResult.text.toString())
            }

    }
    private fun modifyScreenForResult() {
        binding.layoutButtons.visibility=View.GONE
        binding.layoutResult.visibility=View.VISIBLE
    }

    private fun navigate(operation: Operation){
        val bundle = Bundle()
        bundle.putSerializable(OPERATION,operation)
        val calculateFragment = Calculate()
        calculateFragment.arguments=bundle
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,calculateFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}