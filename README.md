# Badge To Path

> Generate a badge and write to path

This repository contains a GitHub Action powered by [Quarkus GitHub Action](https://github.com/quarkiverse/quarkus-github-action).

When pushing to the `main` branch, the GitHub Action artifact is automatically published to the Maven repository of this GitHub repository.

The `action.yml` descriptor instructs GitHub Actions to run this published artifact using JBang when the action is executed.

## Related Guides

- GitHub Action ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-github-action/dev/index.html)): Develop GitHub Actions in Java with Quarkus
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
