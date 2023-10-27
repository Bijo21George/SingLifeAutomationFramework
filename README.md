# Automation Testing Framework
Cucumber-Java-Maven based Automation framework for Mobile and WebApp testing.

## To Run Tests on Local device
- Start appium server at 0.0.0.0 at port 4723

## Maven commands
- ``mvn clean test -PMobileCloudSequential``
- ``mvn clean test -POnlineCloudSequential``

## To Run Tests on Browserstack device
- For parallel testing use parallel testNG cloud xml files.
- Update Thread count in testngCloud.xml
- Update Tag in TestNGParallelRunner1Mobile,2 for mobile
- Update Tag in TestNGParallelRunner1Online,2 for online

## Framework Features:
- Mobile App Testing (On both local and cloud)
- Web App Testing (On cloud)

## Setup For Local Device Execution
- ### Softwares:
- JDK (v11)
- Android Studio (for adb and for executing on android virtual devices)
- Appium (v1.21)
- IntelliJ
- Git
- Maven

- ### Set Env variables in Windows machine:
- JAVA_HOME with C:\Program Files\Java\jdk-17.0.2
- Path with %JAVA_HOME%\bin

- ### Set Env variables in Appium Server:
- ANDROID_HOME with C:\Users\user\AppData\Local\Android\Sdk
- JAVA_HOME with C:\Program Files (x86)\Java\jre1.8.0_311

## ADB
- With Android Studio ADB will be installed automatically.
- To enable developer options: About Phone->Software Information-> Tap Build Number multiple times
- Navigate to Developer options and turn on USB debugging
- Navigate to C:\Users\user\AppData\Local\Android\Sdk\platform-tools in command prompt and type ``adb``
- Now type `adb devices` to view the list of devices connected and their IDs
- To install apk type ``adb -s <DEVICE ID> install <PATH TO APK>``

## GitHub
- ### For importing existing project from GitHub and creating feature branch
  - Clone the project:
    ````
    git clone https://github.com/harigithub1/AutomationFramework.git
    ````
  - Create your feature branch: `git branch my-new-feature-branch-name`
  - Add your code changes: `git add my-file-name`
  - Commit your changes: `git commit -m "commit message"`
  - Push to the branch: `git push origin my-new-feature-branch-name`
  - Open GitHub and create a pull request to main branch
  - To delete feature branch in github: `git push -d origin feature-branch-name`