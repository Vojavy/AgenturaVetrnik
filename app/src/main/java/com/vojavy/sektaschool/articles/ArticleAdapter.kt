package com.vojavy.sektaschool.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vojavy.sektaschool.R
import com.vojavy.sektaschool.databinding.ArtilcleItemBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    private val articleList = ArrayList<Article>()

    class ArticleHolder(item : View) :RecyclerView.ViewHolder(item) {
        private val binding = ArtilcleItemBinding.bind(item)
        fun bind(article : Article) = with(binding){
            //проверка на то, какую тему имеет статья. Далее название и другое
            when (article.theme) {
                1 -> ivArticleTheme.setImageResource(R.drawable.babkol)

                2 ->ivArticleTheme.setImageResource(R.drawable.menwomen)

                3 ->ivArticleTheme.setImageResource(R.drawable.book)
            }

            tvName.text = article.name
            tvIntro.text = article.intro
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artilcle_item, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    //Здесь я должен копировать информацию из иодуля взаимодействия с бд
    //Сейчас тут затычка для проверки

    fun addAll(list: List<Article>){
        articleList.clear()
        articleList.addAll(list)
        notifyDataSetChanged()
    }
}