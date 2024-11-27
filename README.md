# Kotlin Calculator
Mobile Development Introduction to Kotlin/Android Studio calculator application. A Kotlin language based Statistics Calculator produced through Android Studios.

Mobile Development class at UW-Eau Claire provided this project in class for the Computer Science Major. 
***
## Invalid Input
|Input|Example|Description|
|-----|-------|-----------|
|#√#|2√16   |Must do multiplication of a square root separately.|
|#Xtr(%)|√Xtr(%), 4Xtr(%)|This button only works isolated, not in pair with other buttons.|
|#^#^#|2^3^2|Can not nest exponents.|
|√√#|√√16|Can not nest square roots.|
|{√, #^}|{√4, 2^5}|Can not include exponents or square roots in dataset input.|

## Calculator Functions
|Button|Input|Function|
|------|-----|--------|
|Stat| |Provides mean, max, min, median, trimmed mean, and summation of dataset values.|
|^|#|Raises previous number to the power of proceeding number.|
|√|#|Provides square root of proceeding number.|
|Xtr(%)|#|Given a percentage (#), calculates trimmed mean on dataset values to show in Stat|
|-|#|Allowed to use for negative number calculations.|
