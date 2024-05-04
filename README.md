# Example-Mod

> This meticulously crafted template, intricately woven with the threads of our organizational ethos, stands as 
> an unparalleled bastion reserved exclusively for the consummate utilization within the sacred halls of our esteemed 
> establishment. Its sanctified purpose, deeply enshrined within the annals of our operational protocol, is irrevocably 
> bound by the immutable decree that its sacred contents shall remain veiled from the purview of all external entities 
> and the prying gaze of the wider populace. Its sanctity is upheld with an unwavering resolve, ensuring that it remains 
> secluded within the inner sanctum of our corporate enclave, shielded from the tumultuous winds of the outside world 
> and safeguarded against the voracious appetite of unauthorized dissemination or public exposure.

**tl;dr**: This template is designated solely for internal use within our organization, intended strictly for private 
access and not for distribution or publication to external parties or the public.

## Notes to Cursee Dev (that's you)

[The Example-Mod repository](https://github.com/Cursee-Development/Example-Mod) is marked as a template on GitHub. 
By clicking on `Use this template`, we create a copy of the repository with the same directory structure and files, 
that is not an explicit fork of the original repository.
When creating a new repository in this manner, make sure to select `Include all branches`, as we develop on Minecraft 
versions 1.20 and up, with 1.20.1 and the latest version being the most important. 

**Repositories that are not associated 
with our client Lupin must remain private until the mod has been published on CurseForge and Modrinth.**

## Getting Started

After the initial repository is created from [Example-Mod](https://github.com/Cursee-Development/Example-Mod), it's best
to begin with a refactor of the project, to change the namespace from `com.cursee.example` to the appropriate namespace
for the mod being created. This guide assumes that you're using IntelliJ IDEA, with default keybindings.

> Make sure to change the rootProject.name property in settings.gradle, this should match the folder name of your project!!!

1. Expand the relevant directories (common, fabric, forge, and neoforge projects, down to the *java/com* and *java/resources* directories)
2. In any project, select the `example` package from `com.cursee.example`, and press `SHIFT+F6` to refactor. Change `example` to the appropriate mod ID.
3. Update files ending with `.mixins.json` (common, fabric, forge, and neoforge projects) to have the appropriate mod ID in the filename, and appropriate namespace within the file.
4. In the fabric project, ensure that *resources/fabric.mod.json* has the correct mod ID **within** the file. (leave the file named **fabric.mod.json**)
5. Update `gradle.properties` with the appropriate values for the mod being created. Include the client as the first author on the project.
6. In the common project, update CommonConstants with the correct mod ID and mod name for the mod being created.

---

This project was created as a fork from [MultiLoader-Template](https://github.com/jaredlll08/MultiLoader-Template). 

The following are notes from the contributors to the MultiLoader-Template project:

### IntelliJ IDEA

1. Clone or download this repository to your computer.
2. Configure the project by editing the `group`, `mod_name`, `mod_author`, and `mod_id` properties in the `gradle.properties` file. You will also need to change the `rootProject.name`  property in `settings.gradle`, this should match the folder name of your project, or else IDEA may complain.
3. Open the template's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
4. If your default JVM/JDK is not Java 17 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`and changing the value to a valid Java 17 JVM. You will also need to set the Project SDK to Java 17. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
5. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
6. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Forge > Tasks > forgegradle runs > genIntellijRuns`. Run this task to set up run configurations for Forge.
7. Open your Run/Debug Configurations. Under the Application category there should now be options to run Forge and Fabric projects. Select one of the client options and try to run it.
8. Assuming you were able to run the game in step 7 your workspace should now be set up.

### Eclipse
While it is possible to use this template in Eclipse it is not recommended. During the development of this template multiple critical bugs and quirks related to Eclipse were found at nearly every level of the required build tools. While we continue to work with these tools to report and resolve issues support for projects like these are not there yet. For now Eclipse is considered unsupported by this project. The development cycle for build tools is notoriously slow so there are no ETAs available.

## Development Guide
When using this template the majority of your mod is developed in the Common project. The Common project is compiled against the vanilla game and is used to hold code that is shared between the different loader-specific versions of your mod. The Common project has no knowledge or access to ModLoader specific code, apis, or concepts. Code that requires something from a specific loader must be done through the project that is specific to that loader, such as the Forge or Fabric project.

Loader specific projects such as the Forge and Fabric project are used to load the Common project into the game. These projects also define code that is specific to that loader. Loader specific projects can access all of the code in the Common project. It is important to remember that the Common project can not access code from loader specific projects.
