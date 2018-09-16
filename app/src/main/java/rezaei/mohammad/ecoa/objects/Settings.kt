package rezaei.mohammad.ecoa.objects

/**
 * Created by Neo on 2/9/2018.
 */
object Settings {
    enum class ProgrammerLevel{
        Intern,
        Beginner,
        SemiProfessional,
        Professional,
        FullStack;

        fun getBasePrice():Long{
            return when(this){
                Intern -> 100000
                Beginner -> 300000
                SemiProfessional -> 500000
                Professional -> 800000
                FullStack -> 1000000
            }
        }

        fun getTimeRate():Int{
            return when(this){
                Intern -> 5
                Beginner -> 4
                SemiProfessional -> 3
                Professional -> 2
                FullStack -> 1
            }
        }
    }

    enum class AppHardness{
        VerySimple,
        Simple,
        Medium,
        SemiHard,
        Hard;

        fun getHardnessRate():Float{
            return when(this){
                VerySimple -> 1f
                Simple -> 1.5f
                Medium -> 2.5f
                SemiHard -> 3.5f
                Hard -> 4f
            }
        }

        fun getSourceRate():Int{
            return when(this){
                VerySimple -> 10
                Simple -> 15
                Medium -> 25
                SemiHard -> 30
                Hard -> 40
            }
        }

        fun getTimeRate():Int{
            return when(this){
                VerySimple -> 5
                Simple -> 10
                Medium -> 15
                SemiHard -> 20
                Hard -> 35
            }
        }

    }

    enum class Graphic{
        None,
        Beginner,
        Acceptable,
        Good,
        Best;

        fun getGraphicRate():Float{
            return when(this){
                None -> 1f
                Beginner -> 1.5f
                Acceptable -> 2.5f
                Good -> 3.5f
                Best -> 4f
            }
        }

        fun getTimeRate():Int{
            return when(this){
                None -> 2
                Beginner -> 5
                Acceptable -> 8
                Good -> 10
                Best -> 15
            }
        }
    }

    const val activityBasePrice = 70000
    const val serviceBasePrice = 50000
    const val supportRate = 25
}