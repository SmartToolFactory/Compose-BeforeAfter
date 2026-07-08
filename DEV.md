# Developer guide

Developer setup, dependency coordinates, API reference snippets, and usage examples for Compose Before-After.

[Back to README](README.md)

## Requirements

- JDK 17 or newer
- Android SDK Platform 37

## Gradle Setup

To get a Git project into your build:

**Step 1:** Add the JitPack repository in `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2:** Add the dependency

<details>
<summary>Installation options</summary>

### Release version

```gradle
dependencies {
    implementation 'com.github.SmartToolFactory:Compose-BeforeAfter:<version>'
}
```

where `<version>` is one available in [![](https://www.jitpack.io/v/SmartToolFactory/Compose-BeforeAfter.svg)](https://www.jitpack.io/#SmartToolFactory/Compose-BeforeAfter)

### Snapshot version

To use the latest `master` build from [JitPack](https://www.jitpack.io/#SmartToolFactory/Compose-BeforeAfter/master-SNAPSHOT):

```gradle
dependencies {
    implementation 'com.github.SmartToolFactory:Compose-BeforeAfter:master-SNAPSHOT'
}
```

### Commit version

To use a specific commit from [JitPack](https://www.jitpack.io/#SmartToolFactory/Compose-BeforeAfter/2dc478ceb9):

```gradle
dependencies {
    implementation 'com.github.SmartToolFactory:Compose-BeforeAfter:2dc478ceb9'
}
```

</details>

## BeforeAfterImage

`BeforeAfterImage` takes two `ImageBitmap` values and draws them in the selected order.
The default overloads provide labels and an overlay. Other overloads accept a composable overlay.

### State and migration

Use a controlled `progress`/`onProgressChange` pair when the caller owns comparison state. Progress
is always constrained to `0f..100f`. For local state, use `rememberBeforeAfterState` and pass it to
the stateful overload:

```kotlin
val state = rememberBeforeAfterState(initialProgress = 50f)

BeforeAfterImage(
    state = state,
    beforeImage = imageBefore,
    afterImage = imageAfter,
)
```

The legacy overloads that do not accept `progress` or `state` are deprecated. They remain available
for one migration cycle and delegate to `BeforeAfterState` internally.

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
) {
}
```

and

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    @FloatRange(from = 0.0, to = 100.0) progress: Float = 50f,
    onProgressChange: ((progress: Float) -> Unit)? = null,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
)
```

The first two overloads use the built-in default overlay. Use an `overlay` lambda when you need a custom overlay.

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    overlay: @Composable BeforeAfterImageScope.() -> Unit
)
```

and

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    @FloatRange(from = 0.0, to = 100.0) progress: Float = 50f,
    onProgressChange: ((progress: Float) -> Unit)? = null,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    overlay: @Composable BeforeAfterImageScope.() -> Unit
)
```

The `overlay` parameter adds another composable at the image position.

### Usage

```kotlin
BeforeAfterImage(
    modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .aspectRatio(4 / 3f),
    beforeImage = imageBefore,
    afterImage = imageAfter,
    contentScale = contentScale
)
```

or

```kotlin
BeforeAfterImage(
    modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .border(3.dp, Color(0xffE91E63), RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .aspectRatio(4 / 3f),
    beforeImage = imageBefore3,
    afterImage = imageAfter3,
    progress = progress,
    onProgressChange = { progress = it },
    contentScale = contentScale,
)
```

### Parameters

- `beforeImage` and `afterImage` are the images to compare.
- `progress` ranges from `0f` to `100f` when using a controlled overload.
- `enableProgressWithTouch` enables slider dragging; `enableZoom` enables pinch zoom and pan.
- `contentOrder`, `alignment`, and `contentScale` control drawing behavior.
- `alpha`, `colorFilter`, and `filterQuality` apply to image rendering.
- `overlay` draws a custom composable at the image position.

## BeforeAfterLayout

`BeforeAfterLayout` draws any composable, image, or video as before and after content. It can zoom
and pan when `enableZoom` is `true`, and controlled overloads let callers animate progress.

It also accepts `BeforeAfterState` for local progress ownership:

```kotlin
val state = rememberBeforeAfterState()

BeforeAfterLayout(
    state = state,
    beforeContent = { BeforeComposable() },
    afterContent = { AfterComposable() },
)
```

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeContent: @Composable () -> Unit,
    afterContent: @Composable () -> Unit,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
)
```

### Custom layout overlays

Two overloads accept a custom overlay. The overlay receives the content size and the current touch
position.

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    beforeContent: @Composable () -> Unit,
    afterContent: @Composable () -> Unit,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    overlay: @Composable ((DpSize, Offset) -> Unit)?,
)
```

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 100.0) progress: Float = 50f,
    onProgressChange: ((progress: Float) -> Unit)? = null,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableProgressWithTouch: Boolean = true,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    beforeContent: @Composable () -> Unit,
    afterContent: @Composable () -> Unit,
    beforeLabel: @Composable (BoxScope.() -> Unit)? = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable (BoxScope.() -> Unit)? = { AfterLabel(contentOrder = contentOrder) },
    overlay: @Composable ((DpSize, Offset) -> Unit)?,
)
```

### Controlled layout progress

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    enableProgressWithTouch: Boolean = true,
    onProgressStart: ((progress: Float) -> Unit)? = null,
    onProgressEnd: ((progress: Float) -> Unit)? = null,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    @FloatRange(from = 0.0, to = 100.0) progress: Float = 50f,
    onProgressChange: ((progress: Float) -> Unit)? = null,
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeContent: @Composable () -> Unit,
    afterContent: @Composable () -> Unit,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
)
```

## Usage

### Customize

```kotlin
BeforeAfterLayout(
    modifier = Modifier
        .shadow(1.dp, RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .aspectRatio(4 / 3f),
    beforeContent = {
        DemoImage(imageBitmap = imageBefore)
    },
    afterContent = {
        DemoImage(imageBitmap = imageAfter)
    },
    overlayStyle = OverlayStyle(
        dividerColor = Color(0xffF44336),
        dividerWidth = 2.dp,
        thumbShape = CutCornerShape(8.dp),
        thumbBackgroundColor = Color.Red,
        thumbTintColor = Color.White
    )
)
```

### Display difference between Composables with Material Design2 and M3

```kotlin
        BeforeAfterLayout(
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    progress = progress,
    beforeContent = {
        BeforeComposable(progress)
    },
    afterContent = {
        AfterComposable(progress)
    },
    enableProgressWithTouch = false,
    enableZoom = false,
    beforeLabel = null,
    afterLabel = null,
    overlay = null
)
```

### Animate like a ProgressBar

```kotlin
val transition: InfiniteTransition = rememberInfiniteTransition()

// Infinite progress animation
val progress by transition.animateFloat(
    initialValue = 0f,
    targetValue = 100f,
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 4000,
            easing = FastOutSlowInEasing
        ),
        repeatMode = RepeatMode.Reverse
    )
)

BeforeAfterLayout(
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    progress = progress,
    beforeContent = {
        BeforeComposable(progress)
    },
    afterContent = {
        AfterComposable(progress)
    },
    enableProgressWithTouch = false,
    enableZoom = false,
    beforeLabel = null,
    afterLabel = null,
    overlay = null
)
```

### Display before and after videos with Exoplayer

The demo app contains an `ExoPlayerUsingTextureView` example. It is intentionally not part of the
library artifact; applications should own their Media3 dependency and player lifecycle.

```kotlin
BeforeAfterLayout(
    modifier = Modifier
        .fillMaxSize()
        .aspectRatio(4 / 3f),
    beforeContent = {
        VideoPlayer(
            uri = "asset:///floodplain_dirty.mp4"
        )
    },
    afterContent = {
        VideoPlayer(
            uri = "asset:///floodplain_clean.mp4"
        )
    },
    enableZoom = false
)
```

> [!NOTE]
> Provide a player composable backed by `TextureView` so Compose transforms work correctly. The
> demo app's `ExoPlayerUsingTextureView` is one reference implementation.

## Local development

Run focused library checks:

```bash
./gradlew :beforeafter:testDebugUnitTest
./gradlew :beforeafter:compileDebugAndroidTestKotlin
./gradlew :beforeafter:assembleDebug
```

Unit tests live in `beforeafter/src/test`; Compose interaction tests live in
`beforeafter/src/androidTest` and require an emulator or device to execute:

```bash
./gradlew :beforeafter:connectedDebugAndroidTest
```

Run the full local verification suite:

```bash
./gradlew lint test
./gradlew :beforeafter:dokkaGenerate
```

The Dokka task refreshes the tracked API reference in `docs/` after public API or KDoc changes.
