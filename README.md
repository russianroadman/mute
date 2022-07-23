# mute
Telegram bot for dealing with voice messages in group chats

To create telegram bot and get your personal bot name and token, visit https://core.telegram.org/bots

Before start, to use bot you will have to create two system environment variables:
- MUTE_BOT_NAME=<bot_name>
- MUTE_BOT_TOKEN=<bot_token>

This application has two simple implementations for handling voice messages properly:
- Delete
- Respond angrily

When application started, that means bot is already working

To stop bot, use GET request `/stop` and to start again, use `/start`

You can use `/get-muter-associations` GET request, to get list of available anti-voice-message services

By default there are two:
- VoiceMessageMeanResponder (Respond angrily to voice message)
- VoiceMessageTerminator (Delete voice message)

You can create implementation of `MuteService.kt` class and it will be added to list of services as well

To set service without restarting bot, use `/set-muter?value=<class_name>` GET request, where `class_name` is any service from `/get-muter-associations` response list
