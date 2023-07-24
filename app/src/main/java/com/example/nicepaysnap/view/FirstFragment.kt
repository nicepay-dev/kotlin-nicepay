package com.example.nicepaysnap.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nicepaysnap.Ewallet
import com.example.nicepaysnap.EwalletInquiry
import com.example.nicepaysnap.databinding.FragmentFirstBinding
import com.example.nicepaysnap.virtualAccount

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            val int = Intent(context, virtualAccount::class.java)
            startActivity(int)
        }

        binding.buttonSecond.setOnClickListener {

            val int = Intent(context, Ewallet::class.java)
            startActivity(int)
        }

        binding.buttonThird.setOnClickListener {

            val int = Intent(context, EwalletInquiry::class.java)
            startActivity(int)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}