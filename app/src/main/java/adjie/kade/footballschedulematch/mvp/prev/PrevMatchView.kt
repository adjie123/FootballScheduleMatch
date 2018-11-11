package adjie.kade.footballschedulematch.mvp.fragment.prev

import adjie.kade.footballschedulematch.model.MatchSchedule

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchSchedule>)
}