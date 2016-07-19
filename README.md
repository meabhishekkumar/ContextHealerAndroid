# Contextual Healer Android APP

### How to Build the project:

1. Install Prerequisite tools
- Android Studio with Android SDK
- Android NDK

2. Clone the code from github


### For JNI-Build

- For ARM architecture

Step 1: Add NDK_HOME to system Path


```
$ cd jni-build
$ export NDK_HOME="/Users/dev/Library/Android/ndk"
$ export PATH=$PATH:$NDK_HOME
```
Step 2 :

```
$ make
$ make install
```


- For x86 and x86_64 architecture

Step 1: Add NDK_HOME to system Path


```
$ cd jni-build-x86
$ export NDK_HOME="/Users/dev/Library/Android/ndk"
$ export PATH=$PATH:$NDK_HOME
```
Step 2 :

```
$ make
$ make install
```


3. Trained Model file

Put the tensorflow model file (*.pb file) in **app/src/main/assets** folder.




