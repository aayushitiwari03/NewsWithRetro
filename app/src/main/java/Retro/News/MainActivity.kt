package Retro.News

import android.media.session.PlaybackState.CustomAction
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(), NewsItemClicked {

    lateinit var adapter: NewsAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()

        recyclerView=findViewById(R.id.newsList)
    }

    private fun getNews() {
        val news=NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object : retrofit2.Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("Aayushi", "News Successful")

                    adapter=NewsAdapter(this@MainActivity, news.articles)
                    recyclerView.adapter=adapter
                    recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Aayushi", "error in fatching news", t)

            }

        })
    }

    override fun onClicked(articles: Articles) {
        val builder = CustomAction.Builder()
        val customAction=
    }
}
