package rezaei.mohammad.ecoa.ui
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hsalf.smilerating.BaseRating
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rezaei.mohammad.ecoa.EstimateCostAndTime
import rezaei.mohammad.ecoa.R
import rezaei.mohammad.ecoa.objects.Settings
import java.text.DecimalFormat
import kotlin.text.Typography.times

class MainActivity : AppCompatActivity() {

    val typeface by lazy {
        ResourcesCompat.getFont(this,R.font.iran_sans)
    }
    private var programmerLevel: Settings.ProgrammerLevel = Settings.ProgrammerLevel.Intern
    private var appHardness: Settings.AppHardness = Settings.AppHardness.VerySimple
    private var activities = 0
    private var services = 0
    private var graphic: Settings.Graphic = Settings.Graphic.None
    private var needSource: Boolean = false
    private var needSupport: Boolean = false
    private val HoursOfDay = 8.0
    private val DaysOfWeek = 5.0
    private val WeeksOfMonth = 4.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        initAppbar()
        initViews()
    }

    private fun initAppbar(){
        val param = appBar.layoutParams
        val screenX = resources.displayMetrics.widthPixels
        val height = screenX.div(1.8f)
        param.height = height.toInt()
        appBar.layoutParams = param

        scrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if(scrollView.canScrollVertically(-1)){
                ViewCompat.setElevation(appBar,8f)
            } else {
                ViewCompat.setElevation(appBar,0f)
            }
        }
    }

    private fun initViews(){
        checkboxSource.typeface = typeface
        checkboxSupport.typeface = typeface
        checkboxSource.setOnCheckedChangeListener { buttonView, isChecked ->
            needSource = isChecked
            showResult()
        }
        checkboxSupport.setOnCheckedChangeListener { buttonView, isChecked ->
            needSupport = isChecked
            showResult()
        }

        txtPrice.setDecimalFormat(DecimalFormat("###,###,###"))
        txtPrice.setAnimationDuration(1000)
        txtTime.setAnimationDuration(1000)

        layTime.setOnClickListener {
            TransitionManager.beginDelayedTransition(layResult)
            if(layTimeDetail.visibility == View.VISIBLE) {
                layTimeDetail.visibility = View.GONE
                imgCollapse.setImageResource(R.drawable.arrow_down)
            }
            else {
                layTimeDetail.visibility = View.VISIBLE
                imgCollapse.setImageResource(R.drawable.arrow_up)
            }
        }

        help.setOnClickListener {
            AlertDialog.Builder(this)
                    .setView(R.layout.time_tip)
                    .setPositiveButton("خب") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
        }

        pickerActivites.setListener {
            activities = it
            showResult()
        }
       pickerServices.setListener {
           services = it
           showResult()
       }

        viewProgrammerLevel.setNameForSmile(BaseRating.TERRIBLE,"کارآموز")
        viewProgrammerLevel.setNameForSmile(BaseRating.BAD,"مبتدی")
        viewProgrammerLevel.setNameForSmile(BaseRating.OKAY,"نیمه حرفه ای")
        viewProgrammerLevel.setNameForSmile(BaseRating.GOOD,"حرفه ای")
        viewProgrammerLevel.setNameForSmile(BaseRating.GREAT,"فول استک")
        viewProgrammerLevel.setTypeface(typeface)
        viewProgrammerLevel.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewAppHardness.setNameForSmile(BaseRating.TERRIBLE,"خیلی ساده")
        viewAppHardness.setNameForSmile(BaseRating.BAD,"ساده")
        viewAppHardness.setNameForSmile(BaseRating.OKAY,"متوسط")
        viewAppHardness.setNameForSmile(BaseRating.GOOD,"حدودا سخت")
        viewAppHardness.setNameForSmile(BaseRating.GREAT,"سخت")
        viewAppHardness.setTypeface(typeface)
        viewAppHardness.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewGraphic.setNameForSmile(BaseRating.TERRIBLE,"بدون گرافیک")
        viewGraphic.setNameForSmile(BaseRating.BAD,"مبتدی")
        viewGraphic.setNameForSmile(BaseRating.OKAY,"قابل قبول")
        viewGraphic.setNameForSmile(BaseRating.GOOD,"خوب")
        viewGraphic.setNameForSmile(BaseRating.GREAT,"عالی")
        viewGraphic.setTypeface(typeface)
        viewGraphic.setSelectedSmile(BaseRating.TERRIBLE,false)

        viewProgrammerLevel.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> programmerLevel = Settings.ProgrammerLevel.Intern
                BaseRating.BAD -> programmerLevel = Settings.ProgrammerLevel.Beginner
                BaseRating.OKAY -> programmerLevel = Settings.ProgrammerLevel.SemiProfessional
                BaseRating.GOOD -> programmerLevel = Settings.ProgrammerLevel.Professional
                BaseRating.GREAT -> programmerLevel = Settings.ProgrammerLevel.FullStack
            }
            showResult()
        }

        viewAppHardness.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> appHardness = Settings.AppHardness.VerySimple
                BaseRating.BAD -> appHardness = Settings.AppHardness.Simple
                BaseRating.OKAY -> appHardness = Settings.AppHardness.Medium
                BaseRating.GOOD ->appHardness = Settings.AppHardness.SemiHard
                BaseRating.GREAT -> appHardness = Settings.AppHardness.Hard
            }
            showResult()
        }

        viewGraphic.setOnSmileySelectionListener { smiley, reselected ->
            when(smiley){
                BaseRating.TERRIBLE -> graphic = Settings.Graphic.None
                BaseRating.BAD -> graphic = Settings.Graphic.Beginner
                BaseRating.OKAY -> graphic = Settings.Graphic.Acceptable
                BaseRating.GOOD -> graphic = Settings.Graphic.Good
                BaseRating.GREAT -> graphic = Settings.Graphic.Best
            }
            showResult()
        }

        showResult()

    }

    @SuppressLint("SetTextI18n")
    private fun showResult(){
        activities = pickerActivites.value
        services = pickerServices.value

        val costAndTime = EstimateCostAndTime(programmerLevel
                , appHardness
                , graphic
                , activities
                , services
                , needSource
                , needSupport).getCostAndTime()

        txtPrice.countAnimation(txtPrice.text.toString().filter { it.isDigit() }.toInt(),costAndTime.first.toInt())
        txtTime.countAnimation(txtTime.text.toString().toInt(),costAndTime.second)

        val month = Math.floor(costAndTime.second.div(HoursOfDay).div(DaysOfWeek).div(WeeksOfMonth))
        val week = Math.floor((costAndTime.second.div(HoursOfDay).div(DaysOfWeek)).minus(month.times(WeeksOfMonth)))
        val day = Math.floor((costAndTime.second.div(HoursOfDay)).minus(month.times(WeeksOfMonth).times(DaysOfWeek) + week.times(DaysOfWeek)))

        txtTimeDetail.text = spanTimeText(month.toInt().toString(),"ماه و").append("\n" +
                spanTimeText(week.toInt().toString(),"هفته و").append("\n" +
                        spanTimeText(day.toInt().toString(),"روز")))
    }

    private fun spanTimeText(time: String, text: String): SpannableStringBuilder {
        val result = SpannableStringBuilder("$time $text")
        result.setSpan(StyleSpan(Typeface.BOLD),
                0, time.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_info -> {
                val intent = Intent(this@MainActivity,AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
