import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.findByType
import org.gradle.plugins.signing.SigningExtension
import java.io.File

val Project.cpuFeaturesDir: File
    get() = rootDir.resolve("cpu_features")

val Project.ci: Provider<Boolean>
    get() = providers.environmentVariable("CI").map { it.toBoolean() }.orElse(false)

val Project.ciPublishCore: Provider<Boolean>
    get() = providers.environmentVariable("CI_PUBLISH_CORE").map { it.toBoolean() }.orElse(false)

fun Project.publishCore() {
    if (!ci.get() || ciPublishCore.get()) {
        plugins.apply("publishing-conventions")
    }
}

fun Project.configurePublishing() {
    extensions.findByType<SigningExtension>()?.apply {
        val signingKey = findProperty("signingKey") as? String
        val signingPassword = findProperty("signingPassword") as? String
        if (signingKey != null) {
            println("${project.name}: Signing with in-memory PGP keys")
            useInMemoryPgpKeys(signingKey, signingPassword)
        }
    }
}
