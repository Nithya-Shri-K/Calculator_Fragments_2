package com.example.calculatorfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.calculatorfragments.databinding.FragmentCalculateBinding

class Calculate : Fragment() {
    private lateinit var binding : FragmentCalculateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalculateBinding.inflate(layoutInflater,container,false)
        val operation : Operation? = arguments?.getSerializable(OPERATION) as? Operation
        binding.button.text = operation?.name
        binding.button.setOnClickListener {

            when(operation){
                Operation.ADD ->calculate(Operation.ADD)
                Operation.SUBTRACT ->calculate(Operation.SUBTRACT)
                Operation.MULTIPLY ->calculate(Operation.MULTIPLY)
                Operation.DIVIDE ->calculate(Operation.DIVIDE)
            }
        }

        return binding.root
    }

    private fun calculate(operation: Operation) {
        val operand1 = binding.input1.text.toString()
        val operand2 = binding.input2.text.toString()
        if (operand1.isNotEmpty() && operand2.isNotEmpty()) {
            if (operation == Operation.DIVIDE && operand2.toFloat() == 0.0f) {
                Toast.makeText(
                    activity,
                    getString(R.string.toast_divide_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val result = when (operation) {
                    Operation.ADD -> operand1.toFloat() + operand2.toFloat()
                    Operation.SUBTRACT -> operand1.toFloat() - operand2.toFloat()
                    Operation.MULTIPLY -> operand1.toFloat() * operand2.toFloat()
                    Operation.DIVIDE -> operand1.toFloat() / operand2.toFloat()
                }
                returnResult(operand1, operand2, result, operation)
            }
        }
        else{
            Toast.makeText(activity,getString(R.string.valid_input_text), Toast.LENGTH_SHORT).show()
        }
    }
    private fun returnResult(
        operand1: String,
        operand2: String,
        result: Float,
        operation: Operation
    ) {
        val resultValue = if(result - result.toInt() > 0 )
            result.toString()
        else
            result.toInt().toString()

        setFragmentResult(REQUEST_KEY, bundleOf(RESULT to resultValue , OPERAND1 to operand1,
            OPERAND2 to operand2, OPERATION to operation.name)
        )
        parentFragmentManager.popBackStack()
    }

}