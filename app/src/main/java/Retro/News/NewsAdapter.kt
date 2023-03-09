package Retro.News

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,val articles: List<Articles>,val listener:MainActivity):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    class ArticleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val newsImage=itemView.findViewById<ImageView>(R.id.newsImage)
        val newsTitle=itemView.findViewById<TextView>(R.id.newsTitle)
        val newsDesc=itemView.findViewById<TextView>(R.id.newsDesc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        val viewHolder = ArticleViewHolder(view)
        view.setOnClickListener{
            listener.onClicked(articles[viewHolder.adapterPosition])
        }
        return viewHolder
//        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles=articles[position]
        holder.newsTitle.text=articles.title
        holder.newsDesc.text=articles.description

        holder.itemView.setOnClickListener {
            Toast.makeText(context,articles.title,Toast.LENGTH_SHORT).show()
        }
        Glide.with(context).load(articles.urlToImage).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
interface NewsItemClicked{

    fun onClicked(articles: Articles)
}