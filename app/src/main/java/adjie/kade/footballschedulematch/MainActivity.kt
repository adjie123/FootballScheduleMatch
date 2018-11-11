package adjie.kade.footballschedulematch

import adjie.kade.footballschedulematch.adapter.MatchAdapter
import adjie.kade.footballschedulematch.api.ApiRequest
import adjie.kade.footballschedulematch.model.MatchSchedule
import adjie.kade.footballschedulematch.mvp.fragment.next.NextMatchPresenter
import adjie.kade.footballschedulematch.mvp.fragment.next.NextMatchView
import adjie.kade.footballschedulematch.utils.invisible
import adjie.kade.footballschedulematch.utils.visible
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), NextMatchView {

    private var schedules: MutableList<MatchSchedule> = mutableListOf()
    private lateinit var listSchedules: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: NextMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipeRefresh
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = matchParent)

                    listSchedules = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }

                    progressBar = progressBar {
                        id = R.id.pbNextEvent
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }

        adapter = MatchAdapter(schedules)
        listSchedules.adapter = adapter

        val request = ApiRequest()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)

        swipeRefresh.onRefresh {
            presenter.getMatchList("4328")
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<MatchSchedule>) {
        swipeRefresh.isRefreshing = false
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
