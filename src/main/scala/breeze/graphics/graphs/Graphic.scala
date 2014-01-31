package breeze.graphics.graphs

/**
 * @author ktakagaki
 * @date 1/31/14.
 */
class Graphic {

//  /**Encapsulates default options for color, etc.*/
//  val kkRootPrimitive = new KKGP()
//
//
//  //Image size related
//
//  private var _kkImageSize: Double = 500d
//  /**Width of the KKGraphics object, in pixels*/
//  def kkImageSize = _kkImageSize
//  def kkImageSize_(value: Double)= {
//    if (value != _kkImageSize) _kkImageSize = value; rescale
//  }
//
//  private var _kkAspectRatio = 1d / kazukazus.math.KKConstants.kkGoldenRatio
//  def kkAspectRatio = _kkAspectRatio
//  def kkAspectRatio_=(automatic: String): Unit = {
//    if (automatic == "Automatic") kkAspectRatioImpl( 1d / kazukazus.math.KKConstants.kkGoldenRatio)
//    else printOptionError("kkAspectRatio", automatic)
//  }
//  def kkAspectRatio_=(value: Double): Unit = {
//    if (value > 0) kkAspectRatioImpl(value)
//    else printOptionError("kkAspectRatio", value)
//  }
//  private def kkAspectRatioImpl(value: Double) = {
//    if (value != _kkAspectRatio) _kkAspectRatio = value; rescale
//  }
//
//
//
//  def rescale = {
//    prefWidth = kkImageSize
//    prefHeight = kkImageSize * kkAspectRatio
//  }
//  scaleY = -1
//  //  translateY = -100
//  rescale
//
//
//  //  prefHeight = 100
//  //  prefWidth = 200
//
//
//  val line = new Polyline {
//
//    fill = Color.LIGHTBLUE
//    stroke = Color.RED
//    delegate.getPoints.addAll(0.0, 0.0, 10.0, sin(1.0)*10+10, 20.0, sin(2.0)*10+10, 30.0, sin(3.0)*10+10)
//  }
//
//
//
//  content = Set(line)
//
//  //addGraphicsPrimitive
//  //addEpilog
//
//  //axis related
//
//  //background related

}

//class KKGraphics(width:Double, height:Double) extends scalafx.scene.canvas.Canvas(width, height) {
//val gc: GraphicsContext = this.graphicsContext2D
//
////  private var xOffset: Double = 0D
////  private var yOffset: Double = 0D
////  private var xGain: Double = 250D
////  private var yGain: Double = 300D
////  def toCoordX(x: Double) = x * xGain + xOffset
////  def toCoordY(y: Double) = y * yGain + yOffset
////  def toCoord(x: Double, y: Double): (Double, Double) = (toCoordX(x), toCoordY(y))
//
//var plotRangeX = (0D, 500D)
//var plotRangeY = (0D, 200D)
//
//def getPlotRangeX0 : Double = plotRangeX._1
//def getPlotRangeX1 : Double = plotRangeX._2
//
//def updatePlotRangeX {
//gc.getTransform
//
//}
//
//var axisColor = Color.BLACK
//var axisThickness = 4
//var axisOrigin = (0D, 0D)
//var axisLabels = ("", "")
//
//def draw() {
//gc.setStroke(axisColor)
//gc.setLineWidth(axisThickness)
//gc.strokeLine(0, 0, 500, 0)
//gc.strokeLine(0, 0, 0, 300)
//
////    gc.beginPath
////    gc.moveTo(50, 50)
////    gc.lineTo(150,50)
////    gc.moveTo(50, 50)
////    gc.lineTo(50,250)
////
////    //gc.bezierCurveTo(150, 20, 150, 150, 75, 150)
////    gc.closePath
//
//
//}
//
//draw()

