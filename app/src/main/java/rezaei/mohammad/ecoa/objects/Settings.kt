package rezaei.mohammad.ecoa.objects

/**
 * Created by Neo on 2/9/2018.
 */
object Settings {
    enum class ProgrammerLevel{
        Beginner,
        SemiProfessional,
        Professional;

        fun getBasePrice():Long{
            return when(this){
                Beginner -> 100000
                SemiProfessional -> 300000
                Professional -> 500000
            }
        }
    }

    enum class AppHardness{
        VerySimple,
        Simple,
        Medium,
        SemiHard,
        Hard,
        VeryHard;

        fun getHardnessRate():Float{
            return when(this){
                VerySimple -> 1f
                Simple -> 1.2f
                Medium -> 1.5f
                SemiHard -> 2f
                Hard -> 2.5f
                VeryHard -> 3f
            }
        }

        fun getSourceRate():Int{
            return when(this){
                VerySimple -> 10
                Simple -> 15
                Medium -> 25
                SemiHard -> 30
                Hard -> 40
                VeryHard -> 50
            }
        }

        fun getTimeRate():Int{
            return when(this){
                VerySimple -> 5
                Simple -> 10
                Medium -> 15
                SemiHard -> 20
                Hard -> 35
                VeryHard -> 40
            }
        }

    }

    enum class Graphic{
        Simple,
        Normal,
        Good,
        Best;

        fun getGraphicRate():Float{
            return when(this){
                Simple -> 1f
                Normal -> 1.2f
                Good -> 1.5f
                Best -> 2f
            }
        }
    }

    const val activityBasePrice = 50000
    const val serviceBasePrice = 30000
    const val supportRate = 25
}