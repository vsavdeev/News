package ru.valentin.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.valentin.news.R
import ru.valentin.news.adapters.CustomAdapter
import ru.valentin.news.databinding.FragmentSearchBinding
import ru.valentin.news.ui.vm.SearchViewModel
import ru.valentin.news.utils.Constants

class SearchFragment : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater,container,false)

        var job: Job? = null

        binding.edittext.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.SEARCH_DELAY)
                editable.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    } else {
                        viewModel.getNews()
                    }
                }
            }
        }

        viewModel.searchList.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = CustomAdapter(it)
        }

        viewModel.newsList.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = CustomAdapter(it)
        }

        binding.saves.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_saveFragment).onClick(it)
        }

        return binding.root
    }
}