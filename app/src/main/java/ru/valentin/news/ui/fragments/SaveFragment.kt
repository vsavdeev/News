package ru.valentin.news.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.valentin.news.adapters.CustomAdapter
import ru.valentin.news.adapters.SaveAdapter
import ru.valentin.news.databinding.FragmentSaveBinding
import ru.valentin.news.databinding.FragmentSearchBinding
import ru.valentin.news.ui.vm.SaveViewModel
import ru.valentin.news.ui.vm.SearchViewModel
import ru.valentin.news.utils.Constants

class SaveFragment : Fragment(){

    private val viewModel by viewModel<SaveViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSaveBinding.inflate(inflater,container,false)

        viewModel.articles.observe(viewLifecycleOwner) {
            binding.saves.layoutManager = LinearLayoutManager(context)
            binding.saves.adapter = SaveAdapter(it)
        }

        return binding.root
    }
}