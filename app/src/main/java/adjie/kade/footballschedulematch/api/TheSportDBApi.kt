package adjie.kade.footballschedulematch.api

import adjie.kade.footballschedulematch.BuildConfig

object TheSportDBApi {


    fun getNextMatch(idLeague: String?): String{
        return BuildConfig.BASE_URL +  "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeague
    }
    fun getPrevMatch(idLeague: String?): String{
        return BuildConfig.BASE_URL +  "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeague
    }
}