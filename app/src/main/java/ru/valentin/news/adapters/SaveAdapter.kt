package ru.valentin.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.valentin.news.R
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.usecases.ItemUseCase

class SaveAdapter(private val list: List<ArticlesItem?>?): RecyclerView.Adapter<SaveAdapter.ViewHolder>(), KoinComponent {

    private val usecase by inject<ItemUseCase>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.save_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)

        holder.title.text = item?.title

        holder.itemView.setOnClickListener {
            usecase.deleteArticle(item)
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}