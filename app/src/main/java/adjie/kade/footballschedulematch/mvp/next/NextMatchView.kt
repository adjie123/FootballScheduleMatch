package adjie.kade.footballschedulematch.mvp.fragment.next

import adjie.kade.footballschedulematch.model.MatchSchedule

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchSchedule>)
}