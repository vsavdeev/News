package ru.valentin.news.ui.vm

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.valentin.news.usecases.ItemUseCase

class SaveViewModel : ViewModel(), KoinComponent {

    private val useCase by inject<ItemUseCase>()

    val articles = useCase.getAllArticles()
}