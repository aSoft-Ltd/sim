import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(kotlinz.plugins.multiplatform) apply false
    alias(kotlinz.plugins.serialization) apply false
    alias(kotlinz.plugins.compose) apply false
    alias(asoft.plugins.library) apply false
    alias(vanniktech.plugins.maven.publish) apply false
    alias(kotlinz.plugins.root.compiler.compose) apply false
    alias(kotlinz.plugins.dokka)
}

repositories {
    publicRepos()
}

val g = "tz.co.asoft"
val v = libs.versions.asoft.get()

allprojects {
    group = g
    version = v
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Nation")
    outputDirectory.set(rootDir.resolve("docs"))
    moduleVersion.set(asoft.versions.root.get())
    includes.from("ReadMe.md")
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    val p = this
    version = v

    configure<MavenPublishBaseExtension> {
        publishToMavenCentral(SonatypeHost.DEFAULT, automaticRelease = true)

        signAllPublications()

        coordinates("tz.co.asoft", p.name, v)

        pom {
            name.set(p.name)
            description.set(p.description)
            inceptionYear.set("2019")
            url.set("https://github.com/aSoft-Ltd/sim")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://github.com/aSoft-Ltd/sim/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("andylamax")
                    name.set("Anderson Lameck")
                    url.set("https://github.com/andylamax/")
                }
            }
            scm {
                url.set("https://github.com/aSoft-Ltd/sim/")
                connection.set("scm:git:git://github.com/aSoft-Ltd/sim.git")
                developerConnection.set("scm:git:ssh://git@github.com/aSoft-Ltd/sim.git")
            }
        }
    }
}