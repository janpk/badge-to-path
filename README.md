# Badge To Path

> Generate a badge and write to path

A badge generator action written in [Kotlin](https://kotlinlang.org/) powered
by [Quarkus GitHub Action](https://github.com/quarkiverse/quarkus-github-action). Internally it uses
the [Badge4j](https://github.com/silentsoft/badge4j) library to generate the svg badges. This means
that all styles, colors and logos supported by the [Badge4j](https://github.com/silentsoft/badge4j)
library is supported by this action.

This badge generator started as an experiment of utilizing the [Quarkus]() framework and [Kotlin]()
to create GitHub Actions with the least amount of effort and code.

The action was created for the specific usecase of saving the generated badge into a dedicated
branch of the target repository. See Example Usage for more details.

## Usage

![](docfiles/default.svg)

### Inputs

| Parameter      | Mandatory | Default Value | Description                                                                                           |
|----------------|-----------|---------------|-------------------------------------------------------------------------------------------------------|
| `style`        | false     | flat          | The style to apply to the badge [**flat,flat-square,for-the-badge,plastic,social**]                   |
| `label`        | true      |               | The left label of the badge, usually static                                                           |
| `message`      | true      |               | The right status of the badge, usually based on results                                               |
| `labelColor`   | false     | #007ec6       | The color for the label part of the badge                                                             |
| `messageColor` | false     | #9f9f9f       | The color for the message part of the badge                                                           |
| `link`         | false     |               | Optional link to add to the badge                                                                     |
| `logo`         | false     |               | Logo to put on the badge. Values to use are **simple-icons** slug or **data:image/svg+xml;base64,..** |
| `logoWidth`    | false     |               | The width of the logo if other than logo default                                                      |
| `path`         | false     |               | Output image path                                                                                     |
| `github-token` | true      |               | Github Token                                                                                          |

#### Styles

| Style         | Example                       |
|---------------|-------------------------------|
| flat          | ![](docfiles/flat.svg)        |
| flat-square   | ![](docfiles/flat-square.svg) |
| for-the-badge | ![](docfiles/forthebadge.svg) |
| plastic       | ![](docfiles/plastic.svg)     |
| social        | ![](docfiles/social.svg)      |

#### Colors

The named colors supported are

![](docfiles/color-brightgreen.svg)
![](docfiles/color-green.svg)
![](docfiles/color-yellow.svg)
![](docfiles/color-yellowgreen.svg)
![](docfiles/color-orange.svg)
![](docfiles/color-red.svg)
![](docfiles/color-blue.svg)
![](docfiles/color-grey.svg)
![](docfiles/color-lightgrey.svg)
![](docfiles/color-gray.svg)
![](docfiles/color-lightgray.svg)
![](docfiles/color-critical.svg)
![](docfiles/color-important.svg)
![](docfiles/color-success.svg)
![](docfiles/color-informational.svg)
![](docfiles/color-inactive.svg)

In addition,

- Any valid [CSS color](https://developer.mozilla.org/en-US/docs/Web/CSS/color_value)
    - named color
        - ![](docfiles/color-black.svg)
        - ![](docfiles/color-rebeccapurple.svg)
        - etc.
    - hexadecimal numbers
        - ![](docfiles/color-ff69b4.svg)
        - ![](docfiles/color-9cf.svg)
        - etc.
    - rgb[a](red, green, blue[, opacity])
    - cmyk[a](cyan, magenta, yellow, black[, opacity])
    - hsl[a](hue, saturation, lightness[, opacity])

### Outputs

`badge-src` - The generated badge svg in xml format

## Example Usage

```yaml
uses: janpk/badge-to-path@v1
with:
  label: 'Lines Of Code'
  message: '1.3K'
  labelColor: 'blue'
  messageColor: 'grey'
  path: 'badges/loc.svg'
```

### Incorporating Into a Workflow

For more extensive examples, please see the [badge-to-path-playground](https://github.com/janpk/badge-to-path-playground)

### Creating a dedicated branch for badges

I have found that having a dedicated branch to keep generated badges work the best for me. To create
an empty branch

```bash
git checkout --orphan <branchname>

git rm -rf .
```

That gets rid of all the files tracked by git from the branch. In addition there you might have some
files that are not tracked by git that you want to remove since the second command above probably
removed the .gitignore file from the branch.

```bash
rm <some lingering file>
rm -Rf <some lingering directory>
```

After this you should probably add a README.md file to the branch to remind anyone who might stop by
the purpose of the branch. Then you add the file(s) you want, commit and push

```bash
git add .

git commit -m "commit message"

git push -u origin <branchname>
```

If you want to write generates badges to this branch, you need to check out the branch in your
workflow job

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Checkout badges branch to a badges directory nested inside first checkout
        uses: actions/checkout@v3
        with:
          ref: badges
          path: badges
```

you can then specify the path in the badge-to-path action like

```yaml
uses: janpk/badge-to-path@v1
with:
  label: 'Lines Of Code'
  message: '1.3K'
  labelColor: 'blue'
  messageColor: 'grey'
  path: 'badges/loc.svg'
```

and then you need to remember adding a commit step to your job like

```yaml
      - name: Commit the badge (if it changed)
        if: github.ref == 'refs/heads/main'
        run: |
          cd badges
          if [[ `git status --porcelain *.svg` ]]; then
            git config --global user.email "test@example.com"
            git config --global user.name "Badge Gen"
            git add *.svg
            git commit -m "Autogenerated badge(s)" --allow-empty
            git push
          fi
```

## Developer Related

When pushing to the `main` branch, the GitHub Action artifact is automatically published to the
Maven repository of this GitHub repository.

The `action.yml` descriptor instructs GitHub Actions to run this published artifact using JBang when
the action is executed.

## Related Guides

- GitHub
  Action ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-github-action/dev/index.html)):
  Develop GitHub Actions in Java with Quarkus
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
