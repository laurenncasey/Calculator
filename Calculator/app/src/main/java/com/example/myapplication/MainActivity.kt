package com.example.myapplication
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.SyncStateContract
import android.widget.Button
import android.widget.TextView
import java.io.IOError
import java.io.IOException
import java.lang.NumberFormatException
import kotlin.math.pow
import kotlin.math.sqrt
import android.provider.SyncStateContract.Constants

import org.jetbrains.annotations.NotNull

import androidx.annotation.NonNull
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    /**
     * Boolean and Double declarations
     * for further functions to share
     */
    var addB: Boolean = false
    var subB: Boolean = false
    var divB: Boolean = false
    var timesB: Boolean = false
    var basic: Boolean = false
    var dataOp: Boolean = false
    var tr: Boolean = false
    var expo: Boolean = false
    var square: Boolean = false
    //counters
    var dataC: Int = 0
    var currentNum: Double = 0.0
    var beforeNum: Double = 0.0
    var trimmThisMuch: Double = 0.0

    /**
     * NumbersList : holds inputted dataset values from user
     * EmptyList : To reset NumbersList if we delete all items
     */
    var numbersList: ArrayList<Double> = arrayListOf()



    @Override
    override fun onSaveInstanceState(outState: Bundle){
        outState.putBoolean("addBool", addB)
        outState.putBoolean("subBool", subB)
        outState.putBoolean("timesBool", timesB)
        outState.putBoolean("divBool", divB)
        outState.putBoolean("dataOption", dataOp)
        outState.putBoolean("squaring", square)
        outState.putBoolean("expoing",expo )
        outState.putBoolean("basicOps", basic)
        outState.putBoolean("trimming", tr)

        outState.putInt("dataCounter", dataC)
        outState.putDouble("current", currentNum)
        outState.putDouble("before", beforeNum)
        outState.putDouble("trimThis", trimmThisMuch)

        outState.putParcelableArrayList("listOfNums", numbersList as ArrayList<Parcelable>)
        outState.putString("screenText", findViewById<TextView>(R.id.screen).text.toString())

        super.onSaveInstanceState(outState)

    }

    @Override
    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)

        addB = savedInstanceState.getBoolean("addBool")
        subB = savedInstanceState.getBoolean("subBool")
        timesB = savedInstanceState.getBoolean("timesBool")
        divB = savedInstanceState.getBoolean("divBool")
        dataOp = savedInstanceState.getBoolean("dataOption")
        square = savedInstanceState.getBoolean("squaring")
        expo = savedInstanceState.getBoolean("expoing")
        basic = savedInstanceState.getBoolean("basicOps")
        tr = savedInstanceState.getBoolean("trimming")

        dataC = savedInstanceState.getInt("dataCounter")
        currentNum = savedInstanceState.getDouble("current")
        beforeNum = savedInstanceState.getDouble("before")
        trimmThisMuch = savedInstanceState.getDouble("trimThis")

        numbersList = savedInstanceState.getParcelableArrayList<Parcelable>("listOfNums") as ArrayList<Double>
        findViewById<TextView>(R.id.screen).text = savedInstanceState.getString("screenText")


    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Screen : the calulator screen providing numbers to user
         * Add/Times/Sub/Div : 4 main calculator function buttons
         */
        //screen textview....
        val screen = findViewById<TextView>(R.id.screen)

        //four main functions buttons
        val add = findViewById<Button>(R.id.plus)
        val sub = findViewById<Button>(R.id.minus)
        val div = findViewById<Button>(R.id.divide)
        val times = findViewById<Button>(R.id.mult)

        /**
         * One - Dec : Buttons pressed by user accounting for all numbers and decimal
         */
        //number buttons
        val one = findViewById<Button>(R.id.one)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val four = findViewById<Button>(R.id.four)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val sev = findViewById<Button>(R.id.seven)
        val eig = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)
        val zero = findViewById<Button>(R.id.zero)
        val dec = findViewById<Button>(R.id.decimal)

        /**
         * Data - Sq: Buttons pressed by user accounting for statistical data
         *
         * Clear - Enter: Buttons pressed by user accounting for entering
         * a value or clearing the screen or a dataset value
         */
        //stats related buttons
        val data = findViewById<Button>(R.id.dataset)
        val stat = findViewById<Button>(R.id.stats)
        val trim = findViewById<Button>(R.id.trimmed)
        val ex = findViewById<Button>(R.id.expo)
        val sq = findViewById<Button>(R.id.root)

        //clear and enter button
        val clear = findViewById<Button>(R.id.clear)
        val enter = findViewById<Button>(R.id.enter)

        var firstCleared = false
        var backup = ""


        /**
         * Exponent Parser : takes in the screens numbers input and breaks apart
         * string into proper substrings to calculate exponents.
         * Reassigns the current number on the screen to that solution.
         */
        fun exponentParser(quote: String) {
            val beginning = quote.subSequence(0, quote.indexOf("^"))
            val ending = quote.subSequence(quote.indexOf("^") + 1, quote.length)
            currentNum = beginning.toString().toDouble().pow(ending.toString().toDouble())
        }

        /**
         * SquareRoot Parser : takes in the screens numbers input and breaks apart
         * string into proper substrings to calculate square root.
         * Reassigns the current number on the screen to that solution.
         */
        fun squareRootParser(quote: String) {
            currentNum = (sqrt(
                (quote.subSequence(quote.indexOf("√") + 1, quote.length)).toString().toDouble()
            ))
        }

        /**
         * Functions zero - dec: appends selected button text onto
         * TextView and classifies it as the new current number.
         * Considers more than single digit or decimal numbers.
         */
        zero?.setOnClickListener { //0 button activated
                screen.append(getString(R.string.zero))     //appends 0 to display
                if (!dataOp && !expo && !square && !tr) {
                    try {
                        currentNum = screen.text.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        screen.text = "Error"
                    }
                }
        }

        one?.setOnClickListener {                         //1 button activated
            screen.append(getString(R.string.one))     //appends 1 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        two?.setOnClickListener {                         //2 button activated
            screen.append(getString(R.string.two))     //appends 2 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        three?.setOnClickListener {                         //3 button activated
            screen.append(getString(R.string.three))     //appends 3 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        four?.setOnClickListener {                         //4 button activated
            screen.append(getString(R.string.four))     //appends 4 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        five?.setOnClickListener {                         //5 button activated
            screen.append(getString(R.string.five))     //appends 5 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        six?.setOnClickListener {                         //6 button activated
            screen.append(getString(R.string.six))     //appends 6 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        sev?.setOnClickListener {                           //7 button activated
            screen.append(getString(R.string.seven))     //appends 7 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

        eig?.setOnClickListener {                           //8 button activated
            screen.append(getString(R.string.eight))     //appends 8 to display
            if (!dataOp && !expo && !square && !tr) {
                try {
                    currentNum = screen.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    screen.text = "Error"
                }
            }
        }

            nine?.setOnClickListener {                          //9 button activated
                screen.append(getString(R.string.nine))     //appends 9 to display
                if (!dataOp && !expo && !square && !tr) {
                    try {
                        currentNum = screen.text.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        screen.text = "Error"
                    }
                }
            }

                dec?.setOnClickListener { //. button activated
                    screen.append(getString(R.string.zero))     //appends . to display
                }

                /**
                 * Add - Div: If one of the four basic functions is selected
                 * it notifies the enter button by Boolean assignment.
                 *
                 * Basic : boolean used to let subtract know whether or not "-"
                 * is being used for subtraction or negative sign.
                 *
                 * Clears screen after button selection.
                 */
                add?.setOnClickListener {
                    if (dataOp) {
                        screen.text = "Error"
                    } else {
                        basic = true
                        divB = false
                        addB = true
                        subB = false
                        timesB = false
                        beforeNum = screen.text.toString().toDouble()
                        screen.text = null
                    }
                }

                sub?.setOnClickListener {
                    if (square) {
                        screen.text = "Error"
                        divB = false
                        addB = false
                        timesB = false
                        basic = false
                    }
                    if ((basic && dataOp)||basic) {
                        screen.append("-")                     //appends - to make beforeNum negative


                    } else {
                        basic = true
                        divB = false
                        addB = false
                        timesB = false
                        beforeNum = screen.text.toString().toDouble()
                        subB = true
                        screen.text = null
                    }
                }

                times?.setOnClickListener {
                    if (dataOp) {
                        screen.text = "Error"
                    } else {
                        basic = true
                        divB = false
                        addB = false
                        subB = false
                        timesB = true
                        beforeNum = screen.text.toString().toDouble()
                        screen.text = null
                    }
                }

                div?.setOnClickListener {
                    if (dataOp) {
                        screen.text = "Error"
                    } else {
                        basic = true
                        divB = true
                        addB = false
                        subB = false
                        timesB = false
                        beforeNum = screen.text.toString().toDouble()
                        screen.text = null
                    }
                }

                /**
                 * Trimmed Parser : takes in text on screen after inputting trimmed mean percentage
                 * breaks apart string to single out percentage and turn this into decimal
                 */
                fun trimmedParser(quote: String) {
                    if (quote.length > 9) {
                        val cut = quote.subSequence(quote.indexOf("=") + 2, quote.length)       //just get inputted percent
                        trimmThisMuch = cut.toString().toDouble()
                    } else {
                        trimmThisMuch = 0.0
                    }
                }

                /**
                 *Enter : Grabs assigned boolean from basic function to perform math.
                 * Resets output as new before number.
                 * Hitting enter twice while editting a dataset closes the dataset
                 */
                enter?.setOnClickListener {
                    //entering dataset values
                    if (dataOp) {
                        screen.append(",")
                    }

                    //enter an exponent or square root
                    if (tr) {
                        try{
                            if (screen.text.length > 9) {
                                trimmedParser(screen.text.toString())
                            }
                            tr = false
                        }catch(e:NumberFormatException){
                            screen.text = "Error"
                        }
                    }
                    if (expo && !tr) {
                        exponentParser(screen.text.toString())
                        expo = false
                    }
                    if (square && !tr) {
                        squareRootParser(screen.text.toString())
                        square = false
                    }
                    //account for basic four functions
                    if (addB && !tr) {
                        currentNum += beforeNum                 //add previous number to new number
                        addB = false
                    } else if (divB && !tr) {
                        currentNum =
                            beforeNum / currentNum       //previous number divided by new number
                        divB = false
                    } else if (timesB &&!tr) {
                        currentNum *= beforeNum                   //previous number times new number
                        timesB = false
                    } else if (subB&&!tr) {
                        currentNum = beforeNum - currentNum       //previous number minus new number
                        subB = false
                    }
                    if (!dataOp &&!tr) {
                        basic = false
                        screen.text = (currentNum).toString()
                        beforeNum = screen.text.toString().toDouble()
                    }

                }


        /**
         * Clear: clears screen, or dataset values
         * Resets all values
         */
        clear?.setOnClickListener {
                    if (dataOp) {
                        if(numbersList.size <= 1){
                            screen.text = ""
                            dataOp = false
                            dataC = 0
                        }else{
                            var temp = screen.text
                            try {
                                if(firstCleared){
                                    temp = temp.subSequence(0, temp.length - 1)
                                }
                                backup = temp.subSequence(0, temp.lastIndexOf(",")).toString()
                                firstCleared = true
                                numbersList.removeAt(numbersList.size - 1)
                                screen.text = backup

                            } catch (e: IOException) {                                         //catches try if at first dataset entry
                                dataC = 0
                                numbersList = arrayListOf()
                                screen.text = ""
                                firstCleared = false
                            }
                            if(numbersList.size == 0){
                                dataC = 0
                                numbersList = arrayListOf()
                                screen.text = ""
                                firstCleared = false
                            }
                        }
                    } else {
                        screen.text = ""
                    }
                    basic;addB;timesB;subB;divB;expo;square = false     //reset booleans
                    beforeNum = 0.0                                    //reset value
                    currentNum = 0.0                                  //reset value
                }


                /**
                 * Dataset Parser: Takes in string of whats on screen - will be dataset values just put in
                 * goes through the string and adds each number in dataset to List to use for Stats
                 */
                fun datasetParser(quote: String) {
                    try{
                        numbersList = arrayListOf()
                        if (quote.length > 1) {                             //if you have items in the list
                            screen.text = null                              //if you're done inputting data
                            val quoter = quote.substring(1, quote.length)
                            val temp = quoter.split(",")           //separates dataset by ,
                            for (numbers in temp) {
                                numbersList.add(numbers.toDouble())
                            }
                        }
                    }catch(e: IOException) {
                        screen.text = "Error"
                    }
                }

        /**
         * Reassign Data: Trims copy of dataset to do trimmed mean on
         * Returns average (mean) of trimmed dataset
         */
        fun reassignData(numberToCut: Int): Double {
                    val tempAr = ArrayList<Double>()
                    var counter = numberToCut
                    while (counter < numbersList.size - counter) {
                        tempAr.add(numbersList[numberToCut])
                        counter += 1
                    }
                    return tempAr.average()
                }

        /**
         * Trimmed Data: determines - given percentage- how many
         * to take off head and tail of dataset for trimmed mean
         * Returns solution : trimmed mean given percentage
         */
                fun trimmedData(): Double {
                    var solution = 0.0
                    //takes trimmed mean double
                    //if second digit is bigger than 0, then do half and half
                    if (trimmThisMuch % 10 > 0) {
                        val upper = trimmThisMuch / 10 + 1
                        val lower = trimmThisMuch / 10 - 1
                        solution = reassignData(upper.toInt()) - reassignData(lower.toInt())
                        //do bigger and smaller and difference
                    } else {
                        solution = reassignData((trimmThisMuch / 10).toInt())
                    }
                    return solution
                }


                /**
                 * Data: Brings up an empty set for user
                 * to input data into for further calculations.
                 * Hitting "{ }" twice will close dataset input option
                 * Numbers inputted are stored in list.
                 */

                data?.setOnClickListener {
                    dataC += 1
                    dataOp = true
                    if(screen.text.toString().length <= 1 && dataC %2==0){
                        screen.text = ""
                        dataOp = false
                        dataC = 0
                    }
                    if(numbersList.size > 0 && dataC % 2 == 0){
                        datasetParser(screen.text.toString())           //sends data and "closes" dataset
                        screen.text = numbersList.toString()
                        dataOp = false
                    }
                    if (dataC % 2 == 0 && numbersList.size <= 0) {
                        try{
                            datasetParser(screen.text.toString())           //sends data and "closes" dataset
                            dataOp = false
                        }catch (e:NumberFormatException){
                            screen.text = "Error"
                        }
                    }
                    if(numbersList.size > 0 && dataC%2 !=0) {
                        screen.text = numbersList.toString().dropLast(1) + ","

                    }
                    if (dataC == 1) {
                        screen.text = null                  //empty screen
                        screen.append("{")                  //provide start of dataset
                    }
                }

        fun findMedian(arrayList: List<Double>) : Double{
            var median = 0.0
            if(arrayList.size % 2 == 0){
                median = ((arrayList[arrayList.size/2] + arrayList[arrayList.size / 2 - 1])/2)
            }else{
                median = (arrayList[arrayList.size/2])
            }
            return median
        }

        /**
         * Stat: Given the dataset has values, provides the following statistics:
         * Mean, Median, First Quartile, Third Quartile, Maximum Number, Miniumum Number, Summation, Trimmed Mean
         */
                stat?.setOnClickListener {
                    var maximum = ""
                    var mini = ""
                    var summ = ""
                    var summation = 0.0

                    var mean = ""
                    var trimmedMeanSt = ""
                    var median = ""
                    if (numbersList.isNullOrEmpty()) {
                            maximum = "..."
                            mini = "..."
                            summ = "..."
                            mean = "..."
                            trimmedMeanSt = "..."
                            median = "..."
                            screen.text = "Empty {}"
                        } else {
                        maximum = numbersList.maxOrNull().toString()
                        mini = numbersList.minOrNull().toString()
                        mean = numbersList.average().toString()
                        trimmedMeanSt = trimmedData().toString()

                        for (each in numbersList) {
                            summation += each
                        }
                        summ = summation.toString()
                        var orderedList = numbersList.sortedDescending()
                        println("DECENDING $orderedList")
                        median = findMedian(orderedList).toString()
                    }
                        screen.text =
                            ("x̅ = $mean\n∑x = $summ\nmin = $mini\nMed = $median\nmax = $maximum\nXtr(%) = $trimmedMeanSt")

                    }


        /**
         * Trim: takes in number, given as a percentage to do trimmed mean to dataset
         */

        trim?.setOnClickListener {
                    tr = true
                    screen.append("Xtr(%) = ")           //take in and print w stats
                }

        /**
         * Ex: Allows for numbers to the power of a number
         * Accounts for double exponention and throws Error
         */
                ex?.setOnClickListener {
                    if (square || expo || dataOp) {
                        screen.text = "Error"
                        dataOp = false
                        expo = false
                        dataC = 0
                    } else {
                        expo = true                         //when you hit enter - does calculations
                        screen.append("^")
                    }
                }

        /**
         * Sq: Number given after this square root function provides
         * user with square root of the number selected
         * Account for nested square roots and throws Error
         */
        sq?.setOnClickListener {
                    if (expo || square || dataOp) {
                        square = false
                        dataOp = false
                        dataC = 0
                        screen.text = "Error"
                    } else {
                        square = true
                        screen.append("√")
                    }

                }
            }
        }





