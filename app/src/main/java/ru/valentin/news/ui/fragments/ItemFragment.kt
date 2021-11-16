package ru.valentin.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.valentin.news.R
import ru.valentin.news.databinding.FragmentItemBinding
import ru.valentin.news.ui.vm.ItemViewModel
import ru.valentin.news.ui.vm.SearchViewModel

class ItemFragment : Fragment(){

    private val viewModel by viewModel<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentItemBinding.inflate(inflater,container,false)

        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            save.setOnClickListener {
                viewModel.addArticle()
            }
        }

        viewModel.imageUrl.observe(viewLifecycleOwner) {
            Glide.with(this)
                .load(it)
                .centerCrop()
                .into(binding.image)
        }

        return binding.root
    }
}