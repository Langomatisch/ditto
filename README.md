# Ditto Debugger

This is a simple Minecraft Spigot/BungeeCord plugin that allows you to debug your code more quickly.


### WARNING: This is a work in progress project. It will change and break over time. - Not everything is implemented yet.
### Only spigot is supported at the moment.

## How to use

First of all you need to use a DebugWrapper to wrap your code.
Then you can use the Debugger to debug your code.

See the Example module for more information.

The command to use the debugger:

`/debug <getOrExecute|set|list> <wrapper> <player> <method or field> [args]`

## Ideas:

- [ ] Add support for BungeeCord
- [ ] Notify the player when a value has changed
- [ ] Add support for private/final/static fields
- [ ] Add support for more internal classes of Spigot
- [ ] Add support for more internal classes of BungeeCord
- [ ] Create a more sophisticated way to use the parser