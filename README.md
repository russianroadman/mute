# mute
Telegram bot for dealing with voice messages in group chats

To create telegram bot and get your personal bot name and token, visit https://core.telegram.org/bots

Before start, to use bot you will have to create two system environment variables:
- MUTE_BOT_NAME=<bot_name>
- MUTE_BOT_TOKEN=<bot_token>

This application has multiple simple implementations for handling voice messages properly

For example:
- Delete
- Respond angrily

When application started, that means bot is already working

To stop bot, use GET request `/stop` and to start again, use `/start`

Available GET requests:
- `stop` - stop bot
- `start` - start bot
- `get-mute-service-list` - get available services list
- `set-mute-service?value=<service_name>` - set service to handle voice messages (from `get-mute-service-list` list)
- `get-current-service` - get currently selected service name

By default, there are some MuteService implementations like:
- VoiceMessageMeanResponder (Respond angrily to voice message)
- VoiceMessageTerminator (Delete voice message)

You can create your own implementation of `MuteService.kt` class, and it will be automatically added to list of services as well

This rule also applies to `CommandResolver.kt`. You can add command to CommandEnum class and create implementation of `CommandResolver.kt` interface. It will be added to context automatically when you run application
