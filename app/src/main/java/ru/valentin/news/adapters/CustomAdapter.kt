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

class CustomAdapter(private val list: List<ArticlesItem?>?): RecyclerView.Adapter<CustomAdapter.ViewHolder>(), KoinComponent {

    private val usecase by inject<ItemUseCase>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.image)
        val source = itemView.findViewById<TextView>(R.id.source)
        val title = itemView.findViewById<TextView>(R.id.title)
        val text = itemView.findViewById<TextView>(R.id.text)
        val date = itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)

        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .centerCrop()
            .into(holder.image)

        holder.source.text = item?.source.toString()
        holder.title.text = item?.title
        holder.text.text = item?.description
        holder.date.text = item?.publishedAt

        holder.itemView.setOnClickListener {
            usecase.item.postValue(item)
            Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_itemFragment2).onClick(holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return 6
    }
}