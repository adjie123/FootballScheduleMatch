package adjie.kade.footballschedulematch.mvp.fragment.next

import adjie.kade.footballschedulematch.api.ApiRequest
import adjie.kade.footballschedulematch.api.TheSportDBApi
import adjie.kade.footballschedulematch.model.MatchScheduleResponse
import adjie.kade.footballschedulematch.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val  view: NextMatchView,
                         private val apiRequest: ApiRequest,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getMatchList(id: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getNextMatch(id)),
                        MatchScheduleResponse::class.java
                )
            }
            view.showMatchList(data.await().match)
            view.hideLoading()
        }
    }
}
