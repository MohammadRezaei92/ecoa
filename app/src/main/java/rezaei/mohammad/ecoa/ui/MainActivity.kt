package rezaei.mohammad.ecoa.ui

import android.content.Intent
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.hsalf.smilerating.BaseRating
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import rezaei.mohammad.ecoa.EstimateCostAndTime
import rezaei.mohammad.ecoa.R
import rezaei.mohammad.ecoa.R.id.*
import rezaei.mohammad.ecoa.objects.Cons
import rezaei.mohammad.ecoa.objects.Settings
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val typeface by lazy {
        ResourcesCompat.getFont(this,R.font.iran_sans)
    }
    private var programmerLevel: Settings.ProgrammerLevel = Settings.ProgrammerLevel.Intern
    private var appHardness: Settings.AppHardness = Settings.AppHardness.VerySimple
    var activities = 0
    var services = 0
    private var graphic: Settings.Graphic = Settings.Graphic.None
    private var needSource: Boolean = false
    private var needSupport: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        initAppbar()
        initViews()
    }

    private fun initAppbar(){
        val param = appBar.layoutParams
        val screenX = resources.displayMetrics.widthPixels
        val height = screenX.div(1.8f)
        param.height = height.toInt()
        appBar.layoutParams = param
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

        txtPrice.countAnimation(txtPrice.text.toString().replace(",","").toInt(),costAndTime.first.toInt())
        txtTime.countAnimation(txtTime.text.toString().toInt(),costAndTime.second)
    }
}
