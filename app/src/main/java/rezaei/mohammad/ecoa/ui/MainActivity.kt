package rezaei.mohammad.ecoa.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rezaei.mohammad.ecoa.EstimateCostAndTime
import rezaei.mohammad.ecoa.R
import rezaei.mohammad.ecoa.objects.Cons
import rezaei.mohammad.ecoa.objects.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnSubmit.setOnClickListener {
            readDataFromLayout()
        }
    }

    private fun readDataFromLayout(){
        lateinit var programmerLevel: Settings.ProgrammerLevel
        lateinit var appHardness: Settings.AppHardness
        var activities: Int = 0
        var services: Int = 0
        lateinit var graphic: Settings.Graphic
        val needSource: Boolean
        val needSupport: Boolean

        when(groupProgrammerLevel.checkedRadioButtonId){
            levelBeginner.id -> programmerLevel = Settings.ProgrammerLevel.Beginner
            levelSemiProfessional.id -> programmerLevel = Settings.ProgrammerLevel.SemiProfessional
            levelProfessional.id -> programmerLevel = Settings.ProgrammerLevel.Professional
        }

        when(groupAppHardness.checkedRadioButtonId){
            haVerySimple.id -> appHardness = Settings.AppHardness.VerySimple
            haSimple.id -> appHardness = Settings.AppHardness.Simple
            haMedium.id -> appHardness = Settings.AppHardness.Medium
            haSemiHard.id -> appHardness = Settings.AppHardness.SemiHard
            haHard.id -> appHardness = Settings.AppHardness.Hard
            haVeryHard.id -> appHardness = Settings.AppHardness.VeryHard
        }

        when(groupGraphic.checkedRadioButtonId){
            graphicSimple.id -> graphic = Settings.Graphic.Simple
            graphicNormal.id -> graphic = Settings.Graphic.Normal
            graphicGood.id -> graphic = Settings.Graphic.Good
            graphicBest.id -> graphic = Settings.Graphic.Best
        }

        if(validateInputs()) {
            activities = inputActivities.text.toString().toInt()
            services = inputServices.text.toString().toInt()
        }

        needSource = checkboxSource.isChecked
        needSupport = checkboxSupport.isChecked

        val costAndTime = EstimateCostAndTime(programmerLevel, appHardness, graphic, activities, services
                , needSource, needSupport)
                .getCostAndTime()

        val result = Intent(this, ResultActivity::class.java)
        result.putExtra(Cons.COST,costAndTime.first)
        result.putExtra(Cons.TIME,costAndTime.second)
        startActivity(result)
    }

    private fun validateInputs():Boolean{
        return if(inputActivities.text.isNotEmpty() && inputServices.text.isNotEmpty()){
            true
        } else{
            Toast.makeText(this,"لطفا تعداد اکتیویتی، فرگمن و سرویس را وارد کنید!",Toast.LENGTH_LONG).show()
            false
        }
    }
}
