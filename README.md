# Compose Before-After

[![](https://jitpack.io/v/SmartToolFactory/Compose-BeforeAfter.svg)](https://jitpack.io/#SmartToolFactory/Compose-BeforeAfter)

Composables to display Images, or Composables as before and after composables to display
differences or animate progress between 2 layouts or Composables with overlay and
customization options and progress observe properties for animating before-after progress

https://github.com/user-attachments/assets/60a3b755-046f-4683-b3eb-cd89fb728d64

## Gradle Setup

To get a Git project into your build:

**Step 1:** Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
      ...
      maven { url 'https://jitpack.io' }
  }
}
```

**Step 2:** Add the dependency

```gradle
dependencies {
    implementation 'com.github.SmartToolFactory:Compose-BeforeAfter:<version>'
}
```

where `<version>` is one available in [![](https://www.jitpack.io/v/SmartToolFactory/Compose-BeforeAfter.svg)](https://www.jitpack.io/#SmartToolFactory/Compose-BeforeAfter)

## BeforeAfterImage

Image that takes two `ImageBitmaps as parameter and displays them based on specified order.
By default Labels and Overlay is provided and overload function that accept other Composables
as overlay to customize

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
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
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
)
```

overloads has default `DefaultOverlay`

```kotlin
@Composable
internal fun DefaultOverlay(
    width: Dp,
    height: Dp,
    position: Offset,
    overlayStyle: OverlayStyle
)
```

```kotlin
@Composable
fun BeforeAfterImage(
    modifier: Modifier = Modifier,
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    enableProgressWithTouch: Boolean = true,
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
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    @FloatRange(from = 0.0, to = 100.0) progress: Float = 50f,
    onProgressChange: ((progress: Float) -> Unit)? = null,
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

has `overlay`parameters to add another Composable to draw overlay

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
    onProgressChange = {},
    contentScale = contentScale,
)
```

### Parameters

-   **beforeImage** image that show initial progress
-   **afterImage** image that show final progress
-   **enableProgressWithTouch** flag to enable drag and change progress with touch
-   **enableZoom** when enabled images are zoomable and pannable
-   **contentOrder** order of images to be drawn
-   **alignment** determines where image will be aligned inside `BoxWithConstraints`
-   **contentScale** how image should be scaled inside Canvas to match parent dimensions.
-   `ContentScale.Fit` for instance maintains src ratio and scales image to fit inside the parent.
-   **alpha** Opacity to be applied to `beforeImage` from 0.0f to 1.0f representing fully transparent to fully opaque respectively
-   **colorFilter** ColorFilter to apply to the `beforeImage` when drawn into the destination
-   **filterQuality** Sampling algorithm applied to the `beforeImage` when it is scaled and drawn into the destination. The default is `FilterQuality.Low` which scales using a bilinear sampling algorithm
-   **overlay** is a Composable that can be matched at exact position where `beforeImage` is drawn. This is useful for drawing thumbs, cropping or another layout that should match position with the image that is scaled is drawn

## BeforeAfterLayout

Layout can draw any Composable, image, or video as before and after layout and can be zoomed and
panned when [enableZoom] is **true** and returns a callback to animate before/after progress

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    enableProgressWithTouch: Boolean = true,
    enableZoom: Boolean = true,
    contentOrder: ContentOrder = ContentOrder.BeforeAfter,
    overlayStyle: OverlayStyle = OverlayStyle(),
    beforeContent: @Composable () -> Unit,
    afterContent: @Composable () -> Unit,
    beforeLabel: @Composable BoxScope.() -> Unit = { BeforeLabel(contentOrder = contentOrder) },
    afterLabel: @Composable BoxScope.() -> Unit = { AfterLabel(contentOrder = contentOrder) },
)
```

and

```kotlin
@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    enableProgressWithTouch: Boolean = true,
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

There is `ExoPlayerUsingTextureView` composable that you can use to display before and after videos.

```kotlin
BeforeAfterLayout(
    modifier = Modifier
        .fillMaxSize()
        .aspectRatio(4 / 3f),
    beforeContent = {
        ExoPlayerUsingTextureView(
            uri = "asset:///floodplain_dirty.mp4"
        )
    },
    afterContent = {
        ExoPlayerUsingTextureView(
            uri = "asset:///floodplain_clean.mp4"
        )
    },
    enableZoom = false
)
```

> [!NOTE]  
> If you would like the ability to customize and build your own VideoPlayer composable using Exoplayer, then take a look at the implementation of `ExoPlayerUsingTextureView` composable. You can duplicate the composable and modify the behaviour.
>
> Only thing to take care of is that `BeforeAfterLayout` requires Exoplayer that works with a TextureView.
> Inside the `ExoPlayerUtil` is a public extension function named `ExoPlayer.createTextureView` that you should use with your ExoPlayer.

## License

[Apache License, Version 2.0](LICENSE.md)
