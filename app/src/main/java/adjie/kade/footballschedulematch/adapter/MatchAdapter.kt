package adjie.kade.footballschedulematch.adapter

import adjie.kade.footballschedulematch.R
import adjie.kade.footballschedulematch.R.id.*
import adjie.kade.footballschedulematch.model.MatchSchedule
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class MatchAdapter (private val match: List<MatchSchedule>) :
        RecyclerView.Adapter<MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(match[position])
    }




}

class ItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            linearLayout {
                backgroundColor = Color.WHITE
                orientation = LinearLayout.VERTICAL
                padding = dip(8)

                textView {
                    id = R.id.ID_DATE
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL

                    textView {
                        id = R.id.ID_HOME_TEAM
                        gravity = Gravity.CENTER
                        textSize = 18f
                        text = "home"
                    }.lparams(matchParent, wrapContent, 1f)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id = R.id.ID_HOME_SCORE
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }

                        textView {
                            text = "vs"
                        }

                        textView {
                            id = R.id.ID_AWAY_SCORE
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            text = "0"
                        }
                    }

                    textView {
                        id = R.id.ID_AWAY_TEAM
                        gravity = Gravity.CENTER
                        textSize = 18f
                        text = "away"
                    }.lparams(matchParent, wrapContent, 1f)
                }
            }.lparams(matchParent, matchParent) {
                setMargins(dip(16), dip(8), dip(16), dip(8))
            }
        }
    }
}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val matchDate: TextView = view.find(ID_DATE)
    private val matchHomeTeam: TextView = view.find(ID_HOME_TEAM)
    private val matchHomeScore: TextView = view.find(ID_HOME_SCORE)
    private val matchAwayTeam: TextView = view.find(ID_AWAY_TEAM)
    private val matchAwayScore: TextView = view.find(ID_AWAY_SCORE)
    fun bindItem(event: MatchSchedule) {
        matchDate.text = event.dateEvent
        matchHomeTeam.text = event.strHomeTeam
        matchHomeScore.text = event.intHomeScore
        matchAwayTeam.text = event.strAwayTeam
        matchAwayScore.text = event.intAwayScore

    }
}

