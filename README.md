# Passkey

Passkey Integration for android with ktor

### Note

<p>You need to have https request to use Passkey.</p>
You can use ngrak if you are broke like me :)

## Install

1. create a ngrok [account](https://ngrok.com/).
2. follow the instructions on https://dashboard.ngrok.com/get-started/setup/windows
3. start a proxy server. Ex: ngrok http http://localhost:8080
4. Replace the urls on [Server](https://github.com/POULASTAAdAS/Passkey/blob/main/PasskeyServer/src/main/kotlin/com/poulastaa/utils/Constants.kt) and App [Constants](https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/java/com/poulastaa/passekyapp/utils/Constants.kt) , [string.xml](https://github.com/POULASTAAdAS/Passkey/blob/main/PassekyApp/app/src/main/res/values/strings.xml)
5. Run Ktor , android App.
6. Obser Android Log to follow SignUp and Login Flow

# Perview

<table>
    <tr>
        <td>
            <img scr="https://github.com/POULASTAAdAS/Passkey/blob/main/ss/showcase-gif.gif"  width="320" height="620">
        </td>
    </tr>
</table>

# License

```xml
Designed and developed by 2023 Poulastaa Das

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
