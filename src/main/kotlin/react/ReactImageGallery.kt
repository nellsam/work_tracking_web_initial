@file:JsModule("react-image-gallery")
@file:JsNonModule

package react

/**
 * React image gallery wrapper
 */

@JsName("default")
external val imageGallery: RClass<ImageGalleryProps>

external interface ImageGalleryProps : RProps {
    var items: Array<ImageItem>
    var original: String
    var thumbnail: String
    var originalAlt: String
    var thumbnailAlt: String
    var imageSet : dynamic
    var infinite: Boolean
    var originalTitle: String
    var autoPlay: Boolean
    var slideDuration: Int
    var slideInterval: Int
    var showIndex: Boolean
    var showBullets: Boolean
    var showThumbnails: Boolean
    var showFullscreenButton: Boolean
    var showGalleryFullscreenButton: Boolean
    var showPlayButton: Boolean
    var showGalleryPlayButton: Boolean
    var showNav: Boolean
    var sizes : dynamic
}



