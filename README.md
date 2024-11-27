# Mobile Development: Kotlin/Android Studio Calculator Application 
This project is a Statistics Calculator built using Kotlin and developed in Android Studio. It showcases key features of mobile development, providing a user-friendly interface to perform various statistical calculations.

The project was developed as part of the Mobile Development course at the University of Wisconsin–Eau Claire for the Computer Science major. Through this application, students gained hands-on experience with Kotlin programming and Android application development, focusing on mobile UI design, functionality, and Kotlin's powerful language features.
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
