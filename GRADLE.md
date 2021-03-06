# The Gradle project of The Midnight

The Midnight is divided into four projects:

- **`:` - the root project.** This project does not contain any useful code. It's not being built and only combines the three subprojects listed below to make the system run in the IDE.
- **`:MidnightAPI` - the API code.** This project contains the API classes which are publicly exposed for compatibility support.
- **`:MidnightCore` - the core code.** This project contains the systems and hooks of the Midnight that are required in order for the Midnight to work properly. This project contains the API classes.
- **`:MidnightMod` - the implementation code.** This project contains the implementation of the API and the core classes and makes them 'the Midnight'.
- **`buildSrc` - the Gradle code.** A simple Gradle plugin uses to do advanced Gradle magic, such as renaming a java package when packaging a dependency into one of the jar files.

## Building

To build %he Midnight, simply run `./gradlew build`. It will build `buildSrc` and then all subprojects, and you can find the jar files in the `build/libs` folders of all subprojects after it has built successfully.

## Testing The Midnight

To test The Midnight, simply generate the run files of whatever IDE you're using (`./gradlew genIntellijRuns` or `./gradlew genEclipseRuns`) and run the client/server as normal. If The Midnight does not initialize with Minecraft, simply generate the run files again. Please note that because of the complex Gradle structure, your IDE might take a little bit of time before it loads the game.  
You may have noticed that there's a `runTestServer` run configuration. **Do not run it!** It is meant for our GitHub Actions CI and you will just crash on world load if you run it, since that is what it's meant to do.

## Updating the Changelog

The Midnight's changelog can be edited in `changelog.json`. To update the changelogs in the `versioninfo/` folder, simply run `./gradlew makeVersionInfo`.

## Information on GitHub Actions

We use GitHub Actions to build and test The Midnight on every commit, and at 12:00 PM (whatever time zone GH Actions uses, probably UTC) every Monday and Friday. To ensure that The Midnight is able to run on the vast majority of platforms, the server test is conducted on Windows Server, macOS, and Ubuntu. If you are making a pull request, please **do not change** the YAML files located under `.github/workflows/`. We will ask that you revert them in your PR if you happen to do so.
