ForestryFix
===========

A mod that disables Forestry's mod protection on the client.

As you may or may not know, if Forestry detects that one of the mods on your client is modified, then it will prevent Minecraft from starting. This can get very annoying, especially if you need to modify a mod for your server.

So, that is where this fix is stepping in. Just place it into your 'mods' folder, and it will make Forestry *not* stop Minecraft when some mod fails the verification.

####Installation
Place into the **mods** folder. Not required on the server, because Forestry doesn't perform mod checks there.

####How does it work?
ForestryFix uses the built-in Forge library to modify Forestry's code when it is just about to load, removing the method call that stops Minecraft. It doesn't do anything else, so you can even see the warning messages that Forestry leaves just before stopping everything.
