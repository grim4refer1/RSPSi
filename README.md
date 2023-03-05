# RSPSi -- Build guide

# Contents {#contents .TOC-Heading}

[RSPSi -- Build guide [1](#rspsi-build-guide)](#rspsi-build-guide)

[Step 1 [2](#step-1)](#step-1)

[Step 2 [2](#step-2)](#step-2)

[Step 3 [2](#step-3)](#step-3)

[Step 4 [3](#step-4)](#step-4)

[Step 5 [4](#step-5)](#step-5)

[Result [6](#result)](#result)

## Step 1

First download IntelliJ (This could work with other IDEs but I cannot
guarantee that they would be able to handle such an old database of
code).

Link to download is here:
[https://www.jetbrains.com/idea/download/#section=windows](https://www.jetbrains.com/idea/download/%23section=windows)

## Step 2

Import the project by unzipping the original download and selecting File
-\> Open -\> *the RSPSI-Master code base*

## Step 3

Allow Gradle to build the project, IntelliJ will show you that Gradle is
working in the lower left corner of the screen.

Then access the main "build.gradle" file.

![Graphical user interface, text, application Description automatically
generated](media/image1.png){width="2.0211154855643043in"
height="1.2397561242344708in"}

Here you should go down to plugins and take the comment marks from

**"id \'org.openjfx.javafxplugin\' version \'0.0.7\' & "id
\'org.beryx.runtime\' version \'1.2.0\' apply false"**

![Graphical user interface, text Description automatically
generated](media/image2.png){width="6.268055555555556in"
height="2.395138888888889in"}

Also go down to "**subprojects"** and uncomment **" apply plugin:
\'org.openjfx.javafxplugin\' ".**

![Text Description automatically
generated](media/image3.png){width="5.042369860017498in"
height="2.1044608486439196in"}

Also go down to Javafx and uncomment it

![Graphical user interface, text, application Description automatically
generated](media/image4.png){width="6.268055555555556in"
height="1.042361111111111in"}

## Step 4

Download the following files and put them somewhere easily accessible.

(JavaFX v11 & jfoenix & Java 11 JDK)

Link : Check Fiverr for the "RequiredFiles" archive.

Go to File -\> Project structure

Access Modules -\> dependencies

![A screenshot of a computer Description automatically generated with
medium confidence](media/image5.png){width="6.268055555555556in"
height="2.1243055555555554in"}

Press the "+ "button and choose the Jfoneix library. Press "apply". IF
asked for which classes to apply it to, select all of them.

In the project structure, advance to "Libraries" and Press the "+ "
button. Then choose Java add all the modules in the JavaFX sdk.

![A screenshot of a computer Description automatically
generated](media/image6.png){width="5.823729221347332in"
height="3.5838331146106737in"}

Do the same with Jfoenix but just choose the jar.

![](media/image7.png){width="5.584113079615048in"
height="0.34379811898512685in"}

## Step 5 

To set up the run configuration, go to Run -\> Edit configurations and
add a java application.

![Graphical user interface, text, application Description automatically
generated](media/image8.png){width="3.2608716097987753in"
height="5.761220472440945in"}

Then click "Modify options"

![](media/image9.png){width="6.268055555555556in"
height="0.46944444444444444in"}

And press the following button.

![](media/image10.png){width="3.781778215223097in"
height="0.33338035870516186in"}

From here, copy paste the following configuration into your vm box
whilst CHANGING THE

Javafx sdk path so that it reflects where your path is.

\--module-path \"change this path \\javafx-sdk-11.0.1\\lib\"
\--add-modules=javafx.swing,javafx.graphics,javafx.fxml,javafx.media,javafx.web
\--add-reads javafx.graphics=ALL-UNNAMED \--add-opens
javafx.controls/com.sun.javafx.charts=ALL-UNNAMED \--add-opens
javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED \--add-opens
javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED \--add-opens
javafx.graphics/com.sun.javafx.css=ALL-UNNAMED \--add-opens
javafx.base/com.sun.javafx.runtime=ALL-UNNAMED

Then choose the main class and copy my configuration

![Graphical user interface, text Description automatically
generated](media/image11.png){width="6.268055555555556in"
height="0.9583333333333334in"}

## Result 

![Graphical user interface, text, website Description automatically
generated](media/image12.png){width="6.268055555555556in"
height="2.5506944444444444in"}

![A screenshot of a computer Description automatically generated with
medium confidence](media/image13.png){width="6.268055555555556in"
height="4.026388888888889in"}



- Generate...

    - Getter and Setter

        - Generates getter and setter methods for fields in a class

    - Constructor

        - Generates a constructor method for a class

    - equals() and hashCode()

        - Generates `equals()` and `hashCode()` methods for a class

    - toString()

        - Generates a `toString()` method for a class

    - Delegate Methods

        - Generates delegate methods for a class

    - Builder

        - Generates a builder pattern for a class

    - Test methods

        - Generates test methods for a class

    - Delegate Methods

        - Generates delegate methods for a class

    - Implementation methods

        - Generates method stubs for an interface

    - Parcelable

        - Generates `Parcelable` methods for a class
