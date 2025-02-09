# Passkey

Passkey Integration for android with ktor

### Note

You need to have https request to use Passkey.
Try using ngrak[https://ngrok.com/](https://ngrok.com/) if you are broke like me :)

## Install

1. create a ngrok account.
2. follow the instructions on https://dashboard.ngrok.com/get-started/setup/windows
3. start a proxy server. Ex: ngrok http http://localhost:8080
4. Replace the urls on Server[https://github.com/POULASTAAdAS/Passkey/blob/main/PasskeyServer/src/main/kotlin/com/poulastaa/utils/Constants.kt](https://github.com/POULASTAAdAS/Passkey/blob/main/PasskeyServer/src/main/kotlin/com/poulastaa/utils/Constants.kt) and App Constants[https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/java/com/poulastaa/passekyapp/utils/Constants.kt](https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/java/com/poulastaa/passekyapp/utils/Constants.kt) , string.xml[https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/res/values/strings.xml](https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/res/values/strings.xml)
5. Run Ktor , android App.
6. Obser Android Log to follow SignUp and Login Flow
