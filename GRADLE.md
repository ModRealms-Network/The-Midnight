# The Gradle project of The Midnight

The Midnight is divided into four projects:

- **`:` - the root project.** This project does not contain any useful code. It's not being built and only combines the three subprojects listed below to make the system run in the IDE.
- **`:MidnightAPI` - the API code.** This project contains the API classes which are publicly exposed for compatibility support.
- **`:MidnightCore` - the core code.** This project contains the systems and hooks of the Midnight that are required in order for the Midnight to work properly. This project contains the API classes.
- **`:MidnightMod` - the implementation code.** This project contains the implementation of the API and the core classes and makes them 'the Midnight'.
- **`buildSrc` - the Gradle code.** A simple Gradle plugin uses to do advanced Gradle magic, such as renaming a java package when packaging a dependency into one of the jar files.

## Building

To build the Midnight, simply run `./gradlew :build`. It will build `buildSrc` and then all subprojects, and you can find the jar files in the `build/libs` folders of all subprojects after it has built successfully.

## Updating the Changelog

The Midnight's changelog can be edited in `changelog.json`. To update the changelogs in the `versioninfo/` folder, simply run `./gradlew makeVersionInfo`.
