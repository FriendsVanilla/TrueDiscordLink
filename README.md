# TrueDiscordLink [![Build Status](https://img.shields.io/travis/com/FireController1847/TrueDiscordLink)](https://travis-ci.com/github/FireController1847/TrueDiscordLink/) [![Latest Release](https://img.shields.io/github/v/release/FireController1847/TrueDiscordLink)](https://github.com/FireController1847/TrueDiscordLink/releases) [![Download](https://img.shields.io/github/downloads/FireController1847/TrueDiscordLink/total?color=%234C1)](https://github.com/FireController1847/TrueDiscordLink/releases) [![Issues](https://img.shields.io/github/issues/FireController1847/TrueDiscordLink)](https://github.com/FireController1847/TrueDiscordLink/issues)
Provides a link between Discord and your Minecraft server.

[See this plugin on SpigotMC](https://www.spigotmc.org/resources/truediscordlink.86967/)

## Commands
### Minecraft
- /truediscordlink or /discordlink or /discord or /tdl - The primary command. Lists other commands
- /tdl reload - Reloads the configuration
- /tdl link \[username\] - Initiates the linking process
- /tdl link yes - Confirms that the registered Discord user is in fact the correct one
- /tdl link no - Responds to inform to re-run tdl link with the first few characters
- /tdl unlink - Unlinks your Discord and Minecraft accounts.

### Discord
You can add custom commands in the configuration. These are the default ones.  
You can change the prefix in the configuration. This is the default one.
- tdl!link confirm - The final step of the linking process to confirm that it is, indeed, you
- tdl!ping - Pings the bot to ensure it's online.

## Permissions
### Star Permissions
- truediscordlink.*
- truediscordlink.command.*

### Regular Permissions
- truediscordlink.command.discord
- truediscordlink.command.reload
- truediscordlink.command.link
- truediscordlink.command.unlink
- truediscordlink.tagging

## Default Configuration
```yml
###########################################################################################
#                                         WARNING                                         #
# THIS PLUGIN IS FOR MODERATELY ADVANCED USERS. FOR A SIMPLISTIC PLUGIN, SEE DISCORD SRV  #
#  IF YOU MAKE A MISTAKE IN CONFIGURATION, WE WILL NOT BABY YOU THROUGH FIGURING IT OUT.  #
###########################################################################################

####################
#     Language     #
####################
# The name of the file in the lang/ folder to use for configuration.
# Default is en which results in lang/en.yml
lang: en

######################
#     Configured     #
######################
# Set this to true once you have configured the configuration.
configured: false

####################
#     Webhooks     #
####################
# Webhooks can only be used for relaying messages. They cannot be used to receive messages.
webhooks:

  # Whether or not webhooks should be enabled.
  enabled: true

  # A list of webhooks to send messages to.
  urls:
    - https://discord.com/webhook1

  # Whether or not to use the player's skin as the avatar for the webhook.
  use_avatar: true

  # The URL that should be used for providing the skins to the webhook for each user.
  # %uuid is replaced with the user's uuid.
  # %name is replaced with the user's name.
  skins_url: https://minotar.net/helm/%uuid%/2048.png

###############
#     Bot     #
###############
# Bots can be used for both relaying and receiving messages.
# They can also be used to provide other information about the server in unique ways.
bot:

  # Whether or not the usage of a bot is enabled.
  # This value will not change until this server is restarted.
  enabled: true

  # Whether or not the bot should re-send edited Discord messages with an asterisk at the end.
  show_edits: true

  # Whether or not discord users should be able to send messages using color codes
  allow_colors: true

  # The token of the bot that should be used.
  token: "INSERT_TOKEN_HERE"

  # A list of channel ids that should receive messages from the Minecraft server.
  # Leave blank to disable receiving messages from the Minecraft server.
  # Useful if you want to use webhooks in combination with a bot.
  from_mc_channels:
    - 000000000000000000

  # A list of channel ids to watch for when messages are sent to send them to the Minecraft server.
  # Leave blank to disable sending messages to the Minecraft server.
  to_mc_channels:
    - 000000000000000000

  # Options related to the Discord server that become available when using a bot
  discord:

    # The prefix to use for Discord commands
    # Defaults to "tdl!" if null or blank
    prefix: "tdl!"

    # The different games / activities that the Discord bot can show
    activities:
      - "Minecraft"
      - "mywebsite.net"

    # The rate at which these statuses will cycle in seconds (minimum 15 seconds)
    # Changes take effect after restart
    activity_cycle_speed: 300

    # The commands to include on the Discord server that users can run.
    # Leave blank to disable all commands.
    # Supports Placeholder API
    commands:
      ip: "Join us at `server.mywebsite.net`"
      website: "Visit our website at https://www.mywebsite.net/"
      list: "Online Players: %listplayers_in_world_world%"
      online: "There are currently %server_online% out of %server_max_players% players online."

    # Whether or not to auto-update the channel topic for specific channels
    # Leave blank to disable
    auto_channel_topic_ids:
      - 000000000000000000

    # The rate at which the channel topics should be updated in seconds (minimum 5 minutes) (this is due to a Discord rate limit)
    # Set to -1 to disable updating (aka set once when the bot launches)
    # Changes take effect after restart
    auto_channel_topic_update_rate: 300

    # The message to put for auto-channel-topic.
    # Supports Placeholder API
    auto_channel_topic_message: ""

  # A list of options that allow you to link accounts between a Discord server and Minecraft
  linking:

    # Whether or not the linking of user accounts is enabled
    enabled: true

    # The id of the server to search through the players list for
    server: "000000000000000000"

    # A list of roles to give users after they have linked their account
    # In the format: 'discord_server_id:role_id'. See https://support.discord.com/hc/en-us/articles/206346498
    roles:
      - "000000000000000000:000000000000000000"


###########################
#     System Messages     #
###########################
# Messages that will send on the following events. Messages configurable in lang file.
# Set to false to disable the messages from sending.
events:
  server_start: true
  server_shutdown: true
  player_join: true
  player_quit: true
  player_death: true
  player_advance: true

###################
#     Tagging     #
###################
tagging:
  # Whether or not to allow Minecraft users to tag Discord users
  enable_user_tagging: true

  # Enables detection of attempted tags to convert them to proper Discord mentions. (Ex: @Fire -> <@112732946774962176>)
  # If enable_user_tagging is enabled, you probably want this enabled to.
  # This only works if bot.enabled is set to true.
  enable_user_tagging_shortcut: true

  # Whether or not to allow Minecraft users to tag Discord roles
  enable_role_tagging: false

  # Whether or not to allow Minecraft users to tag Discord's @everyone and @here
  enable_everyone_tagging: false

  # Whether or not to make a noise & show chat color when a Minecraft user's username is said in Discord.
  mention_minecraft_users: true

  # Don't change this unless you know exactly what it should be.
  mention_minecraft_noise: "ENTITY_EXPERIENCE_ORB_PICKUP"

  # A list of guild ids to use the member lists from to check for mentions.
  mention_servers:
    - 000000000000000000

###################
#     Database    #
###################
# Used to keep track of player linking.
database:

  # Possible options: SQLite, MySQL, MariaDB
  type: "SQLite"

  # The host of the database. 'localhost' and '127.0.0.1' both point to the local machine
  host: "127.0.0.1"

  # The database to use. Make sure it is created before attempting to connect.
  # Points to a file path from the plugin directory for SQLite. Do not include .db
  database: "truediscordlink"

  # The username to use to login to the database. Leave blank for SQLite
  username: ""

  # The password to use to login to the database. Leave blank for SQLite
  password: ""

  # The prefix to prepend to all created tables
  table_prefix: "tdl_"
```