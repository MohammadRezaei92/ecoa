package rezaei.mohammad.ecoa

import rezaei.mohammad.ecoa.objects.Settings

/**
 * Created by Neo on 2/9/2018.
 */
class EstimateCostAndTime(private val programmerLevel: Settings.ProgrammerLevel
                          ,private val appHardness: Settings.AppHardness
                          ,private val graphic: Settings.Graphic
                          ,private val activity: Int,private val service: Int
                          ,private val sourceCode: Boolean, val support: Boolean) {

    fun getCostAndTime():Pair<Float,Int>{

        var totalPrice: Float = programmerLevel.getBasePrice() + estimateActivitiesCost()
        + estimateServicesCost()

        if(sourceCode){
            totalPrice += estimateSourceCost(totalPrice)
        }
        if(support){
            totalPrice += estimateSupportCost(totalPrice)
        }
        return Pair(totalPrice,estimateTime())
    }

    private fun estimateActivitiesCost(): Float {
        return activity * Settings.activityBasePrice * graphic.getGraphicRate()* appHardness.getHardnessRate()
    }

    private fun estimateServicesCost():Float{
        return service * Settings.serviceBasePrice * appHardness.getHardnessRate()
    }

    private fun estimateSourceCost(totalPrice: Float):Float{
        return (totalPrice * appHardness.getSourceRate()) / 100
    }

    private fun estimateSupportCost(totalPrice: Float):Float{
        return (totalPrice * Settings.supportRate) / 100
    }

    private fun estimateTime():Int{
        return (activity + service) * appHardness.getTimeRate()
    }
}