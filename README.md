# TabletAviso

Aplicativo Android 10 para mostrar um aviso uma única vez.

## Comportamento

- Primeira abertura: mostra o aviso.
- Toque em "ENTENDI": grava `aviso_lido=true`.
- Próximas aberturas: fecha imediatamente.
- Registra `BOOT_COMPLETED` e `USER_UNLOCKED` para tentar mostrar o aviso após a inicialização.

## Importante sobre Android 10

O Android pode bloquear a abertura de uma Activity a partir do background. Se isso ocorrer no tablet, o APK ainda funciona normalmente ao ser aberto manualmente.

Para garantir execução automática no boot em uma GSI personalizada, uma opção é instalar o APK como aplicativo de sistema/priv-app dentro da imagem do sistema.

## Build

Abra esta pasta no Android Studio e faça:

Build > Build Bundle(s) / APK(s) > Build APK(s)

O APK ficará em:

app/build/outputs/apk/debug/app-debug.apk
"# test" 
